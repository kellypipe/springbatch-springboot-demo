
##  <center>springbatch 结合springboot 的一个demo </center> 
 在这里就不对springbatch 做详细的描述关于springbatch可以参考：https://blog.csdn.net/kellypipe/article/details/83018416。
 ![在这里插入图片描述](https://github.com/kellypipe/plugin/blob/master/picture/spring/springbatch/springbatch.jpg?raw=true)
 如图，在springbatch每一个任务都有不同的step组成。每一个step 可以拆解为  读数据 ，处理数据，写入数据 3 个步骤。如果你的任务不涉及读，处理，写 这种特点，只是简单的任务处理 springbatch 也提供了 tasklet 来处理任务。所以在demo中 整合这两者 ，实现在不修改原有任务， 进行任务step 的添加 ，删除，修改等。

下面就介绍下项目中使用的springbatch 同步数据的过程。（由于项目不方便对外，我删除了实际项目的业务部分，并结合一个简单的同步任务来说明问题：假设现在有个同步数据的场景：将元数据 进行计算处理后将结果更新到另外一个库中。写入成功后发送邮件 ）


对于这个简单的任务首先拆解成两个step.
 step01：标准的读取数据，处理数据，写入数据：对于这类的任务只要实现
 step02:  这个step 只是用来发送邮件 .springbatch 也提供了tasklet 来处理这些不需要类似step01 那样复杂的操作。 

在demo中对于step01 需要定义3 个步骤：读数据，处理数据，写入数据。例如：
 
```/
 //定义读取bean
 /**
     * 任务一的数据读取
     * @param param1
     * @return
     */
    @Bean(name = "contactReader")
    @StepScope
    public MyBatisCursorItemReader getMyBatisCursorItemReader(@Value("#{jobParameters[param1]}") String param1){
        log.info("开始读取数据的demo01。。。。。。。");
        //如果查询需要条件可以从这里进行设置查询的条件
        Map condition = new HashMap();
        condition.put("param1",param1);
        MyBatisCursorItemReader myBatisCursorItemReader = new MyBatisCursorItemReader();
        myBatisCursorItemReader.setQueryId("selectAllUsers");
        myBatisCursorItemReader.setSqlSessionFactory(sqlSessionFactory);
        myBatisCursorItemReader.setParameterValues(condition);
        return myBatisCursorItemReader;
    }
```
```/

/每个任务对应一个processor.每个process 需要注入读取数据和写入数据的bean
@Component
@OrderProcessor(jobName = "sampleJob",stepName = "sync_user_info",order = 3)
public class ContactItemProcessor extends CustomItemProcessor<User,UserTo> {

    @Autowired
    @Qualifier("contactReader")
    private MyBatisCursorItemReader myBatisCursorItemReader;

    @Autowired
    @Qualifier("contactWriter")
    private MyBatisBatchItemWriter myBatisBatchItemWriter;

    @Override
    public UserTo process(User user) {
        //模拟对user 进行处理
        UserTo userTo = new UserTo();
        BeanUtils.copyProperties(user,userTo);
        userTo.setName("任务一");
        return userTo;
    }

    @Override
    public ItemReader getItemReader() {
        return myBatisCursorItemReader;
    }

    @Override
    public ItemWriter getItemWriter() {
        return myBatisBatchItemWriter;
    }
}
```

```/
// 写入数据的bean
/**
     * 任务一的写入
     * @return
     */
    @Bean
    @Qualifier("contactWriter")
    @StepScope
    public MyBatisBatchItemWriter getMyBatisCursorItemWriter(){
        log.info("开始写入数据。。。。。。。");
        MyBatisBatchItemWriter myBatisBatchItemWriter = new MyBatisBatchItemWriter();
        myBatisBatchItemWriter.setSqlSessionFactory(currentSqlSessionFactory);
        myBatisBatchItemWriter.setStatementId("hgp.demo.springbatch.dao.currentDbDao.UserToMapper.insert");
        return myBatisBatchItemWriter;
    }
```
在demo中对于step02的处理只需要实现AbstractTask ，然后实现自己的同步任务即可，如发邮件等。例如：
```/
@Component
@OrderTask(jobName = "sampleJob",stepName = "tasklet01",order = 1)
public class Task01 extends AbstractTask {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("tasklet01 开始执行任务...........");
        //处理自己的业务逻辑
        return RepeatStatus.FINISHED;
    }

```
在springboot 启动的时候会根据注解@OrderProcessor 来生成任务中的step01 和step02.
 

 
 

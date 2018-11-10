package hgp.demo.springbatch.batch.processor;

import hgp.demo.springbatch.batch.annotation.OrderProcessor;
import hgp.demo.springbatch.batch.reader.CustomJdbcCursorItemReader;
import hgp.demo.springbatch.batch.task.CustomItemProcessor;
import hgp.demo.springbatch.batch.writer.CustomItemWriter;
import hgp.demo.springbatch.model.User;
import hgp.demo.springbatch.model.UserTo;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 每一个任务都需要一个extends CustomItemProcessor .
 */
@Component
@OrderProcessor(jobName = "sampleJob",stepName = "sync_user_info",order = 3)
public class ContactItemProcessor extends CustomItemProcessor<User,UserTo> {

    /**
     * 读取数据{@link CustomJdbcCursorItemReader}
     */
    @Autowired
    @Qualifier("contactReader")
    private MyBatisCursorItemReader myBatisCursorItemReader;

    /**
     * 写入数据{@link CustomItemWriter}
     */
    @Autowired
    @Qualifier("contactWriter")
    private MyBatisBatchItemWriter myBatisBatchItemWriter;

    /**
     * 处理数据
     * @param user
     * @return
     */
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

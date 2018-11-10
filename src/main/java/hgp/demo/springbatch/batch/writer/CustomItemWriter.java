package hgp.demo.springbatch.batch.writer;

import hgp.demo.springbatch.batch.reader.CustomJdbcCursorItemReader;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义写入数据的bean
 * @creator:gp.huang
 * @since:2018-10-28 15:16
 */
@Configuration
public class CustomItemWriter {
    private static final Logger log =  LoggerFactory.getLogger(CustomItemWriter.class);
    @Autowired(required = false)
    @Qualifier("currentSqlSessionFactory")
    private SqlSessionFactory currentSqlSessionFactory;

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

    /**
     * 任务4的写入
     * @return
     */
    @Bean
    @Qualifier("demo04Writer")
    @StepScope
    public MyBatisBatchItemWriter getMyBatisDemo04Writer(){
        log.info("开始写入数据demo04。。。。。。。");
        MyBatisBatchItemWriter myBatisBatchItemWriter = new MyBatisBatchItemWriter();
        myBatisBatchItemWriter.setSqlSessionFactory(currentSqlSessionFactory);
        myBatisBatchItemWriter.setStatementId("hgp.demo.springbatch.dao.currentDbDao.UserToMapper.insert");
        return myBatisBatchItemWriter;
    }


}

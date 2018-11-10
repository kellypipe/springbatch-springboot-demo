package hgp.demo.springbatch.batch.reader;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义读取数据 bean
 * @creator:gp.huang
 * @since:2018-09-25 10:59
 */
@Configuration
public class CustomJdbcCursorItemReader {
    @Autowired(required = false)
    @Qualifier("otherSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;


    private static final Logger log =  LoggerFactory.getLogger(CustomJdbcCursorItemReader.class);

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

    /**
     * 任务4 的数据写入
     * @param param1
     * @return
     */
    @Bean(name = "demo04Reader")
    @StepScope
    public MyBatisCursorItemReader getMyBatisDemo04Reader(@Value("#{jobParameters[param1]}") String param1){
        log.info("开始读取数据demo04。。。。。。。");
        //如果查询需要条件可以从这里进行设置查询的条件
        Map condition = new HashMap();
        condition.put("param1",param1);
        MyBatisCursorItemReader myBatisCursorItemReader = new MyBatisCursorItemReader();
        myBatisCursorItemReader.setQueryId("selectAllUsers");
        myBatisCursorItemReader.setSqlSessionFactory(sqlSessionFactory);
        myBatisCursorItemReader.setParameterValues(condition);
        return myBatisCursorItemReader;
    }





}

package hgp.demo.springbatch.config.mybatisConfig;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@MapperScan(value = "hgp.demo.springbatch.dao.otherDbDao",sqlSessionFactoryRef = "otherSqlSessionFactory",sqlSessionTemplateRef = "otherSqlSessionTemplate")
@EnableTransactionManagement
public class OtherDbMyBatisConfig{

    private String mapperLocation = "classpath:mapper/other/*.xml";

    @Bean(name = "otherSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("otherBusiness") DataSource otherDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(otherDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocation));
        //sqlSessionFactoryBean.setPlugins(new Interceptor[]{pagePlugin()});
        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name = "otherSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("otherSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}

package hgp.demo.springbatch.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 多数据源
 * @creator:gp.huang
 * @since:2018-09-11 09:51
 */
@Configuration
public class DynamicDatasourceConfig {
    /**
     * 不同的datasource 实例
     * @return
     */
    @Bean
    @Qualifier("currentBusiness")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource currentBusinessDB() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("otherBusiness")
    @ConfigurationProperties(prefix = "other.business.druid")
    public DataSource otherBusinessDB() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * JdbcTemplate 不同数据源对应的实例
     * @param dataSource
     * @return
     */
    @Bean(name = "currentBusinessJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("currentBusiness") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean(name = "otherBusinessJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("otherBusiness") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}

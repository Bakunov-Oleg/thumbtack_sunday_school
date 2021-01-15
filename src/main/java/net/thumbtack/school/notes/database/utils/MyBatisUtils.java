package net.thumbtack.school.notes.database.utils;

import net.thumbtack.school.notes.database.mappers.SuperUserMapper;
import net.thumbtack.school.notes.database.mappers.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;

public class MyBatisUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisUtils.class);
    private static SqlSessionFactory sqlSessionFactory;

    public static boolean initSqlSessionFactory() {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            return true;
        } catch (IOException e) {
            LOGGER.error("Error loading mybatis-config.xml", e);
            return false;
        }
    }

    @Bean
    public static SqlSessionFactory getSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        SqlSessionFactory sessionFactory = factoryBean.getObject();

        return sessionFactory;
    }

    @Bean
    public static DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.build();
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("schema.sql")
//                .addScript("data.sql")
//                .build();
    }
}




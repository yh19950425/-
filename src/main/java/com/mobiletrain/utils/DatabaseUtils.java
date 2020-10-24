package com.mobiletrain.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseUtils implements ServletContextListener {
    private static DataSource ds;

    public static DataSource getDataSource() {
        return ds;
    }

    public static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(ds);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ServletContext application = event.getServletContext();
            InputStream is = application.getResourceAsStream("WEB-INF/database.properties");
            Properties properties = new Properties();
            properties.load(is);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}

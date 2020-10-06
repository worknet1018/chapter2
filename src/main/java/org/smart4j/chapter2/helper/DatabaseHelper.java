package org.smart4j.chapter2.helper;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.util.PropsUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public final class DatabaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf= PropsUtil.loadProps("config.properties");
        DRIVER= conf.getProperty("jdbc.driver");
        URL=conf.getProperty("jdbc.url");
        USERNAME=conf.getProperty("jdbc.username");
        PASSWORD= conf.getProperty("jdbc.password");
        try{
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            LOGGER.error("can not load jdbc driver",e);
        }
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure",e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure",e);
            }
        }
    }

    /**
     * 查询实体类
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList;
        Connection conn=getConnection();
        try {
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure",e);
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }

        return entityList;
    }

}

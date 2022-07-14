package com.github.quxiucheng.calcite.schema.tutorial;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author quxiucheng
 * @date 2019-04-28 17:06:00
 */
public class ConnectionFactoryTest {

    public void createSchemaFactoryConnection() throws SQLException {
        Properties info = new Properties();
        info.put("lex", "mysql");
        String model = "calcite-tutorial-3-schema/src/main/resources/model.json";
        info.put("model", model);
        DriverManager.getConnection("jdbc:calcite:", info);
        /**
         * 1.从model.yaml读取factory 为TutorialSchemaFactory，
         * 2.执行TutorialSchemaFactory.create创建表TutorialTable
         *
         */
    }

    public void createTableFactoryConnection() throws SQLException {
        Properties info = new Properties();
        info.put("lex", "mysql");
        String model = "calcite-tutorial-3-schema/src/main/resources/model.yaml";
        info.put("model", model);
        DriverManager.getConnection("jdbc:calcite:", info);
        /**
         * 1.从model.yaml读取factory 为TutorialTableFactory，
         * 2.执行TutorialTableFactory.create创建表TutorialTable
         *
         */

    }

    public static void main(String[] args) throws SQLException {
        new ConnectionFactoryTest().createSchemaFactoryConnection();
//        new ConnectionFactoryTest().createTableFactoryConnection();

    }
}

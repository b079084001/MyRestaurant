/**
 * 对数据库操作的类
 * 功能：对数据库的操作就是(CRUD增删改查)
 * 调用存储过程[oracle]
 */
package com.xp.MyRestaurant.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class SqlHelper {
    // 定义需要的对象
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection ct = null;

    // String dirver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // String url="jdbc:sqlserver://127.0.0.1:1433;databaseName=Restaurant;user=sa;password=sa;";
    String dirver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/restaurant?useUnicode=true&characterEncoding=utf-8";
    String user = "root";
    String password = "123456";

    // 构造函数，初始化ct数据库连接
    public SqlHelper() {
        try {
            // 加载驱动
            Class.forName(dirver);
            // 得到连接
            ct = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库连接失败!!" + "\r\n请检查数据源配置及数据库用户名密码是否正确!!", "数据库连接提示", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // 把对数据库的增、删、改写一个函数
    public boolean exeUpdate(String sql, String[] paras) {
        boolean b = true;
        try {
            ps = ct.prepareStatement(sql);
            // 对sql的参数赋值
            for (int i = 0; i < paras.length; i++) {
                ps.setString(i + 1, paras[i]);
            }
            // 执行
            ps.executeUpdate();
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        }

        return b;
    }

    // 查询结果集
    public ResultSet query(String sql, String[] paras) {
        try {
            ps = ct.prepareStatement(sql);
            // 对sql的参数赋值
            for (int i = 0; i < paras.length; i++) {
                ps.setString(i + 1, paras[i]);
            }
            // 执行
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    // 关闭资源的方法
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (ct != null) {
                ct.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
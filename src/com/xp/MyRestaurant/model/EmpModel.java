package com.xp.MyRestaurant.model;

import com.xp.MyRestaurant.db.SqlHelper;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 * 人事的数据模型，完成对人事表的增删改查
 */

public class EmpModel extends AbstractTableModel {
    Vector<String> colums;
    Vector<Vector> rows;

    // 通过工号来删除员工
    public boolean delEmpById(String empId) {
        boolean b = true;

        String sql = "delete from rszl where empid=?";
        String[] params = {empId};
        SqlHelper sqlHelper = new SqlHelper();
        try {
            b = sqlHelper.exeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.close();
        }
        return b;
    }

    // 写一个方法用于查询显示的人事信息
    public void query(String sql, String[] params) {
        // 初始化列
        this.colums = new Vector<String>();
        // 方式1
        // this.colums.add("员工号");
        // this.colums.add("姓名");
        // this.colums.add("性别");
        // this.colums.add("职位");

        this.rows = new Vector<Vector>();
        // 创建SqlHelper对象
        SqlHelper sqlHelper = new SqlHelper();
        ResultSet rs = sqlHelper.query(sql, params);

        try {
            // 方式2
            ResultSetMetaData rsmt = rs.getMetaData();
            // 从rs对象中可以得到一个ResultSetMetaData对象,即获得结果集的列数及名字
            for (int i = 0; i < rsmt.getColumnCount(); i++) {
                // this.colums.add(rsmt.getColumnName(i + 1)); //获取列名
                this.colums.add(rsmt.getColumnLabel(i + 1)); // 获取列名的别名(SQL查询语句的as别名)
            }
            // 把rs的结果放到rows
            while (rs.next()) {
                Vector<String> temp = new Vector<String>();
                // 方式1
                //  temp.add(rs.getString(1));
                //  temp.add(rs.getString(2));
                //  temp.add(rs.getString(3));
                //  temp.add(rs.getString(4));

                // 方式2
                for (int i = 0; i < rsmt.getColumnCount(); i++) {
                    temp.add(rs.getString(i + 1));
                }
                this.rows.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.close();
        }
    }

    @Override
    public int getRowCount() {
        return this.rows.size();
    }

    @Override
    public int getColumnCount() {
        return this.colums.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return ((Vector) rows.get(rowIndex)).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return (String) this.colums.get(column).toString();
    }
}

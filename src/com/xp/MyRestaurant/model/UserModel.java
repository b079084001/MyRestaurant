package com.xp.MyRestaurant.model;

import java.sql.ResultSet;

import com.xp.MyRestaurant.db.SqlHelper;

/**
 * 用户表数据模型，用它完成对用户的各种操作
 */
public class UserModel {
    /**
     * @param userId 用户编号
     * @param passwd 用户密码
     * @return 该用户的职位, 如果该用户不存在，则返回""
     */
    public String checkUser(String userId, String passwd) {
        String zhiwei = "";
        SqlHelper sp = null;
        try {
            // 组织sql语句和参数列表
            String sql = "select r.zhiwei from login l,rszl r where l.empid=r.empid and l.empid=? and l.passwd=?";
            String paras[] = {userId, passwd};
            sp = new SqlHelper();
            ResultSet rs = sp.query(sql, paras);
            if (rs.next()) {
                // 取出职位zhiwei
                zhiwei = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sp.close();
        }

        return zhiwei;
    }
}
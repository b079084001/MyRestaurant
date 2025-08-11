package com.xp.MyRestaurant.view;

import com.xp.MyRestaurant.model.EmpModel;
import com.xp.MyRestaurant.tools.MyTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用于显示人事管理的界面
 */

public class EmpInfo extends JPanel implements ActionListener {
    // 定义需要的组件
    JPanel p1, p2, p3, p4, p5;
    JLabel p1_lab1, p3_lab1;
    JTextField p1_jtf1;
    JButton p1_jb1, p4_jb1, p4_jb2, p4_jb3, p4_jb4;
    JTable jtable;
    JScrollPane jsp;
    EmpModel em;

    public EmpInfo() {
        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1_lab1 = new JLabel("请输入姓名(员工号或职位)：");
        p1_lab1.setFont(MyTools.f2);

        p1_jtf1 = new JTextField(20);
        p1_jb1 = new JButton("查询");
        p1_jb1.addActionListener(this);
        p1_jb1.setFont(MyTools.f2);

        // 加入到p1
        p1.add(p1_lab1);
        p1.add(p1_jtf1);
        p1.add(p1_jb1);

        // 处理中间的p2面板
        em = new EmpModel();
        String[] params = {"1"};
        em.query("select empid as '员工号',empname as '姓名',sex as '性别',zhiwei as '职位' from rszl where 1=?", params);
        jtable = new JTable(em);
        p2 = new JPanel(new BorderLayout());
        jsp = new JScrollPane(jtable);
        p2.add(jsp);


        // 处理南部的p3面板
        p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // p3_lab1 = new JLabel("总记录数是**条");
        p3_lab1 = new JLabel("总记录数是" + em.getRowCount() + "条");
        p3_lab1.setFont(MyTools.f3);
        p3.add(p3_lab1);

        p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p4_jb1 = new JButton("详细信息");
        p4_jb1.addActionListener(this);
        p4_jb1.setFont(MyTools.f3);
        p4_jb2 = new JButton("添加");
        p4_jb2.addActionListener(this);
        p4_jb2.setFont(MyTools.f3);
        p4_jb3 = new JButton("修改");
        p4_jb3.addActionListener(this);
        p4_jb3.setFont(MyTools.f3);
        p4_jb4 = new JButton("删除");
        p4_jb4.addActionListener(this);
        p4_jb4.setFont(MyTools.f3);
        p4.add(p4_jb1);
        p4.add(p4_jb2);
        p4.add(p4_jb3);
        p4.add(p4_jb4);

        p5 = new JPanel(new BorderLayout());
        p5.add(p3, "West");
        p5.add(p4, "East");

        // 把总的JPanel设置为BorderLayout布局
        this.setLayout(new BorderLayout());
        this.add(p1, "North");
        this.add(p2, "Center");
        this.add(p5, "South");

        // this.setBackground(Color.pink);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 用户点击查询
        if (this.p1_jb1 == e.getSource()) {
            System.out.println("用户点击查询");
            // 因为把对表的数据封装到EmpModel中，就可以比较简单的完成查询
            String name = this.p1_jtf1.getText().trim();
            // SQL语句
            // String sql = "select empid as '员工号',empname as '姓名',sex as '性别',zhiwei as '职位' from rszl where empid = ?";
            String sql = "select empid as '员工号',empname as '姓名',sex as '性别',zhiwei as '职位' from rszl " +
                    "where CONCAT(`empid`, `zhiwei`) like concat('%',?,'%')";

            String[] params = {name};

            if (name.equals("")) {
                sql = "select empid as '员工号',empname as '姓名',sex as '性别',zhiwei as '职位' from rszl where 1 = ?";
                params = new String[]{"1"};
            }

            // 构建新的数据模型类
            em = new EmpModel();
            em.query(sql, params);
            jtable.setModel(em);
        }
        // 用户点击详细信息
        else if (this.p4_jb1 == e.getSource()) {
            System.out.println("用户点击详细信息");
        }
        // 用户点击修改
        else if (this.p4_jb2 == e.getSource()) {
            System.out.println("用户点击添加");
        }
        // 用户点击修改
        else if (this.p4_jb3 == e.getSource()) {
            System.out.println("用户点击修改");
        }
        // 用户点击删除
        else if (this.p4_jb4 == e.getSource()) {
            int selRowNum = jtable.getSelectedRow();
            String empNo = (String) em.getValueAt(selRowNum, 0);
            System.out.println("编号：" + empNo);
            // if (em.delEmpById(empNo)) {
            //     JOptionPane.showMessageDialog(null, "删除成功");
            // } else {
            //     JOptionPane.showMessageDialog(null, "删除失败");
            // }

            int response = JOptionPane.showConfirmDialog(null, "是否确认删除？", "提示",
                    JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.OK_OPTION) {
                if (em.delEmpById(empNo)) {
                    JOptionPane.showMessageDialog(null, "删除成功");
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败");
                }
            } else {
                System.out.println("用户取消删除");
            }


            // 更新数据模型
            em = new EmpModel();
            String[] params = {"1"};
            em.query("select empid as '员工号',empname as '姓名',sex as '性别',zhiwei as '职位' from rszl where 1=?", params);
            jtable.setModel(em);
        }
    }

}

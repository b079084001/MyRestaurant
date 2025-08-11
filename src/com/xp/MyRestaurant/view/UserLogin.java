package com.xp.MyRestaurant.view;

import com.xp.MyRestaurant.model.UserModel;
import com.xp.MyRestaurant.tools.MyTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class UserLogin extends JDialog implements ActionListener {
    JLabel jl1, jl2, jl3;
    JTextField JName;
    JPasswordField jPasswd;
    JButton jCon;
    JButton jCancel;

    public static void main(String[] args) {
        UserLogin ul = new UserLogin();
    }

    public UserLogin() {
        Container ct = this.getContentPane();
        this.setLayout(null);

        // Font f1 = new Font("宋体", Font.PLAIN, 16);
        // Font f2 = new Font("宋体", Font.PLAIN, 14);

        // 创建各个组件
        jl1 = new JLabel("请输入用户名：");
        // jl1.setFont(f1);
        jl1.setFont(MyTools.f1);
        jl1.setBounds(60, 190, 150, 30);
        // 放入
        ct.add(jl1);

        JName = new JTextField(20);
        // 光标选中
        JName.requestDefaultFocus();
        // JName.setFont(f1);
        JName.setFont(MyTools.f1);
        JName.setBounds(180, 190, 120, 30);
        // 设置下凹
        JName.setBorder(BorderFactory.createLoweredBevelBorder());
        ct.add(JName);

        jl2 = new JLabel("(员工号)");
        // jl2.setFont(f2);
        jl2.setFont(MyTools.f2);
        // 设置背景色
        jl2.setForeground(Color.red);
        jl2.setBounds(100, 210, 100, 30);
        ct.add(jl2);

        jl3 = new JLabel("请输入密码：");
        // jl3.setFont(f1);
        jl3.setFont(MyTools.f1);
        jl3.setBounds(60, 240, 150, 30);
        ct.add(jl3);

        jPasswd = new JPasswordField(20);
        // jPasswd.setFont(f1);
        jPasswd.setFont(MyTools.f1);
        jPasswd.setBounds(180, 240, 120, 30);
        // 设置下凹
        jPasswd.setBorder(BorderFactory.createLoweredBevelBorder());
        ct.add(jPasswd);

        jCon = new JButton("确定");
        jCon.addActionListener(this);
        // jCon.setFont(f1);
        jCon.setFont(MyTools.f1);
        jCon.setBounds(110, 300, 70, 30);
        ct.add(jCon);

        jCancel = new JButton("取消");
        jCancel.addActionListener(this);
        // jCancel.setFont(f1);
        jCancel.setFont(MyTools.f1);
        jCancel.setBounds(220, 300, 70, 30);
        ct.add(jCancel);

        // 创建一个BackImage对象
        BackImage bi = new BackImage();
        // 把位置确定
        bi.setBounds(0, 0, 360, 360); // 背景图片大小
        // 把一个组件加入到JFrame或者JDialog，或者直接放入
        ct.add(bi);  // JDK1.5以前使用
        // this.add(bi);  //JDK1.5以上使用

        // 不使用上下框
        this.setUndecorated(true);  // 隐藏标题栏
        this.setSize(360, 360);

        // 确定JWindow的初始位置
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width / 2 - 200, height / 2 - 150);
        // this.setLocationRelativeTo(null); //居中
        this.setVisible(true);
    }

    // 内部类(加载一个图片)
    class BackImage extends JPanel {
        Image im;

        public BackImage() {
            try {
                im = ImageIO.read(new File("image\\login.gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void paintComponent(Graphics g) {
            g.drawImage(im, 0, 0, 360, 360, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 响应用户登录的请求,判断是不是点击确定按钮
        if (e.getSource() == jCon) {
            // System.out.println("点击确定");
            String uid = this.JName.getText().trim();
            String p = new String(this.jPasswd.getPassword());
            // System.out.println(uid+","+p);

            UserModel um = new UserModel();
            String res = um.checkUser(uid, p);
            System.out.println(uid + " 用户职位：" + res);
            if (res.equals("经理") || res.equals("主管") || res.equals("系统管理员")) {
                new Windows1();
                // 同时关闭登录界面
                this.dispose();
            }
        } else if (e.getSource() == jCancel) {
            this.dispose();
        }
    }
}

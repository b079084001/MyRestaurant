package com.xp.MyRestaurant.view;

import com.xp.MyRestaurant.tools.ImagePanel;
import com.xp.MyRestaurant.tools.MyTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * 这是系统管理员、经理、主管可以进的操作界面
 */
public class Windows1 extends JFrame implements ActionListener, MouseListener {
    // 定义需要的组件
    Image titleIcon, timeGg;
    JMenuBar jmb;
    // 一级菜单
    JMenu jm1, jm2, jm3, jm4, jm5, jm6;
    // 二级菜单
    JMenuItem jmm1, jmm2, jmm3, jmm4, jmm5;
    // 图标
    ImageIcon jmm1_icon1, jmm1_icon2, jmm1_icon3, jmm1_icon4, jmm1_icon5;

    // 工具栏
    JToolBar jtb;
    // 图标按钮
    JButton jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8, jb9, jb10;

    // 定义中间需要的5个面板
    JPanel p1, p2, p3, p4, p5;
    // 状态栏显示时间
    JLabel timeNow;
    // 定时触发Action事件
    javax.swing.Timer t;

    // 中间P1面板
    JLabel p1_lab1, p1_lab2, p1_lab3, p1_lab4, p1_lab5, p1_lab6, p1_lab7, p1_lab8;
    ImagePanel p1_imgPanel;

    // p2面板，向左和向右的箭头
    JLabel p2_lab1, p2_lab2;

    // 拆分窗口
    JSplitPane jsp1;

    // 不然做不了切换
    CardLayout cardP3;
    CardLayout cardP2;

    public static void main(String[] args) {
        Windows1 w = new Windows1();
    }

    // 初始化菜单
    public void initMenu() {
        // 创建图标
        jmm1_icon1 = new ImageIcon("image/dl.gif");
        jmm1_icon2 = new ImageIcon("image/qh.gif");
        jmm1_icon3 = new ImageIcon("image/dlg.gif");
        jmm1_icon4 = new ImageIcon("image/wnl.gif");
        jmm1_icon5 = new ImageIcon("image/exit.gif");

        // 创建一级菜单
        jm1 = new JMenu("系统管理");
        jm1.setFont(MyTools.f1);
        // 创建二级菜单
        jmm1 = new JMenuItem("切换用户", jmm1_icon1);
        jmm1.setFont(MyTools.f2);
        jmm2 = new JMenuItem("切换到收款界面", jmm1_icon2);
        jmm2.setFont(MyTools.f2);
        jmm3 = new JMenuItem("登录管理", jmm1_icon3);
        jmm3.setFont(MyTools.f2);
        jmm4 = new JMenuItem("万年历", jmm1_icon4);
        jmm4.setFont(MyTools.f2);
        jmm5 = new JMenuItem("退出", jmm1_icon5);
        jmm5.setFont(MyTools.f2);
        // 加入
        jm1.add(jmm1);
        jm1.add(jmm2);
        jm1.add(jmm3);
        jm1.add(jmm4);
        jm1.add(jmm5);

        jm2 = new JMenu("人事管理");
        jm2.setFont(MyTools.f1);
        jm3 = new JMenu("菜单服务");
        jm3.setFont(MyTools.f1);
        jm4 = new JMenu("报表统计");
        jm4.setFont(MyTools.f1);
        jm5 = new JMenu("成本及库房");
        jm5.setFont(MyTools.f1);
        jm6 = new JMenu("帮助");
        jm6.setFont(MyTools.f1);

        // 把一级菜单加入到菜单栏
        jmb = new JMenuBar();
        jmb.add(jm1);
        jmb.add(jm2);
        jmb.add(jm3);
        jmb.add(jm4);
        jmb.add(jm5);
        jmb.add(jm6);

        // 把菜单栏放到JFrame
        this.setJMenuBar(jmb);
    }

    // 初始化工具栏
    public void initToolBar() {
        // 处理工具栏的组件
        jtb = new JToolBar();
        // 设置工具栏不能浮动
        jtb.setFloatable(false);
        jb1 = new JButton(new ImageIcon("image/qh.gif"));
        jb2 = new JButton(new ImageIcon("image/dl.gif"));
        jb3 = new JButton(new ImageIcon("image/dlg.gif"));
        jb4 = new JButton(new ImageIcon("image/rs.gif"));
        jb5 = new JButton(new ImageIcon("image/ls.gif"));
        jb6 = new JButton(new ImageIcon("image/cp.gif"));
        jb7 = new JButton(new ImageIcon("image/jg.gif"));
        jb8 = new JButton(new ImageIcon("image/gy.gif"));
        jb9 = new JButton(new ImageIcon("image/bz.gif"));
        jb10 = new JButton(new ImageIcon("image/exit.gif"));
        // 把图标按钮加入到jtb
        jtb.add(jb1);
        jtb.add(jb2);
        jtb.add(jb3);
        jtb.add(jb4);
        jtb.add(jb5);
        jtb.add(jb6);
        jtb.add(jb7);
        jtb.add(jb8);
        jtb.add(jb9);
        jtb.add(jb10);
    }

    // 初始化中间的4个面板
    public void initAllPanels() {
        // 处理p1面板
        Image p1_bg = null;
        p1 = new JPanel(new BorderLayout());
        try {
            p1_bg = ImageIO.read(new File("image/p1.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 设置光标形状为手型
        Cursor myCursor = new Cursor(Cursor.HAND_CURSOR);


        this.p1_imgPanel = new ImagePanel(p1_bg);
        this.p1_imgPanel.setLayout(new GridLayout(8, 1)); // 8行1列
        // 处理p1面板的8个label
        p1_lab1 = new JLabel(new ImageIcon("image/09.gif"));
        p1_lab1.setFont(MyTools.f3);
        p1_imgPanel.add(p1_lab1);
        p1_lab2 = new JLabel("人 事 管 理", new ImageIcon("image/rs.gif"), 0);
        p1_lab2.setFont(MyTools.f3);
        p1_lab2.setCursor(myCursor);
        p1_lab2.setEnabled(false);// 设置该label不可用
        p1_lab2.addMouseListener(this);
        p1_imgPanel.add(p1_lab2);
        p1_lab3 = new JLabel("登 录 管 理", new ImageIcon("image/dlg.gif"), 0);
        p1_lab3.setFont(MyTools.f3);
        p1_lab3.setCursor(myCursor);
        p1_lab3.setEnabled(false);
        p1_lab3.addMouseListener(this);
        p1_imgPanel.add(p1_lab3);
        p1_lab4 = new JLabel("菜 谱 价 格", new ImageIcon("image/cp.gif"), 0);
        p1_lab4.setFont(MyTools.f3);
        p1_lab4.setCursor(myCursor);
        p1_lab4.setEnabled(false);
        p1_lab4.addMouseListener(this);
        p1_imgPanel.add(p1_lab4);
        p1_lab5 = new JLabel("报 表 统 计", new ImageIcon("image/ls.gif"), 0);
        p1_lab5.setFont(MyTools.f3);
        p1_lab5.setCursor(myCursor);
        p1_lab5.setEnabled(false);
        p1_lab5.addMouseListener(this);
        p1_imgPanel.add(p1_lab5);
        p1_lab6 = new JLabel("成本及库房", new ImageIcon("image/jg.gif"), 0);
        p1_lab6.setFont(MyTools.f3);
        p1_lab6.setCursor(myCursor);
        p1_lab6.setEnabled(false);
        p1_lab6.addMouseListener(this);
        p1_imgPanel.add(p1_lab6);
        p1_lab7 = new JLabel("系 统 设 置", new ImageIcon("image/07.gif"), 0);
        p1_lab7.setFont(MyTools.f3);
        p1_lab7.setCursor(myCursor);
        p1_lab7.setEnabled(false);
        p1_lab7.addMouseListener(this);
        p1_imgPanel.add(p1_lab7);
        p1_lab8 = new JLabel("动 画 帮 助", new ImageIcon("image/bz.gif"), 0);
        p1_lab8.setFont(MyTools.f3);
        p1_lab8.setCursor(myCursor);
        p1_lab8.setEnabled(false);
        p1_lab8.addMouseListener(this);
        p1_imgPanel.add(p1_lab8);
        p1.add(this.p1_imgPanel);

        // 处理p2(向左、向右)、p3、p4(包含p2、p3)面板
        p4 = new JPanel(new BorderLayout());
        // p2=new JPanel(new CardLayout());
        this.cardP2 = new CardLayout();
        p2 = new JPanel(this.cardP2);
        p2_lab1 = new JLabel(new ImageIcon("image/21.gif"));
        p2_lab1.addMouseListener(this);
        p2_lab2 = new JLabel(new ImageIcon("image/22.gif"));
        p2_lab2.addMouseListener(this);
        p2.add(p2_lab1, "0");
        p2.add(p2_lab2, "1");

        // p3 = new JPanel(new CardLayout());
        this.cardP3 = new CardLayout();
        p3 = new JPanel(this.cardP3);
        // 给p3一个主界面的面板
        Image zhu_image = null;
        try {
            zhu_image = ImageIO.read(new File("image/00.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImagePanel ip = new ImagePanel(zhu_image);
        p3.add(ip, "0");
        // 对p3添加几个图片
        // 创建EmpInfo对象
        EmpInfo eInfo = new EmpInfo();
        p3.add(eInfo, "1");
        // JLabel renshi = new JLabel(new ImageIcon("image/renshi.gif"));
        // p3.add(renshi, "1");
        JLabel denglu = new JLabel(new ImageIcon("image/dlgl.jpg"));
        p3.add(denglu, "2");


        // 把p2、p3加入到p4
        p4.add(p2, "West");
        p4.add(p3, "Center");

        // 做一个拆分窗口，分别存放p1和p4
        jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, p1, p4);// 横向面板
        // 指定左边的面板多大
        jsp1.setDividerLocation(120);
        // 把边界线设为0
        jsp1.setDividerSize(0);
    }

    public Windows1() {
        // 创建组件
        try {
            titleIcon = ImageIO.read(new File("image/jiubei.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 调用初始化菜单
        this.initMenu();

        // 调用初始化工具栏
        this.initToolBar();

        // 中间部分,5个面板
        // 调用初始化中间4个面板
        this.initAllPanels();

        // 处理p5面板--状态栏
        p5 = new JPanel(new BorderLayout());
        t = new Timer(1000, this); // 每隔一秒触发
        t.start();
        // 注意timeNow是Label
        // timeNow = new JLabel(Calendar.getInstance().getTime().toLocaleString()); //中国常见的时间格式
        timeNow = new JLabel(Calendar.getInstance().getTime().toString());
        timeNow.setFont(MyTools.f2);

        try {
            timeGg = ImageIO.read(new File("image/zhuangtailan.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImagePanel ip1 = new ImagePanel(timeGg);
        ip1.setLayout(new BorderLayout());
        ip1.add(timeNow, "East");
        p5.add(ip1);

        // 从JFrame中取得Container
        Container ct = this.getContentPane();
        ct.add(jtb, "North");
        // ct.add(p1, "Center");
        ct.add(jsp1, "Center");
        ct.add(p5, "South");

        // 确定JWindow的大小
        this.setSize(400, 250);
        // 确定JWindow的初始位置
        int w = Toolkit.getDefaultToolkit().getScreenSize().width;
        int h = Toolkit.getDefaultToolkit().getScreenSize().height;
        // 关闭窗口的时候，退出系统
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口图片
        this.setIconImage(titleIcon);
        this.setTitle("满汉楼餐饮管理系统");
        this.setSize(w, h - 30);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this.timeNow.setText("当前时间：" + Calendar.getInstance().getTime().toLocaleString() + "   ");//中国常见的时间格式
        this.timeNow.setText("当前时间：" + Calendar.getInstance().getTime().toString() + "   ");

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 判断用户点击哪一个操作JLabel
        if (e.getSource() == this.p1_lab2) {
            this.cardP3.show(p3, "1");
        } else if (e.getSource() == this.p1_lab3) {
            this.cardP3.show(p3, "2");
        } else if (e.getSource() == this.p1_lab5) {
            this.cardP3.show(p3, "4");
        } else if (e.getSource() == this.p2_lab1) {
            // 把显示各种操作的界面p1，隐藏
            // 显示向右的箭头
            this.cardP2.show(p2, "1");
            this.jsp1.setDividerLocation(0);
        } else if (e.getSource() == this.p2_lab2) {
            // 把显示各种操作的界面p1，展开
            // 显示向左的箭头
            this.cardP2.show(p2, "0");
            this.jsp1.setDividerLocation(120);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // 如果用户选择了某个Label，则高亮
        if (e.getSource() == this.p1_lab2) {
            // System.out.println("选中人事管理");
            p1_lab2.setEnabled(true);
        } else if (e.getSource() == this.p1_lab3) {
            p1_lab3.setEnabled(true);
        } else if (e.getSource() == this.p1_lab4) {
            p1_lab4.setEnabled(true);
        } else if (e.getSource() == this.p1_lab5) {
            p1_lab5.setEnabled(true);
        } else if (e.getSource() == this.p1_lab6) {
            p1_lab6.setEnabled(true);
        } else if (e.getSource() == this.p1_lab7) {
            p1_lab7.setEnabled(true);
        } else if (e.getSource() == this.p1_lab8) {
            p1_lab8.setEnabled(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // 如果用户离开某个Label，则禁用
        if (e.getSource() == this.p1_lab2) {
            // System.out.println("离开人事管理");
            p1_lab2.setEnabled(false);
        } else if (e.getSource() == this.p1_lab3) {
            p1_lab3.setEnabled(false);
        } else if (e.getSource() == this.p1_lab4) {
            p1_lab4.setEnabled(false);
        } else if (e.getSource() == this.p1_lab5) {
            p1_lab5.setEnabled(false);
        } else if (e.getSource() == this.p1_lab6) {
            p1_lab6.setEnabled(false);
        } else if (e.getSource() == this.p1_lab7) {
            p1_lab7.setEnabled(false);
        } else if (e.getSource() == this.p1_lab8) {
            p1_lab8.setEnabled(false);
        }
    }
}


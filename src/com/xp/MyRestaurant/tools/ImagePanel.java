package com.xp.MyRestaurant.tools;

import javax.swing.*;
import java.awt.*;

/**
 * 可以动态加载图片作为背景的JPanel
 */
public class ImagePanel extends JPanel {
    Image im;

    public ImagePanel(Image im) {
        this.im = im;
        // 确定JWindow的初始位置（大小自适应）
        int w = Toolkit.getDefaultToolkit().getScreenSize().width;
        int h = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(w, h);
    }

    // 画出背景
    public void paintComponent(Graphics g) {
        // 清屏
        super.paintComponent(g);
        g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

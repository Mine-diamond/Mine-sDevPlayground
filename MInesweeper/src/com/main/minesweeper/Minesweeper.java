package com.main.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Minesweeper extends JFrame {

    boolean ifSettingFrameOpen = false;
    boolean ifHelpFrameOpen = false;

        Minesweeper() {
            initialize();

        }

        public void initialize(){
            setTitle("扫雷游戏");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JToolBar toolBar = new JToolBar();
            toolBar.setFloatable(false);
            toolBar.setRollover(true);
            add(toolBar, BorderLayout.NORTH);

            JButton buttonSetting = new JButton("游戏设置");
            buttonSetting.setFocusable(false);
            buttonSetting.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SettingFrame();
                }
            });
            toolBar.add(buttonSetting);

            // 添加分隔符
            toolBar.addSeparator();

            JButton buttonHelp = new JButton("帮助");
            buttonHelp.setFocusable(false);
            buttonHelp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    helpFrame();
                }
            });
            toolBar.add(buttonHelp);

            JPanel panelBottom = new JPanel();
            panelBottom.setLayout(new GridLayout(1,3));
            add(panelBottom, BorderLayout.SOUTH);

            JButton buttonReset = new JButton("重新开始");
            buttonReset.setFocusable(false);
            buttonReset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            JLabel labelStepCount = new JLabel();
            labelStepCount.setText("步数：0");
            labelStepCount.setHorizontalAlignment(SwingConstants.CENTER);


            JLabel labelTime = new JLabel();
            labelTime.setText("时间：0");
            labelTime.setHorizontalAlignment(SwingConstants.CENTER);

            panelBottom.add(labelStepCount);
            panelBottom.add(buttonReset);
            panelBottom.add(labelTime);

            setVisible(true);
        }


        public void SettingFrame(){

            if(ifSettingFrameOpen){
                return;
            }

            JFrame settingframe = new JFrame();
            settingframe.setTitle("设置");
            settingframe.setSize(300, 300);
            settingframe.setLocationRelativeTo(null);
            settingframe.setLayout(new BorderLayout());
            settingframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            settingframe.setResizable(false);
            ifSettingFrameOpen = true;

            JToolBar toolBarCheck = new JToolBar();
            toolBarCheck.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            toolBarCheck.setFloatable(false);
            settingframe.add(toolBarCheck, BorderLayout.SOUTH);

            JButton buttonYes = new JButton("确认");
            buttonYes.setFocusable(false);
            buttonYes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    settingframe.dispose();
                    ifSettingFrameOpen = false;
                }
            });
            toolBarCheck.add(buttonYes);
            toolBarCheck.addSeparator();

            JButton buttonNo = new JButton("取消");
            buttonNo.setFocusable(false);
            buttonNo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            toolBarCheck.add(buttonNo);

            // 禁用最小化按钮
            settingframe.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    // 监听到窗口最小化，恢复窗口
                    settingframe.setExtendedState(JFrame.NORMAL); // 恢复窗口状态
                }
            });

            Runnable setAlwaysOnTop = () -> {
                boolean isFrame2Focused = settingframe.isFocused();
                boolean isFrame1Focused = this.isFocused();
                settingframe.setAlwaysOnTop(isFrame2Focused || isFrame1Focused);
            };

            // 添加焦点监听器到 frame2 和 frame1
            WindowFocusListener focusListener = new WindowFocusListener() {
                @Override
                public void windowGainedFocus(WindowEvent e) {
                    setAlwaysOnTop.run();
                }

                @Override
                public void windowLostFocus(WindowEvent e) {
                    setAlwaysOnTop.run();
                }
            };

            //settingframe.addWindowFocusListener(focusListener);
            this.addWindowFocusListener(focusListener);
            settingframe.setVisible(true);

            settingframe.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    ifSettingFrameOpen = false;
                }
            });
        }


        public void helpFrame(){

            if(ifHelpFrameOpen){
                return;
            }

            JFrame helpframe = new JFrame();
            helpframe.setTitle("帮助");
            helpframe.setSize(600, 600);
            helpframe.setLocationRelativeTo(null);
            helpframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ifHelpFrameOpen = true;

            helpframe.setLayout(new BorderLayout());

            JScrollPane scrollPane = new JScrollPane();
            helpframe.add(scrollPane, BorderLayout.CENTER);

            JTextArea textArea1 = new JTextArea();
            textArea1.setEditable(false);
            textArea1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
            textArea1.append(TextArea.helpContent);
            textArea1.setLineWrap(true);
            textArea1.setWrapStyleWord(true);
            textArea1.setCaretPosition(0);
            scrollPane.setViewportView(textArea1);

            helpframe.setVisible(true);

            helpframe.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    ifHelpFrameOpen = false;
                }
            });


        }


}

package com.main.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameUI extends JFrame {

    boolean ifSettingFrameOpen = false;
    boolean ifHelpFrameOpen = false;
    boolean ifHistoryFrameOpen = false;

        GameUI() {
            initialize();
        }

        public void initialize(){
            setTitle("扫雷游戏");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(53*GameLogic.col,43*GameLogic.row  + 85);
            setLocationRelativeTo(null);
            setResizable(false);
            setLayout(new BorderLayout());

            drawFrame();

            setVisible(true);
        }

        public void drawFrame(){
            getContentPane().removeAll();
            setSize(53*GameLogic.col,43*GameLogic.row  + 85);

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

            toolBar.addSeparator();

            JButton ButtonHistory = new JButton("历史记录");
            ButtonHistory.setFocusable(false);
            ButtonHistory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    historyFrame();
                }
            });
            toolBar.add(ButtonHistory);

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

            JPanel panelGrid = new JPanel();
            panelGrid.setLayout(new GridLayout(GameLogic.row,GameLogic.col));
            gridArea(panelGrid);
            add(panelGrid, BorderLayout.CENTER);


        }

        public void gridArea(JPanel panel){
            for (int i = 0; i < GameLogic.col; i++) {
                for (int j = 0; j < GameLogic.row; j++) {
                    JButton button = new JButton("旗");
                    System.out.println(10*i+j+1);
                    button.setFocusable(false);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                        }
                    });
                    panel.add(button);
                }
            }
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



            JPanel panelGrid = new JPanel();
            panelGrid.setLayout(new FlowLayout(FlowLayout.LEFT));
            settingframe.add(panelGrid, BorderLayout.NORTH);

            JPanel panelGridInter = new JPanel();
            panelGridInter.setLayout(new GridLayout(2,2));
            panelGrid.add(panelGridInter);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.NONE; // 不拉伸，保持组件最小大小
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.0; // 列宽权重
            gbc.weighty = 0.0; // 行高权重（设置为较大值）
            JLabel labelRow = new JLabel("行数：");
            panelGrid.add(labelRow,gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            JTextField textFieldRow = new JTextField();
            textFieldRow.setPreferredSize(new Dimension(50, 20));
            panelGrid.add(textFieldRow,gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            JLabel labelCon = new JLabel("列数：");
            panelGrid.add(labelCon,gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            JTextField textFieldCon = new JTextField();
            textFieldCon.setPreferredSize(new Dimension(50, 20));
            panelGrid.add(textFieldCon,gbc);

            JButton buttonYes = new JButton("确认");
            buttonYes.setFocusable(false);
            buttonYes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if(Integer.parseInt(textFieldRow.getText()) > 3 && Integer.parseInt(textFieldRow.getText()) < 20){
                            GameLogic.row = Integer.parseInt(textFieldRow.getText());
                            System.out.println("转换后的整数为: " + GameLogic.row);
                        }
                    } catch (NumberFormatException e1) {
                        System.out.println("无法将字符串转换为整数: " + e1.getMessage());
                    }
                    try {
                        if (Integer.parseInt(textFieldCon.getText()) > 3 && Integer.parseInt(textFieldCon.getText()) < 20) {
                            GameLogic.col = Integer.parseInt(textFieldCon.getText());
                            System.out.println("转换后的整数为: " + GameLogic.col);
                        }
                    } catch (NumberFormatException e1) {
                        System.out.println("无法将字符串转换为整数: " + e1.getMessage());
                    }
                    settingframe.dispose();
                    ifSettingFrameOpen = false;

                    drawFrame();
                }
            });
            toolBarCheck.add(buttonYes);
            toolBarCheck.addSeparator();

            JButton buttonNo = new JButton("取消");
            buttonNo.setFocusable(false);
            buttonNo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    settingframe.dispose();
                    ifSettingFrameOpen = false;
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

        public void historyFrame(){
            if(ifHistoryFrameOpen){
                return;
            }

            JFrame historyframe = new JFrame();
            historyframe.setSize(200, 200);
            historyframe.setLocationRelativeTo(null);
            historyframe.setLayout(null);
            historyframe.setResizable(false);
            historyframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ifHistoryFrameOpen = true;

            Runnable setAlwaysOnTop = () -> {
                boolean isFrame2Focused = historyframe.isFocused();
                boolean isFrame1Focused = this.isFocused();
                historyframe.setAlwaysOnTop(isFrame2Focused || isFrame1Focused);
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

            historyframe.setVisible(true);
            historyframe.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    ifHistoryFrameOpen = false;
                }
            });
        }


}

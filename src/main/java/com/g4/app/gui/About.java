package com.g4.app.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class About extends JDialog {
    private final JPanel contentPanel = new JPanel();

    public static void main(String[] args) {
        try {
            About dialog = new About();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public About() {
        setResizable(false);
        setBounds(100, 100, 639, 320);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] { 131, 100, 154, 115, 119, 0 };
        gbl_contentPanel.rowHeights = new int[] { 0, 81, 42, 0 };
        gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };

        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel lblNewLabel = new JLabel("Parking Gestion");
            GridBagConstraints gbc_newLabel = new GridBagConstraints();
            gbc_newLabel.gridwidth = 5;
            gbc_newLabel.insets = new Insets(0, 0, 5, 0);
            gbc_newLabel.gridx = 0;
            gbc_newLabel.gridy = 1;
            lblNewLabel.setOpaque(true);
            lblNewLabel.setBackground(Color.orange);
            contentPanel.add(lblNewLabel, gbc_newLabel);
        }
        {
            JPanel panel = new JPanel();
            GridBagConstraints gbc_panel = new GridBagConstraints();
            gbc_panel.fill = GridBagConstraints.BOTH;
            gbc_panel.insets = new Insets(0, 0, 5, 0);
            gbc_panel.gridx = 0;
            gbc_panel.gridy = 1;
            contentPanel.add(panel, gbc_panel);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setIcon(new ImageIcon(getClass().getResource("resources/assets/smash.jpg")));
                panel.add(label, BorderLayout.NORTH);
            }
            {
                JLabel lblName = new JLabel("Smash Ball", SwingConstants.CENTER);
                panel.add(lblName, BorderLayout.SOUTH);
            }
        }
        {
            JPanel panel = new JPanel();
            GridBagConstraints gbc_panel = new GridBagConstraints();
            gbc_panel.fill = GridBagConstraints.BOTH;
            gbc_panel.insets = new Insets(0, 0, 5, 0);
            gbc_panel.gridx = 1;
            gbc_panel.gridy = 1;
            contentPanel.add(panel, gbc_panel);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setIcon(new ImageIcon(getClass().getResource("resources/assets/isaac.jpg")));
                panel.add(label, BorderLayout.NORTH);
            }
            {
                JLabel lblName = new JLabel("Mom's knife", SwingConstants.CENTER);
                panel.add(lblName, BorderLayout.SOUTH);
            }
        }
        {
            JPanel panel = new JPanel();
            GridBagConstraints gbc_panel = new GridBagConstraints();
            gbc_panel.fill = GridBagConstraints.BOTH;
            gbc_panel.insets = new Insets(0, 0, 5, 0);
            gbc_panel.gridx = 3;
            gbc_panel.gridy = 1;
            contentPanel.add(panel, gbc_panel);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setIcon(new ImageIcon(getClass().getResource("resources/assets/ring.jpg")));
                panel.add(label, BorderLayout.NORTH);
            }
            {
                JLabel lblName = new JLabel("AEW", SwingConstants.CENTER);
                panel.add(lblName, BorderLayout.SOUTH);
            }
        }
        {
            JPanel panel = new JPanel();
            GridBagConstraints gbc_panel = new GridBagConstraints();
            gbc_panel.fill = GridBagConstraints.BOTH;
            gbc_panel.insets = new Insets(0, 0, 5, 0);
            gbc_panel.gridx = 4;
            gbc_panel.gridy = 1;
            contentPanel.add(panel, gbc_panel);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setIcon(new ImageIcon(getClass().getResource("resources/assets/pokeball.jpg")));
                panel.add(label, BorderLayout.NORTH);
            }
            {
                JLabel lblName = new JLabel("Pokemon", SwingConstants.CENTER);
                panel.add(lblName, BorderLayout.SOUTH);
            }
        }
        {
            JLabel lblNewLabel = new JLabel("2021");
            GridBagConstraints gbc_newLabel = new GridBagConstraints();
            gbc_newLabel.gridwidth = 5;
            gbc_newLabel.insets = new Insets(0, 0, 5, 0);
            gbc_newLabel.gridx = 0;
            gbc_newLabel.gridy = 2;
            lblNewLabel.setOpaque(true);
            lblNewLabel.setBackground(Color.orange);
            contentPanel.add(lblNewLabel, gbc_newLabel);
        }
        {
            JLabel buttonPan = new JLabel();
            buttonPan.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPan, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPan.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
    }
}

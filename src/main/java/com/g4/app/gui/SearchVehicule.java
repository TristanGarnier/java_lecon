package com.g4.app.gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchVehicule extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private String immatriculation;
    private JTextField immat = new JTextField();

    public static void main(String[] args) {
        SearchVehicule dialog = new SearchVehicule();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    public String getImmat() {
        return immatriculation;
    }

    public SearchVehicule() {
        setBounds(100, 100, 289, 126);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
        setUndecorated(true);
        {
            JLabel label = new JLabel("Immatriculation");
            contentPanel.add(label);
        }
        {

            // immat.setColumns(10);
            contentPanel.add(immat);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                System.out.println("Je suis ici");
                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (immat.getText().length() == 0) {
                            JOptionPane.showMessageDialog(null, "Veuillez compléter l'immatriculation", "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        immatriculation = immat.getText();
                        dispose();
                    }

                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                {
                    JButton cancelButton = new JButton("Annuler");
                    cancelButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    cancelButton.setActionCommand("Cancel");
                    buttonPane.add(cancelButton);
                }
            }
        }
    }

    public boolean getValue() {
        return immatriculation.length() != 0;
    }
}

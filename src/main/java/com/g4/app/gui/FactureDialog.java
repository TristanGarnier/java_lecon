package com.g4.app.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import com.g4.app.business.Vehicule;
import com.g4.app.business.Voiture;
import com.g4.app.business.Facture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FactureDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private Vehicule vehicule;
    private JTextArea lblcont;

    FactureDialog() {
        setResizable(false);
        setBounds(100, 100, 287, 287);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new LineBorder(Color.ORANGE, 5, false));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            lblcont = new JTextArea("[cont]");
            lblcont.setWrapStyleWord(true);
            lblcont.setTabSize(4);
            lblcont.setMaximumSize(new Dimension(280, 200));
            lblcont.setText("");
            contentPanel.add(lblcont);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonPane.setBackground(new Color(170, 8, 54));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton();
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg) {
                        dispose();
                    }
                });
                // Ajout du button sur le content pane
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
    }

    FactureDialog(Voiture voiture) {
        this(new Facture(voiture));
    }

    FactureDialog(Facture facture) {
        this();
        System.out.println(facture);
        lblcont.setText(facture.toString());
    }

    public static void main(String[] args) {
        FactureDialog dialog = new FactureDialog(new Voiture("aze", "s", "df", "azerty"));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);
        dialog.setBounds(100, 100, 287, 287);
        dialog.setVisible(true);
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public boolean getValue() {
        return vehicule != null;
    }

}

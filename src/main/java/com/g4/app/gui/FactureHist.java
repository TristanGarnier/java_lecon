package com.g4.app.gui;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.g4.app.business.Facture;

import java.awt.event.MouseEvent;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class FactureHist extends JDialog {
    private final JPanel contentPanel = new JPanel();

    public static void main(String[] args) {
        FactureHist dialog = new FactureHist();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    public FactureHist() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
        setUndecorated(true);
        {
            JList<Facture> list = new JList<Facture>();
            list.setModel(new AbstractListModel<Facture>() {

                @Override
                public int getSize() {
                    return Facture.getFactures().size();
                }

                @Override
                public Facture getElementAt(int index) {
                    return Facture.getFactures().get(index);
                }

            });
            list.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    @SuppressWarnings("unchecked")
                    JList<Facture> list = (JList<Facture>) evt.getSource();
                    if (evt.getClickCount() == 2) {
                        FactureDialog fd = new FactureDialog((Facture) list.getSelectedValue());
                        fd.setModal(true);
                        fd.setVisible(true);
                        fd.setModal(false);
                    }
                }
            });
            contentPanel.add(list);
            {
                JPanel buttonPane = new JPanel();
                buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
                getContentPane().add(buttonPane, BorderLayout.SOUTH);
                {
                    JButton okButton = new JButton();
                    okButton.setActionCommand("ok");
                    okButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }

                    });
                    buttonPane.add(okButton);
                    getRootPane().setDefaultButton(okButton);
                }
            }
        }
    }
}

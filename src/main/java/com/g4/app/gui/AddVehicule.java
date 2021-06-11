package com.g4.app.gui;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.g4.app.business.Vehicule;

public class AddVehicule extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JTextField immaT;
    private JTextField ModelT;
    private JTextField marqT;
    private JTextField proT;
    private Vehicule vehicule;
    final JComboBox<String> combobox;

    public static void main(String[] args) {
        try {
            AddVehicule dialog = new AddVehicule();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    // creaion de la fenetre de add vehicule

    public AddVehicule() {
        setBounds(100, 100, 287, 287);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(5, 2, 0, 0));
        {
            JLabel label = new JLabel("Immatriculation");
            contentPanel.add(label);
            immaT = new JTextField();
            contentPanel.add(immaT);
            immaT.setColumns(10);
        }
        {
            JLabel label = new JLabel("Modèle");
            contentPanel.add(label);
            ModelT = new JTextField();
            contentPanel.add(ModelT);
            ModelT.setColumns(10);
        }
        {
            JLabel label = new JLabel("Marque");
            contentPanel.add(label);
            marqT = new JTextField();
            contentPanel.add(marqT);
            marqT.setColumns(10);
        }
        {
            JLabel label = new JLabel("Propriétaire");
            contentPanel.add(label);
            proT = new JTextField();
            contentPanel.add(proT);
            proT.setColumns(10);
        }
        {
            JLabel label = new JLabel("Type de Véhicule");
            contentPanel.add(label);
            combobox = new JComboBox<String>();
            combobox.setModel(new DefaultComboBoxModel<String>(new String[] { "Moto", "Voiture", "Camion" }));
            contentPanel.add(combobox);
        }
        {
            JPanel buttonPan = new JPanel();
            buttonPan.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPan, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (immaT.getText().length() == 0 || ModelT.getText().length() == 0
                                || marqT.getText().length() == 0 || proT.getText().length() == 0) {
                            JOptionPane.showMessageDialog(null, "Tous les champs sont requis", "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        String cn = "com.g4.app.business." + (String) combobox.getSelectedItem();
                        try {
                            Constructor<?> constructor = Class.forName(cn).getConstructor(String.class, String.class,
                                    String.class, String.class);
                            vehicule = (Vehicule) constructor.newInstance(new Object[] { immaT.getText(),
                                    ModelT.getText(), marqT.getText(), proT.getText() });
                            setVisible(false);
                        } catch (InstantiationException | NoSuchMethodException | SecurityException
                                | ClassNotFoundException | IllegalAccessException | IllegalArgumentException
                                | InvocationTargetException exeception) {
                            exeception.printStackTrace();
                        }
                    }
                });
                okButton.setActionCommand("OK");
                buttonPan.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPan.add(cancelButton);
            }
        }
    }
}

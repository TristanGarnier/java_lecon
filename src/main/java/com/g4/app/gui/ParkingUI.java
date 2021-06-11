package com.g4.app.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.g4.app.business.Parking;
import com.g4.app.exception.PasAssezObservateurException;
import com.g4.app.exception.PlaceOccupeeException;

public class ParkingUI {
    private JFrame frame;
    private Parking parking = Parking.getInstance();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    ParkingUI window = new ParkingUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // création de l'application
    public ParkingUI() throws PasAssezObservateurException, ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initialize();
    }

    // initialisation du contenue de notre frame
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 828, 491);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frame.getContentPane().add(menuBar, BorderLayout.NORTH);

        JMenu monFichier = new JMenu("FIchier");
        menuBar.add(monFichier);

        JMenuItem monMenuItem_1 = new JMenuItem("Imprimer");

        monFichier.add(monMenuItem_1);
        monMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);

        JMenuItem monMenuItem_2 = new JMenuItem("Quitter");
        monMenuItem_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        monFichier.add(monMenuItem_2);
        monMenuItem_2.setHorizontalAlignment(SwingConstants.LEFT);

        JMenuItem monMenuItem_3 = new JMenuItem("A propos");
        monMenuItem_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                About about = new About();
                about.setModal(true);
                about.setLocationRelativeTo(null);
                about.setVisible(true);
            }
        });
        monFichier.add(monMenuItem_3);
        monMenuItem_3.setHorizontalAlignment(SwingConstants.LEFT);

        JMenu mnActions = new JMenu("Action");
        menuBar.add(mnActions);

        JMenuItem mtnAjouterVehicule = new JMenuItem("Ajouter un vehicule");
        mtnAjouterVehicule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddVehicule av = new AddVehicule();
                av.setModal(true);
                av.setLocationRelativeTo(frame);
                av.setVisible(true);
                try {
                    Parking.getInstance().park(av.getVehicule());
                } catch (PlaceOccupeeException e1) {
                    JOptionPane.showMessageDialog(null, "Plus de place disponible", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
                av.dispose();
            }
        });
        mnActions.add(mtnAjouterVehicule);
        mtnAjouterVehicule.setHorizontalAlignment(SwingConstants.LEFT);
    }

}
package com.g4.app.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.g4.app.business.Constante;
import com.g4.app.business.Parking;
import com.g4.app.exeption.PasAssezDObservateurException;
import com.g4.app.exeption.PlaceLibreExeption;
import com.g4.app.exeption.PlaceOccupeeException;

public class ParkingUI {
    private JFrame frame;
    private Parking parking = Parking.getInstance();
    private List<PlaceButton> placeButtons = new ArrayList<PlaceButton>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    ParkingUI window = new ParkingUI();
                    window.frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    // création de l'application
    public ParkingUI() throws PasAssezDObservateurException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initialize();
    }

    // initialisation du contenu de notre frame
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 828,491);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frame.getContentPane().add(menuBar, BorderLayout.NORTH);

        JMenu mnFichier = new JMenu("Fichier");
        menuBar.add(mnFichier);

        JMenuItem mntNewMenuItem_1 = new JMenuItem("Imprimer");
        // todo ajouter l'action listener
        mnFichier.add(mntNewMenuItem_1);
        mntNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);

        JMenuItem mntNewMenuItem_2 = new JMenuItem("Quitter");
        mntNewMenuItem_2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        mnFichier.add(mntNewMenuItem_2);

        JMenuItem mntNewMenuItem_3 = new JMenuItem("A propos");
        mntNewMenuItem_3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                About about = new About();
                about.setModal(true);
                about.setLocationRelativeTo(frame);
                about.setVisible(true);
            }
        });
        mnFichier.add(mntNewMenuItem_3);

        JMenu mnActions = new JMenu("Actions");
        menuBar.add(mnActions);

        JMenuItem mntAjouterVehicule = new JMenuItem("Ajouter un Véhicule");
        mntAjouterVehicule.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                AddVehicule av = new AddVehicule();
                av.setModal(true);
                av.setLocationRelativeTo(frame);
                av.setVisible(true);
                try {
                    Parking.getInstance().park(av.getVehicule());
                } catch (PlaceOccupeeException e1) {
                    JOptionPane.showMessageDialog(frame,"Plus de place disponible", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                av.dispose();
            }
        });
        mnActions.add(mntAjouterVehicule);

        JMenuItem mntmCreerUneFacture = new JMenuItem("Afficher les Factures");
        mntmCreerUneFacture.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FacturesHist fd = new FacturesHist();
                fd.setModal(true);
                fd.setLocationRelativeTo(frame);
                fd.setVisible(true);
                fd.setModal(false);
            }
        });
        mnActions.add(mntmCreerUneFacture);

        JMenuItem mntmChercherVehicule = new JMenuItem("Chercher un Véhicule");
        mntmChercherVehicule.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchVehicule sv = new SearchVehicule();
                sv.setModal(true);
                sv.setLocationRelativeTo(frame);
                sv.setVisible(true);
                int location = -1;
                if(!sv.getValue()) return;
                try {
                    location = Parking.getInstance().getLocalisation(sv.getImmat());
                } catch (PlaceLibreExeption e1) {
                    e1.printStackTrace();
                }
                if(location == -1){
                    JOptionPane.showMessageDialog(
                        frame, 
                        "Le véhicule recherché est introuvable", 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                        frame,
                        "Le véhicule se trouve à la place n° " + location,
                        "Recherche",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        mnActions.add(mntmChercherVehicule);

        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        for(int i = 0; i <Constante.nbPlaceParticulier + Constante.nbPlaceTransporteur; i++){
            placeButtons.add(new PlaceButton());
        }
        //parking observer places dans classe parking

    }
}

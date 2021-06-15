package com.g4.app.gui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import com.g4.app.business.Parking;
import com.g4.app.business.Place;
import com.g4.app.business.Vehicule;
import com.g4.app.exception.PlaceLibreException;
import com.g4.app.exception.PlaceOccupeeException;
import com.g4.app.exception.PlusAucunePlaceException;

public class PlaceButton extends JButton {
    private JPopupMenu popup = new JPopupMenu();
    private JMenuItem garerVehicule = new JMenuItem();
    private JMenuItem reserverPlace = new JMenuItem();
    private JMenuItem libererPlace = new JMenuItem();
    private JMenuItem retirerVehicule = new JMenuItem();
    private JMenuItem showInfo = new JMenuItem();

    private static int nbInstance = 0;
    private Place place;
    private int numero;
    private Color c = Color.GREEN;
    private static Map<String, BufferedImage> loadedImage = new HashMap<String, BufferedImage>();

    PlaceButton() {
        numero = nbInstance++;
        garerVehicule.setText("Garer un vehicule ici");
        garerVehicule.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddVehicule av = new AddVehicule();
                av.setModal(true);
                av.setVisible(true);
                if (!av.getValue()) {
                    return;
                }
                av.getVehicule().isTransporteur();
                try {
                    Parking.getInstance().park(av.getVehicule(), numero);
                } catch (PlaceOccupeeException e1) {
                    JOptionPane.showMessageDialog(null, "Impossible de garer le vehicule ici", "Operation Impossible",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        popup.add(garerVehicule);

        reserverPlace.setText("Reserver cette place?");
        reserverPlace.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddVehicule av = new AddVehicule();
                av.setModal(true);
                av.setVisible(true);
                if (!av.getValue()) {
                    return;
                }
                av.getVehicule().isTransporteur();
                try {
                    Parking.getInstance().bookPlace(av.getVehicule(), numero);
                } catch (PlusAucunePlaceException e1) {
                    JOptionPane.showMessageDialog(null, "Impossible de reserver la place", "Operation Impossible",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        popup.add(reserverPlace);

        libererPlace.setText("Libérer cette place?");
        libererPlace.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Parking.getInstance().freePlace(numero);
                } catch (PlaceLibreException e1) {
                    JOptionPane.showMessageDialog(null, "Impossible de libérer la place", "Operation Impossible",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        popup.add(libererPlace);

        retirerVehicule.setText("Retirer le véhicule?");
        retirerVehicule.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Parking.getInstance().unpark(numero);
                } catch (PlaceLibreException | PlaceOccupeeException e1) {
                    JOptionPane.showMessageDialog(null, "La place est déjà libre", "Operation Impossible",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        popup.add(retirerVehicule);

        showInfo.setText("Information du véhicule");
        showInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Vehicule v = place.getParkedVehicule();
                if (v != null) {
                    String info = v.toString();
                    JOptionPane.showMessageDialog(null, info, "Infos", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun véhicule ici", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        popup.add(showInfo);

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });
        setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        Image bi;
        if (!place.isFree()) {
            bi = loadImages(place.getParkedVehicule().getClass().getSimpleName());
        } else {
            bi = null;
        }
        if (bi != null) {
            g.drawImage(bi, getWidth() / 2 - bi.getWidth(null) / 2, getHeight() - bi.getHeight(null) / 2, null);
        }
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(numero), 8, 16);
    }

    private static BufferedImage loadImages(String name) {
        try {
            if (!loadedImage.containsKey(name)) {
                loadedImage.put(name,
                        ImageIO.read(loadedImage.getClass().getResourceAsStream("/assets/" + name + ".png")));
            }
            return loadedImage.get(name);
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place p) {
        place = p;
        if (!p.isFree()) {
            c = Color.RED;
        } else if (p.isreserved()) {
            c = Color.ORANGE;
        } else {
            c = Color.GREEN;
        }
        update(null, null);
    }

    public void update(Observable o, Object arg) {
        if (!place.isFree()) {
            c = Color.RED;
        } else if (place.isreserved()) {
            c = Color.ORANGE;
        } else {
            c = Color.GREEN;
        }
        setBorder(BorderFactory.createDashedBorder(c));
        if (arg != null) {
            FactureDialog fd = new FactureDialog((Vehicule) arg);
            fd.setModal(true);
            fd.setVisible(true);
        }
        repaint();
    }
}

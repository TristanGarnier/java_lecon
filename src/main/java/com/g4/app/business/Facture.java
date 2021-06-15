package com.g4.app.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.tools.FileObject;

public class Facture {
    private static int numFactures = 0;
    private static List<Facture> factures = load();
    private int numFacture = ++numFactures;
    private FeeStrategy tarif = Constante.tarif;
    private Vehicule vehicule;

    public static List<Facture> getFactures() {
        return factures;
    }

    public Facture(Vehicule v) {
        vehicule = v;
        factures.add(this);
    }

    // le prix de stationnement HT
    public double getHT() {
        return tarif.calculerCout(vehicule);
    }

    // le prix de stationnement TTC
    public double getTTC() {
        return getHT() + (getHT() * Constante.TVA / 100);
    }

    public String toString() {
        return "Facture N°" + numFacture + ":\n" + vehicule + "\nTarif calculé" + tarif.descritpion()
                + "\n-----------------------------------\n" + getHT() + "HT;" + getTTC() + "TTC\n";
    }

    // TODO fin le 30/04/2021 17h10
    @SuppressWarnings("unchecked")
    private static List<Facture> load() {
        List<Facture> factures = new ArrayList<Facture>();
        File fact = new File("factures/" + "factures.bin");
        if (!fact.exists()) {
            return factures;
        }

        try (FileInputStream fis = new FileInputStream("factures/" + "factures.bin");
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            factures = (List<Facture>) ois.readObject();
            numFactures = factures.size();
            fis.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } // finally {
          // try {
          // if (fis != null) {
          // fis.close();
          // }
          // } catch (IOException e) {
          // e.printStackTrace();
          // } finally {
          // try {
          // if (ois != null) {
          // ois.close();
          // }
          // } catch (IOException e) {
          // e.printStackTrace();
          // }
          // }
          // }
        return factures;
    }

    public void save() {
        File dir = new File("factures");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (FileOutputStream fos = new FileOutputStream("factures/" + "factures.bin");
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(factures);
        } catch (Exception e) {
            System.err.println("facture non sauvegardées...");
        }
    }
}

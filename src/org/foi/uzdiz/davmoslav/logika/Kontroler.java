/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.davmoslav.logika;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.davmoslav.helper.Validacija;

/**
 *
 * @author Davor
 */
public class Kontroler {
    
    private static Kontroler instance = null;
    Datoteka datoteka;
    
    protected Kontroler() {
       datoteka = new Datoteka();
    }
    
    public static Kontroler getInstance() {
       if(instance == null) {
          instance = new Kontroler();
       }
       return instance;
    }

    public void PokreniProgram(String[] args) {
        if (args.length == 1) {
            File f = new File(args[0]);
            if(f.exists() && !f.isDirectory()) { 
                datoteka.ProcitajDatoteku(args[0]);
                KorisnickiOdabir();
            }
            else{
                System.err.println("Datoteka nepostoji!");
            }
        }
        else {
            System.err.println("Pogrešan broj argumenata!");
        }
    }

    private void KorisnickiOdabir(){
        String odabir = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean render = true;
        while (render) {
            IspisiIzbornik();
            try {
                odabir = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch (odabir) {
                case "1":
                    System.out.println("Pregled stanja ucitane datoteke!");
                    System.out.println("Odaberite tip pretrage:\n1. Korisnika \n2. Grupe \n3. Objekta");
                    int tip_pretrage = Integer.parseInt(Validacija.ValidateInput("^[1-3]{1}$"));
                    if(tip_pretrage == 3){
                        datoteka.PretrazivanjeObjekata();
                    }
                    else {
                        datoteka.PretrazivanjeKorisnikaGrupa(tip_pretrage);
                    }

                    break;
                case "2":
                    System.out.println("Unesite ID korisnika, grupe ili objekta:");
                    int id_dijete = Validacija.getInt(5);
                    System.out.println("Unesite ID grupe:");
                    int id_roditelj =  Validacija.getInt(5);
                    System.out.println("Unesite tip akcije (0: Preporuka | 1: Kupovina | 2: Praćenje):");
                    int id_tip = Validacija.getInt(1);
                    System.out.println("Unesite vrstu akcije (0: Osobno | 1: Grupno | 2:Javno):");
                    int id_vrsta = Validacija.getInt(1);
                    datoteka.DodajAkciju(id_dijete, id_roditelj, id_tip, id_vrsta);
                    break;
                case "3":
                    datoteka.ObrisiAkciju();
                    break;
                case "4":
                    System.out.println("Upisite putanju do datoteke:");
                    String path = "";
                    try {
                        path = br.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    datoteka.ProcitajDatoteku(path);
                    break;
                case "5":
                    render = false;
                    break;
                default:
                    System.out.println("Odabrana opcija ne postoji!");
                    break;
            }
        }
        System.out.println("Kraj");
    }
    
    
    private void IspisiIzbornik()
    {
        System.out.println("====== MAIN MENU - START =========");
        System.out.println("1: Pregled stanja");
        System.out.println("2: Unos dodatne akcije");
        System.out.println("3: Brisanje postojeće akcije");
        System.out.println("4: Učitavanje dodatne datoteke");
        System.out.println("5: Izlaz");
        System.out.println("====== MAIN MENU - END =========\n");
        System.out.println("Vaš odabir: ");

    }
    
}

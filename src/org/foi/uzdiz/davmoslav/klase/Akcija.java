/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.davmoslav.klase;

/**
 *
 * @author Davor
 */
public class Akcija {
    private int sifraKorisnika;
    private int sifraGrupe;
    private int tip;
    private int vrsta;

    public int getSifraKorisnika() {
        return sifraKorisnika;
    }

    public void setSifraKorisnika(int sifraKorisnika) {
        this.sifraKorisnika = sifraKorisnika;
    }

    public int getSifraGrupe() {
        return sifraGrupe;
    }

    public void setSifraGrupe(int sifraGrupe) {
        this.sifraGrupe = sifraGrupe;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getVrsta() {
        return vrsta;
    }

    public void setVrsta(int vrsta) {
        this.vrsta = vrsta;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Akcija)) {
            return false;
        }
        Akcija that = (Akcija) obj;
        return this.sifraGrupe == that.sifraGrupe && 
                this.sifraKorisnika == that.sifraKorisnika && 
                this.tip == that.tip &&
                this.vrsta == that.vrsta;

    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = hashCode * 31 + Integer.toString(sifraGrupe).hashCode();
        hashCode = hashCode * 31 + Integer.toString(sifraKorisnika).hashCode();
        hashCode = hashCode * 31 + Integer.toString(tip).hashCode();
        hashCode = hashCode * 31 + Integer.toString(vrsta).hashCode();
        return hashCode;
    }
}

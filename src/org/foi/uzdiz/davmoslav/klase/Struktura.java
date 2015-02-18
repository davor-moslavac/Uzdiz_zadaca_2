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
public class Struktura {
    private int dijete;
    private int roditelj;
    private int uloga;

    public int getDijete() {
        return dijete;
    }

    public void setDijete(int dijete) {
        this.dijete = dijete;
    }

    public int getRoditelj() {
        return roditelj;
    }

    public void setRoditelj(int roditelj) {
        this.roditelj = roditelj;
    }

    public int getUloga() {
        return uloga;
    }

    public void setUloga(int uloga) {
        this.uloga = uloga;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Struktura)) {
            return false;
        }
        Struktura that = (Struktura) obj;
        return this.roditelj == that.roditelj && this.uloga == that.uloga && this.dijete == that.dijete;

    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = hashCode * 31 + Integer.toString(dijete).hashCode();
        hashCode = hashCode * 31 + Integer.toString(roditelj).hashCode();
        hashCode = hashCode * 31 + Integer.toString(uloga).hashCode();
        return hashCode;
    }  
    
}

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
public class Grupa {
    private Integer sifra;
    private String naziv;

    public Integer getSifra() {
        return sifra;
    }

    public void setSifra(Integer sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Grupa)) {
            return false;
        }
        Grupa that = (Grupa) obj;
        return this.naziv.equals(that.naziv) && this.sifra.equals(that.sifra);
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = hashCode * 31 + this.naziv.hashCode();
        hashCode = hashCode * 31 + this.sifra.hashCode();
        return hashCode;
    }
}

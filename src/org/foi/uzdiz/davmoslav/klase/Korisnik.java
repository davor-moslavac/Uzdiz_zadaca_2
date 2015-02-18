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
public class Korisnik {
    
    private Integer sifra;
    private Integer vrstaKorisnika;
    private Integer roditeljKorisnika;
    private String ime;

    public Integer getSifra() {
        return sifra;
    }

    public void setSifra(Integer sifra) {
        this.sifra = sifra;
    }

    public Integer getVrstaKorisnika() {
        return vrstaKorisnika;
    }

    public void setVrstaKorisnika(Integer vrstaKorisnika) {
        this.vrstaKorisnika = vrstaKorisnika;
    }

    public Integer getRoditeljKorisnika() {
        return roditeljKorisnika;
    }

    public void setRoditeljKorisnika(Integer roditeljKorisnika) {
        this.roditeljKorisnika = roditeljKorisnika;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Korisnik)) {
            return false;
        }
        Korisnik that = (Korisnik) obj;
        return this.ime.equals(that.ime) && this.sifra.equals(that.sifra);
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = hashCode * 31 + this.ime.hashCode();
        hashCode = hashCode * 31 + this.sifra.hashCode();
        return hashCode;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.davmoslav.builder;

import org.foi.uzdiz.davmoslav.klase.Korisnik;

/**
 *
 * @author Davor
 */
public class KorisnikBuilder {
    private Korisnik korisnik;

    public KorisnikBuilder(int sifra, String ime) {
        korisnik = new Korisnik();
        korisnik.setSifra(sifra);
        korisnik.setIme(ime);
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }
}

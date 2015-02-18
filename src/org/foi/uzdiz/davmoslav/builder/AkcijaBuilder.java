/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.davmoslav.builder;

import org.foi.uzdiz.davmoslav.klase.Akcija;

/**
 *
 * @author Davor
 */
public class AkcijaBuilder {
    private Akcija akcija;

    public AkcijaBuilder(int sifraKorisnika, int sifraGrupe, int tip, int vrsta) {
        akcija = new Akcija();
        akcija.setSifraGrupe(sifraGrupe);
        akcija.setSifraKorisnika(sifraKorisnika);
        akcija.setTip(tip);
        akcija.setVrsta(vrsta);
    }

    public Akcija getAkcija() {
        return akcija;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.davmoslav.builder;

import org.foi.uzdiz.davmoslav.klase.Grupa;

/**
 *
 * @author Davor
 */
public class GrupaBuilder {
    private Grupa grupa;

    public GrupaBuilder(int sifra, String naziv) {
        grupa = new Grupa();
        grupa.setSifra(sifra);
        grupa.setNaziv(naziv);
    }

    public Grupa getGrupa() {
        return grupa;
    }
}

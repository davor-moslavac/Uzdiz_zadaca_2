/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.davmoslav.builder;

import org.foi.uzdiz.davmoslav.klase.Objekt;

/**
 *
 * @author Davor
 */
public class ObjektBuilder {
     private Objekt objekt;

    public ObjektBuilder(int sifra, String naziv) {
        objekt = new Objekt();
        objekt.setSifra(sifra);
        objekt.setNaziv(naziv);
    }

    public Objekt getObjekt() {
        return objekt;
    }
}

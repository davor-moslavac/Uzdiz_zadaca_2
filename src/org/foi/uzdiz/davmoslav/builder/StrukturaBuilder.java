/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.davmoslav.builder;

import org.foi.uzdiz.davmoslav.klase.Struktura;

/**
 *
 * @author Davor
 */
public class StrukturaBuilder {
    private Struktura struktura;

    public StrukturaBuilder(int dijete, int roditelj, int uloga) {
        struktura = new Struktura();
        struktura.setDijete(dijete);
        struktura.setRoditelj(roditelj);
        struktura.setUloga(uloga);
    }

    public Struktura getStruktura() {
        return struktura;
    }
}

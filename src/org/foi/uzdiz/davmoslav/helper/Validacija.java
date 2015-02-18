/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.davmoslav.helper;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Davor
 */
public final class Validacija {

    private Validacija() {
    }

    public static int getInt(int maxVelicina) {
        Scanner sc = new Scanner(System.in);
        String regex = String.format("\\d{%d,%d}", 1, maxVelicina);
        while (!sc.hasNext(Pattern.compile(regex)))
        {
            System.out.println(String.format("Pogresan unos! Unesite broj od max. %d znakova.", maxVelicina));
            sc.next();
        }
        return sc.nextInt();
    }
    
    public static boolean isIntValid(String val, int maxVelicina)
    {
        String regex = String.format("\\d{%d,%d}", 1, maxVelicina);
        return val.matches(regex);
    }
    
    public static boolean isStringValid(String val, int maxVelicina)
    {
        String regex = String.format("^[A-Za-z0-9 ]++${%d,%d}", 1, maxVelicina);
        return val.matches(regex);
    }
    
    public static String ValidateInput(String regex)
    {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNext(Pattern.compile(regex)))
        {
            System.out.println("Pogresan unos!");
            sc.next();
        }
        return sc.next();
    }
}

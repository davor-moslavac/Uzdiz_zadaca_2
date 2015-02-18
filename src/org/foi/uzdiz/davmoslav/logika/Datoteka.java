/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.davmoslav.logika;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.foi.uzdiz.davmoslav.builder.AkcijaBuilder;
import org.foi.uzdiz.davmoslav.builder.GrupaBuilder;
import org.foi.uzdiz.davmoslav.builder.KorisnikBuilder;
import org.foi.uzdiz.davmoslav.builder.ObjektBuilder;
import org.foi.uzdiz.davmoslav.builder.StrukturaBuilder;
import org.foi.uzdiz.davmoslav.helper.Validacija;
import org.foi.uzdiz.davmoslav.klase.Akcija;
import org.foi.uzdiz.davmoslav.klase.Grupa;
import org.foi.uzdiz.davmoslav.klase.Korisnik;
import org.foi.uzdiz.davmoslav.klase.Objekt;
import org.foi.uzdiz.davmoslav.klase.Struktura;

/**
 *
 * @author Davor
 */
public class Datoteka {

    private List<Korisnik> listaKorisnika = null;
    private List<Grupa> listaGrupa = null;
    private List<Objekt> listaObjekata = null;
    private List<Struktura> listaStruktura = null;
    private List<Akcija> listaAkcija = null;

    public Datoteka() {
        this.listaKorisnika = new ArrayList<>();
        this.listaGrupa = new ArrayList<>();
        this.listaObjekata = new ArrayList<>();
        this.listaStruktura = new ArrayList<>();
        this.listaAkcija = new ArrayList<>();
    }
    
    
    
    void ProcitajDatoteku(String path) {
            System.out.println("Citanje datoteke!");
            try (BufferedReader br = new BufferedReader(new FileReader(path)))
            {
                String linija;
                while ((linija = br.readLine()) != null) {
                    if(!linija.equals(""))
                    {
                        String[] dijelovi = linija.split("\t");
                        if(dijelovi.length == 4 && dijelovi[0].equals("0")){
                            KreiranjeDefinicije(dijelovi);
                        }
                        else if(dijelovi.length == 4 && dijelovi[0].equals("1")){
                            KreiranjeStrukture(dijelovi);
                        }
                        else if(dijelovi.length == 5 && dijelovi[0].equals("2")){
                            KreiranjeAkcije(dijelovi);
                        }
                        else {
                            System.out.println("Nedovoljan broj dijelova reda. Preskačem zapis...");
                        }
                    }
                }
                
                System.out.println("Datoteka je pročitana!");
                System.out.println(listaKorisnika.size() + " korisnika");
                System.out.println(listaGrupa.size() + " grupa");
                System.out.println(listaObjekata.size() + " objekata");
                System.out.println(listaStruktura.size() + " struktura");
                System.out.println(listaAkcija.size() + " akcija");

            } catch (IOException e) {
                System.err.println("Dogodila se je greška prilikom čitanja datoteke!");
            } 
    }

    private void KreiranjeDefinicije(String[] dijelovi) {
        int id = 0;
        if(Validacija.isIntValid(dijelovi[1], 5)) {
            id = Integer.parseInt(dijelovi[1]);
        }
        else {
            System.err.println("Pogreška! Id nije ispravnog formata.");
            return;
        }
        
        String ime="";
        if(Validacija.isStringValid(dijelovi[2].trim(), 20)) {
            ime = dijelovi[2].trim();
        }
        else {
            System.err.println(String.format("Pogreška! Ime (%s) nije ispravnog formata i duljine.", dijelovi[2].trim()));
            return;
        }
        
        switch (dijelovi[3]) {
            case "0":
                // kreiranje korisnika
                KorisnikBuilder kBuilder = new KorisnikBuilder(id, ime);
                Korisnik korisnik = kBuilder.getKorisnik();
                if(!listaKorisnika.contains(korisnik)){
                    listaKorisnika.add(korisnik);
                }
                else {
                    System.out.println("Korisnik " + korisnik.getIme() + " s šifrom " +  korisnik.getSifra() + " vec postoji. Preskačem zapis...");
                }   
                break;
            case "1":
                //kreiranje grupe
                GrupaBuilder gBuilder = new GrupaBuilder(id, ime);
                Grupa grupa = gBuilder.getGrupa();
                if(!listaGrupa.contains(grupa)){
                    listaGrupa.add(grupa);
                }
                else {
                    System.out.println("Grupa " + grupa.getNaziv() + " s šifrom " +  grupa.getSifra() + " vec postoji. Preskačem zapis...");
                }   
                break;
            case "2":
                //kreiranje objekta
                ObjektBuilder oBuilder = new ObjektBuilder(id, ime);
                Objekt objekt = oBuilder.getObjekt();
                if(!listaObjekata.contains(objekt)){
                    listaObjekata.add(objekt);
                }
                else {
                    System.out.println("Objekt " + objekt.getNaziv() + " s šifrom " +  objekt.getSifra() + " vec postoji. Preskačem zapis...");
                }   
                break;
            default:
                System.out.println("Nepoznata definicija: " + dijelovi[3] + "! Preskačem zapis...");
                break;
        }
            
    }

    private void KreiranjeStrukture(String[] dijelovi) {
        int dijete = 0, roditelj = 0, uloga = 0;
        if(Validacija.isIntValid(dijelovi[1], 5)) {
            dijete = Integer.parseInt(dijelovi[1]);
        }
        else {
            System.err.println("Pogreška! Dijete nije ispravnog formata.");
            return;
        }
        if(Validacija.isIntValid(dijelovi[2], 5)){
            roditelj = Integer.parseInt(dijelovi[2]);
        }
        else {
            System.err.println("Pogreška! Roditelj nije ispravnog formata.");
            return;
        }
        if(Validacija.isIntValid(dijelovi[3], 1)){
            uloga = Integer.parseInt(dijelovi[3]);
        }
        else {
            System.err.println("Pogreška! Uloga nije ispravnog formata.");
            return;
        }
        
        if(ListaSadrziId(dijete)){
            if(ListaSadrziId(roditelj)){
            
                if(IsGroup(roditelj)){
                    StrukturaBuilder sBuilder = new StrukturaBuilder(dijete, roditelj, uloga);
                    Struktura s = sBuilder.getStruktura();
                    if(!listaStruktura.contains(s)){
                        listaStruktura.add(s);
                    }
                    else {
                        System.out.println("Struktura s roditeljem " + s.getRoditelj() + " i djetetom " + s.getDijete() + " vec postoji. Preskačem zapis...");
                    }
                }
                else{
                    System.out.println("[Struktura] Greška: Šifra " + roditelj + " nije grupa!");
                }
            }
            else {
                System.out.println("[Struktura] Greška: Ne postoji šifra: " + roditelj);
            }
        }
        else {
            System.out.println("[Struktura] Greška: Ne postoji šifra: " + dijete);
        }
    }

    private void KreiranjeAkcije(String[] dijelovi) {
        
        int id1 = 0, id2 = 0, tip = 0, vrsta = 0;
        if(Validacija.isIntValid(dijelovi[1], 5)) {
            id1 = Integer.parseInt(dijelovi[1]);
        }
        else {
            System.err.println("Pogreška! Sifra objekta nije ispravnog formata.");
            return;
        }
        if(Validacija.isIntValid(dijelovi[2], 5)){
            id2 = Integer.parseInt(dijelovi[2]);
        }
        else {
            System.err.println("Pogreška! Sifra korisnika/grupe nije ispravnog formata.");
            return;
        }
        if(Validacija.isIntValid(dijelovi[3], 1)){
            tip = Integer.parseInt(dijelovi[3]);
        }
        else {
            System.err.println("Pogreška! Tip akcije nije ispravnog formata.");
            return;
        }
        if(Validacija.isIntValid(dijelovi[4], 1)){
            vrsta = Integer.parseInt(dijelovi[4]);
        }
        else {
            System.err.println("Pogreška! Vrsta akcije nije ispravnog formata.");
            return;
        }
        
        if(ListaSadrziId(id1)){
            if(ListaSadrziId(id2)){
                AkcijaBuilder aBuilder = new AkcijaBuilder(id1, id2, tip, vrsta);
                Akcija akcija = aBuilder.getAkcija();
                 if(!listaAkcija.contains(akcija)){
                    listaAkcija.add(akcija);
                }
                else {
                    System.out.println("Akcija vec postoji! Preskačem zapis...");
                } 
                
            }
            else {
                System.out.println("[Akcija] Greška: Ne postoji šifra: " + id2);
            }
        }
        else {
            System.out.println("[Akcija] Greška: Ne postoji šifra: " + id1);
        }
        
    }
    
    public void DodajAkciju(int id_dijete, int id_roditelj, int tip, int vrsta) {  
        if(ListaSadrziId(id_dijete)){
            if(ListaSadrziId(id_roditelj)){
                AkcijaBuilder aBuilder = new AkcijaBuilder(id_dijete, id_roditelj, tip, vrsta);
                Akcija akcija = aBuilder.getAkcija();
                 if(!listaAkcija.contains(akcija)){
                    listaAkcija.add(akcija);
                     System.out.println("Akcija je dodana!. Sada imamo ukupno " + listaAkcija.size() + " akcija");
                }
                else {
                    System.out.println("Akcija vec postoji! Preskačem zapis...");
                }
            }
            else {
                System.out.println("[Akcija] Greška: Ne postoji šifra: " + id_roditelj);
            }
        }
        else {
            System.out.println("[Akcija] Greška: Ne postoji šifra: " + id_dijete);
        }  
    }
    
    public void ObrisiAkciju() {
        String korisnici = listaKorisnika.stream()
                .map(i -> i.getSifra().toString())
                .collect(Collectors.joining(", "));
        System.out.println("Korisnici:");
        System.out.println(korisnici);

        String grupe = listaGrupa.stream()
            .map(i -> i.getSifra().toString())
            .collect(Collectors.joining(", "));

        System.out.println("Grupe:");
        System.out.println(grupe);
        System.out.println("Unesite ID grupe ili korisnika:");
        int id = Validacija.getInt(5);
        if(!IsKorisnikIliGrupa(id)) {
            System.err.println("Ne postoji grupa/korisnik s nevedenom sifrom!");
            return;
        }
        System.out.println(String.format("Upisite redni broj akcije koju zelite obrisati (0 - %d):", (listaAkcija.size() -1)));
        int index_akcije = Validacija.getInt(5);
        if(index_akcije < listaAkcija.size()){
            listaAkcija.remove(index_akcije);
            System.out.println("Akcija je uspjesno obrisana!");
        }
        else {
            System.err.println(String.format("[Brisanje] Greška! Index akcije mora biti izmedu 0 i %d", (listaAkcija.size() -1)));
        }
    }
    
    public void PretrazivanjeObjekata() {
        String objekti = listaObjekata.stream()
            .map(i -> i.getSifra().toString())
            .collect(Collectors.joining(", "));
        System.out.println("Objekti:");
        System.out.println(objekti);
        System.out.println("Unesite ID objekta:");
        int objekt_id = Validacija.getInt(5);
        List<Integer> lstTipAkcije = DohvatiTipAkcije();
        int vrsta_akcije = DohvatiVrstuAkcije();
        String akcije = lstTipAkcije.stream()
            .map(i -> i.toString())
            .collect(Collectors.joining(", "));
        System.out.println(String.format("Pretrazivanje svih korisnika i grupa povezanih sa Objekt Id-ijem: %s \nFiltar akcija: Tip: %s, Vrsta: %s", String.valueOf(objekt_id), akcije, String.valueOf(vrsta_akcije)));
        int count = 0;
        
        for(Akcija akcija : listaAkcija){
            if(akcija.getSifraKorisnika() == objekt_id){
                if(lstTipAkcije.contains(akcija.getTip())){
                    if(akcija.getVrsta() == vrsta_akcije){
                        count ++;
                        String ime_objekta = "";
                        String ime_kor_grupe = "";
                        for(Objekt objekt : listaObjekata){
                            if(objekt.getSifra().equals(objekt_id)){
                                ime_objekta = objekt.getNaziv();
                                break;
                            }
                        }
                      
                        for(Korisnik kor : listaKorisnika){
                            if(kor.getSifra().equals(akcija.getSifraGrupe())){
                                ime_kor_grupe = kor.getIme();
                                break;
                            }
                        }
                      
                        if(ime_kor_grupe.length() == 0){
                            for(Grupa grupa : listaGrupa){
                                if(grupa.getSifra().equals(akcija.getSifraGrupe())){
                                    ime_kor_grupe = grupa.getNaziv();
                                    break;
                                }
                            }
                        }
                      
                       System.out.println(String.format("Objekt: %s  -> Grupa/Korisnik: %s", ime_objekta, ime_kor_grupe));  
                    }
                }
            }
        }
        if(count == 0){
            System.out.println("Nema rezultata!");
        }
        else {
            System.out.println(String.format("Pronadeno ukupno %d rezultata!", count));
        }
        
    }

    void PretrazivanjeKorisnikaGrupa(int tip_pretrage) {
        
        if(tip_pretrage == 1){
            String korisnici = listaKorisnika.stream()
                .map(i -> i.getSifra().toString())
                .collect(Collectors.joining(", "));
            System.out.println("Korisnici:");
            System.out.println(korisnici);
        
            System.out.println("Unesite ID korisnika:");
        }
        else {
            String grupe = listaGrupa.stream()
                .map(i -> i.getSifra().toString())
                .collect(Collectors.joining(", "));

            System.out.println("Grupe:");
            System.out.println(grupe);
            System.out.println("Unesite ID grupe:");
        }
        int id = Validacija.getInt(5);
        List<Integer> lstTipAkcije = DohvatiTipAkcije();
        int vrsta_akcije = DohvatiVrstuAkcije();
        String akcije = lstTipAkcije.stream()
            .map(i -> i.toString())
            .collect(Collectors.joining(", "));
        System.out.println(String.format("Pretrazivanje svih objekata povezanih s ID-jem korisnika/grupe: %s \nFiltar akcija: Tip: %s, Vrsta: %s", String.valueOf(id), akcije, String.valueOf(vrsta_akcije)));
        int count = 0;
        for(Akcija akcija : listaAkcija){
            if(akcija.getSifraGrupe()== id){
                if(lstTipAkcije.contains(akcija.getTip())){
                    if(akcija.getVrsta() == vrsta_akcije){
                        count ++;
                        String ime_objekta = "";
                        String ime_kor_grupe = "";
                        for(Objekt objekt : listaObjekata){
                            if(objekt.getSifra().equals(akcija.getSifraKorisnika())){
                                ime_objekta = objekt.getNaziv();
                                break;
                            }
                        }
                      
                        for(Korisnik kor : listaKorisnika){
                            if(kor.getSifra().equals(akcija.getSifraGrupe())){
                                ime_kor_grupe = kor.getIme();
                                break;
                            }
                        }
                      
                        if(ime_kor_grupe.length() == 0){
                            for(Grupa grupa : listaGrupa){
                                if(grupa.getSifra().equals(akcija.getSifraGrupe())){
                                    ime_kor_grupe = grupa.getNaziv();
                                    break;
                                }
                            }
                        }
                      
                       System.out.println(String.format("Objekt: %s  -> Grupa/Korisnik: %s", ime_objekta, ime_kor_grupe));  
                    }
                }
            }
        }
        if(count == 0){
            System.out.println("Nema rezultata!");
        }
        else {
            System.out.println(String.format("Pronadeno ukupno %d rezultata!", count));
        }
    }
    
    private List<Integer> DohvatiTipAkcije(){
        List<Integer> lstTipAkcije = new ArrayList<>();
        System.out.println("Zelite li pretraziti sve tipove akcija (1 - DA | 0 - NE):");
        int tipPretrazivanja = Integer.parseInt(Validacija.ValidateInput("^[0-1]{1}$"));
        if(tipPretrazivanja == 1){
            lstTipAkcije.add(0);
            lstTipAkcije.add(1);
            lstTipAkcije.add(2);
        }
        else{
             System.out.println("Unesite tip akcije povezane sa ID-em [0 - Preporuka | 1 - Kupovina | 2 - Pracenje]:");
             int tipAkcije = Integer.parseInt(Validacija.ValidateInput("^[0-2]{1}$"));
             lstTipAkcije.add(tipAkcije);
        }
        return lstTipAkcije;
    }
    
    private Integer DohvatiVrstuAkcije(){
       System.out.println("Unesite vrstu akcije povezane sa ID-em [0 - Osobno | 1 - Grupno | 2 - Javno]:");
       return Integer.parseInt(Validacija.ValidateInput("^[0-2]{1}$"));
    }
    
    private boolean ListaSadrziId(int sifra){
        for (Korisnik k : listaKorisnika) {
            if (k.getSifra() == sifra) {
              return true;
            }
        }
        
        for (Grupa g : listaGrupa) {
            if (g.getSifra() == sifra) {
              return true;
            }
        }
        
        for (Objekt o : listaObjekata) {
            if (o.getSifra() == sifra) {
              return true;
            }
        }
        
        return false;
    }
    
    private boolean IsGroup(int sifra){
        for (Grupa g : listaGrupa) {
            if (g.getSifra() == sifra) {
              return true;
            }
        }
        
        return false;
    }
    
    private boolean IsKorisnikIliGrupa(int sifra){
        for (Korisnik k : listaKorisnika) {
            if (k.getSifra() == sifra) {
              return true;
            }
        }
        
        for (Grupa g : listaGrupa) {
            if (g.getSifra() == sifra) {
              return true;
            }
        }
        
        return false;
    }


}

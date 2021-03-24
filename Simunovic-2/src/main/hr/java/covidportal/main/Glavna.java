package main.hr.java.covidportal.main;

import main.hr.java.covidportal.model.*;

import java.util.Arrays;
import java.util.Scanner;

public class Glavna {
    public static final Integer BROJ_ZUPANIJA = 3;
    public static final Integer BROJ_SIMPTOMA = 3;
    public static final Integer BROJ_BOLESTI = 4;
    public static final Integer BROJ_OSOBA = 3;

    public static void main(String[] args) {
        Scanner unos = new Scanner(System.in);
        Zupanija[] zupanije = new Zupanija[BROJ_ZUPANIJA];
        System.out.println("Unesite podatke o 3 županije: ");
        for (int i = 0; i < BROJ_ZUPANIJA; i++) {
            zupanije[i] = unesiZupaniju(i, unos);
        }

        Simptom[] simptomi = new Simptom[BROJ_SIMPTOMA];
        System.out.println("Unesite podatke o 3 simptoma: ");
        for (int i = 0; i < BROJ_SIMPTOMA; i++) {
            simptomi[i] = unesiSimptome(i, unos);
        }

        Bolest[] bolesti = new Bolest[BROJ_BOLESTI];
        System.out.println("Unesite podatke o 4 bolesti ili virusa: ");
        for (int i = 0; i < BROJ_BOLESTI; i++) {
            bolesti[i] = unesiBolesti(i, unos, simptomi);
        }

        Osoba[] osobe = new Osoba[BROJ_OSOBA];
        System.out.println("Unesite podatke o 3 osobe:");
        for (int i = 0; i < BROJ_OSOBA; i++) {
            osobe[i] = unesiOsobe(i, unos, bolesti, zupanije, osobe);
        }
        System.out.println("Unesene osobe: ");
        for (int i = 0; i < BROJ_OSOBA; i++) {
            System.out.println("================================================================");
            System.out.println("Ime i prezime: " + osobe[i].getIme() + "  " + osobe[i].getPrezime());
            System.out.println("Starost: " + osobe[i].getStarost());
            System.out.println("Županija prebivališta: " + osobe[i].getZupanija().getNaziv());
            System.out.println("Zaražen bolešću: " + osobe[i].getZarazenBolescu().getNaziv());
            if (osobe[i].getKontaktiraneOsobe() != null) {
                System.out.print("Kontakt osobe: ");
                for (int o = 0; o < osobe[i].getKontaktiraneOsobe().length; o++)
                    if (osobe[i].getKontaktiraneOsobe()[o] != null) {
                        System.out.print(osobe[i].getKontaktiraneOsobe()[o].getIme() + " " + osobe[i].getKontaktiraneOsobe()[o].getPrezime() + ", ");
                    }
                System.out.println("");
            } else {
                System.out.println("Kontakt osobe:  Nema kontaktiranih osoba!");
            }
        }

    }


    private static Osoba unesiOsobe(int i, Scanner unos, Bolest[] bolesti, Zupanija[] zupanije, Osoba[] osobe) {
        String ime, prezime;
        Integer starost;
        Zupanija zupanijaPrebivalista = new Zupanija("Naziv", 0);
        Bolest zarazenBolescu = new Bolest("Naziv", null);
        Osoba[] kontaktOsobe = new Osoba[0];
        System.out.print("Unesite ime " + (i + 1) + ". osobe: ");
        ime = unos.nextLine();
        System.out.print("Unesite prezime " + (i + 1) + ". osobe: ");
        prezime = unos.nextLine();
        System.out.print("Unesite starost " + (i + 1) + ". osobe: ");
        starost = unos.nextInt();
        unos.nextLine();
        boolean ponoviUnos = false;
        do {
            System.out.println("Odaberite zupaniju prebivališta: ");
            for (int z = 0; z < BROJ_ZUPANIJA; z++) {
                System.out.println((z + 1) + ". " + zupanije[z].getNaziv());
            }
            System.out.print("Odabir: ");
            Integer odabranaZupanija = unos.nextInt();
            unos.nextLine();
            if (odabranaZupanija > zupanije.length || odabranaZupanija < 1) {
                System.out.println("Pograšan unos!");
                ponoviUnos = true;
            } else {
                ponoviUnos = false;
                zupanijaPrebivalista = zupanije[odabranaZupanija - 1];
            }
        } while (ponoviUnos);

        do {
            System.out.println("Odaberite bolest osobe: ");
            for (int b = 0; b < BROJ_BOLESTI; b++) {
                System.out.println((b + 1) + ". " + bolesti[b].getNaziv());
            }
            System.out.print("Odabir: ");
            Integer odabranaBolest = unos.nextInt();
            unos.nextLine();
            if (odabranaBolest < 1 || odabranaBolest > bolesti.length) {
                System.out.println("Pogrešan unos!");
                ponoviUnos = true;
            } else {
                zarazenBolescu = bolesti[odabranaBolest - 1];
                ponoviUnos = false;
            }
        } while (ponoviUnos);

        Integer brojKontaktOsoba = 0;
        if (i < 1) {
            Osoba novaOsoba = new Osoba.Builder()
                    .seZove(ime)
                    .sePreziva(prezime)
                    .imaGodina(starost)
                    .pripadaZupaniji(zupanijaPrebivalista)
                    .imaBolest(zarazenBolescu)
                    .build();
            return novaOsoba;
        } else {
            do {
                System.out.print("Unesite broj kontaktiranih osoba: ");
                brojKontaktOsoba = unos.nextInt();
                unos.nextLine();
                if (brojKontaktOsoba > i) {
                    System.out.println("Broj kontaktiranih osoba ne može biti veći od broja unesenih osoba ! ");
                    ponoviUnos = true;
                } else {
                    ponoviUnos = false;
                }
            } while (ponoviUnos);

            Osoba[] prosirenoPolje = Arrays.copyOf(osobe, brojKontaktOsoba);
            for (int o = 0; o < brojKontaktOsoba; o++) {
                do {
                    System.out.println("Odaberite " + (o + 1) + ". kontakt osobu: ");
                    for (int kontOsob = 0; kontOsob < BROJ_OSOBA; kontOsob++) {
                        if (osobe[kontOsob] != null) {
                            System.out.println((kontOsob + 1) + ". " + osobe[kontOsob].getIme() + " " + osobe[kontOsob].getPrezime());
                        }
                    }
                    System.out.print("Odabir: ");
                    Integer odabranaKontaktOsoba = unos.nextInt();
                    unos.nextLine();
                    if (odabranaKontaktOsoba < 1 || odabranaKontaktOsoba > osobe.length) {
                        System.out.println("Pogrešan unos! ");
                        ponoviUnos = true;
                    } else {
                        kontaktOsobe = prosirenoPolje;
                        kontaktOsobe[o] = osobe[odabranaKontaktOsoba - 1];
                        ponoviUnos = false;
                    }
                } while (ponoviUnos);


            }
            Osoba novaOsoba = new Osoba.Builder()
                    .seZove(ime)
                    .sePreziva(prezime)
                    .imaGodina(starost)
                    .pripadaZupaniji(zupanijaPrebivalista)
                    .imaBolest(zarazenBolescu)
                    .kontaktiraneOsobe(kontaktOsobe)
                    .build();
            return novaOsoba;
        }
    }

    private static Bolest unesiBolesti(int i, Scanner unos, Simptom[] simptomi) {
        System.out.println("Unosite li bolest ili virus ?");
        System.out.println("1. BOLEST");
        System.out.println("2. VIRUS");
        System.out.print("Odabir: ");
        Integer vrstaBolesti = 0;
        vrstaBolesti = unos.nextInt();
        unos.nextLine();
        boolean provjeraBolesti=true;
        do {
            if(vrstaBolesti == 1 || vrstaBolesti == 2){
                provjeraBolesti=false;
            }
            else {
                System.out.println("Pogrešan unos! Pokušajte ponovno!");
                provjeraBolesti=true;
                System.out.println("Unosite li bolest ili virus ?");
                System.out.println("1. BOLEST");
                System.out.println("2. VIRUS");
                System.out.print("Odabir: ");
                vrstaBolesti = unos.nextInt();
                unos.nextLine();
            }
        } while (provjeraBolesti);
        if (vrstaBolesti == 1) {
            String naziv;
            Integer brojSimptoma = 0;
            System.out.print("Unesite naziv bolesti: ");
            naziv = unos.nextLine();
            System.out.print("Unesite broj simptoma: ");
            brojSimptoma = unos.nextInt();
            Simptom[] simptomiBolesti = new Simptom[brojSimptoma];
            Integer odabraniSimptom;
            boolean ponoviUnos = false;

            for (int j = 0; j < brojSimptoma; j++) {
                do {
                    System.out.println("Odaberite " + (j + 1) + ". simptom: ");
                    for (int s = 0; s < BROJ_SIMPTOMA; s++) {
                        System.out.println((s + 1) + ". " + simptomi[s].getNaziv() + " " + simptomi[s].getVrijednost());
                    }
                    System.out.print("Odabir: ");
                    odabraniSimptom = unos.nextInt();
                    unos.nextLine();
                    if (odabraniSimptom < 1 || odabraniSimptom > simptomi.length) {
                        System.out.println("Pogrešan unos!");
                        ponoviUnos = true;
                    } else {
                        ponoviUnos = false;
                        simptomiBolesti[j] = simptomi[odabraniSimptom - 1];
                    }
                } while (ponoviUnos);
            }
            Bolest novaBolest = new Bolest(naziv, simptomiBolesti);
            return novaBolest;
        } else {
            String naziv;
            Integer brojSimptoma = 0;
            System.out.print("Unesite naziv virusa: ");
            naziv = unos.nextLine();
            System.out.print("Unesite broj simptoma: ");
            brojSimptoma = unos.nextInt();
            Simptom[] simptomiBolesti = new Simptom[brojSimptoma];
            Integer odabraniSimptom;
            boolean ponoviUnos = false;

            for (int j = 0; j < brojSimptoma; j++) {
                do {
                    System.out.println("Odaberite " + (j + 1) + ". simptom: ");
                    for (int s = 0; s < BROJ_SIMPTOMA; s++) {
                        System.out.println((s + 1) + ". " + simptomi[s].getNaziv() + " " + simptomi[s].getVrijednost());
                    }
                    System.out.print("Odabir: ");
                    odabraniSimptom = unos.nextInt();
                    unos.nextLine();
                    if (odabraniSimptom < 1 || odabraniSimptom > simptomi.length) {
                        System.out.println("Pogrešan unos!");
                        ponoviUnos = true;
                    } else {
                        ponoviUnos = false;
                        simptomiBolesti[j] = simptomi[odabraniSimptom - 1];
                    }
                } while (ponoviUnos);
            }
            Bolest novaBolest = new Virus(naziv, simptomiBolesti);
            return novaBolest;
        }
    }

    private static Simptom unesiSimptome(int i, Scanner unos) {
        String naziv, vrijednost;
        System.out.print("Unesite naziv " + (i + 1) + ". simptoma: ");
        naziv = unos.nextLine();
        boolean ponoviUnos = false;
        do {
            System.out.print("Unesite vrijednost simptoma (RIJETKO, SREDNJE, ČESTO): ");
            vrijednost = unos.nextLine();

            if (vrijednost.equals("RIJETKO") || vrijednost.equals("SREDNJE") || vrijednost.equals("ČESTO")) {
                ponoviUnos = false;
            } else {
                System.out.println("Molimo koristite samo zadane vrijednosti (RIJETKO;SREDNJE;ČESTO)!");
                ponoviUnos = true;
            }
        } while (ponoviUnos);
        Simptom noviSimptom = new Simptom(naziv, vrijednost);
        return noviSimptom;
    }

    private static Zupanija unesiZupaniju(int i, Scanner unos) {
        String naziv;
        Integer brojStanovnika;
        System.out.print("Unesite naziv " + (i + 1) + ". županije: ");
        naziv = unos.nextLine();
        System.out.print("Unesite broj stanovnika: ");
        brojStanovnika = unos.nextInt();
        unos.nextLine();
        Zupanija novaZupanija = new Zupanija(naziv, brojStanovnika);
        return novaZupanija;
    }
}

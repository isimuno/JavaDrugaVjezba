package main.hr.java.covidportal.model;

import main.hr.java.covidportal.main.Glavna;

public class Osoba {
    private String ime;
    private String prezime;
    private Integer starost;
    private Zupanija zupanija;
    private Bolest zarazenBolescu;
    private Osoba[] kontaktiraneOsobe;


    public Osoba(String ime, String prezime, Integer starost, Zupanija zupanija, Bolest zarazenBolescu, Osoba[] kontaktiraneOsobe) {
        this.ime = ime;
        this.prezime = prezime;
        this.starost = starost;
        this.zupanija = zupanija;
        this.zarazenBolescu = zarazenBolescu;
        this.kontaktiraneOsobe = kontaktiraneOsobe;
        if (zarazenBolescu instanceof Virus) {
            Virus nazivVirusa = new Virus(zarazenBolescu.getNaziv(), zarazenBolescu.getSimptomi());
            if(kontaktiraneOsobe != null){
                for (int i = 0; i < kontaktiraneOsobe.length; i++) {
                    nazivVirusa.prelazakZarazeNaOsobu(kontaktiraneOsobe[i]);
                }
            }
        }
    }

    public static class Builder {
        private String ime;
        private String prezime;
        private Integer starost;
        private Zupanija zupanija;
        private Bolest zarazenBolescu;
        private Osoba[] kontaktiraneOsobe;

        public Builder() {
        }

        public Builder seZove(String ime) {
            this.ime = ime;
            return this;
        }

        public Builder sePreziva(String prezime) {
            this.prezime = prezime;
            return this;
        }

        public Builder imaGodina(Integer starost) {
            this.starost = starost;
            return this;
        }

        public Builder pripadaZupaniji(Zupanija zupanija) {
            this.zupanija = zupanija;
            return this;
        }

        public Builder imaBolest(Bolest zarazenBolescu) {
            this.zarazenBolescu = zarazenBolescu;
            return this;
        }

        public Builder kontaktiraneOsobe(Osoba[] kontaktiraneOsobe) {
            this.kontaktiraneOsobe = kontaktiraneOsobe;
            return this;
        }

        public Osoba build() {
            return new Osoba(ime, prezime, starost, zupanija, zarazenBolescu, kontaktiraneOsobe);
        }
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Integer getStarost() {
        return starost;
    }

    public void setStarost(Integer starost) {
        this.starost = starost;
    }

    public Zupanija getZupanija() {
        return zupanija;
    }

    public void setZupanija(Zupanija zupanija) {
        this.zupanija = zupanija;
    }

    public Bolest getZarazenBolescu() {
        return zarazenBolescu;
    }

    public void setZarazenBolescu(Bolest zarazenBolescu) {
        this.zarazenBolescu = zarazenBolescu;
    }

    public Osoba[] getKontaktiraneOsobe() {
        return kontaktiraneOsobe;
    }

    public void setKontaktiraneOsobe(Osoba[] kontaktiraneOsobe) {
        this.kontaktiraneOsobe = kontaktiraneOsobe;
    }
}

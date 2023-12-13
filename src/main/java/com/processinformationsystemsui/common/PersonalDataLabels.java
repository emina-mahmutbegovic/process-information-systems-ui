package com.processinformationsystemsui.common;

import javax.swing.*;

public class PersonalDataLabels {
    private JLabel ime;
    private JLabel prezime;
    private JLabel kontaktTelefon;
    private JLabel biografija;

    public PersonalDataLabels() {
        this.ime = new JLabel();
        this.prezime = new JLabel();
        this.kontaktTelefon = new JLabel();
        this.biografija = new JLabel();
    }

    public JLabel getIme() {
        return ime;
    }

    public void setIme(JLabel ime) {
        this.ime = ime;
    }

    public JLabel getPrezime() {
        return prezime;
    }

    public void setPrezime(JLabel prezime) {
        this.prezime = prezime;
    }

    public JLabel getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(JLabel kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    public JLabel getBiografija() {
        return biografija;
    }

    public void setBiografija(JLabel biografija) {
        this.biografija = biografija;
    }
}

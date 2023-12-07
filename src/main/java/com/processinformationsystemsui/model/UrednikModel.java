package com.processinformationsystemsui.model;

public class UrednikModel {
    private String idUrednika;
    private String imeUrednika;
    private String prezimeUrednika;
    private String kontaktTelefonUrednika;

    public UrednikModel(String idUrednika,
                        String imeUrednika,
                        String prezimeUrednika,
                        String kontaktTelefonUrednika) {
        this.idUrednika = idUrednika;
        this.imeUrednika = imeUrednika;
        this.prezimeUrednika = prezimeUrednika;
        this.kontaktTelefonUrednika = kontaktTelefonUrednika;
    }

    public UrednikModel() {}

    public String getIdUrednika() {
        return idUrednika;
    }

    public void setIdUrednika(String idUrednika) {
        this.idUrednika = idUrednika;
    }

    public String getImeUrednika() {
        return imeUrednika;
    }

    public void setImeUrednika(String imeUrednika) {
        this.imeUrednika = imeUrednika;
    }

    public String getPrezimeUrednika() {
        return prezimeUrednika;
    }

    public void setPrezimeUrednika(String prezimeUrednika) {
        this.prezimeUrednika = prezimeUrednika;
    }

    public String getKontaktTelefonUrednika() {
        return kontaktTelefonUrednika;
    }

    public void setKontaktTelefonUrednika(String kontaktTelefonUrednika) {
        this.kontaktTelefonUrednika = kontaktTelefonUrednika;
    }
}

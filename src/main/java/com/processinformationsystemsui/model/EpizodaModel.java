package com.processinformationsystemsui.model;

public class EpizodaModel {
    private String idEpizode;
    private String nazivEpizode;
    private int brojEpizode;
    private int brojSezone;
    private String opisEpizode;
    private EmisijaModel emisija;

    public EpizodaModel(String idEpizode,
                   String nazivEpizode,
                   int brojEpizode,
                   int brojSezone,
                   String opisEpizode,
                   EmisijaModel emisija){
        this.idEpizode = idEpizode;
        this.nazivEpizode = nazivEpizode;
        this.brojEpizode = brojEpizode;
        this.brojSezone = brojSezone;
        this.opisEpizode = opisEpizode;
        this.emisija = emisija;
    }

    public EpizodaModel() {}

    public String getIdEpizode() {
        return idEpizode;
    }

    public void setIdEpizode(String idEpizode) {
        this.idEpizode = idEpizode;
    }

    public String getNazivEpizode() {
        return nazivEpizode;
    }

    public void setNazivEpizode(String nazivEpizode) {
        this.nazivEpizode = nazivEpizode;
    }

    public int getBrojEpizode() {
        return brojEpizode;
    }

    public void setBrojEpizode(int brojEpizode) {
        this.brojEpizode = brojEpizode;
    }

    public int getBrojSezone() {
        return brojSezone;
    }

    public void setBrojSezone(int brojSezone) {
        this.brojSezone = brojSezone;
    }

    public String getOpisEpizode() {
        return opisEpizode;
    }

    public void setOpisEpizode(String opisEpizode) {
        this.opisEpizode = opisEpizode;
    }

    public EmisijaModel getEmisija() {
        return emisija;
    }

    public void setEmisija(EmisijaModel emisija) {
        this.emisija = emisija;
    }
}

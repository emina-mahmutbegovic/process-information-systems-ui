package com.processinformationsystemsui.panel.Epizoda.Create;

public class CreateEpizodaModel {
    private String nazivEpizode;
    private int brojEpizode;
    private int brojSezone;
    private String opisEpizode;
    private String idEmisije;

    public CreateEpizodaModel(
                        String nazivEpizode,
                        int brojEpizode,
                        int brojSezone,
                        String opisEpizode,
                        String idEmisije) {
        this.nazivEpizode = nazivEpizode;
        this.brojEpizode = brojEpizode;
        this.brojSezone = brojSezone;
        this.opisEpizode = opisEpizode;
        this.idEmisije = idEmisije;
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

    public String getIdEmisije() {
        return idEmisije;
    }

    public void setIdEmisije(String idEmisije) {
        this.idEmisije = idEmisije;
    }
}

package com.processinformationsystemsui.panel.TerminEmitovanja.Create;

import java.sql.Date;
import java.sql.Time;

public class CreateTerminEmitovanjaModel {
    private Time vrijemePocetka;
    private Time vrijemeZavrsetka;
    private Date datumEmitovanja;
    private String idEpizode;

    public CreateTerminEmitovanjaModel(Time vrijemePocetka,
                                 Time vrijemeZavrsetka,
                                 Date datumEmitovanja,
                                 String idEpizode) {
        this.vrijemePocetka = vrijemePocetka;
        this.vrijemeZavrsetka = vrijemeZavrsetka;
        this.datumEmitovanja = datumEmitovanja;
        this.idEpizode = idEpizode;
    }

    public CreateTerminEmitovanjaModel() {}

    public Time getVrijemePocetka() {
        return vrijemePocetka;
    }

    public void setVrijemePocetka(Time vrijemePocetka) {
        this.vrijemePocetka = vrijemePocetka;
    }

    public Time getVrijemeZavrsetka() {
        return vrijemeZavrsetka;
    }

    public void setVrijemeZavrsetka(Time vrijemeZavrsetka) {
        this.vrijemeZavrsetka = vrijemeZavrsetka;
    }

    public Date getDatumEmitovanja() {
        return datumEmitovanja;
    }

    public void setDatumEmitovanja(Date datumEmitovanja) {
        this.datumEmitovanja = datumEmitovanja;
    }

    public String getIdEpizode() {
        return idEpizode;
    }

    public void setIdEpizode(String idEpizode) {
        this.idEpizode = idEpizode;
    }
}

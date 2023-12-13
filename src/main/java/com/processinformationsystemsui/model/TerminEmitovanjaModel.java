package com.processinformationsystemsui.model;

import java.sql.Date;
import java.sql.Time;

public class TerminEmitovanjaModel {
    private Time vrijemePocetka;
    private Time vrijemeZavrsetka;
    private Date datumEmitovanja;
    private EpizodaModel epizoda;

    public TerminEmitovanjaModel(Time vrijemePocetka,
                            Time vrijemeZavrsetka,
                            Date datumEmitovanja,
                            EpizodaModel epizoda) {
        this.vrijemePocetka = vrijemePocetka;
        this.vrijemeZavrsetka = vrijemeZavrsetka;
        this.datumEmitovanja = datumEmitovanja;
        this.epizoda = epizoda;
    }

    public TerminEmitovanjaModel() {}

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

    public EpizodaModel getEpizoda() {
        return epizoda;
    }

    public void setEpizoda(EpizodaModel epizoda) {
        this.epizoda = epizoda;
    }
}

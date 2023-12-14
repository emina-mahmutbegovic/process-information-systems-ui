package com.processinformationsystemsui.panel.Emisija.Create;

import java.util.Set;

public class CreateEmisijaModel {
    private String nazivEmisije;
    private String opisEmisije;
    private int trajanjeEmisije;
    private int ocjenaEmisije;
    private String idVrsteEmisije;
    private String idVoditelja;
    private String idUrednika;
    private Set<String> idGostiju;

    public CreateEmisijaModel(String nazivEmisije,
                        String opisEmisije,
                        int trajanjeEmisije,
                        int ocjenaEmisije,
                        String idVrsteEmisije,
                        String idVoditelja,
                        String idUrednika,
                        Set<String> idGostiju) {
        this.nazivEmisije = nazivEmisije;
        this.opisEmisije = opisEmisije;
        this.trajanjeEmisije = trajanjeEmisije;
        this.ocjenaEmisije = ocjenaEmisije;
        this.idVrsteEmisije = idVrsteEmisije;
        this.idVoditelja = idVoditelja;
        this.idUrednika = idUrednika;
        this.idGostiju = idGostiju;
    }

    public String getNazivEmisije() {
        return nazivEmisije;
    }

    public void setNazivEmisije(String nazivEmisije) {
        this.nazivEmisije = nazivEmisije;
    }

    public String getOpisEmisije() {
        return opisEmisije;
    }

    public void setOpisEmisije(String opisEmisije) {
        this.opisEmisije = opisEmisije;
    }

    public int getTrajanjeEmisije() {
        return trajanjeEmisije;
    }

    public void setTrajanjeEmisije(int trajanjeEmisije) {
        this.trajanjeEmisije = trajanjeEmisije;
    }

    public int getOcjenaEmisije() {
        return ocjenaEmisije;
    }

    public void setOcjenaEmisije(int ocjenaEmisije) {
        this.ocjenaEmisije = ocjenaEmisije;
    }

    public String getIdVrsteEmisije() {
        return idVrsteEmisije;
    }

    public void setIdVrsteEmisije(String idVrsteEmisije) {
        this.idVrsteEmisije = idVrsteEmisije;
    }

    public String getIdVoditelja() {
        return idVoditelja;
    }

    public void setIdVoditelja(String idVoditelja) {
        this.idVoditelja = idVoditelja;
    }

    public String getIdUrednika() {
        return idUrednika;
    }

    public void setIdUrednika(String idUrednika) {
        this.idUrednika = idUrednika;
    }

    public Set<String> getIdGostiju() {
        return idGostiju;
    }

    public void setIdGostiju(Set<String> idGostiju) {
        this.idGostiju = idGostiju;
    }
}

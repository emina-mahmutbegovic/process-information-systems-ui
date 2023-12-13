package com.processinformationsystemsui.model;

import java.util.UUID;

public class VrstaEmisijeModel {
    private String idVrsteEmisije;
    private String nazivVrsteEmisije;

    public VrstaEmisijeModel(String nazivVrsteEmisije, String idVrsteEmisije){
        this.idVrsteEmisije = idVrsteEmisije;
        this.nazivVrsteEmisije = nazivVrsteEmisije;
    }

    public VrstaEmisijeModel() {}

    public String getIdVrsteEmisije() {
        return idVrsteEmisije;
    }

    public void setIdVrsteEmisije(String idVrsteEmisije) {
        this.idVrsteEmisije = idVrsteEmisije;
    }

    public String getNazivVrsteEmisije() {
        return nazivVrsteEmisije;
    }

    public void setNazivVrsteEmisije(String nazivVrsteEmisije) {
        this.nazivVrsteEmisije = nazivVrsteEmisije;
    }
}

package com.processinformationsystemsui.model;

public class GostujeModel {
    private String id_gosta;
    private String id_emisije;

    public GostujeModel(String id_gosta,
                        String id_emisije) {
        this.id_gosta = id_gosta;
        this.id_emisije = id_emisije;
    }

    public GostujeModel() {}

    public String getId_gosta() {
        return id_gosta;
    }

    public void setId_gosta(String id_gosta) {
        this.id_gosta = id_gosta;
    }

    public String getId_emisije() {
        return id_emisije;
    }

    public void setId_emisije(String id_emisije) {
        this.id_emisije = id_emisije;
    }
}

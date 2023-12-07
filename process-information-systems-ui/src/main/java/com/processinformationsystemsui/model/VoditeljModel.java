package com.processinformationsystemsui.model;

public class VoditeljModel {
    private String idVoditelja;
    private String imeVoditelja;
    private String prezimeVoditelja;
    private String kontaktTelefonVoditelja;

    public VoditeljModel(String idVoditelja,
                         String imeVoditelja,
                         String prezimeVoditelja,
                         String kontaktTelefonVoditelja) {
        this.idVoditelja = idVoditelja;
        this.imeVoditelja = imeVoditelja;
        this.prezimeVoditelja = prezimeVoditelja;
        this.kontaktTelefonVoditelja = kontaktTelefonVoditelja;
    }

    public VoditeljModel() {}

    public String getIdVoditelja() {
        return idVoditelja;
    }

    public void setIdVoditelja(String idVoditelja) {
        this.idVoditelja = idVoditelja;
    }

    public String getImeVoditelja() {
        return imeVoditelja;
    }

    public void setImeVoditelja(String imeVoditelja) {
        this.imeVoditelja = imeVoditelja;
    }

    public String getPrezimeVoditelja() {
        return prezimeVoditelja;
    }

    public void setPrezimeVoditelja(String prezimeVoditelja) {
        this.prezimeVoditelja = prezimeVoditelja;
    }

    public String getKontaktTelefonVoditelja() {
        return kontaktTelefonVoditelja;
    }

    public void setKontaktTelefonVoditelja(String kontaktTelefonVoditelja) {
        this.kontaktTelefonVoditelja = kontaktTelefonVoditelja;
    }
}

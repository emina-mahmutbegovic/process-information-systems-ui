package com.processinformationsystemsui.panel.Voditelj.Create;

public class CreateVoditeljModel {
    private String imeVoditelja;
    private String prezimeVoditelja;
    private String kontaktTelefonVoditelja;

    public CreateVoditeljModel(String imeVoditelja,
                         String prezimeVoditelja,
                         String kontaktTelefonVoditelja) {
        this.imeVoditelja = imeVoditelja;
        this.prezimeVoditelja = prezimeVoditelja;
        this.kontaktTelefonVoditelja = kontaktTelefonVoditelja;
    }

    public CreateVoditeljModel() {}

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

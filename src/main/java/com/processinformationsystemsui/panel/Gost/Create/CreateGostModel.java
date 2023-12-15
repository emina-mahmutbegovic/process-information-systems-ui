package com.processinformationsystemsui.panel.Gost.Create;

public class CreateGostModel {
    private String imeGosta;
    private String prezimeGosta;
    private String biografijaGosta;
    private String kontaktTelefonGosta;

    public CreateGostModel(String imeGosta,
                     String prezimeGosta,
                     String biografijaGosta,
                     String kontaktTelefonGosta) {
        this.imeGosta = imeGosta;
        this.prezimeGosta = prezimeGosta;
        this.biografijaGosta = biografijaGosta;
        this.kontaktTelefonGosta = kontaktTelefonGosta;
    }

    public CreateGostModel() {}

    public String getImeGosta() {
        return imeGosta;
    }

    public void setImeGosta(String imeGosta) {
        this.imeGosta = imeGosta;
    }

    public String getPrezimeGosta() {
        return prezimeGosta;
    }

    public void setPrezimeGosta(String prezimeGosta) {
        this.prezimeGosta = prezimeGosta;
    }

    public String getBiografijaGosta() {
        return biografijaGosta;
    }

    public void setBiografijaGosta(String biografijaGosta) {
        this.biografijaGosta = biografijaGosta;
    }

    public String getKontaktTelefonGosta() {
        return kontaktTelefonGosta;
    }

    public void setKontaktTelefonGosta(String kontaktTelefonGosta) {
        this.kontaktTelefonGosta = kontaktTelefonGosta;
    }
}

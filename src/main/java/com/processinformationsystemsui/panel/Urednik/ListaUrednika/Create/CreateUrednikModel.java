package com.processinformationsystemsui.panel.Urednik.ListaUrednika.Create;

public class CreateUrednikModel {
    private String imeUrednika;
    private String prezimeUrednika;
    private String kontaktTelefonUrednika;

    public CreateUrednikModel(String imeUrednika,
                              String prezimeUrednika,
                              String kontaktTelefonUrednika) {
        this.imeUrednika = imeUrednika;
        this.prezimeUrednika = prezimeUrednika;
        this.kontaktTelefonUrednika = kontaktTelefonUrednika;
    }

    public CreateUrednikModel() {}

    public String getImeUrednika() {
        return imeUrednika;
    }

    public void setImeUrednika(String imeUrednika) {
        this.imeUrednika = imeUrednika;
    }

    public String getPrezimeUrednika() {
        return prezimeUrednika;
    }

    public void setPrezimeUrednika(String prezimeUrednika) {
        this.prezimeUrednika = prezimeUrednika;
    }

    public String getKontaktTelefonUrednika() {
        return kontaktTelefonUrednika;
    }

    public void setKontaktTelefonUrednika(String kontaktTelefonUrednika) {
        this.kontaktTelefonUrednika = kontaktTelefonUrednika;
    }
}

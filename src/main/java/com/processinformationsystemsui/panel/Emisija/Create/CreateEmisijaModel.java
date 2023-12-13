package com.processinformationsystemsui.panel.Emisija.Create;

import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.model.VrstaEmisijeModel;

import java.util.Set;

public class CreateEmisijaModel {
    private String nazivEmisije;
    private String opisEmisije;
    private int trajanjeEmisije;
    private int ocjenaEmisije;
    private VrstaEmisijeModel vrstaEmisije;
    private VoditeljModel voditelj;
    private UrednikModel urednik;
    private Set<GostModel> gosti;

    public CreateEmisijaModel(String nazivEmisije,
                        String opisEmisije,
                        int trajanjeEmisije,
                        int ocjenaEmisije,
                        VrstaEmisijeModel vrstaEmisije,
                        VoditeljModel voditelj,
                        UrednikModel urednik,
                        Set<GostModel> gosti) {
        this.nazivEmisije = nazivEmisije;
        this.opisEmisije = opisEmisije;
        this.trajanjeEmisije = trajanjeEmisije;
        this.ocjenaEmisije = ocjenaEmisije;
        this.vrstaEmisije = vrstaEmisije;
        this.voditelj = voditelj;
        this.urednik = urednik;
        this.gosti = gosti;
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

    public VrstaEmisijeModel getVrstaEmisije() {
        return vrstaEmisije;
    }

    public void setVrstaEmisije(VrstaEmisijeModel vrstaEmisije) {
        this.vrstaEmisije = vrstaEmisije;
    }

    public VoditeljModel getVoditelj() {
        return voditelj;
    }

    public void setVoditelj(VoditeljModel voditelj) {
        this.voditelj = voditelj;
    }

    public UrednikModel getUrednik() {
        return urednik;
    }

    public void setUrednik(UrednikModel urednik) {
        this.urednik = urednik;
    }

    public Set<GostModel> getGosti() {
        return gosti;
    }

    public void setGosti(Set<GostModel> gosti) {
        this.gosti = gosti;
    }
}

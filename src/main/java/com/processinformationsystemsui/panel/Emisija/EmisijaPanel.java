package com.processinformationsystemsui.panel.Emisija;

import com.processinformationsystemsui.common.dialog.message.ErrorMessageDialog;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;
import com.processinformationsystemsui.model.*;
import com.processinformationsystemsui.panel.Emisija.Create.CreateEmisijaPanel;
import com.processinformationsystemsui.common.Common;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EmisijaPanel extends CreateEmisijaPanel {
    // Util
    private final InformationMessageDialog informationMessageDialog = new InformationMessageDialog(EmisijaPanel.this);

    public EmisijaPanel (EmisijaModel emisija) throws IOException {
        super(emisija);

        this.emisija = emisija;
        this.selectedUrednik = emisija.getUrednik();
        this.selectedVoditelj = emisija.getVoditelj();
        this.selectedVrstaEmisije = emisija.getVrstaEmisije();

        addDeleteButton();
    }

    private void addDeleteButton() {

        JButton deleteButton = new JButton("DELETE");
        deleteButton.setForeground(Color.RED);

        // Add ActionListener for the Delete button
        deleteButton.addActionListener(e -> {
            // Add logic for deleting the Emisija
            try {
                emisijaApiResources.deleteEmisija(emisija.getIdEmisije());

                informationMessageDialog.showMessage(String.format("Emisija: %s uspjesno obrisana!", emisija.getNazivEmisije()));

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                parentFrame.dispose();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Common.addMouseListener(deleteButton);

        buttonsPanel.add(deleteButton);
    }

    @Override
    protected void onSaveButtonClicked() throws IOException {
        emisijaApiResources.updateEmisija(emisija, emisija.getIdEmisije());
    }

    @Override
    protected void setVoditeljInfo() {
        voditeljLabelsData.getIme().setText(emisija.getVoditelj().getImeVoditelja());
        voditeljLabelsData.getPrezime().setText(emisija.getVoditelj().getPrezimeVoditelja());
        voditeljLabelsData.getKontaktTelefon().setText(emisija.getVoditelj().getKontaktTelefonVoditelja());
    }

    @Override
    protected void setUrednikInfo() {
        urednikLabelsData.getIme().setText(emisija.getUrednik().getImeUrednika());
        urednikLabelsData.getPrezime().setText(emisija.getUrednik().getPrezimeUrednika());
        urednikLabelsData.getKontaktTelefon().setText(emisija.getUrednik().getKontaktTelefonUrednika());
    }

    @Override
    protected void setVrstaEmisijeInfo() {
        vrstaEmisijeLabel.setText(emisija.getVrstaEmisije().getNazivVrsteEmisije());
    }

    @Override
    protected void setTrajanjeEmisijeInfo() {
        String.format("%s minuta", emisija.getTrajanjeEmisije());
    }

    @Override
    protected void setOcjenaEmisijeInfo() {
        ocjenaEmisije.setText(String.valueOf(emisija.getOcjenaEmisije()));
    }
}

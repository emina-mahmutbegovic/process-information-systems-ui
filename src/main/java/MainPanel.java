import com.processinformationsystemsui.panel.Emisija.ListaEmisija.ListaEmisija;
import com.processinformationsystemsui.util.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainPanel extends JPanel {
    private static final String monacoFont  = "Monaco";
    public MainPanel () {
        // Define content pane layout
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // Add title label to the top of the content pane
        JLabel titleLabel = new JLabel("Dobro došli u aplikaciju za upravljanje i nadzor TV programa", SwingConstants.CENTER);
        titleLabel.setFont(new Font(monacoFont, Font.BOLD, 18));
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0,0, gbc);

        // Add buttons to the center of the content pane
        JButton createNewShow = new JButton("Kreiraj novu emisiju");
        createNewShow.setFont(new Font(monacoFont, Font.BOLD, 16));
        createNewShow.setHorizontalAlignment(SwingConstants.CENTER);
        createNewShow.setVerticalAlignment(SwingConstants.CENTER);

        JButton getAllShows = new JButton("Prikaži sve emisije");
        getAllShows.setFont(new Font(monacoFont, Font.BOLD, 16));
        getAllShows.setHorizontalAlignment(SwingConstants.CENTER);
        getAllShows.setVerticalAlignment(SwingConstants.CENTER);

        gbc.insets = new Insets(100, 5, 2, 5); // Add some padding

        gbc.gridwidth = 2; // Make the button span across two columns
        gbc.gridy = 2;

        addComponent(createNewShow, 0, 4, gbc);
        addComponent(getAllShows, 0, 6, gbc);

        Common.addMouseListener(getAllShows);

        // Button click actions
        getAllShows.addActionListener(e -> {
            try {
                new ListaEmisija();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Common.addMouseListener(createNewShow);

        createNewShow.addActionListener(e -> JOptionPane.showMessageDialog(MainPanel.this, "Create New Clicked!"));
    }

    private void addComponent(Component component, int gridx, int gridy, GridBagConstraints gbc) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        add(component, gbc);
    }
}

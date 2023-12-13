import com.processinformationsystemsui.panel.Emisija.ListaEmisija.ListaEmisija;
import com.processinformationsystemsui.common.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainPanel extends JPanel {
    private static final String monacoFont  = "Monaco";
    private final JMenuBar menuBar;

    public MainPanel () {
        // Define content pane layout
        setLayout(new GridLayout(2,1));

        Font headingFont = new Font(monacoFont, Font.BOLD, 18);

        // Add title to the top of the content pane
        JLabel titleLabel = new JLabel("Dobro došli u aplikaciju za upravljanje i nadzor TV programa", SwingConstants.CENTER);
        titleLabel.setFont(headingFont);

        JLabel titleLabel1 = new JLabel("Molimo Vas da odaberete neku od opcija iz menija", SwingConstants.CENTER);
        titleLabel1.setFont(headingFont);

        add(titleLabel);
        add(titleLabel1);

        // Create a menu bar
        menuBar = new JMenuBar();

        Font menuItemFont = new Font(monacoFont, Font.BOLD, 16);
        // Create menu items
        JMenuItem emisijeMenuItem = new JMenuItem("Emisije");
        emisijeMenuItem.setFont(menuItemFont);
        emisijeMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JMenuItem voditeljiMenuItem = new JMenuItem("Voditelji");
        voditeljiMenuItem.setFont(menuItemFont);
        voditeljiMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JMenuItem uredniciMenuItem = new JMenuItem("Urednici");
        uredniciMenuItem.setFont(menuItemFont);
        uredniciMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JMenuItem gostiMenuItem = new JMenuItem("Gosti");
        gostiMenuItem.setFont(menuItemFont);
        gostiMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add action listeners to menu items
        emisijeMenuItem.addActionListener(e -> {
            try {
                new ListaEmisija();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        voditeljiMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Voditelji clicked"));

        uredniciMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Urednici clicked"));

        gostiMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Gosti clicked"));

        // Create menus
        JMenu mainMenu = new JMenu("Menu");
        mainMenu.add(emisijeMenuItem);
        mainMenu.add(voditeljiMenuItem);
        mainMenu.add(uredniciMenuItem);
        mainMenu.add(gostiMenuItem);

        mainMenu.setFont(headingFont);
        mainMenu.setForeground(Color.BLUE);
        mainMenu.setBackground(Color.YELLOW);

        mainMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add menus to the menu bar
        menuBar.add(mainMenu);

        add(menuBar);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}

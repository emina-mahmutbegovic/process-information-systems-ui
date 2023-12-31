import com.processinformationsystemsui.common.menu.BaseMenuItem;
import com.processinformationsystemsui.panel.Emisija.ListaEmisija.ListaEmisija;
import com.processinformationsystemsui.panel.Gost.ListaGostiju.Edit.ListaGostijuEdit;
import com.processinformationsystemsui.panel.Urednik.ListaUrednika.Edit.ListaUrednikaEdit;
import com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.Edit.ListaVoditeljaEdit;
import com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.Edit.ListaVrstaEmisijaEdit;

import javax.swing.*;
import java.awt.*;
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

        // Create open menu item actions
        Runnable openEmisijaAction = () -> {
            try {
                new ListaEmisija();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };

        Runnable openVrsteEmisijaAction = () -> {
            try {
                new ListaVrstaEmisijaEdit();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };

        Runnable openVoditeljiAction = () -> {
            try {
                new ListaVoditeljaEdit();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };

        Runnable openUredniciAction = () -> {
            try {
                new ListaUrednikaEdit();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };

        Runnable openGostiAction = () -> {
            try {
                new ListaGostijuEdit();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };

        // Create menu items
        JMenuItem emisijeMenuItem = new BaseMenuItem("Emisije", openEmisijaAction);

        JMenuItem vrsteEmisijaMenuItem = new BaseMenuItem("Vrste emisija", openVrsteEmisijaAction);

        JMenuItem voditeljiMenuItem = new BaseMenuItem("Voditelji", openVoditeljiAction);

        JMenuItem uredniciMenuItem = new BaseMenuItem("Urednici", openUredniciAction);

        JMenuItem gostiMenuItem = new BaseMenuItem("Gosti", openGostiAction);

        // Create menus
        JMenu mainMenu = new JMenu("Menu");
        mainMenu.add(emisijeMenuItem);
        mainMenu.add(vrsteEmisijaMenuItem);
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

import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        // Create an instance of Main panel
        MainPanel mainPanel = new MainPanel();

        // Set the Main panel as the content pane
        setContentPane(mainPanel);

        // Define frame layout
        setTitle("TV Program");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}


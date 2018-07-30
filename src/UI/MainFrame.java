package UI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    MainPanel neuralPanel;

    public MainFrame(String title) throws HeadlessException {
        super(title);

        setSize(new Dimension(1280,800));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

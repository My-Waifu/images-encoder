package fr.skah.imageencoder.swing;

/*
 *  * @Created on 2021 - 18:22
 *  * @Project ImageEncoder
 *  * @Author jimmy  / vSKAH#0075
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final MainFrame mainPanel;

    public MainFrame() throws HeadlessException {
        mainPanel = this;
        setTitle("Choose Type : ");
        setSize(400, 75);
        setResizable(false);
        setUndecorated(false);
        setLocationRelativeTo(null);
        setContentPane(new MainPanel(this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public MainFrame getMainPanel() {
        return mainPanel;
    }
}

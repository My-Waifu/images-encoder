package fr.skah.imageencoder.swing.encode;

/*
 *  * @Created on 2021 - 18:41
 *  * @Project ImageEncoder
 *  * @Author jimmy  / vSKAH#0075
 */

import javax.swing.*;
import java.awt.*;

public class EncodeFrame extends JFrame {

    private static EncodeFrame mainPanel;

    public EncodeFrame() throws HeadlessException {
        mainPanel = this;
        setTitle("Encode : ");
        setSize(400, 180);
        setResizable(false);
        setUndecorated(false);
        setContentPane(new EncodePanel());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static EncodeFrame getEncodeFrame() {
        return mainPanel;
    }
}

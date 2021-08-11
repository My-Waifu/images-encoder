package fr.skah.imageencoder.swing.decode;

/*
 *  * @Created on 2021 - 16:32
 *  * @Project ImageEncoder
 *  * @Author jimmy  / vSKAH#0075
 */

import javax.swing.*;
import java.awt.*;

public class DecodeFrame extends JFrame {

    private static DecodeFrame decodeFrame;
    private final ImagePanel imagePanel = new ImagePanel();

    public DecodeFrame() throws HeadlessException {
        decodeFrame = this;
        setTitle("Decode : ");
        setSize(800, 800);
        setResizable(false);
        setUndecorated(false);
        getContentPane().add(new DecodePanel(),  BorderLayout.SOUTH);
        getContentPane().add(imagePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static DecodeFrame getDecodeFrame() {
        return decodeFrame;
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }
}

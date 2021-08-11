package fr.skah.imageencoder.swing.decode;

/*
 *  * @Created on 2021 - 17:03
 *  * @Project ImageEncoder
 *  * @Author jimmy  / vSKAH#0075
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private BufferedImage bufferedImage;

    public ImagePanel() {
        setLayout(null);
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bufferedImage != null) {
            g.drawImage(bufferedImage, 0, 0, null);
        }
    }
}

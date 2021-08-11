package fr.skah.imageencoder;

/*
 *  * @Created on 2021 - 18:19
 *  * @Project ImageEncoder
 *  * @Author jimmy  / vSKAH#0075
 */

import fr.skah.imageencoder.mysql.ConnectionManager;
import fr.skah.imageencoder.swing.MainFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

public class ImagesEncoder {

    private static ImagesEncoder instance;

    public static void main(String[] args) {
        instance = new ImagesEncoder();
        new ConnectionManager();
        new MainFrame();
    }

    public byte[] encodeImage(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public BufferedImage drawImageFromBytes(byte[] bytes) throws IOException {
        InputStream is = new ByteArrayInputStream(bytes);
        return ImageIO.read(is);
    }

    public static ImagesEncoder getInstance() {
        return instance;
    }
}

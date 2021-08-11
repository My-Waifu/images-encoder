package fr.skah.imageencoder.swing.decode;

/*
 *  * @Created on 2021 - 16:33
 *  * @Project ImageEncoder
 *  * @Author jimmy  / vSKAH#0075
 */

import fr.skah.imageencoder.ImagesEncoder;
import fr.skah.imageencoder.mysql.ConnectionManager;
import fr.skah.imageencoder.mysql.ImagesProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DecodePanel extends JPanel implements ActionListener {

    private JTextField idTextField;
    private JButton decodeButton;

    public DecodePanel() {
        addButtons();
        addTextFields();
    }

    public void addTextFields() {
        idTextField = new JTextField("id");
        add(idTextField);
    }

    public void addButtons() {
        decodeButton = new JButton("Decode !");
        decodeButton.addActionListener(this);
        add(decodeButton);
    }


    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == decodeButton && !idTextField.getText().isEmpty()) {
            try {
                ConnectionManager.getInstance().connect();
                int id = Integer.parseInt(idTextField.getText());
                BufferedImage bufferedImage = ImagesEncoder.getInstance().drawImageFromBytes(new ImagesProvider().getImage(id));
                DecodeFrame.getDecodeFrame().getImagePanel().setBufferedImage(bufferedImage);
                DecodeFrame.getDecodeFrame().setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
                ConnectionManager.getInstance().disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

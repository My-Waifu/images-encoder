package fr.skah.imageencoder.swing;

/*
 *  * @Created on 2021 - 18:31
 *  * @Project ImageEncoder
 *  * @Author jimmy  / vSKAH#0075
 */

import fr.skah.imageencoder.swing.decode.DecodeFrame;
import fr.skah.imageencoder.swing.encode.EncodeFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {

    private JButton encode;
    private JButton decode;

    private final MainPanel mainPanel;
    private final MainFrame mainFrame;

    public MainPanel(MainFrame mainFrame) {
        mainPanel = this;
        this.mainFrame = mainFrame;
        addButtons();
    }


    public void addButtons() {
        encode = new JButton("Encode");
        decode = new JButton("Decode");
        encode.addActionListener(this);
        decode.addActionListener(this);
        getMainPanel().add(encode);
        getMainPanel().add(decode);
    }


    public MainPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        setVisible(false);
        mainFrame.setVisible(false);

        if (event.getSource() == encode) {
            new EncodeFrame();
        }
        if(event.getSource() == decode) {
            new DecodeFrame();
        }
    }
}

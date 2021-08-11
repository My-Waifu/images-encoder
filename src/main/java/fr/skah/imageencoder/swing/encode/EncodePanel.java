package fr.skah.imageencoder.swing.encode;

/*
 *  * @Created on 2021 - 18:41
 *  * @Project ImageEncoder
 *  * @Author jimmy  / vSKAH#0075
 */

import fr.skah.imageencoder.ImagesEncoder;
import fr.skah.imageencoder.mysql.ConnectionManager;
import fr.skah.imageencoder.mysql.ImagesProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EncodePanel extends JPanel implements ActionListener {

    private JTextField pathTextField, tagTextField;
    private JButton encodeButton, folderExplorerButton;
    private final JFileChooser folderChooser = new JFileChooser();
    private int option;
    private File folder;


    public EncodePanel() {
        setLayout(null);
        folderChooser.setMultiSelectionEnabled(false);
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        addLabels();
        addTextFields();
        addButtons();
    }

    public void addLabels() {
        final JLabel pathLabel = new JLabel("Chemin d'accès :");
        pathLabel.setBounds(20, 20, 138, 25);
        add(pathLabel);

        final JLabel tagLabel = new JLabel("Tag :");
        tagLabel.setBounds(20, 55, 60, 25);
        add(tagLabel);
    }

    public void addTextFields() {
        pathTextField = new JTextField();
        pathTextField.setBounds(145, 20, 180, 25);
        add(pathTextField);

        tagTextField = new JTextField();
        tagTextField.setBounds(75, 55, 200, 25);
        add(tagTextField);
    }

    public void addButtons() {
        encodeButton = new JButton("Encode !");
        encodeButton.setBounds(153, 100, 95, 25);
        encodeButton.addActionListener(this);
        add(encodeButton);

        folderExplorerButton = new JButton("...");
        folderExplorerButton.setBounds(340, 19, 40, 25);
        folderExplorerButton.addActionListener(this);
        add(folderExplorerButton);


    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == folderExplorerButton) {
            option = folderChooser.showDialog(EncodeFrame.getEncodeFrame(), "Encoder");
        }

        if (option == JFileChooser.APPROVE_OPTION) {
            folder = folderChooser.getSelectedFile();
            if (folder != null) pathTextField.setText(folder.getPath());
        }

        if (event.getSource() == encodeButton) {
            if (pathTextField.getText().isEmpty() || tagTextField.getText().isEmpty()) return;

            changeStates(false);

            ConnectionManager.getInstance().connect();
            final ImagesProvider imagesProvider = new ImagesProvider();
            for (File file : folder.listFiles()) {


                byte[] bytes = ImagesEncoder.getInstance().encodeImage(file);
                if (!imagesProvider.hasImage(bytes)) {
                    System.out.println(bytes.length);
                    System.out.println(file.getParentFile().getName() + " a été ajouté dans la db");
                    imagesProvider.addImage(file.getParentFile().getName(), tagTextField.getText(), bytes);
                }
            }

            ConnectionManager.getInstance().disconnect();
            changeStates(true);
        }

    }

    public void changeStates(boolean value) {
        pathTextField.setEnabled(value);
        tagTextField.setEnabled(value);
        encodeButton.setEnabled(value);
        folderExplorerButton.setEnabled(value);
    }
}

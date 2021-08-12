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
import java.util.Objects;

public class EncodePanel extends JPanel implements ActionListener {

    private JTextField pathTextField, tagTextField;
    private JButton folderExplorerButton, encodeButton;
    private final JCheckBox nsfwCheckBox = new JCheckBox("NSFW", false);
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

        final JLabel nsfwLabel = new JLabel("NSFW :");
        nsfwLabel.setBounds(20, 100, 60, 25);
        add(nsfwLabel);
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
        folderExplorerButton = new JButton("...");
        folderExplorerButton.setBounds(340, 19, 40, 25);
        folderExplorerButton.addActionListener(this);
        add(folderExplorerButton);

        nsfwCheckBox.setBounds(75, 100, 20, 20);
        add(nsfwCheckBox);

        encodeButton = new JButton("Encode !");
        encodeButton.setBounds(153, 100, 95, 25);
        encodeButton.addActionListener(this);
        add(encodeButton);
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
            for (File file : Objects.requireNonNull(folder.listFiles())) {

                byte[] bytes = ImagesEncoder.getInstance().encodeImage(file);
                if (!imagesProvider.hasImage(bytes)) {
                    System.out.println(bytes.length);
                    System.out.println(file.getParentFile().getName() + " a été ajouté dans la db");
                    imagesProvider.addImage(getFileExtension(file), file.getParentFile().getName(), tagTextField.getText(), nsfwCheckBox.isSelected(), bytes);
                }
            }

            ConnectionManager.getInstance().disconnect();
            changeStates(true);
        }

    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) return "";
        return name.substring(lastIndexOf);
    }

    private void changeStates(boolean value) {
        pathTextField.setEnabled(value);
        tagTextField.setEnabled(value);
        encodeButton.setEnabled(value);
        folderExplorerButton.setEnabled(value);
    }
}

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

public class SaveButton extends Save{
    String[] filePath = new String[1];
    SaveButton(JTextArea textArea, JMenu menu, String[] filePath){
        super(textArea, menu, filePath);
        JMenuItem saveButton = new JMenuItem("Kaydet");
        saveButton.setFont(new Font("Roboto", Font.PLAIN, 12));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filePath[0] == null){
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(".txt", "txt");
                    fileChooser.addChoosableFileFilter(txtFilter);
                    int returnValue = fileChooser.showSaveDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        try {
                            FileWriter writer = new FileWriter(selectedFile, false);
                            writer.write(textArea.getText());
                            writer.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                else {
                    File file = new File(filePath[0]);
                    try {
                        FileWriter writer = new FileWriter(file, false);
                        writer.write(textArea.getText());
                        writer.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        menu.add(saveButton);
    }
}

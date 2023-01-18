import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

public class Save {
    String[] filePath = new String[1];

    public static class SaveBuilder {
        private JTextArea textArea;
        private JMenu menu;
        private String[] filePath;

        public static SaveBuilder newInstance(){
            return new SaveBuilder();
        }

        private SaveBuilder(){};

        public SaveBuilder setTextArea(JTextArea textArea) {
            this.textArea = textArea;
            return this;
        }

        public SaveBuilder setMenu(JMenu menu) {
            this.menu = menu;
            return this;
        }

        public SaveBuilder setFilePath(String[] filePath) {
            this.filePath = filePath;
            return this;
        }

        public Save build(){
            return new Save(this.textArea, this.menu, this.filePath);
        }
    }


    Save(JTextArea textArea, JMenu menu, String[] filePath){
        this.filePath = filePath;
        JMenuItem save = new JMenuItem("Farklı Kaydet");
        save.setFont(new Font("Roboto", Font.PLAIN, 12));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });
        menu.add(save);
    }
}

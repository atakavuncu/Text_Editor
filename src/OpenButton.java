import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

// Singleton Design Pattern kullanılmıştır.
public class OpenButton {
    private static OpenButton instance;
    public static String[] filePath;
    public static JTextArea textArea;
    private static JMenu menu;

    public JMenuItem openButton;

    public OpenButton(JTextArea textArea, JMenu menu, String[] filePath){
        this.textArea = textArea;
        this.menu = menu;
        this.filePath = filePath;
        openButton = new JMenuItem("Aç");
        openButton.setFont(new Font("Roboto", Font.PLAIN, 12));
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(".txt", "txt");
                fileChooser.addChoosableFileFilter(txtFilter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) { // Tamam ' a basılıp basılmadığının kontrolü. Basılırsa returnValue= JFileChooser.APPROVE_OPTION olur.
                    File selectedFile = fileChooser.getSelectedFile();
                    open(restoreMemento(new Memento(selectedFile.getPath()))[0]);
                    try {
                        Scanner fileScanner = new Scanner(selectedFile);
                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            textArea.append(line + "\n");
                        }
                        fileScanner.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        menu.add(openButton);

    }

    public void open(String filePath) {
        this.filePath[0] = filePath;
        // Açılan dosyanın içeriği JTextArea nesnesine yüklenir.
    }

    public String[] restoreMemento(Memento memento) {
        return memento.getFilePath();
    }

    public static synchronized OpenButton getInstance(JTextArea textArea, JMenu menu, String[] filePath) {
        if (instance == null) {
            instance = new OpenButton(textArea, menu, filePath);
        }
        return instance;
    }
}


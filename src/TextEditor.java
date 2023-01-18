import javax.swing.*;
import java.awt.*;

public class TextEditor {
    public static void main(String[] args) {
        final String[] filePath = new String[1];
        JFrame frame = new JFrame("TextEditor");
        frame.setSize(800, 400);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        menuBar.setBackground(Color.white);

        JMenu menu0 = new JMenu("Dosya");
        Font font = new Font("Roboto", Font.PLAIN, 12);
        menu0.setFont(font);
        menuBar.add(menu0);

        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(8,8,8,8));
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);

        // "Aç" Butonu
        OpenButton openButton = OpenButton.getInstance(textArea, menu0, filePath);

        // "Kaydet" Butonu
        SaveButton saveButton = new SaveButton(textArea, menu0, filePath);

        // "Farklı Kaydet" Butonu
        Save.SaveBuilder saveAsButton;
        saveAsButton = Save.SaveBuilder.newInstance().setTextArea(textArea)
                                                     .setMenu(menu0)
                                                     .setFilePath(filePath);

        // "Kapat" Butonu
        CloseButton closeButton = new CloseButton(menu0);

        JMenu menu1 = new JMenu("Düzenle");
        menu1.setFont(font);
        menuBar.add(menu1);

        // "Bul" Butonu
        FindButton findButton = new FindButton(textArea, menu1, frame);

        // "Geri Al" Butonu
        UndoButton undoButton = new UndoButton(textArea, menu1);

        frame.setVisible(true);
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class FindButton{

    FindButton(JTextArea textArea, JMenu menu, JFrame frame) {
        JMenuItem findButton = new JMenuItem("Bul");
        findButton.setFont(new Font("Roboto", Font.PLAIN, 12));

        FindCommand findCommand = new FindCommand(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog(frame, "Aranacak Kelime:");
                String text = textArea.getText().toLowerCase(Locale.ROOT);
                int index = text.indexOf(searchTerm.toLowerCase());
                if (index != -1) {
                    textArea.setSelectionStart(index);
                    textArea.setSelectionEnd(index + searchTerm.length());
                } else {
                    JOptionPane.showMessageDialog(frame, "Bulunamadı");
                }
            }
        });

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findCommand.execute();
            }
        });
        menu.add(findButton);
    }
}

class FindCommand implements Command {
    private ActionListener action;

    public FindCommand(ActionListener action) {
        this.action = action;
    }

    @Override
    public void execute() {
        action.actionPerformed(null);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseButton {
    CloseButton(JMenu menu){
        JMenuItem closeButton = new JMenuItem("Kapat");
        closeButton.setFont(new Font("Roboto", Font.PLAIN, 12));

        CloseCommand closeCommand = new CloseCommand(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCommand.execute();
            }
        });
        menu.add(closeButton);
    }
    }



class CloseCommand implements Command {
    private ActionListener action;

    public CloseCommand(ActionListener action) {
        this.action = action;
    }

    @Override
    public void execute() {
        action.actionPerformed(null);
    }
}
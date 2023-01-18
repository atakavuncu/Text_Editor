import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoButton {
    UndoButton(JTextArea textArea, JMenu menu){
        UndoManager undoManager = new UndoManager();
        textArea.getDocument().addUndoableEditListener(undoManager);

        JMenuItem undoButton = new JMenuItem("Geri Al");
        undoButton.setFont(new Font("Roboto", Font.PLAIN, 12));

        UndoCommand undoCommand = new UndoCommand(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoCommand.execute();
            }
        });
        menu.add(undoButton);
    }
}

class UndoCommand implements Command {
    private ActionListener action;

    public UndoCommand(ActionListener action) {
        this.action = action;
    }

    @Override
    public void execute() {
        action.actionPerformed(null);
    }
}
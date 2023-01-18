import static org.junit.Assert.*;
import org.junit.Test;

import javax.swing.*;

public class OpenButtonTest{
    @Test
    public void testSingletonOpenButton(){
        OpenButton openButton1 = OpenButton.getInstance(new JTextArea(), new JMenu(), new String[1]);
        OpenButton openButton2 = OpenButton.getInstance(new JTextArea(), new JMenu(), new String[1]);
        assertSame(openButton1, openButton2);
    }
}

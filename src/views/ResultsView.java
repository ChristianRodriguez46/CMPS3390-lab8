package views;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResultsView extends JFrame{
    private JPanel panelMain;
    private JPanel panelNorth;
    private JPanel panelSouth;
    private JLabel labelResults;
    private JButton buttonOK;
    private JScrollPane scrollResult;
    private JEditorPane editorResult;

    public ResultsView(String title, String html) {
        setContentPane(panelMain);
        setTitle(title + " Results");
        setSize(500, 400);

        editorResult.setText(html);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void attachOKListener(ActionListener listener){
        buttonOK.addActionListener(listener);
    }
}

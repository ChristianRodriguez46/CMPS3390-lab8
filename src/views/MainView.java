package views;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JTabbedPane tabsMain;
    private JPanel panelMain;
    private JPanel panelSearch;
    private JPanel panelFindReplace;
    private JPanel panelSearchSouth;
    private JPanel panelSearchNorth;
    private JTextArea textAreaSearch;
    private JScrollPane scrollSearch;
    private JTextField textFieldSearch;
    private JButton buttonSearch;
    private JLabel labelSearch;

    public MainView() {
        setContentPane(panelMain);
        setTitle("Text Tools");
        setSize(500, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getSearchTerm(){
        return textFieldSearch.getText();
    }

    public String getSearchText(){
        return textAreaSearch.getText();
    }

    public void displayError(String error){
        JOptionPane.showMessageDialog(this, error);
    }

    public void attachSearchListener(ActionListener listener){
        buttonSearch.addActionListener(listener);
    }
}

package ch.lebois.troyclient.chat;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;

/**
 * Project: Hermann
 * Package: ch.lebois.troyclient.functions
 **/
public class Chat extends JDialog {

    private static Chat instance;
    protected DefaultListModel<String> listModel;
    private JList<String> list;

    private Chat() {
        setAlwaysOnTop(true);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setVisible(true);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);


        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);

        add(list, BorderLayout.NORTH);
        add(new SenderField(), BorderLayout.SOUTH);
        pack();
    }


    public static Chat getInstance() {
        if (instance == null) {
            instance = new Chat();
        }
        return instance;
    }

    public void addMessage(String message) {
        listModel.addElement(message);
        setVisible(true);
        pack();
    }
}

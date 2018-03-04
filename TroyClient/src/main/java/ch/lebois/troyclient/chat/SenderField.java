package ch.lebois.troyclient.chat;

import ch.lebois.troyclient.main.GetContext;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JPanel;

/**
 * Project: Hermann
 **/
public class SenderField extends JPanel {

    public SenderField() {
        setLayout(new BorderLayout());
        TextField textField = new TextField("");
        textField.setColumns(30);
        Button button = new Button("Send");
        button.setSize(100, 20);

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();

                if (!text.equals("")) {
                    Chat.getInstance().addMessage("me: " + text);
                    GetContext.SENDER.send("message", "CHAT - client: " + text);
                }
                textField.setText("");
                textField.requestFocus();
            }
        });

        add(textField, BorderLayout.WEST);
        add(button, BorderLayout.EAST);
    }
}

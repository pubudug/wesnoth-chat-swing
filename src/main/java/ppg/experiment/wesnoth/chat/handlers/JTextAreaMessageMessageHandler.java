package ppg.experiment.wesnoth.chat.handlers;

import javax.swing.JTextArea;

import ppg.experiment.wesnoth.chat.handler.MessageMessageHandler;

public class JTextAreaMessageMessageHandler extends MessageMessageHandler {

    private JTextArea textArea;

    public JTextAreaMessageMessageHandler(JTextArea textArea) {
        super();
        this.textArea = textArea;
    }

    @Override
    public void showMessage(String sender, String message) {
        textArea.append(sender + " : " + message + "\n");
    }

}

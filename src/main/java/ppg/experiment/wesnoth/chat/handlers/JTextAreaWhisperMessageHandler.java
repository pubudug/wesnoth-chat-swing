package ppg.experiment.wesnoth.chat.handlers;

import javax.swing.JTextArea;

import ppg.experiment.wesnoth.chat.WhisperMessageHandler;

public class JTextAreaWhisperMessageHandler extends WhisperMessageHandler {

    private JTextArea textArea;

    public JTextAreaWhisperMessageHandler(JTextArea textArea) {
        super();
        this.textArea = textArea;
    }

    @Override
    public void showWhisperedMessage(String sender, String message) {
        textArea.append(sender + "(whispered) : " + message + "\n");
    }

}

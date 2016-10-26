package ppg.experiment.wesnoth.chat.handlers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ppg.experiment.wesnoth.chat.MustLoginRequestHandler;

public class PasswordDialogMustloginRequestHandler
        extends MustLoginRequestHandler {
    private JFrame frame;

    public PasswordDialogMustloginRequestHandler(JFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public String getUserName() {
        return JOptionPane.showInputDialog(frame, "Enter your nick");
    }
}

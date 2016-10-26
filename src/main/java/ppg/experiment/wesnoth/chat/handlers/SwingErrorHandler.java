package ppg.experiment.wesnoth.chat.handlers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ppg.experiment.wesnoth.chat.ErrorHandler;

public class SwingErrorHandler extends ErrorHandler {
    private JFrame frame;

    public SwingErrorHandler(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public String requestDifferentNick(String message) {
        return JOptionPane.showInputDialog(frame,
                message + " Choose a different nickname.");
    }

}

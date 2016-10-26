package ppg.experiment.wesnoth.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Application {

    public static void main(String[] args) {
        new Application();
    }

    public Application() {
        JFrame frame = new JFrame("Wesnoth Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new UserListPanel(), BorderLayout.WEST);
        frame.getContentPane().add(getHistoryArea(), BorderLayout.CENTER);
        frame.getContentPane().add(getChatArea(), BorderLayout.SOUTH);

        frame.setSize(new Dimension(400, 400));
        frame.setVisible(true);
    }

    private JScrollPane getHistoryArea() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }

    private JScrollPane getChatArea() {
        JTextArea histtoryTextArea = new JTextArea();
        histtoryTextArea.setLineWrap(true);
        JScrollPane historyScrollPane = new JScrollPane(histtoryTextArea);
        historyScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        historyScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return historyScrollPane;
    }
}

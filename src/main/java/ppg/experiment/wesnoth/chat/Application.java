package ppg.experiment.wesnoth.chat;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class Application {

    public static void main(String[] args) {
        new Application();
    }

    public Application() {

        VersionRequestHandler versionRequestHandler = getVersionRequestHandler();
        final JFrame frame = new JFrame("Wesnoth Chat");

        MustLoginRequestHandler mustLoginRequestHandler = new MustLoginRequestHandler() {

            @Override
            public String getUserName() {
                return JOptionPane.showInputDialog(frame, "Enter your nick");
            }
        };
        WesnothChatClient client = new WesnothChatClient(versionRequestHandler,
                mustLoginRequestHandler);
        new Thread(client).start();

        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(getUserListPanel(),
                new GridBagConstraints(0, 0, 1, 5, 1, 1,
                        GridBagConstraints.WEST, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
        frame.getContentPane().add(getHistoryArea(),
                new GridBagConstraints(1, 0, 3, 4, 1, 1,
                        GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
        frame.getContentPane().add(getChatArea(),
                new GridBagConstraints(1, 4, 3, 1, 1, 1,
                        GridBagConstraints.SOUTHEAST, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

        frame.setMinimumSize(new Dimension(440, 550));

        frame.setVisible(true);
    }

    private VersionRequestHandler getVersionRequestHandler() {
        VersionRequestHandler versionRequestHandler = new VersionRequestHandler() {
            @Override
            String getVersion() {
                return "1.12.6";
            }
        };
        return versionRequestHandler;
    }

    private JScrollPane getUserListPanel() {
        DefaultListModel<String> dataModel = new DefaultListModel<String>();
        JScrollPane scrollPane = new JScrollPane(new JList<String>(dataModel));
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane
                .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        scrollPane.setPreferredSize(new Dimension(100, 500));
        return scrollPane;
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
        scrollPane.setPreferredSize(new Dimension(300, 400));
        return scrollPane;
    }

    private JScrollPane getChatArea() {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        return scrollPane;
    }
}

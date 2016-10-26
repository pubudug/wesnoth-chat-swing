package ppg.experiment.wesnoth.chat;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;

import ppg.experiment.wesnoth.chat.handlers.JListGameListDiffMessageHandler;
import ppg.experiment.wesnoth.chat.handlers.JListUserMessageHandler;
import ppg.experiment.wesnoth.chat.handlers.JTextAreaMessageMessageHandler;
import ppg.experiment.wesnoth.chat.handlers.JTextAreaWhisperMessageHandler;
import ppg.experiment.wesnoth.chat.handlers.PasswordDialogMustloginRequestHandler;

public class Application {

    public static void main(String[] args) {
        new Application();
    }

    public Application() {

        VersionRequestHandler versionRequestHandler = getVersionRequestHandler();
        final JFrame frame = new JFrame("Wesnoth Chat");
        DefaultListModel<String> userListModel = new DefaultListModel<String>();
        JTextArea historyTextArea = new JTextArea();

        MustLoginRequestHandler mustLoginHandler = new PasswordDialogMustloginRequestHandler(
                frame);
        UserMessageHandler userHandler = new JListUserMessageHandler(
                userListModel);
        GameListDiffMessageHandler gameListDiffHandler = new JListGameListDiffMessageHandler(
                userListModel);

        JTextAreaWhisperMessageHandler whisperHandler = new JTextAreaWhisperMessageHandler(
                historyTextArea);

        JTextAreaMessageMessageHandler messageHandler = new JTextAreaMessageMessageHandler(
                historyTextArea);

        WesnothChatClient client = new WesnothChatClient(versionRequestHandler,
                mustLoginHandler, userHandler, gameListDiffHandler,
                whisperHandler, messageHandler);
        new Thread(client).start();

        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(getUserListPanel(userListModel),
                new GridBagConstraints(0, 0, 1, 5, .2, 1,
                        GridBagConstraints.WEST, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
        frame.getContentPane().add(getHistoryArea(historyTextArea),
                new GridBagConstraints(1, 0, 4, 4, .8, .8,
                        GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
        frame.getContentPane().add(getChatArea(),
                new GridBagConstraints(1, 4, 4, 1, .8, .2,
                        GridBagConstraints.SOUTHEAST, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

        frame.setMinimumSize(new Dimension(550, 550));

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

    private JScrollPane getUserListPanel(ListModel<String> userListModel) {
        JScrollPane scrollPane = new JScrollPane(
                new JList<String>(userListModel));
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane
                .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        scrollPane.setPreferredSize(new Dimension(100, 500));
        return scrollPane;
    }

    private JScrollPane getHistoryArea(JTextArea historyTextArea) {
        historyTextArea.setEditable(false);
        historyTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 400));
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
        scrollPane.setPreferredSize(new Dimension(400, 100));
        return scrollPane;
    }
}

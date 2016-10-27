package ppg.experiment.wesnoth.chat;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import ppg.experiment.wesnoth.chat.handler.GameListDiffMessageHandler;
import ppg.experiment.wesnoth.chat.handler.MustLoginRequestHandler;
import ppg.experiment.wesnoth.chat.handler.UserMessageHandler;
import ppg.experiment.wesnoth.chat.handler.VersionRequestHandler;
import ppg.experiment.wesnoth.chat.handlers.FixedVersionRequestHandler;
import ppg.experiment.wesnoth.chat.handlers.JListGameListDiffMessageHandler;
import ppg.experiment.wesnoth.chat.handlers.JListUserMessageHandler;
import ppg.experiment.wesnoth.chat.handlers.JTextAreaMessageMessageHandler;
import ppg.experiment.wesnoth.chat.handlers.JTextAreaWhisperMessageHandler;
import ppg.experiment.wesnoth.chat.handlers.PasswordDialogMustloginRequestHandler;
import ppg.experiment.wesnoth.chat.handlers.SwingErrorHandler;

public class Application {

    public static void main(String[] args) {
        new Application();
    }

    public Application() {

        final JFrame frame = new JFrame("Wesnoth Chat");
        DefaultListModel<String> userListModel = new DefaultListModel<String>();
        JList<String> userList = new JList<String>(userListModel);
        JTextArea historyTextArea = new JTextArea();
        JTextArea chatTextArea = new JTextArea();

        VersionRequestHandler versionRequestHandler = new FixedVersionRequestHandler();
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

        SwingErrorHandler errorHandler = new SwingErrorHandler(frame);

        WesnothChatClient client = new WesnothChatClient("server.wesnoth.org", 15000,
                versionRequestHandler, mustLoginHandler, userHandler,
                gameListDiffHandler, whisperHandler, messageHandler,
                errorHandler);

        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(getUserListPanel(userList),
                new GridBagConstraints(0, 0, 1, 6, .2, 1,
                        GridBagConstraints.WEST, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
        frame.getContentPane().add(getHistoryArea(historyTextArea),
                new GridBagConstraints(1, 0, 4, 4, .8, .8,
                        GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
        frame.getContentPane().add(getChatArea(chatTextArea),
                new GridBagConstraints(1, 4, 4, 1, .8, .2,
                        GridBagConstraints.SOUTHEAST, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

        frame.getContentPane().add(
                getButtonsPanel(userList, chatTextArea, client),
                new GridBagConstraints(1, 5, 4, 1, 0, 0,
                        GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 0), 0, 0));

        frame.setMinimumSize(new Dimension(600, 600));

        new Thread(client).start();
        frame.setVisible(true);
    }

    private Component getButtonsPanel(final JList<String> userList,
            final JTextArea chatTextArea, final WesnothChatClient client) {
        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(300, 40));

        JButton clearListSelectionButton = new JButton("Clear Selection");
        clearListSelectionButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                userList.clearSelection();
            }
        });
        buttons.add(clearListSelectionButton);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                List<String> selectedValuesList = userList
                        .getSelectedValuesList();
                if (selectedValuesList.isEmpty()) {
                    client.sendMessage(chatTextArea.getText());
                    chatTextArea.setText("");
                } else {
                    for (String nick : selectedValuesList) {
                        client.whisper(nick, chatTextArea.getText());
                        chatTextArea.setText("");
                    }
                }
            }
        });
        buttons.add(sendButton);
        return buttons;
    }

    private JScrollPane getUserListPanel(JList<String> list) {
        JScrollPane scrollPane = new JScrollPane(list);
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

    private JScrollPane getChatArea(JTextArea textArea) {
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

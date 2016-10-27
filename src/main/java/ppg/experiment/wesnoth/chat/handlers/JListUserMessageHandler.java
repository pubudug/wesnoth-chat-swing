package ppg.experiment.wesnoth.chat.handlers;

import javax.swing.DefaultListModel;

import ppg.experiment.wesnoth.chat.handler.UserMessageHandler;

public class JListUserMessageHandler extends UserMessageHandler {

    private DefaultListModel<String> userListModel;

    public JListUserMessageHandler(DefaultListModel<String> userListModel) {
        super();
        this.userListModel = userListModel;
    }

    @Override
    public void addUser(String name) {
        userListModel.addElement(name);
    }
}

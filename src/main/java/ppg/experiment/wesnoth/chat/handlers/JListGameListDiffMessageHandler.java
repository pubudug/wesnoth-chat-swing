package ppg.experiment.wesnoth.chat.handlers;

import javax.swing.DefaultListModel;

import ppg.experiment.wesnoth.chat.handler.GameListDiffMessageHandler;

public class JListGameListDiffMessageHandler
        extends GameListDiffMessageHandler {
    private DefaultListModel<String> userListModel;

    public JListGameListDiffMessageHandler(
            DefaultListModel<String> userListModel) {
        super();
        this.userListModel = userListModel;
    }

    @Override
    public void removeUser(int index) {
        userListModel.removeElementAt(index);
    }

    @Override
    public void addUser(int index, String name) {
        userListModel.insertElementAt(name, index);
    }
}

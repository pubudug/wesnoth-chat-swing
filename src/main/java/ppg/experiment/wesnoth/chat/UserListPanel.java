package ppg.experiment.wesnoth.chat;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class UserListPanel extends JPanel {

    private static final long serialVersionUID = 1733460575189277073L;

    public UserListPanel() {
        DefaultListModel<String> dataModel = new DefaultListModel<String>();
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        dataModel.addElement("user1");
        dataModel.addElement("user2");
        add(new JList<String>(dataModel));
    }
}

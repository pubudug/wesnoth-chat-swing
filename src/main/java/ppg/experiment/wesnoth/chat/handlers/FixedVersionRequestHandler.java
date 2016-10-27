package ppg.experiment.wesnoth.chat.handlers;

import ppg.experiment.wesnoth.chat.handler.VersionRequestHandler;

public class FixedVersionRequestHandler extends VersionRequestHandler {
    @Override
    public String getVersion() {
        return "1.12.6";
    }
}

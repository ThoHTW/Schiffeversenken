package schiffeversenken;

import java.io.IOException;

public interface empfänger {
    /**
     * allowed in status START, goes to SINKS or SINK_R
     * @param random
     * @throws IOException
     */
    void receiveDice(int random) throws IOException;

    /**
     * allowed in status SINK_R, goes to CONFIRMS
     * @param line
     * @param column
     * @throws IOException
     */
    void receiveCoordinate(int line, int column) throws IOException;

    /**
     * allowed in status CONFIRM_R, goes to SINK_S or END
     * @param hit
     * @throws IOException
     */
    void recieveConfirm(int hit) throws IOException;
}

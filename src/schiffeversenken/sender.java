package schiffeversenken;

import java.io.IOException;

public interface sender {
    /**
     * allowed in status START
     * @param random
     * @throws IOException
     */
    void sendDice(int random) throws IOException, StatusException;

    /**
     * allowed in status SINK_S, goes to CONFIRM_R
     * @param line
     * @param column
     * @throws IOException
     */
    void sendCoordinate(int line, int column) throws IOException, StatusException;

    /**
     * allowed in status CONFIRM_S, goes to SINK_R
     * @param hit
     * @throws IOException
     */
    void sendConfirm(int hit) throws IOException, StatusException;
}


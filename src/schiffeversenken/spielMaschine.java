package schiffeversenken;

import java.io.IOException;

public class spielMaschine implements empfänger, sender {

    @Override
    public void receiveDice(int random) throws IOException {

    }

    @Override
    public void receiveCoordinate(int line, int column) throws IOException {

    }

    @Override
    public void recieveConfirm(int hit) throws IOException {

    }

    @Override
    public void sendDice(int random) throws IOException {

    }

    @Override
    public void sendCoordinate(int line, int column) throws IOException {

    }

    @Override
    public void sendConfirm(int hit) throws IOException {

    }
}

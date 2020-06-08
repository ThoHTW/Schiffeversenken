package schiffeversenken.protocolBinding;

import schiffeversenken.StatusException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StreamBindingSender implements schiffeversenken.sender{

    private final DataOutputStream dos;

    public StreamBindingSender(OutputStream os) {
        this.dos = new DataOutputStream(os);
    }


    @Override
    public void sendDice(int random) throws IOException, StatusException {
        this.dos.writeInt(StreamBinding.DICE);
        this.dos.writeInt(random);
    }

    @Override
    public void sendCoordinate(int line, int column) throws IOException, StatusException {
        this.dos.writeInt(StreamBinding.COORDINATES);
        this.dos.writeInt(line);
        this.dos.writeInt(column);
    }

    @Override
    public void sendConfirm(int hit) throws IOException, StatusException {
        this.dos.writeInt(StreamBinding.CONFIRM);
        this.dos.writeInt(hit);
    }
}

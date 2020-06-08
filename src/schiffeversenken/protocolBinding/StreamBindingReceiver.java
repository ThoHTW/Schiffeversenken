
package schiffeversenken.protocolBinding;

import schiffeversenken.StatusException;
import schiffeversenken.empfänger;

import java.io.*;

public class StreamBindingReceiver extends Thread {
    private final DataInputStream dis;
    private final empfänger receiver;

    public StreamBindingReceiver(InputStream is, empfänger receiver) {
        this.dis = new DataInputStream(is);
        this.receiver = receiver;
    }



    public void readDice() throws IOException, StatusException {
        int random = this.dis.readInt();
        this.receiver.receiveDice(random);
    }


    public void readCoordinate() throws IOException, StatusException {
        int x = this.dis.readInt();
        int y = this.dis.readInt();
        try {
            this.receiver.receiveCoordinate(x, y);
        } catch (StatusException e) {
            System.err.println("cannot execute coordinate - don't inform sender - error not part of protocol: "
                    + e.getLocalizedMessage());
        }
    }


    public void readConfirm() throws IOException, StatusException {
        int hit = this.dis.readInt();
        try{
            this.receiver.recieveConfirm(hit);
        }catch (StatusException e){
            System.err.println("cannot execute confirm - don't inform sender - error not part of protocol: "
                    + e.getLocalizedMessage());
        }
    }

    public void run(){
        boolean again = true;
        while(again) {
            try {
                int cmd = this.dis.readInt();

                switch (cmd) {
                    case StreamBinding.DICE : this.readDice(); break;
                    case StreamBinding.COORDINATES : this.readCoordinate(); break;
                    case StreamBinding.CONFIRM: this.readConfirm(); break;
                    default: again = false; System.err.println("unknown command code: " + cmd);
                }

            } catch (IOException e) {
                System.err.println("IOException: " + e.getLocalizedMessage());
                again = false;
            } catch (StatusException e) {
                System.err.println("Status Exception: " + e.getLocalizedMessage());
                again = false;
            }
        }
    }

}



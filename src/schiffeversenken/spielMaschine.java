package schiffeversenken;

import javax.security.sasl.SaslException;
import java.io.IOException;
import java.util.Random;

public class spielMaschine implements empfänger, sender {
    public static final int UNDEFINED_DICE = -1;
    private final sender sender;
    public static final int DIM = 10;

    svBoardField[][] board = new svBoardField[DIM][DIM];

    private svStatus status;
    private int sentDice = UNDEFINED_DICE;
    private int receivedRandom;

    public spielMaschine(schiffeversenken.sender sender) {
        this.status = svStatus.START;
        this.sender = sender;
        for(int i = 0; i < DIM; i++){
            for(int j = 0; j< DIM; j++){
                this.board[i][j] = svBoardField.EMPTY; //Schiffe werden später gesetzt?
            }
        }
    }


    ///////// Send ///////////////////////////////////
    @Override
    public void sendDice(int random) throws IOException, StatusException {
        if(this.status != svStatus.START){
            throw new StatusException();
        }
        this.sender.sendDice(random);
    }

    @Override
    public void sendCoordinate(int line, int column) throws IOException, StatusException{
        if(this.status != svStatus.COORDINATE_S){
            throw new StatusException();
        }
        this.sender.sendCoordinate(line,column);
    }

    @Override
    public void sendConfirm(int hit) throws IOException, StatusException {
        if(this.status != svStatus.CONFIRM_S){
            throw new StatusException();
        }
        this.sender.sendConfirm(hit);
    }

    ////////////// Receive //////////////////////////
    @Override
    public void receiveDice(int random) throws IOException, StatusException{
        if(this.status != svStatus.START && this.status != svStatus.DICE_SENT){
            throw new StatusException();
        }
        this.receivedRandom = random;

        //niedrige zahl beginnt, bei gleicher zahl nochmal
        if (this.status == svStatus.DICE_SENT) {
            if (random == this.sentDice) {
                this.status = svStatus.START;
            } else if (random > this.sentDice) {
                this.status = svStatus.COORDINATE_R;
            } else {
                this.status = svStatus.COORDINATE_R;
            }
        }
    }

    @Override
    public void receiveCoordinate(int line, int column) throws IOException, StatusException {
        if (this.status != svStatus.CONFIRM_R) {
            throw new StatusException();
        }
    }

    @Override
    public void recieveConfirm(int hit) throws IOException, StatusException{
        if(this.status != svStatus.CONFIRM_R){
            throw new StatusException();
        }

    }

    ///////////interaktion/////////////

    public void placeShip(){

    }

    public boolean checkGameOver(){
        boolean win = false;
        return win;
    }

}

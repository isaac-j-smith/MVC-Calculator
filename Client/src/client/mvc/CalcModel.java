package client.mvc;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import client.statePattern.*;

public class CalcModel extends Observable {

    private String total = "";
    private CalcContext context;
    private Boolean equationFinished = false;
    private Socket socket;


    public CalcModel(){
        try {
            this.socket = new Socket("localhost", 8080);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.context = new CalcContext();
        this.context.setState(new StartState(this.context));
    }

    public void calculate(String i){
        total = this.context.handle(i);
        this.equationFinished = this.context.isFinished();
        upload();
        this.setChanged();
    }

    private void upload(){
        if(this.equationFinished){
            try{

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                outputStream.writeObject(this.context.getEquation());

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public String output(){
        return "" + total;
    }
}

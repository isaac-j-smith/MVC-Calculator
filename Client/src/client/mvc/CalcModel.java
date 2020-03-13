package client.mvc;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

import client.VisitorComposite.OpStack;
import client.VisitorComposite.Operation;
import client.VisitorComposite.Visitor;
import client.statePattern.*;

public class CalcModel extends Observable {

    private String total = "";
    private CalcContext context;
    private Socket socket;
    private ObjectOutputStream out;
    private Visitor visitor;
    private Operation op;
    private OpStack opStack;

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
        upload();
        this.setChanged();
    }

    private void upload(){
        if(this.context.isFinished()){
            try{

                out = new ObjectOutputStream(socket.getOutputStream());

                out.writeObject(this.context.getEquation());
                out.flush();
                this.context.clearEquations();
                this.context.setFinished(false);

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public String output(){
        return "" + total;
    }
}

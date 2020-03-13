package client.statePattern;

public abstract class State {

    CalcContext context;

    public State(CalcContext context){
        this.context = context;
    }

    public void handle(String val){}

}

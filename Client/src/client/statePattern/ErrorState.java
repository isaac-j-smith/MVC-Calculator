package client.statePattern;

public class ErrorState extends State {

    public ErrorState(CalcContext context) {
        super(context);
    }

    @Override
    public void handle(String val){
        if ("0".contains(val)){
            this.context.clear();
            this.context.setState(new StartState(this.context));
        }
        else{
            this.context.setState(this.context.getLastState());
        }
    }
}

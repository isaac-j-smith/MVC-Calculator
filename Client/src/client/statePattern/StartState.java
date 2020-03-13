package client.statePattern;

public class StartState extends State {

    public StartState(CalcContext context) {
        super(context);
    }

    @Override
    public void handle(String val){
        if ("1234567890".contains(val)){
            this.context.setResult(val);
            this.context.setLastState(this);
            this.context.setState(new GetFirstOPState(this.context));
        }else{
            this.context.setResult("0");
            this.context.setState(new StartState(this.context));
        }
    }
}

package client.statePattern;

public class CalculateState extends State{
    public CalculateState(CalcContext context) {
        super(context);
    }

    @Override
    public void handle(String val){

        if ("1234567890".contains(val)){
            this.context.setResult(val);
            this.context.setState(new GetFirstOPState(this.context));
        }else{
            this.context.clear();
            this.context.setState(new StartState(this.context));
        }

    }
}

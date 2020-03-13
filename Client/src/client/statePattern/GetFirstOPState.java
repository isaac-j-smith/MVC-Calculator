package client.statePattern;

public class GetFirstOPState extends State {

    public GetFirstOPState(CalcContext context) {
        super(context);
    }

    @Override
    public void handle(String val){
        if ("1234567890".contains(val)){
            this.context.setResult(this.context.getResult() + val);
            this.context.setLastState(this);
            this.context.setState(new GetFirstOPState(this.context));
        }
        else if("+-/*".contains(val)){
            this.context.addToStack(this.context.getResult());
            this.context.addToEquation(this.context.getResult());
            this.context.waitForNext(val,this);
        }
        else if ("C".contains(val)){
            this.context.clear();
        }
        else{
            this.context.setState(new ErrorState(this.context));
            this.context.error();
        }
    }
}

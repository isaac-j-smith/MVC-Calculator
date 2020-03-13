package client.statePattern;

public class waitNextOperandState extends State {

    public waitNextOperandState(CalcContext context) {
        super(context);
    }

    @Override
    public void handle(String val){
        if ("1234567890".contains(val)){
            this.context.setResult(val);
            this.context.setState(new GetSecondOperandState(this.context));
        }
        else if("C".contains(val)){
            this.context.clear();
        }
        else{
            this.context.setState(new ErrorState(this.context));
            this.context.error();
        }
    }
}

package client.statePattern;

public class GetSecondOperandState extends State {
    public GetSecondOperandState(CalcContext context) {
        super(context);
    }

    @Override
    public void handle(String val) {
        if ("1234567890".contains(val)) {
            this.context.setResult(this.context.getResult() + val);
            this.context.setState(new GetSecondOperandState(this.context));
        } else if ("+-/*".contains(val)) {
            this.context.addToStack(this.context.getResult());
            this.context.addToEquation(this.context.getResult());
            this.context.calculate();
            this.context.waitForNext(val,this);
        } else if ("C".contains(val)) {
            this.context.clear();
        } else {
            this.context.addToStack(this.context.getResult());
            this.context.addToEquation(this.context.getResult());
            this.context.calculate();
            this.context.addToEquation(val);
            this.context.addToEquation(this.context.getResult());
            this.context.setFinished(true);
            this.context.setState(new CalculateState(this.context));
        }
    }
}

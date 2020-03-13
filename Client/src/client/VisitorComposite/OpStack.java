package client.VisitorComposite;
import java.util.Stack;

public class OpStack extends Operation{
    String val = "";
    private Stack<Operation> operations = new Stack<>();

    public void accept(Visitor v){
        v.visit(this);
        while(!operations.isEmpty()){
            v.visit(operations.pop());
        }
    }

    public OpStack(String val){
        super(val);
    }

    public void add(OpStack op){
        this.operations.push(op);
    }

    public String getValue(){
        return this.val;
    }

    public void setVal(String val){
        this.val = val;
    }
}

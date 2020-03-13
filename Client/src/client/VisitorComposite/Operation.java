package client.VisitorComposite;

public abstract class Operation {
    String val = "";

    public Operation(String val) {
        this.val = val;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public String getValue(){
        return this.val;
    }

    public void setVal(String val){
        this.val = val;
    }
}

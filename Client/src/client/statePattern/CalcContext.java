package client.statePattern;

import client.statePattern.*;

import javax.swing.*;
import java.util.Stack;

public class CalcContext {

    private State state;
    private State lastState;
    private String equation = "";
    private boolean finished = false;

    private Stack<String> opStack = new Stack<>();

    private String result;

    public String handle(String val){
        this.state.handle(val);
        return result;
    }

    public void setState(State state){
        this.state = state;
    }

    public void setResult(String r){
        this.result = r;
    }

    public String getResult(){
        return this.result;
    }

    public void addToEquation(String val){
        this.equation = this.equation + val;
    }

    public String getEquation(){
        return this.equation;
    }

    public void clearEquations(){
        this.equation = "";
    }

    public void addToStack(String operation){
        this.opStack.push(operation);
    }

    public void clear(){
        this.setResult("0");
        this.opStack.empty();
        this.setState(new StartState(this));     }

    public State getLastState() {
        return lastState;
    }

    public void setLastState(State lastState) {
        this.lastState = lastState;
    }

    public String calculate(){
        double result = 0;
        Double num2 = Double.parseDouble(opStack.pop());
        String op = opStack.pop();
        Double num1 = Double.parseDouble(opStack.pop());
        switch (op){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        opStack.push(""+result);
        this.result = "" + result;
        return "" + result;
    }

    public void error(){
        Object[] options ={"Reset", "Discard"};

        int n = JOptionPane.showOptionDialog(new JFrame(),
                "Error: would you like to reset or discard",
                "Error",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        this.state.handle(""+n);
    }

    public void waitForNext(String value, State state){

        addToStack(value);
        addToEquation(value);
        setLastState(state);
        setState(new waitNextOperandState(this));
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}

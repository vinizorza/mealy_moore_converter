package entity;

public class Transition {

    private State source;
    private State destination;
    private String output;
    private String input;

    public Transition(State source, State destination, String output, String input) {
        this.source = source;
        this.destination = destination;
        this.output = output;
        this.input = input;
    }

    public Transition() {

    }

    public State getSource() {
        return source;
    }

    public void setSource(State source) {
        this.source = source;
    }

    public State getDestination() {
        return destination;
    }

    public void setDestination(State destination) {
        this.destination = destination;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}

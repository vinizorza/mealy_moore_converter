package entity;

public class State {

    private String label;
    private String output;
    private boolean isFinal;
    private boolean isInitial;

    public State(String label, String output, boolean isFinal, boolean isInitial) {
        this.label = label;
        this.output = output;
        this.isFinal = isFinal;
        this.isInitial = isInitial;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public boolean isInitial() {
        return isInitial;
    }

    public void setInitial(boolean initial) {
        isInitial = initial;
    }
}

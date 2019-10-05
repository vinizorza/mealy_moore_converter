package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Machine {

    private ArrayList<Transition> transitions;
    private Enum<MachineType> type;

    public Machine(ArrayList<Transition> transitions, Enum<MachineType> type) {
        this.transitions = transitions;
        this.type = type;
    }

    public Machine() {
        this.transitions = transitions;
        this.type = type;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    public Enum<MachineType> getType() {
        return type;
    }

    public void setType(Enum<MachineType> type) {
        this.type = type;
    }
}

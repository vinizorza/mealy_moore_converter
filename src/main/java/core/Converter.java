package core;

import entity.Machine;
import entity.MachineType;
import entity.State;
import entity.Transition;

import java.util.ArrayList;

public class Converter {

    public static Machine convert (Machine machine){

        if(MachineType.MEALY.equals(machine.getType())){
            machine = mealyToMoore(machine);
        }else{
            machine = mooreToMealy(machine);
        }

        return machine;
    }

    private static Machine mooreToMealy(Machine machine) {
        machine.setType(MachineType.MEALY);
        for (Transition transition: machine.getTransitions()) {
            transition.setOutput(transition.getDestination().getOutput());
            transition.getDestination().setOutput(null);
        }
        return machine;
    }

    private static Machine mealyToMoore(Machine machine) {
        machine.setType(MachineType.MOORE);
        ArrayList<State> states = getMachineStates(machine);
        ArrayList<Transition> newTransitions = new ArrayList<Transition>();
        ArrayList<State> newStates = new ArrayList<State>();
        for (State state: states) {
            ArrayList<Transition> transitionsForState = getTransitionsForState(state, machine.getTransitions());
            newStates.addAll(createNewStates(transitionsForState));
        }

        for (Transition transition: machine.getTransitions()) {
            for (State state: newStates) {
                if (state.getOutput().equals(transition.getOutput())
                        && state.getOldLabel().equals(transition.getDestination().getLabel())){
                    transition.setDestination(state);
                    transition.setOutput(state.getOutput());
                }
            }
        }

        for (Transition transition: machine.getTransitions()) {
            if (transition.getDestination().getOldLabel() != null){
                Transition newTransition = new Transition();
                    newTransition.setSource(transition.getDestination());
                    newTransition.setDestination(transition.getDestination());
                    newTransition.setInput(transition.getInput());
                    newTransitions.add(newTransition);
            }
        }
        machine.getTransitions().addAll(newTransitions);

        return machine;
    }

    private static ArrayList<State> createNewStates(ArrayList<Transition> transitionsForState) {
        ArrayList<State> states = new ArrayList<State>();
        for (Transition transition: transitionsForState) {
            Boolean contains = false;
            for (State state: states) {
                if(state.getOutput().equals(transition.getOutput())){
                    contains = true;
                }

                if(transition.getDestination().isInitial()){
                    contains = true;
                }
            }

            if(!contains){
                State state = new State();
                state.setLabel(createNewStateLabel(states, transition.getDestination().getLabel()) );
                state.setOldLabel(transition.getDestination().getLabel());
                state.setOutput(transition.getOutput());
                state.setInitial(transition.getDestination().isInitial());
                state.setFinal(transition.getDestination().isFinal());
                states.add(state);
            }
        }

        return states;
    }

    private static String createNewStateLabel(ArrayList<State> states, String label) {
        for (State state: states) {
            if(state.getLabel().equals(label)){
                return label + "'";
            }
        }
        return label;
    }

    private static ArrayList<Transition> getTransitionsForState(State state, ArrayList<Transition> transitions) {
        ArrayList<Transition> transitionsForState = new ArrayList<Transition>();

        for (Transition transition: transitions) {
            if(state.getLabel().equals(transition.getDestination().getLabel())) {
                transitionsForState.add(transition);
            }
        }

        return transitionsForState;
    }

    private static ArrayList<State> getMachineStates(Machine machine) {
        ArrayList<State> states = new ArrayList<State>();

        for (Transition transition: machine.getTransitions()) {
            if(!states.contains(transition.getSource())){
                states.add(transition.getSource());
            }

            if(!states.contains(transition.getDestination())){
                states.add(transition.getDestination());
            }
        }

        return states;
    }

}

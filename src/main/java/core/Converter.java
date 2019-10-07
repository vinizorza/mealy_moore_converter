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
        for (State state: states) {
            ArrayList<Transition> transitionsForState = getTransitionsForState(state, machine.getTransitions());
            ArrayList<State> newStates = createNewStates(transitionsForState);
            //TODO: certo até aqui
            for (Transition transition: machine.getTransitions()) {
                for (State newState: newStates) {
                    if(transition.getDestination().getLabel().equals(newState.getOldLabel())
                        && transition.getOutput().equals(newState.getOutput())){
                        transition.setDestination(newState);
//                        Transition newTransition = new Transition();
//                        newTransition.setSource(newState);
//                        newTransition.setDestination(transition.getDestination());
//                        newTransition.setInput(transition.getInput());
//                        newTransitions.add(newTransition);
                    }
                }
            }

            machine.getTransitions().addAll(newTransitions);
        }



        return null;
    }

    private static ArrayList<State> createNewStates(ArrayList<Transition> transitionsForState) {
        ArrayList<State> states = new ArrayList<State>();
        for (Transition transition: transitionsForState) {
            Boolean contains = false;
            for (State state: states) {
                if(state.getOutput().equals(transition.getOutput())){
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

    private static Integer getSizeDifferentsOutputs(ArrayList<Transition> transitionsForState) {
        ArrayList<String> outputs = new ArrayList<String>();
        for (Transition transition: transitionsForState) {
            if(!outputs.contains(transition.getOutput())){
                outputs.add(transition.getOutput());
            }
        }
        return outputs.size();
    }

    private static ArrayList<Transition> getTransitionsForState(State state, ArrayList<Transition> transitions) {
        ArrayList<Transition> transitionsForState = new ArrayList<Transition>();

        //TODO: pegar todas as transicoes que levam ao mesmo estado, e para cada saída diferente criar um novo estado, fazendo o reapontamento
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

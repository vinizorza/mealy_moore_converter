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
        for (State state: states) {
            ArrayList<Transition> transitionsForState = getDifferentsOutputsTransitionsForState(state, machine.getTransitions());
            for (Transition transition: transitionsForState) {

            }
        }



        return null;
    }

    private static ArrayList<Transition> getDifferentsOutputsTransitionsForState(State state, ArrayList<Transition> transitions) {
        ArrayList<Transition> transitionsForState = new ArrayList<Transition>();
        ArrayList<String> outputs = new ArrayList<String>();

        //TODO: pegar todas as transicoes que levam ao mesmo estado, e para cada saída diferente criar um novo estado, fazendo o reapontamento
        for (Transition transition: transitions) {
            if(state.getLabel().equals(transition.getDestination().getLabel()) && !outputs.contains(transition.getOutput())) {
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

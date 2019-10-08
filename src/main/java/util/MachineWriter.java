package util;

import entity.Machine;
import entity.MachineType;
import entity.State;
import entity.Transition;

import java.awt.image.SampleModel;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MachineWriter {
    public static void writeMachine (Machine machine, String filePath){

        try{
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");

            writer.println(machine.getType().toString().toLowerCase());
            ArrayList<State> states =  getStatesFromTransitions(machine.getTransitions());
            writer.println(parseStringStates(states));
            writer.println(getStringInputAlphabet(machine.getTransitions()));
            writer.println(getStringInitialState(machine.getTransitions()));
            writer.println(getStringFinalStates(machine.getTransitions()));

            if(MachineType.MEALY.equals(machine.getType())){
                writer.println(getStringOutputAlphabetMealy(machine.getTransitions()));
                for (Transition transition: machine.getTransitions()) {
                    writer.println(getStringTransitionMealy(transition));
                }
            }else{
                writer.println(getStringOutputAlphabetMoore(machine.getTransitions()));
                for (Transition transition: machine.getTransitions()) {
                    writer.println(getStringTransitionMoore(transition));
                }
                writer.println("-----");

                for (State state: getStatesFromTransitions(machine.getTransitions())) {
                    writer.println(getStringOutputState(state));
                }


            }
            writer.close();
        }catch (Exception e){

        }

    }

    private static String getStringOutputState(State state) {
        StringBuilder sb = new StringBuilder();
        sb.append(state.getLabel());
        if(state.getOutput() != null){
            sb.append(" ");
            sb.append(state.getOutput());
        }
        return sb.toString();
    }

    private static String getStringTransitionMealy(Transition transition) {
        StringBuilder sb = new StringBuilder();
        sb.append(transition.getSource().getLabel());
        sb.append(" ");
        sb.append(transition.getInput());
        sb.append(" ");
        sb.append(transition.getDestination().getLabel());
        sb.append(" ");
        sb.append(transition.getOutput());
        return sb.toString();
    }

    private static String getStringTransitionMoore(Transition transition) {
        StringBuilder sb = new StringBuilder();
        sb.append(transition.getSource().getLabel());
        sb.append(" ");
        sb.append(transition.getInput());
        sb.append(" ");
        sb.append(transition.getDestination().getLabel());
        return sb.toString();
    }

    private static String getStringOutputAlphabetMealy(ArrayList<Transition> transitions) {
        ArrayList<String> outputAlphabet = new ArrayList<String>();
        for (Transition transition: transitions) {
            if(!outputAlphabet.contains(transition.getOutput())){
                outputAlphabet.add(transition.getOutput());
            }
        }

        StringBuilder str = new StringBuilder();
        for (String elem: outputAlphabet) {
            str.append(elem);
            str.append(" ");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    private static String getStringOutputAlphabetMoore(ArrayList<Transition> transitions) {
        ArrayList<String> outputAlphabet = new ArrayList<String>();
        ArrayList<State> states = getStatesFromTransitions(transitions);

        for (State state: states) {
            if(!outputAlphabet.contains(state.getOutput()) && state.getOutput() != null){
                outputAlphabet.add(state.getOutput());
            }
        }

        StringBuilder str = new StringBuilder();
        for (String elem: outputAlphabet) {
            str.append(elem);
            str.append(" ");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    private static String getStringFinalStates(ArrayList<Transition> transitions) {

        ArrayList<State> finalStates = new ArrayList<State>();

        for (Transition transition: transitions) {
            if(transition.getSource().isFinal() && !finalStates.contains(transition.getSource())){
                finalStates.add(transition.getSource());
            }
            if(transition.getDestination().isFinal() && !finalStates.contains(transition.getDestination())){
                finalStates.add(transition.getDestination());
            }
        }

        StringBuilder str = new StringBuilder();
        for (State state: finalStates) {
            str.append(state.getLabel());
            str.append(" ");
        }

        if(finalStates.isEmpty()){
            return "--";
        }else{
            str.deleteCharAt(str.length() - 1);
        }

        return str.toString();
    }

    private static String getStringInitialState(ArrayList<Transition> transitions) {
        for (Transition transition: transitions) {
            if(transition.getSource().isInitial()){
                return transition.getSource().getLabel();
            }
            if(transition.getDestination().isInitial()){
                return transition.getDestination().getLabel();
            }
        }
        return null;
    }

    private static String getStringInputAlphabet(ArrayList<Transition> transitions) {

        ArrayList<String> inputAlphabet = new ArrayList<String>();
        for (Transition transition: transitions) {
            if(!inputAlphabet.contains(transition.getInput())){
                inputAlphabet.add(transition.getInput());
            }
        }

        StringBuilder str = new StringBuilder();
        for (String elem: inputAlphabet) {
            str.append(elem);
            str.append(" ");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    private static String parseStringStates(ArrayList<State> states) {
        StringBuilder str = new StringBuilder();
        for (State state: states) {
            str.append(state.getLabel());
            str.append(" ");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    private static ArrayList<State> getStatesFromTransitions(ArrayList<Transition> transitions) {
        ArrayList<State> states = new ArrayList<State>();

        for (Transition transition: transitions) {
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

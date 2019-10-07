package util;

import entity.Machine;
import entity.MachineType;
import entity.State;
import entity.Transition;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MachineReader {

    public static Machine getMachine(String filePath){

        Enum<MachineType> type = null;
        HashMap<String, State> states;
        List<String> inputAlphabet;
        State initialState;
        List<State> finalStates;
        List<String> outputAlphabet;
        ArrayList<Transition> transitions = new ArrayList<Transition>();

        String[] strStates;
        String[] strFinalStates;
        String[] strInputAlphabet;
        String[] strOutputAlphabet;
        String strInitialState;

        Machine machine = new Machine();

        try {
            String currentLine;

            BufferedReader objReader = new BufferedReader(new FileReader(filePath));

            if((currentLine = objReader.readLine()) != null){
                type = ("mealy".equals(currentLine)) ? MachineType.MEALY : MachineType.MOORE;
                machine.setType(type);
            }


            if(type.equals(MachineType.MEALY)){

                strStates = objReader.readLine().split(" ");
                strInputAlphabet = objReader.readLine().split(" ");
                strInitialState = objReader.readLine();
                strFinalStates = objReader.readLine().split(" ");
                strOutputAlphabet = objReader.readLine().split(" ");

                while((currentLine = objReader.readLine()) != null){
                    Transition transition = new Transition();
                    String[] strTransition;
                    strTransition = currentLine.split(" ");
                    State source = new State(strTransition[0], null, Arrays.asList(strFinalStates).contains(strTransition[0]), strInitialState.equals(strTransition[0]));
                    State destination = new State(strTransition[2], null, Arrays.asList(strFinalStates).contains(strTransition[2]), strInitialState.equals(strTransition[2]));

                    transition.setInput(strTransition[1]);
                    transition.setOutput(strTransition[3]);
                    transition.setSource(source);
                    transition.setDestination(destination);

                    transitions.add(transition);
                }

                machine.setTransitions(transitions);

            }else{

                strStates = objReader.readLine().split(" ");
                strInputAlphabet = objReader.readLine().split(" ");
                strInitialState = objReader.readLine();
                strFinalStates = objReader.readLine().split(" ");
                strOutputAlphabet = objReader.readLine().split(" ");

                while((currentLine = objReader.readLine()) != null && !"-----".equals(currentLine)){
                    Transition transition = new Transition();
                    String[] strTransition;
                    strTransition = currentLine.split(" ");
                    State source = new State(strTransition[0], null, Arrays.asList(strFinalStates).contains(strTransition[0]), strInitialState.equals(strTransition[0]));
                    State destination = new State(strTransition[2], null, Arrays.asList(strFinalStates).contains(strTransition[2]), strInitialState.equals(strTransition[2]));

                    transition.setInput(strTransition[1]);
                    transition.setSource(source);
                    transition.setDestination(destination);

                    transitions.add(transition);
                }

                while((currentLine = objReader.readLine()) != null){

                    String[] lineSplitted = currentLine.split(" ");

                    if(lineSplitted.length > 1){
                        String strState = lineSplitted[0];
                        String output = lineSplitted[1];

                        for (Transition transition: transitions) {
                            if(transition.getSource().getLabel().equals(strState)){
                                transition.getSource().setOutput(output);
                            }

                            if(transition.getDestination().getLabel().equals(strState)){
                                transition.getDestination().setOutput(output);
                            }
                        }
                    }

                }

                machine.setTransitions(transitions);

            }

        } catch (Exception e) {
            Bundle.get("input.file.error");
        }


        return machine;
    }

}

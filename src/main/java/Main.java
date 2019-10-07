import core.Converter;
import entity.Machine;
import util.Bundle;
import util.MachineReader;
import util.MachineWriter;

public class Main {
    public static void main(String[] args) {

        if(args.length != 4){
            System.out.println(Bundle.get("quantidade.parametros.incorreta"));
            return;
        }

        if(!"-i".equals(args[0]) | !"-o".equals(args[2])){
            System.out.println(Bundle.get("parametros.incorretos"));
            return;
        }

        //Teste 01
        Machine mealy01 = MachineReader.getMachine("C:/Users/Vinicius Zorzanelli/GIT/mealy_moore_converter/test_examples/mealy01.txt");
        MachineWriter.writeMachine(mealy01, "C:/Users/Vinicius Zorzanelli/GIT/mealy_moore_converter/test_examples/mealy01_write.txt");
        MachineWriter.writeMachine(Converter.convert(mealy01), "C:/Users/Vinicius Zorzanelli/GIT/mealy_moore_converter/test_examples/mealy01_converted.txt");

        //Teste 02
        Machine moore01 = MachineReader.getMachine("C:/Users/Vinicius Zorzanelli/GIT/mealy_moore_converter/test_examples/moore01.txt");
        MachineWriter.writeMachine(moore01, "C:/Users/Vinicius Zorzanelli/GIT/mealy_moore_converter/test_examples/moore01_write.txt");
        MachineWriter.writeMachine(Converter.convert(moore01), "C:/Users/Vinicius Zorzanelli/GIT/mealy_moore_converter/test_examples/moore01_converted.txt");
    }
}

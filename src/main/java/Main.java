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

        Machine machine = MachineReader.getMachine(args[1]);
        MachineWriter.writeMachine(Converter.convert(machine), args[3]);
    }
}

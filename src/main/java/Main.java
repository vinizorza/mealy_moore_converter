import entity.Machine;
import util.Bundle;
import util.MachineReader;

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

        MachineReader.getMachine("C:/Users/Vinicius Zorzanelli/GIT/mealy_moore_converter/src/main/java/util/test.txt");
    }
}

import util.Bundle;

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
        System.out.println(args[0]);
        System.out.println(args[1]);
    }
}

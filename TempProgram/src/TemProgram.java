import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kobejamesgt on 5/4/17.
 */

public class TemProgram {

    private List<String> parsedCommands;
    private IDescription instance;
    private ParseCommand pc;
    private String input;

    public TemProgram(String input) {
        this.input = input;
        parsedCommands = new ArrayList<String>();
        pc = new ParseCommand(input);
    }

    public String doIt() throws Exception{
        String[] s = pc.getParsedInput();
        s[0] = s[0].toUpperCase();

        if(s[0].equals("HOT")){
            instance =  new HotResponse();
        }
        else if(s[0].equals("COLD")){
            instance =  new ColdResponse();
        }
        else {
            throw new Exception("Wrong input, input again");
        }

        StringBuilder sb = new StringBuilder();

        try{
            for(int i=1; i<s.length; i++){
                String command = s[i];
                if (isCorrectOrder(command)){
                    switch (s[i]) {
                        case "1":
                            sb.append(", ").append(instance.putOnFootWear());
                            break;
                        case "2":
                            sb.append(", ").append(instance.putOnHeadWear());
                            break;
                        case "3":
                            sb.append(", ").append(instance.putOnSocks());
                            break;
                        case "4":
                            sb.append(", ").append(instance.putOnShirt());
                            break;
                        case "5":
                            sb.append(", ").append(instance.putOnJacket());
                            break;
                        case "6":
                            sb.append(", ").append(instance.putOnPants());
                            break;
                        case "7":
                            sb.append(", ").append(instance.leaveHouse());
                            break;
                        case "8":
                            sb.append(", ").append(instance.takeOffPajamas());
                            break;
                        default:
                            throw new Exception("Wrong input!");
                    }
                }
                parsedCommands.add(command);
            }
        } catch(Exception e){
            if (e.getMessage().equals("fail")) {
                return sb.append(", ").append(e.getMessage()).toString().substring(2);
            } else {
                throw new Exception(e);
            }
        }return (sb.toString()).substring(2);

    }

    private boolean isCorrectOrder(String command) throws Exception {

        if (parsedCommands.size() == 0) {
            // start with PJs
            if (!command.equals("8")) {
                throw new Exception("fail");
            }
        }

        // Only 1 piece of each type of clothing may be put on
        if (parsedCommands.contains(command)) {
            throw new Exception("fail");
        }

        // •	You cannot put on socks when it is hot
        // •	You cannot put on a jacket when it is hot
        if ((command == "3") || (command == "5")){
            if (instance instanceof HotResponse) {
                throw new Exception("fail");
            }
        }

        // •	Socks must be put on before shoes (Hot)
        // •	Pants must be put on before shoes (Hot)
        // •	Pants must be put on before shoes (Cold)
        else if (command == "1") {

            if (instance instanceof HotResponse) {
                if (!parsedCommands.contains("6")) {
                    throw new Exception("fail");
                }
            } else {
                if (!parsedCommands.contains("3") || !parsedCommands.contains("6")) {
                    throw new Exception("fail");
                }
            }
        }

        // •	The shirt must be put on before the headwear or jacket
        else if ((command.equals("2")) || (command.equals("5"))) {
            if (!parsedCommands.contains("4")) {
                throw new Exception("fail");
            }
        }


        // •	You cannot leave the house until all items of clothing are on (except socks and a jacket when it’s hot)
        else if (command.equals("7")) {
            if(instance instanceof ColdResponse){
                if(!parsedCommands.contains("1") || !parsedCommands.contains("2") || !parsedCommands.contains("3")
                   || !parsedCommands.contains("4") || !parsedCommands.contains("5")
                   || !parsedCommands.contains("6")){
                    throw new Exception("fail");
                }else{
                    if(!parsedCommands.contains("1") || !parsedCommands.contains("2")
                       || !parsedCommands.contains("4") || !parsedCommands.contains("6")){
                        throw new Exception("fail");
                    }
                }
            }
        }

        return true;
    }
}




















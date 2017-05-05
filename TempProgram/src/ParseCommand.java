import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kobejamesgt on 5/4/17.
 */
public class ParseCommand {

    // HOT 8, 6, 4, 2, 1, 7
    private String input;
    private String[] command;
    private List<String> commandList;

    public ParseCommand(String input) {
        this.input  = input;
        commandList =  new ArrayList<String>();
    }

    public String[] getParsedInput(){
        input.trim();
        command = input.split(",|\\ ");
        for(int i = 0; i< command.length; i++){
            command[i].trim();
            if(!command[i].isEmpty()){
                commandList.add(command[i]);
            }
        }
        return commandList.toArray(new String[commandList.size()]);
    }

}

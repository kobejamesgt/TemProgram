import java.util.Scanner;

/**
 * Created by Kobejamesgt on 5/4/17.
 */
public class Main {
    public static void main(String[] args) {

        try{
            Scanner sc  = new Scanner(System.in);
            String input  =  sc.nextLine();
            TemProgram p = new TemProgram(input);
            String output = p.doIt();
            System.out.println(output);
        }
        catch(Exception ex){
            System.out.print(ex);
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdventOfCode {
    //get input
    public static List getInput(Scanner in) {
        boolean done = false;
        List<String> output = new ArrayList<>();
        String curIn;
        System.out.println("Enter your IDs, type '-1' to stop:");
        while(!done) {
            curIn = in.nextLine();
            if(!curIn.equals("butter")) {
                output.add(curIn);
            } else {
                done = true;
            }
        }
        System.out.println("Done getting input");
        return output;
    }
}

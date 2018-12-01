import java.util.Scanner;

public class FrequencyChanges {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int result = getFrequency(input);

        System.out.println("The frequency is " + result);
    }

    public static int getFrequency(Scanner in) {
        String curIn;
        int curFreq = 0;
        boolean done = false;

        System.out.println("Enter frequency: ");
        while(!done) {
            curIn = in.nextLine();
            if (isInteger(curIn)) {
                curFreq += Integer.parseInt(curIn);
            }
            else if (curIn.equals("stop")) {
                done = true;
            }
        }

        return curFreq;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}

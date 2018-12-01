import java.util.Scanner;

public class FrequencyChanges {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("The frequency is " +
                getFrequency(in));
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

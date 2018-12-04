/*
Challenge: https://adventofcode.com/2018/day/1
After feeling like you've been falling for a few minutes, you look at the device's tiny screen. "Error: Device must be calibrated before first use. Frequency drift detected. Cannot maintain destination lock." Below the message, the device shows a sequence of changes in frequency (your puzzle input). A value like +6 means the current frequency increases by 6; a value like -3 means the current frequency decreases by 3.

For example, if the device displays frequency changes of +1, -2, +3, +1, then starting from a frequency of zero, the following changes would occur:

Current frequency  0, change of +1; resulting frequency  1.
Current frequency  1, change of -2; resulting frequency -1.
Current frequency -1, change of +3; resulting frequency  2.
Current frequency  2, change of +1; resulting frequency  3.
In this example, the resulting frequency is 3.

Here are other example situations:

+1, +1, +1 results in  3
+1, +1, -2 results in  0
-1, -2, -3 results in -6
Starting with a frequency of zero, what is the resulting frequency after all of the changes in frequency have been applied?
 */

import java.util.Scanner;

public class FrequencyChanges {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("The frequency is " +
                getFrequency(in));
    }

    // read in input and calculate frequency
    // input found in FrequencyChangeInput.txt
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

/*
 Rane Wallin

Challenge: https://adventofcode.com/2018/day/1
You notice that the device repeats the same frequency change list over and over. To calibrate the device, you need to find the first frequency it reaches twice.

For example, using the same list of changes above, the device would loop as follows:

Current frequency  0, change of +1; resulting frequency  1.
Current frequency  1, change of -2; resulting frequency -1.
Current frequency -1, change of +3; resulting frequency  2.
Current frequency  2, change of +1; resulting frequency  3.
(At this point, the device continues from the start of the list.)
Current frequency  3, change of +1; resulting frequency  4.
Current frequency  4, change of -2; resulting frequency  2, which has already been seen.
In this example, the first frequency reached twice is 2. Note that your device might need to repeat its list of frequency changes many times before a duplicate frequency is found, and that duplicates might be found while in the middle of processing the list.

Here are other examples:

+1, -1 first reaches 0 twice.
+3, +3, +4, -2, -4 first reaches 10 twice.
-6, +3, +8, +5, -6 first reaches 5 twice.
+7, +7, -2, -7, -4 first reaches 14 twice.
What is the first frequency your device reaches twice?
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FrequencyChangesTwo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("The first duplicate is " + findDupe(in));
    }

    // Find the first repeated frequency
    private static int findDupe(Scanner in) {
        int curFreq = 0;
        int count = 0;
        List<Integer> frequencyList = new ArrayList<Integer>();
        List<Integer> inputs = getInputs(in);

        // calculate the new frequency, check if it's already been used, add to
        // a list. Loops stops once a duplicate is found.
        while(!isDupe(curFreq, frequencyList)) {
            frequencyList.add(curFreq);
            curFreq += inputs.get(count);
            if (count >= inputs.size() - 1) count = 0;
            else
                count++;
        }

        return curFreq;
    }

    // Read in inputs. Done via console, input is found in
    // ../inputs/FrequencyChangeInput.txt
    private static List<Integer> getInputs(Scanner in) {
        boolean done = false;
        String curIn;

        List <Integer> output = new ArrayList<Integer>();

        System.out.println("Enter your frequencies, type 'stop' to stop: ");
        while(!done) {
            curIn = in.nextLine();
            if ((FrequencyChanges.isInteger(curIn))) {
                output.add(Integer.parseInt(curIn));
            } else {
                done = true;
            }
        }

        return output;
    }

    // check if the frequency is already in the list
    private static boolean isDupe(int freq, List<Integer> frequencies) {
        return frequencies.contains(freq);
    }
}

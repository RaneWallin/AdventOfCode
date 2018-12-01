import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FrequencyChangesTwo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("The first duplicate is " + findDupe(in));
    }

    private static int findDupe(Scanner in) {
        int curFreq = 0;
        int count = 0;
        List<Integer> frequencyList = new ArrayList<Integer>();
        List<Integer> inputs = getInputs(in);


        while(!isDupe(curFreq, frequencyList)) {
            //System.out.println("curFreq is " + curFreq);
            //System.out.println("frequencyList is " + frequencyList);
            frequencyList.add(curFreq);
            curFreq += inputs.get(count);
            if (count >= inputs.size() - 1) count = 0;
            else
                count++;

            //System.out.println("curFreq is " + curFreq);
        }

        return curFreq;
    }

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

    private static boolean isDupe(int freq, List<Integer> frequencies) {
        for (int in: frequencies) {
            //System.out.println("in is "+in+" and freq is "+freq);
            if (freq == in) {
                return true;
            }
        }
        return false;
    }


}

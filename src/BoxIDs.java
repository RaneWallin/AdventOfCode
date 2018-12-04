/*
Rane Wallin

Challenge: https://adventofcode.com/2018/day/2
Late at night, you sneak to the warehouse - who knows what kinds of paradoxes you could cause if you were discovered - and use your fancy wrist device to quickly scan every box and produce a list of the likely candidates (your puzzle input).

To make sure you didn't miss any, you scan the likely candidate boxes again, counting the number that have an ID containing exactly two of any letter and then separately counting those with exactly three of any letter. You can multiply those two counts together to get a rudimentary checksum and compare it to what your device predicts.

For example, if you see the following box IDs:

abcdef contains no letters that appear exactly two or three times.
bababc contains two a and three b, so it counts for both.
abbcde contains two b, but no letter appears exactly three times.
abcccd contains three c, but no letter appears exactly two times.
aabcdd contains two a and two d, but it only counts once.
abcdee contains two e.
ababab contains three a and three b, but it only counts once.
Of these box IDs, four of them contain a letter which appears exactly twice, and three of them contain a letter which appears exactly three times. Multiplying these together produces a checksum of 4 * 3 = 12.

What is the checksum for your list of box IDs?
 */
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoxIDs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> inputs = getInputs(in);
        int checksum = getMatches(inputs);

        System.out.println("checksum is "+checksum);
    }

    // Get inputs. Inputs read in via console. Inputs can be found in
    // ../inputs/BoxIDInputs.txt
    // (This is eventually moved to the base class)
    private static List<String> getInputs(Scanner in) {
        boolean done = false;
        List <String> output = new ArrayList<>();
        String curIn;
        System.out.println("Enter your IDs, type '-1' to stop:");

        while(!done) {
            curIn = in.nextLine();
            if(!curIn.equals("stop")) {
                output.add(curIn);
            } else {
                done = true;
            }
        }

        System.out.println("Done getting input");

        return output;
    }

    // Find how many IDs have exactly 2 repeated characters and how many
    // have exactly 3 repeated characters. If an ID has more than one repeated
    // of a particular number (i.e. 2 occurrences of 2 repeated characters) it is
    // only counted one time
    private static int getMatches(List<String> input) {
        int matches = 0;
        int hasTwo = 0;
        int hasThree = 0;

        for(String id: input) {
            // reset
            Set uniqueLetters = new TreeSet();
            boolean hadThreeAlready = false;
            boolean hadTwoAlready = false;

            // put each unique letter into a set
            for(int i = 0; i < id.length(); i++)
                uniqueLetters.add(id.charAt(i));

            // Create an iterator to search the id for matches
            Iterator searcher = uniqueLetters.iterator();


            while(searcher.hasNext()) {
                String letter = searcher.next().toString();

                // use RegEx to determine the number of matching letters
                Pattern pattern = Pattern.compile(letter);
                Matcher matcher = pattern.matcher(id);
                while(matcher.find()) matches++;

                if (matches == 2 && !hadTwoAlready) {
                    hasTwo++;
                    hadTwoAlready = true;
                }
                else if (matches == 3 && !hadThreeAlready) {
                    hasThree++;
                    hadThreeAlready = true;
                }

                matches=0;
            }

        }
        return hasThree * hasTwo;
    }


}

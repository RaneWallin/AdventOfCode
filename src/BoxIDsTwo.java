/*
Rane Wallin

Challenge: https://adventofcode.com/2018/day/2
Confident that your list of box IDs is complete, you're ready to find the boxes full of prototype fabric.

The boxes will have IDs which differ by exactly one character at the same position in both strings. For example, given the following box IDs:

abcde
fghij
klmno
pqrst
fguij
axcye
wvxyz
The IDs abcde and axcye are close, but they differ by two characters (the second and fourth). However, the IDs fghij and fguij differ by exactly one character, the third (h and u). Those must be the correct boxes.

What letters are common between the two correct box IDs? (In the example above, this is found by removing the differing character from either ID, producing fgij.)
 */

import java.util.List;
    import java.util.Scanner;

    public class BoxIDsTwo {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            List inputSet = AdventOfCode.getInput(in);
            System.out.println("The correct characters are: " + doSearch(inputSet));
        }

        // search for correct boxes
        private static String doSearch(List<String> inputs) {
            int count = 0;
            int totalIts = 0;
            String one[], two[];
            while(count < inputs.size()) {
                for(int i = count + 1; i < inputs.size(); i++) {
                    one = inputs.get(count).split("");
                    two = inputs.get(i).split("");
                    if (isMatch(one, two)) {
                        return getCharacters(one, two);
                    }
                    totalIts++;
                }
                count++;
            }
            System.out.println("Total iterations "+totalIts);
            return "no correct characters";
        }

        // check two IDs to see if they fit requirements
        private static boolean isMatch(String[] one, String[] two) {
            int wrongs = 0;
            for(int i = 0; i < one.length; i++) {
                if (!one[i].equals(two[i])) wrongs++;
            }
            return wrongs == 1;
        }

        // Find the common letters between the two strings
        private static String getCharacters(String[] one, String[] two) {
            StringBuilder commonLetters = new StringBuilder();
            for(int i = 0; i < one.length; i++) {
                if (one[i].equals(two[i])) commonLetters.append(one[i]);
            }
            return commonLetters.toString();
        }
    }

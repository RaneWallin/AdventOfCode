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

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

        private static String getCharacters(String[] one, String[] two) {
            StringBuilder commonLetters = new StringBuilder();
            for(int i = 0; i < one.length; i++) {
                if (one[i].equals(two[i])) commonLetters.append(one[i]);
            }
            return commonLetters.toString();
        }
    }

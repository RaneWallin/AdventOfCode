import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
Rane Wallin

Challenge: https://adventofcode.com/2018/day/3
Amidst the chaos, you notice that exactly one claim doesn't overlap by even a single square inch of fabric with any
other claim. If you can somehow draw attention to it, maybe the Elves will be able to make Santa's suit after all!

For example, in the claims above, only claim 3 is intact after all claims are made.

What is the ID of the only claim that doesn't overlap?
 * Sample input:
 * #1 @ 1,3: 4x4
 * #2 @ 3,1: 4x4
 * #3 @ 5,5: 2x2
 *
 * RegEx
 * ("#\\d @ \\d,\\d: \\dx\\d",
 *      squareNum,
 *      firstRow,
 *      firstCol,
 *      rowWidth,
 *      colHeight
 * )
 *
 * r, c
 *
 *
 */

public class OverlappingSquaresTwo extends AdventOfCode {

    private static Set<Integer> allClaims = new TreeSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> input = getInput(in); // input is found in ../inputs/OverlappingSquares.txt
        Map<String, ArrayList<Integer>> allCoordinates = getCoordinates(input);

        System.out.println("The best claim is "+getBestClaim(allCoordinates));

    }

    // find all covered coordinates for each
    private static Map<String, ArrayList<Integer>> getCoordinates(List<String> in) {
        int id = 0, firstRow = 0, firstCol = 0, numRow = 0, numCol = 0;
        List<Integer> coordList;
        Map<String, ArrayList<Integer>> allCoordinates = new HashMap<>();

        for(String tile: in) {
            Pattern pattern =
                    Pattern.compile("#(?<id>\\d+) @ (?<firstCol>\\d+),(?<firstRow>\\d+): (?<numCols>\\d+)x(?<numRows>\\d+)");

            Matcher matcher = pattern.matcher(tile);
            while (matcher.find()) {
                firstRow = Integer.parseInt(matcher.group("firstRow"));
                firstCol = Integer.parseInt(matcher.group("firstCol"));
                numRow = Integer.parseInt(matcher.group("numRows"));
                numCol = Integer.parseInt(matcher.group("numCols"));
                id = Integer.parseInt(matcher.group("id"));
            }

            // Each square inch is given a coordinate on a grid
            // each coordinate is a key with the value being
            // an ArrayList containing the claim IDs that include
            // that coordinate
            for(int i = firstRow; i < firstRow+numRow; i++) {
                for (int j = firstCol; j < firstCol+numCol; j++) {
                    String coord = i+", "+j;
                    allClaims.add(id);
                    coordList = allCoordinates.containsKey(coord) ? allCoordinates.get(coord) : new ArrayList<>();
                    coordList.add(id);
                    allCoordinates.put(coord, (ArrayList) coordList);

                }
            }
        }
        return allCoordinates;
    }

    // Find the claim without any overlaps
    private static int getBestClaim(Map<String, ArrayList<Integer>> allCoordinates) {
        Set<String> allKeys = allCoordinates.keySet();

        // search each coordinate. If the coordinate has more than two claims, remove
        // that ID from the AllClaims set
        for(String coord: allKeys) {
            if (allCoordinates.get(coord).size() > 1) {
                for(int id: allCoordinates.get(coord)) {
                    allClaims.remove(id);
                }
            }
        }

        // Only one claim should be left. Cast to array and return to first member.
        return (int)allClaims.toArray()[0];
    }
}




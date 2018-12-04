import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
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
        List<String> input = getInput(in);
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

    private static int getBestClaim(Map<String, ArrayList<Integer>> allCoordinates) {
        Set<String> allKeys = allCoordinates.keySet();

        for(String coord: allKeys) {
            if (allCoordinates.get(coord).size() > 1) {
                for(int id: allCoordinates.get(coord)) {
                    allClaims.remove(id);
                }
            }
        }

        return (int)allClaims.toArray()[0];
    }
}




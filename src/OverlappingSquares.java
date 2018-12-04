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

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OverlappingSquares extends AdventOfCode {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> input = getInput(in);
        List<String> allCoordinates = getCoordinates(input);

        System.out.println("There are "+getOverlappingNum(allCoordinates)+" overlapping tiles");

    }

    // find all covered coordinates for each
    private static List<String> getCoordinates(List<String> in) {
        int count = 0;
        int firstRow = 0, firstCol = 0, numRow = 0, numCol = 0;
        List<String> allCoordinates = new ArrayList();

        for(String tile: in) {
            Pattern pattern =
                    Pattern.compile("#(?<id>\\d+) @ (?<firstCol>\\d+),(?<firstRow>\\d+): (?<numRows>\\d+)x(?<numCols>\\d+)");

            Matcher matcher = pattern.matcher(tile);
            while (matcher.find()) {
                firstRow = Integer.parseInt(matcher.group("firstRow"));
                firstCol = Integer.parseInt(matcher.group("firstCol"));
                numRow = Integer.parseInt(matcher.group("numRows"));
                numCol = Integer.parseInt(matcher.group("numCols"));
            }

            for(int i = firstRow; i < firstRow+numRow; i++) {
                for (int j = firstCol; j < firstCol+numCol; j++) {
                    String coord = i+", "+j;
                    allCoordinates.add(coord);
                }
            }
        }
            return allCoordinates;
    }


    // Add all coordinates to a set
    // get difference between all coordinates and
    // all unique coordinates
    private static int getOverlappingNum(List<String> in) {
        Set<String> uniqueCoords = new TreeSet<>();

        for(int i = 0; i < in.size(); i++) {
            uniqueCoords.add(in.get(i));
        }

        System.out.println("Size of in "+in.size());
        System.out.println("Size of out "+uniqueCoords.size());
        return in.size() - uniqueCoords.size();
    }
}


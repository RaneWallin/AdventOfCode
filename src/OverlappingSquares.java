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
            Map<String, Integer> allCoordinates = getCoordinates(input);

            System.out.println("There are "+getOverlappingNum(allCoordinates)+" overlapping tiles");

        }

        // find all covered coordinates for each
        private static Map<String, Integer> getCoordinates(List<String> in) {
            int count = 0;
            int firstRow = 0, firstCol = 0, numRow = 0, numCol = 0;
            Map<String, Integer> allCoordinates = new HashMap<>();

            for(String tile: in) {
                Pattern pattern =
                        Pattern.compile("#(?<id>\\d+) @ (?<firstCol>\\d+),(?<firstRow>\\d+): (?<numCols>\\d+)x(?<numRows>\\d+)");

                Matcher matcher = pattern.matcher(tile);
                while (matcher.find()) {
                    firstRow = Integer.parseInt(matcher.group("firstRow"));
                    firstCol = Integer.parseInt(matcher.group("firstCol"));
                    numRow = Integer.parseInt(matcher.group("numRows"));
                    numCol = Integer.parseInt(matcher.group("numCols"));
                }

                // Each square inch is given a coordinate on a grid
                for(int i = firstRow; i < firstRow+numRow; i++) {
                    for (int j = firstCol; j < firstCol+numCol; j++) {
                        String coord = i+", "+j;
                        if (allCoordinates.containsKey(coord)) {
                            allCoordinates.put(coord, allCoordinates.get(coord) + 1);
                        } else {
                            allCoordinates.put(coord, 1);
                        }
                    }
                }
            }
                return allCoordinates;
        }

        private static int getOverlappingNum(Map<String, Integer> in) {
            final int[] dupes = {0};

            in.forEach((k, v) -> {
                if (in.get(k) > 1) dupes[0]++;
            });

            return dupes[0];
        }
    }


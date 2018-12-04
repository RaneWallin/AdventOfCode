/*
Rane Wallin

Challenge: https://adventofcode.com/2018/day/3
The whole piece of fabric they're working on is a very large square - at least 1000 inches on each side.

Each Elf has made a claim about which area of fabric would be ideal for Santa's suit. All claims have an ID and consist
of a single rectangle with edges parallel to the edges of the fabric. Each claim's rectangle is defined as follows:

The number of inches between the left edge of the fabric and the left edge of the rectangle.
The number of inches between the top edge of the fabric and the top edge of the rectangle.
The width of the rectangle in inches.
The height of the rectangle in inches.
A claim like #123 @ 3,2: 5x4 means that claim ID 123 specifies a rectangle 3 inches from the left edge, 2 inches from
the top edge, 5 inches wide, and 4 inches tall. Visually, it claims the square inches of fabric represented by # (and
ignores the square inches of fabric represented by .) in the diagram below:

...........
...........
...#####...
...#####...
...#####...
...#####...
...........
...........
...........
The problem is that many of the claims overlap, causing two or more claims to cover part of the same areas. For example, consider the following claims:

#1 @ 1,3: 4x4
#2 @ 3,1: 4x4
#3 @ 5,5: 2x2
Visually, these claim the following areas:

........
...2222.
...2222.
.11XX22.
.11XX22.
.111133.
.111133.
........
The four square inches marked with X are claimed by both 1 and 2. (Claim 3, while adjacent to the others, does not overlap either of them.)

If the Elves all proceed with their own plans, none of them will have enough fabric. How many square inches of fabric are within two or more claims?
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
            List<String> input = getInput(in); // input is found in ../inputs/OverlappingSquares.txt
            Map<String, Integer> allCoordinates = getCoordinates(input);

            System.out.println("There are "+getOverlappingNum(allCoordinates)+" overlapping tiles");

        }

        // find all covered coordinates for each
        private static Map<String, Integer> getCoordinates(List<String> in) {
            int count = 0;
            int firstRow = 0, firstCol = 0, numRow = 0, numCol = 0;
            Map<String, Integer> allCoordinates = new HashMap<>();

            for(String tile: in) {
                // parse out variables from the input
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

        // Find how many square inches overlap
        private static int getOverlappingNum(Map<String, Integer> in) {
            final int[] dupes = {0};

            in.forEach((k, v) -> {
                if (in.get(k) > 1) dupes[0]++;
            });

            return dupes[0];
        }
    }


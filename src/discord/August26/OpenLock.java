package discord.August26;

import java.io.IOException;
import java.util.*;

public class OpenLock {
    public static void main(String args[]) throws IOException {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        int count = openLock(deadends, target);
        System.out.println("Count of Turns : " + count);
    }

    private static int openLock(String[] deadends, String target) {
        String startStr = "0000";
        Set<String> seenCombinations = new HashSet<>(Arrays.asList(deadends));

        if (seenCombinations.contains(startStr))
            return -1;

        Queue<String> queue = new LinkedList<>();

        seenCombinations.add(startStr);
        queue.add(startStr);

        int turns = 0;

        while (!queue.isEmpty()) {

            int currentCombinationCount = queue.size();

            for (int count = 0; count < currentCombinationCount; count++) {

                String currentCombination = queue.poll();

                if (currentCombination.contains(target))
                    return turns;

                boolean clockWise = true;

                for (int wheel = 0; wheel < 4; wheel++) {

                    String nextCombinationClockwise = currentCombination.substring(0, wheel)
                            + getNextSlot(currentCombination.charAt(wheel), clockWise)
                            + currentCombination.substring(wheel + 1);

                    if (!seenCombinations.contains(nextCombinationClockwise)) {
                        seenCombinations.add(nextCombinationClockwise);
                        queue.add(nextCombinationClockwise);
                    }

                    clockWise = !clockWise;

                    String nextCombinationAntiClockwise = currentCombination.substring(0, wheel)
                            + getNextSlot(currentCombination.charAt(wheel), clockWise)
                            + currentCombination.substring(wheel + 1);

                    if (!seenCombinations.contains(nextCombinationAntiClockwise)) {
                        seenCombinations.add(nextCombinationAntiClockwise);
                        queue.add(nextCombinationAntiClockwise);
                    }

                }
            }
            turns++;
        }
        return -1;

    }

    private static char getNextSlot(char ch, boolean clockWise) {
        if (clockWise) {
            if (ch == '9')
                return '0';
            else
                return (char) (ch + 1);
        } else {
            if (ch == '0')
                return '9';
            else
                return (char) (ch - 1);
        }
    }
}

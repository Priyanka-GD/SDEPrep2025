package tiktok;
public class CardPackets {

    public static int minAdditionalCards(int[] cardTypes, int packets) {
        int n = cardTypes.length;
        int minAdditions = Integer.MAX_VALUE;

        int maxType = 0;

        for(int count : cardTypes)
            maxType = Math.max(maxType, count);

        for (int p = 2; p <= packets; p++) {
            int currentAdditions = 0;

            for (int count : cardTypes) {
                int remainder = count % p;
                int toAdd = (remainder == 0) ? 0 : p - remainder;
                currentAdditions += toAdd;
            }
            System.out.println("currentAdditions : "+ currentAdditions);
            minAdditions = Math.min(minAdditions, currentAdditions);
        }

        return minAdditions;
    }

    public static void main(String[] args) {
        int[] cardTypes = {4, 7, 5, 11, 15};
        int packets = cardTypes.length; // Maximum number of packets to evaluate
        System.out.println("Minimum additional cards needed: " + minAdditionalCards(cardTypes, packets));
    }
}


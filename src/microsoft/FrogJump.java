package microsoft;

public class FrogJump {
    public static void main(String[] args) {
        int[] blocks = {1, 5, 5, 2, 6};
        System.out.println("Maximum Distance : " + maxDistance(blocks));
    }

    public static int maxDistance(int[] blocks) {
        int len = blocks.length;
        if (len <= 1)
            return len;

        int[] leftDist = new int[len];
        int[] rightDist = new int[len];

        leftDist[0] = 0;
        for (int i = 1; i < len; i++) {
            leftDist[i] = (blocks[i] <= blocks[i - 1]) ? leftDist[i - 1] : i;
        }

        rightDist[len - 1] = len - 1;
        for (int i = len - 2; i >= 0; i--) {
            rightDist[i] = (blocks[i] <= blocks[i + 1]) ? rightDist[i + 1] : i;
        }

        int maxDist = 0;
        for (int i = 0; i < len; i++) {
            maxDist = Math.max(maxDist, rightDist[i] - leftDist[i] + 1);
        }

        return maxDist;
    }
}

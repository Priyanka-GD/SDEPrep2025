package tiktok;

import java.io.IOException;
import java.util.Arrays;

public class ServerUpgrade {

        public static void main(String args[])throws IOException {
            int money[] = {8,9};
            int numServers[] = {4,3};
            int upgrade[] = {4,5};
            int sell[] = {4, 2};
            int count[] = maximizeUpgradingServerCount(money, numServers, upgrade, sell);

            for(int idx = 0; idx < count.length; idx++)
                System.out.println("Server " + idx + " : " + count[idx]);
        }

        private static int[] maximizeUpgradingServerCount(int[] money, int[] numServers, int[] upgrade, int[] sell) {
            int size = numServers.length;
            int result[] = new int[size];

            for(int idx = 0; idx < size; idx++) {
                // Calculate how many servers can be upgraded with the initial funds
                int upgradesWithInitialMoney = Math.min(numServers[idx], money[idx] / upgrade[idx]);
                // If selling one server helps, calculate the impact
                if (numServers[idx] > 1) {
                    int newFunds = money[idx] + sell[idx];
                    int upgradesWithSale = Math.min(numServers[idx] - 1, newFunds / upgrade[idx]);
                    result[idx] = Math.max(upgradesWithInitialMoney, upgradesWithSale);
                } else {
                    result[idx] = upgradesWithInitialMoney;
                }
            }
            return result;
        }
    }


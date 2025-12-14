package amazoninternal.codingassessment;

import java.util.*;

public class TopNCustomers {
    public static void main(String[] args) {

        String log =
                "11-Jun-2018 1:00 AM\t1ABCDEFGHI\n" +
                        "11-Jun-2018 3:01 AM\t1ABCDEFGHI\n" +
                        "11-Jun-2018 4:12 AM\t2ABCDEFGHI\n" +
                        "12-Jun-2018 8:23 AM\t3ABCDEFGHI\n" +
                        "12-Jun-2018 4:21 PM\t2ABCDEFGHI\n" +
                        "13-Jun-2018 1:14 PM\t3ABCDEFGHI";

        Map<String, Set<String>> userToDates = new HashMap<>();

        String[] lines = log.split("\\n");

        for (String line : lines) {
            // TAB delimited
            String[] parts = line.split("\\t");

            String timestamp = parts[0];     // "11-Jun-2018 1:00 AM"
            String userId = parts[1];        // "1ABCDEFGHI"

            // Extract date (first token)
            String date = timestamp.split(" ")[0];

            userToDates
                    .computeIfAbsent(userId, k -> new HashSet<>())
                    .add(date);
        }

        List<String> repeatedCustomerList = new ArrayList<>();
        for (String key : userToDates.keySet()) {
            if (userToDates.get(key).size() > 1) {
                repeatedCustomerList.add(key);
            }
        }
        System.out.println("Repeat customers: " + repeatedCustomerList.toString());
    }
}

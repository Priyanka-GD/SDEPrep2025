package functionalprogramming.streams;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BasicSyntax {
    public static void main(String args[]) throws IOException {


        // calculate the average of a list of integers using streams
        Integer[] numbers = {1, 4, 5, 6, 7, 8, 9, 22, 29, 78, 99, 4, 6, 10, 9};
        List<Integer> modifiableList = new ArrayList<>(Arrays.asList(numbers));

        double result = modifiableList.stream().mapToDouble(Integer::doubleValue)
                .average().getAsDouble();
        System.out.println("Average of a list " + result);

        // to convert a list of strings to uppercase or lowercase using stream
        List<String> stringlist = Arrays.asList(new String[]{"Hello", "mean", "people", "World",});

        List<String> resultList = stringlist.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("List of Strings to uppercase " + resultList);

        // To calculate the sum of all even, odd numbers in a list using streams.

        int evenSum = modifiableList.stream().filter(a -> a % 2 == 0).mapToInt(Integer::intValue).sum();
        System.out.println("Sum of even numbers " + evenSum);

        int oddSum = modifiableList.stream().filter(a -> a % 2 != 0).mapToInt(Integer::intValue).sum();
        System.out.println("Sum of Odd numbers " + oddSum);

        // To remove all duplicate elements from a list using streams

        List<Integer> uniqueList = modifiableList.stream().distinct().toList();
        System.out.println("Unique list " + uniqueList);

        //to count the number of strings in a list that start with a specific letter using streams

        long count = stringlist.stream().filter(s -> s.startsWith("H")).count();
        System.out.println("Count " + count);

        //to sort a list of strings in alphabetical order, ascending and descending using streams

        List<String> sortedList = stringlist.stream().sorted().toList();
        System.out.println("Sorted list ascending order " + sortedList);

        List<String> descendingOrder = stringlist.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("Sorted list descending order " + descendingOrder);

        //to find the maximum and minimum values in a list of integers using streams

        int max = modifiableList.stream().mapToInt(Integer::intValue).max().getAsInt();
        int min = modifiableList.stream().mapToInt(Integer::intValue).min().getAsInt();

        System.out.println("Maximum value " + max + " minimum value " + min);

        //to find the second smallest and largest elements in a list of integers using streams.
        int secondSmallest = modifiableList.stream().sorted().skip(1).findFirst().get();
        int secondLargest = modifiableList.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.println("Second smallest value " + secondSmallest + " largest value " + secondLargest);
    }

}

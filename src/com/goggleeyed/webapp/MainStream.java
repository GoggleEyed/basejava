package com.goggleeyed.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        System.out.println(minVal(1, 2, 3, 3, 2, 3));
        System.out.println(minVal(9, 8));
        System.out.println(minVal(10, 7, 3, 8, 2, 1, 2, 13, 8, 7, 5, 5, 5));
        System.out.println(oddOrEven(1, 2, 3));
        System.out.println(oddOrEven(1, 2, 3, 4));
        System.out.println(oddOrEven(1, 2, 3, 4, 5));
        System.out.println(oddOrEven(10, 7, 3, 8, 2, 1, 2, 13, 8, 7, 5, 5, 5, 1));
    }

    private static int minVal(int... values) {
        return minValue(values);
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .filter((a) -> a < 10 && a > 0)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> a * 10 + b);
    }

    private static List<Integer> oddOrEven(Integer... integers) {
        return oddOrEven(Arrays.asList(integers));
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
//        Integer sum = integers.stream()
//                .reduce(0, Integer::sum);
//        return integers.stream()
//                .filter((a) -> a % 2 != sum % 2)
//                .collect(Collectors.toList());

        Map<Boolean, List<Integer>> map = integers.stream()
                .collect(Collectors.partitioningBy((a) -> a % 2 == 0, Collectors.toList()));
        return map.get(map.get(false).size() % 2 != 0);
    }
}

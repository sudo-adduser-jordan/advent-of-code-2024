package aoc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class DayOne {
    public int getResultA() throws IOException {
        List<Integer> list_left = new ArrayList<>();
        List<Integer> list_right = new ArrayList<>();
        Scanner scanner = new Scanner(new File("aoc/app/src/main/resources/InputOne.txt"));
        // Scanner scanner = new Scanner(new File("aoc/app/src/main/resources/SampleOne.txt"));

        while (scanner.hasNextLine()) {
            list_left.add(scanner.nextInt());
            list_right.add(scanner.nextInt());
        }
        scanner.close();

        if (list_left.size() != list_right.size()) throw new IllegalArgumentException("Lists must be of the same size");

        Collections.sort(list_left);
        Collections.sort(list_right);

        int total_distance = IntStream.range(0, list_left.size())
                .map(index -> Math.abs(list_left.get(index) - list_right.get(index)))
                .sum();

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(String.valueOf(total_distance)), null);

        return total_distance;
    }

    public int getResultB() throws IOException {
        int result = 69;
        Scanner scanner = new Scanner(new File("aoc/app/src/main/resources/SampleOne.txt"));
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(input);
        }
        scanner.close();
        return result;
    }
}

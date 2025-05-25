package aoc;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class DayOne {

    public int getResultA() throws IOException {
        int total_distance;
        List<Integer> list_left = new ArrayList<>();
        List<Integer> list_right = new ArrayList<>();
        Scanner scanner = new Scanner(new File("aoc/app/src/main/resources/InputOne.txt"));

        while (scanner.hasNextLine()) {
            list_left.add(scanner.nextInt());
            list_right.add(scanner.nextInt());
        }
        scanner.close();

        if (list_left.size() != list_right.size()) {
            throw new IllegalArgumentException("Lists must be of the same size");
        }

        Collections.sort(list_left);
        Collections.sort(list_right);

        total_distance = IntStream.range(0, list_left.size())
                .map(index -> Math.abs(list_left.get(index) - list_right.get(index)))
                .sum();

        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(String.valueOf(total_distance)), null);

        return total_distance;
    }

    public int getResultB() throws IOException {
        int similarity_score = 0;
        List<Integer> list_left = new ArrayList<>();
        List<Integer> list_right = new ArrayList<>();
        Map<Integer, Integer> frequency_map = new HashMap<>();
        Scanner scanner = new Scanner(new File("aoc/app/src/main/resources/InputOne.txt"));

        while (scanner.hasNextLine()) {
            list_left.add(scanner.nextInt());
            list_right.add(scanner.nextInt());
        }
        scanner.close();

        if (list_left.size() != list_right.size()) {
            throw new IllegalArgumentException("Lists must be of the same size");
        }

        for (int location_id : list_right) {
            if (frequency_map.containsKey(location_id))
                frequency_map.put(location_id, frequency_map.get(location_id) + 1);
            else
                frequency_map.put(location_id, 1);
        }

        for (int location_id : list_left) {
            if (frequency_map.containsKey(location_id))
                similarity_score += location_id * frequency_map.get(location_id);
        }

        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(String.valueOf(similarity_score)), null);

        return similarity_score;
    }
}

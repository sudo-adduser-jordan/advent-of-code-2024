package aoc;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DayTwo {

    public int getResultA() throws IOException {
        int safe_reports = 0;
        Scanner scanner = new Scanner(new File("aoc/app/src/main/resources/InputTwo.txt"));

        while (scanner.hasNextLine()) {
            String report = scanner.nextLine();
            List<Integer> levels = Arrays.stream(report.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            if (isReportValid(levels))
                safe_reports += 1;
        }
        scanner.close();

        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(String.valueOf(safe_reports)), null);

        return safe_reports;
    }

    public static boolean isReportValid(List<Integer> report) {
        int previous_level = report.get(0);
        boolean isValidReport = true;
        boolean isIncreasing = false;
        boolean isDecreasing = false;

        for (Integer level : report.subList(1, report.size())) {
            if (previous_level == level) {
                isValidReport = false;
                break;
            }

            if (previous_level < level) {
                isIncreasing = true;
            }

            if (previous_level > level) {
                isDecreasing = true;
            }

            if (isIncreasing && isDecreasing) {
                isValidReport = false;
                return isValidReport;
            }

            if (Math.abs(previous_level - level) > 3) {
                isValidReport = false;
                break;
            }
            previous_level = level;
        }
        return isValidReport;
    }

    public int getResultB() throws IOException {
        int safe_reports = 0;
        Scanner scanner = new Scanner(new File("aoc/app/src/main/resources/InputTwo.txt"));

        while (scanner.hasNextLine()) {
            String report = scanner.nextLine();
            List<Integer> levels = Arrays.stream(report.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            if (isReportValid(levels)) {
                safe_reports += 1;
            } else {
                for (int index = 0; index < levels.size(); index++) {
                    List<Integer> levels_modified = Arrays.stream(report.split(" "))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());        
                    levels_modified.remove(index);

                    if (isReportValid(levels_modified)) {
                        safe_reports += 1;
                        break;
                    }
                }
            }
        }
        scanner.close();

        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(String.valueOf(safe_reports)), null);

        return safe_reports;
    }
}

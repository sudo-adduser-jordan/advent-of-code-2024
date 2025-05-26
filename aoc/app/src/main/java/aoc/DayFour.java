package aoc;

import java.awt.Toolkit;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.datatransfer.StringSelection;




public class DayFour {

    public int getResultA(String file_name) throws IOException {
        int result = 0;

        String data = new String(getClass().getResourceAsStream(file_name).readAllBytes(), StandardCharsets.UTF_8);

        // String data = new
        // String(getClass().getResourceAsStream(file_name).readAllBytes(),
        // StandardCharsets.UTF_8);

        System.out.println(data);

        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(String.valueOf(result)), null);

        return result;
    }

    public int getResultB() throws IOException {
        int result = 0;
        String data = new String(Files.readAllBytes(Paths.get("aoc/app/src/main/resources/InputThree.txt")));

        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(String.valueOf(result)), null);

        return result;
    }
}

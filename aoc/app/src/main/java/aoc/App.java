package aoc;

import java.io.IOException;
import java.sql.Time;

// import java.awt.datatransfer.Clipboard;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Advent of Code 2024\n");

        DayOne dayOne = new DayOne();
        System.out.println("Result A: " + dayOne.getResultA());
        // System.out.println("Result B: " + dayOne.getResultB());

        if (java.awt.GraphicsEnvironment.isHeadless()) System.err.println("Cannot access clipboard in headless environment");

        // clipboard will be empty on exit
        System.out.println("Press any key to exit...");
        try {
            System.in.read(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Exiting...");

    }
}
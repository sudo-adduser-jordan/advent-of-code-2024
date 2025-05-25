package aoc;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        if (java.awt.GraphicsEnvironment.isHeadless()) System.err.println("Cannot access clipboard in headless environment");
        
        System.out.println("\nAdvent of Code 2024\n");

        // DayOne dayOne = new DayOne();
        // System.out.println("\nResult A: " + dayOne.getResultA());
        // System.out.println("\nResult B: " + dayOne.getResultB());

        DayTwo dayTwo = new DayTwo();
        // System.out.println("\nResult A: " + dayTwo.getResultA());
        System.out.println("\nResult B: " + dayTwo.getResultB());

        System.out.println("Press any key to exit...");
        // System.in.read(); // clipboard will empty on exit 
        System.out.println("Exiting...");
    }
}
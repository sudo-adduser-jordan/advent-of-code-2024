package aoc;

public class App {
    
    public static void main(String[] args) throws Exception {
        if (java.awt.GraphicsEnvironment.isHeadless()) System.err.println("Cannot access clipboard in headless environment");
        


        System.out.println("\nAdvent of Code 2024\n");

        // DayOne day_one = new DayOne();
        // System.out.println("\nResult A: " + day_one.getResultA());
        // System.out.println("\nResult B: " + day_one.getResultB());

        // DayTwo day_two = new DayTwo();
        // System.out.println("\nResult A: " + day_two.getResultA());
        // System.out.println("\nResult B: " + day_two.getResultB());

        // DayThree day_three = new DayThree();
        // System.out.println("\nResult A: " + day_three.getResultA());
        // System.out.println("\nResult B: " + day_three.getResultB());

        DayFour day_four = new DayFour();
        System.out.println("\nResult A: " + day_four.getResultA("/InputFour.txt"));
        // System.out.println("\nResult A: " + day_four.getResultB("/InputFour.txt"));



        System.out.println("Press any key to exit...");
        // System.in.read(); // clipboard will empty on exit 
        System.out.println("Exiting...");
    }
}
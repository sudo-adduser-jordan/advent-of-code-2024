package aoc;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayFourTest {
    @Test void PartOne() throws IOException
    {
        DayFour day_four = new DayFour();
        assertEquals(18, day_four.getResultA("/SampleFour.txt"), "Result should equal the expected value.");
    };

    @Test void PartTwo() throws IOException
    {
        DayFour day_four = new DayFour();
        assertEquals(69, day_four.getResultA("/SampleFour.txt"), "Result should equal the expected value.");
    };
}

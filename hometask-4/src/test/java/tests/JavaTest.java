package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JavaTest {

    @Test
    void shouldChangeToTheOppositeMinValue() {
        int intMax = Integer.MAX_VALUE;
        System.out.println(intMax + 1);

        assertEquals(Integer.MIN_VALUE, intMax + 1);
    }

    @Test
    void shouldChangeToTheOppositeMaxValue() {
        int intMin = Integer.MIN_VALUE;
        System.out.println(intMin - 1);

        assertEquals(Integer.MAX_VALUE, intMin - 1);
    }

}

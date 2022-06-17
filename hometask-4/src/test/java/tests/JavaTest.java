package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JavaTest {

    @Test
    void dataTypeExample() {

        byte byteValue = 115;                                                                          //(-128 to +127)
        short shortValue = 28923;                                                                //(-32 768 to +32 767)
        int intValue = -147_483_647;                                           //int (-2 147 483 648 to +2 147 483 647)
        long longValue = 7_233_371_036_854_775_808L;       //(-9 223 372 036 854 775 808 to +9 223 372 036 854 775 807)
        float floatValue = 445F;                                                               //(1.40e-45 to 3.40e+38)
        double doubleValue = 332.99;                                                           //(4.9e-324 to 1.8e+308)
        char charValue = 'a';                                 //(min «\u0000» (or 0) max  «\uffff» (or 65535 inclusive)

        System.out.println("Example of byte: " + byteValue);
        System.out.println("Example of short: " + shortValue);
        System.out.println("Example of int: " + intValue);
        System.out.println("Example of long: " + longValue);
        System.out.println("Example of float: " + floatValue);
        System.out.println("Example of double: " + doubleValue);
        System.out.println("Example of char: " + charValue);
        System.out.println();
    }

    @Test
    void booleanExample() {

        boolean booleanAndExample = (3 < 4) && (4 > 5);
        System.out.println("This is an example of boolean \"and\" in Java (True and False): " + booleanAndExample);

        boolean booleanOrExample = (2 <= 3) || (3 >= 4);
        System.out.println("This is an example of boolean \"or\" in Java (True and False): " + booleanOrExample);
    }

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

    @Test
    void arithmeticOperationsExample() {
        int a = 1;
        int b = 2;
        double c = 3;
        double d = 4;
        int result1 = ++a;
        double result2 = d--;
        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(d - c);
        System.out.println(a * c);
        System.out.println(d / b);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println();
    }

    @Test
    void isCold() {
        int temperature = 5;
        if (temperature < 0) {
            System.out.println("На улице холодно");
        } else {
            System.out.println(" На улице тепло");
        }
    }

    @Test
    void timeForTheArmy() {
        int age = 18;
        if (age == 18)
            System.out.println("Явитесь в военкомат");
        else
            System.out.println("Все равно явитесь в военкомат");
    }

}

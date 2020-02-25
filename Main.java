package com.company;


import java.util.Arrays;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {
        //System.out.println(Nano.parseNano("[["));
        //System.out.println(Nano.toString(0));

    }
}


class Nano {
    public static char[] digit
            = {'#', '!', '%', '@', '(', ')', '[', ']', '$'};

    /* ==========================================================
       Return the 2's complement binary representation for the
       Nano number given in String s
       ========================================================== */
    public static int parseNano(String s) {
      /* ------------------------------------------------------------------
         This loop checks if the input contains an illegal (non-Nano) digit
         ------------------------------------------------------------------ */

        for (int i = 0; i < s.length(); i++) {
            int j = 0;
            while (j < digit.length) {
                if (s.charAt(i) == digit[j] || s.charAt(i) == '-') {
                    break;
                }
                j++;
            }

            if (j >= digit.length) {
                System.out.println("Illegal nano digit found in input: "
                        + s.charAt(i));
                System.out.println("A Nano digit must be one of these: "
                        + Arrays.toString(digit));
                System.exit(1);
            }
        }

        //Test Cases:
        //s = "#" return 0
        //s = "!" return 1
        //s = "%" return 2
        //s = "@" return 3
        //s = "(" return 4
        //s = ")" return 5
        //s = "[" return 6
        //s = "]" return 7
        //s = "$" return 8
        //s = "-!" return -1
        //s = "!#" return 9
        //s = "!!" return 10
        //s = !#% return 83
        //s = -[[ return -60

        // Write the parseNano() code here
        int sum = 0;
        char negativeCheck = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < digit.length; j++) {
                if (s.charAt(i) == digit[j]) {
                    //use the handy Math.pow for the 9^ (s.length() - 1 - i)
                    //-1 is used to make the length correct
                    //j to multiply in order for the chars to register
                    sum += j * Math.pow(9, s.length() - 1 - i);
                }
            }
        }
        if (negativeCheck == '-') {
            //if there is a "-" at the beginning, make it negative
            sum *= -1;
        }

        return sum;
    }


    /* ==========================================================
       Return the String of Nano digit that represent the value
       of the 2's complement binary number given in
       the input parameter 'value'
       ========================================================== */
    public static String toString(int value) {
        // Write the toString() code here
        int remainder;
        String sum = "";
        String negative = "-";
        int value1;
        // if is a positive value
        if (value > 0) {
            while (value > 0) {
                remainder = value % 9;
                sum = digit[remainder] + sum;
                value = value / 9;
            }
        }
        //if is a negative value
        else if (value < 0) {
            value1 = value * -1;
            return negative + toString(value1);
        }
        //if 0
        else if (value == 0) {
            return "#";
        }

        return sum;
    }

}

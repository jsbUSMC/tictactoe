package misc;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: John S. Bissonette
 * NetID: jbisson2
 * Date: 9/25/17
 * Assignment -
 */
public class Misc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Test the string entered against regex pattern
        Pattern p = Pattern.compile("[1-9]{2}");

        System.out.print("Enter number: ");
        String loc = sc.nextLine();
        Matcher m = p.matcher(loc);
        boolean good = m.matches();

        while (!good) {
            System.out.print("\nEnter a two-digit number, each digit between 1 and 9: ");
            loc = sc.nextLine();
            m = p.matcher(loc);
            good = m.matches();
        }

        String b = loc.substring(0,1);
        String i = loc.substring(1);
        int board = Integer.parseInt(b);
        int index = Integer.parseInt(i);

//        while (loc.length() != 2 || (0 < board && board > 9) || (0 < index && index > 9)) {
//            System.out.print("\nEnter a two-digit number, each digit between 1 and 9: ");
//            loc = sc.nextLine();
//        }

        System.out.println(String.format("Board: %d, Index: %d", board, index));
    }
}

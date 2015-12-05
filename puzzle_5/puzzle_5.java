import java.io.*;

public class puzzle_5 {

    static String[] naughty_str = {"ab", "cd", "pq", "xy"};

    // Part 1 - niceness criteria for our strings:
    // It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
    // It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
    // It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
    static boolean nice_part1(String str) {
        char a, b;
        boolean letter_repeats = false;
        int vowel_count = 0;

        for (String bad:naughty_str) {
            if (str.indexOf(bad) >= 0)  {
                return false;
            }
        }
        for (int i = 0; i<str.length(); i++) {
            a = str.charAt(i);
            if (i+1 < str.length()) {
                b = str.charAt(i+1);
                if (a == b) {
                    letter_repeats = true;
                }
            }
            switch (a) {
                case 'a': 
                case 'e': 
                case 'i': 
                case 'o': 
                case 'u': vowel_count++;
                          break;
                default:  break;
            }
        }
        return letter_repeats && (vowel_count >= 3);
    }

    public static void main(String[] args) {
        int p1_num_nice = 0;
        int p1_num_naughty = 0;

        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            for(String line; (line = br.readLine()) != null; ) {
                if (nice(line)) {
                    p1_num_nice++;
                } else {
                    p1_num_naughty++;
                }
            }
            System.out.print("Total Nice: " + p1_num_nice + "\n");
            System.out.print("Total Naughty: " + p1_num_naughty + "\n");

        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}

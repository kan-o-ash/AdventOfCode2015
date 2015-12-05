import java.io.*;

public class puzzle_5 {

    // Part 1 - niceness criteria for our strings:
    // It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
    // It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
    // It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
    // This is O(n), where n is string length.
    static boolean nice_part1(String str) {
        String[] naughty_str = {"ab", "cd", "pq", "xy"};
        char a, b;
        boolean letter_repeats = false;
        int vowel_count = 0;

        // Does str contain any naught words?
        for (String bad_word:naughty_str) {
            if (str.indexOf(bad_word) >= 0)  { 
                return false;
            }
        }
        for (int i = 0; i<str.length(); i++) {
            a = str.charAt(i);

            // Do any letters repeat?
            if (i+1 < str.length()) {
                b = str.charAt(i+1);
                if (a == b) {
                    letter_repeats = true;
                }
            } // Do we have at least three vowels?
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

    // Part 2 - niceness criteria for our strings:
    // It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
    // It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
    static boolean nice_part2(String str) {
        char a, b;
        boolean repeat_with_char_between = false, two_repeated = false;
        String sub;

        for (int i = 0; i<str.length(); i++) {
            a = str.charAt(i);
            if (i+2 < str.length()) {
                b = str.charAt(i+2);
                if (a == b) {
                    repeat_with_char_between = true;
                }
            }
        }
        for (int i = 0; i<str.length()-3; i++) {
            sub = str.substring(i,i+2);
            if (str.indexOf(sub, i+2) > 0) {
                two_repeated = true;
            }
        }

        return repeat_with_char_between && two_repeated;
    }

    public static void main(String[] args) {
        int p1_num_nice = 0, p2_num_nice = 0;
        int p1_num_naughty = 0, p2_num_naughty = 0;
        int dummy;

        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            for(String line; (line = br.readLine()) != null; ) {
                dummy = nice_part1(line) ? p1_num_nice++: p1_num_naughty++;
                dummy = nice_part2(line) ? p2_num_nice++: p2_num_naughty++;
            }
            System.out.print("Total Nice: " + p1_num_nice + "\n");
            System.out.print("Total Naughty: " + p1_num_naughty + "\n");
            System.out.print("Total Nice: " + p2_num_nice + "\n");
            System.out.print("Total Naughty: " + p2_num_naughty + "\n");
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}

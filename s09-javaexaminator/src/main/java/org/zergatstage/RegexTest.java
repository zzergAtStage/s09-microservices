package org.zergatstage;

/**
 * @author father
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        String[] words = {"bar", "ear", "car", "march"};
        String regex = "[ec]ar"; // Replace with your desired regex

        for (String word : words) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(word);

            if (matcher.find()) {
                System.out.println(word + " matches the regex.");
            } else {
                System.out.println(word + " does not match the regex.");
            }
        }
    }
}
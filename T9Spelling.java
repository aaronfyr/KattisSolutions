import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class T9Spelling {
    private static final int ASCII_CORRECTION = 96;
    private static final int SPACING = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> keyPresses = new ArrayList<>();
        keyPresses.add("0");
        keyPresses.add("2");
        keyPresses.add("22");
        keyPresses.add("222");
        keyPresses.add("3");
        keyPresses.add("33");
        keyPresses.add("333");
        keyPresses.add("4");
        keyPresses.add("44");
        keyPresses.add("444");
        keyPresses.add("5");
        keyPresses.add("55");
        keyPresses.add("555");
        keyPresses.add("6");
        keyPresses.add("66");
        keyPresses.add("666");
        keyPresses.add("7");
        keyPresses.add("77");
        keyPresses.add("777");
        keyPresses.add("7777");
        keyPresses.add("8");
        keyPresses.add("88");
        keyPresses.add("888");
        keyPresses.add("9");
        keyPresses.add("99");
        keyPresses.add("999");
        keyPresses.add("9999");

        int numOfInput = sc.nextInt();
        sc.nextLine();
        List<String> inputs = new ArrayList<>();

        for (int i = 0; i < numOfInput; i++) {
            inputs.add(sc.nextLine());
        }

        for (int i = 0; i < inputs.size(); i++) {
            StringBuilder sb = new StringBuilder();
            char lastDigitofCurrentChar;
            char firstDigitOfNextChar;
            String currentKeyPress;
            String nextKeyPress;

            char[] chars = inputs.get(i).toCharArray();
            int lengthOfChars = chars.length;

            for (int j = 0; j < lengthOfChars; j++) {
                int currentCharIndex = chars[j] - ASCII_CORRECTION;
                if (chars[j] == ' ') {
                    currentCharIndex = SPACING;
                }
                currentKeyPress = keyPresses.get(currentCharIndex);
                sb.append(currentKeyPress);
                lastDigitofCurrentChar = currentKeyPress.charAt(currentKeyPress.length() - 1);
                if (j != lengthOfChars - 1) { // reached the last element
                    int nextCharIndex = chars[j + 1] - ASCII_CORRECTION;
                    if (chars[j + 1] == ' ') {
                        nextCharIndex = SPACING;
                    }
                    nextKeyPress = keyPresses.get(nextCharIndex);
                    firstDigitOfNextChar = nextKeyPress.charAt(0);
                    if (lastDigitofCurrentChar == firstDigitOfNextChar) {
                        sb.append(" ");
                    }
                }
            }
            System.out.println(String.format("Case #%d: " + sb, i + 1));
        }

        sc.close();
    }
}
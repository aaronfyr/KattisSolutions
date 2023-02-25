
// Aaron Foo Yu Rong, A0230343W
import java.io.*;
import java.util.*;

class SortOfSorting {
    public static void main(String[] argv) throws Exception {

        Kattio io = new Kattio(System.in, System.out);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        while (true) {
            pw.println();
            int temp = io.getInt();
            int noOfStudents = 0;
            ArrayList<String> students = new ArrayList<>();
            if (temp == 0) {
                break;
            } else {
                noOfStudents = temp;
            }

            for (int i = 0; i < noOfStudents; i++) {
                students.add(io.getWord());
            }

            Collections.sort(students, new compareStudentNames());
            students.forEach(x -> pw.println(x));
        }
        pw.flush();
    }
}

class compareStudentNames implements Comparator<String> {
    public int compare(String firstStudentName, String secondStudentName) {
        char firstStudentFirstLetter = firstStudentName.charAt(0);
        char firstStudentSecondLetter = firstStudentName.charAt(1);
        char secondStudentFirstLetter = secondStudentName.charAt(0);
        char secondStudentSecondLetter = secondStudentName.charAt(1);
        if (firstStudentFirstLetter > secondStudentFirstLetter) {
            return 1;
        } else if (firstStudentFirstLetter < secondStudentFirstLetter) {
            return -1;
        } else {
            if (firstStudentSecondLetter > secondStudentSecondLetter) {
                return 1;
            } else if (firstStudentSecondLetter < secondStudentSecondLetter) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) {
            }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
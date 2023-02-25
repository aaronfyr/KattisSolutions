// Aaron Foo Yu Rong, A0230343W

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class BestRelayTeam {
    private static final int maxNoOfRunners = 4;

    public static void main(String[] argv) throws Exception {

        Kattio io = new Kattio(System.in, System.out);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int noOfRunners = io.getInt();
        ArrayList<Runner> listOfRunners = new ArrayList<>();
        ArrayList<Runner> currentTeam = new ArrayList<>();
        ArrayList<Runner> bestTeam = new ArrayList<>();
        double bestTeamTiming = 0;
        double currentTeamTiming = 0;
        int noOfRunnersAdded = 0;

        for (int i = 0; i < noOfRunners; i++) {
            listOfRunners.add(new Runner(io.getWord(), io.getDouble(), io.getDouble()));
        }

        Collections.sort(listOfRunners, new compareRunnersByOtherLegTime());

        for (int i = 0; i < noOfRunners; i++) {
            // add first leg runner to current team and add his timing to current team's
            // time taken
            Runner firstLegRunner = listOfRunners.get(i);
            currentTeam.add(firstLegRunner);
            currentTeamTiming += firstLegRunner.getFirstLegTiming();
            noOfRunnersAdded++;

            for (int k = 0; noOfRunnersAdded < maxNoOfRunners; k++) {
                // should not take into account first leg runner
                if (k == i) {
                    continue;
                }

                // add other leg runner to current team and add his timing to current team's
                // time taken
                Runner currentOtherLegRunner = listOfRunners.get(k);
                currentTeam.add(currentOtherLegRunner);
                currentTeamTiming += currentOtherLegRunner.getOtherLegTiming();
                noOfRunnersAdded++;
            }

            if (i == 0) { // first team should be used as benchmark
                bestTeam = new ArrayList<Runner>(List.copyOf(currentTeam));
                bestTeamTiming = currentTeamTiming;
            } else if (currentTeamTiming < bestTeamTiming) { // overwrite previous best team if current team have better
                                                             // timings
                bestTeam = new ArrayList<Runner>(List.copyOf(currentTeam));
                bestTeamTiming = currentTeamTiming;
            }

            // reset the variables
            noOfRunnersAdded = 0;
            currentTeam.clear();
            currentTeamTiming = 0;
        }
        pw.println(bestTeamTiming);
        bestTeam.forEach(x -> pw.println(x.getName()));
        pw.flush();
    }
}

// used to sort the other leg times in ascending order (note: first leg time is
// not sorted!)
class compareRunnersByOtherLegTime implements Comparator<Runner> {
    public int compare(Runner a, Runner b) {
        if (a.getOtherLegTiming() > b.getOtherLegTiming()) {
            return 1;
        } else if (a.getOtherLegTiming() < b.getOtherLegTiming()) {
            return -1;
        } else {
            return 0;
        }
    }
}

// Runner class for ease of tracking

class Runner {
    private String name;
    private double firstLegTiming;
    private double otherLegTiming;

    Runner(String name, double firstLegTiming, double otherLegTiming) {
        this.name = name;
        this.firstLegTiming = firstLegTiming;
        this.otherLegTiming = otherLegTiming;
    }

    public String getName() {
        return this.name;
    }

    public double getFirstLegTiming() {
        return this.firstLegTiming;
    }

    public double getOtherLegTiming() {
        return this.otherLegTiming;
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

// Aaron Foo Yu Rong, A0230343W

// If the player’s hands are still folded, they are split into two fists (the coconut is cracked in two halves).
// The next round starts with the fist that is the first half of the coconut, then the second half, then going to the next player.

// If a fist is touched last, the hand is turned palm down (the milk spills out).
// The next round starts with the next hand in clockwise order, which could be the other hand of the same player,
// or it could be the hand or folded hands belonging to the next player.

// If a hand that is already turned palm down is touched last, the player to whom it belongs puts the hand behind their back
// and this hand won’t be counted in the following rounds. The next round starts with the next hand in clockwise order as in the previous case.

// If a player has put both of their hands behind their back, that player is out of the game. The game ends when there is only one player left.

import java.io.*;
import java.util.*;

class CoconutSplat {
    public static void main(String[] argv) throws Exception {

        Kattio io = new Kattio(System.in, System.out);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int noOfSyllables = io.getInt();
        int noOfPlayers = io.getInt();
        int playerNo = 1;
        ArrayList<Hand> hands = new ArrayList<>();

        while (playerNo < noOfPlayers + 1) {
            Hand newHand = new Hand(playerNo, HandState.FOLDED);
            hands.add(newHand);
            playerNo++;
        }

        /*
         * When you count in circle, only the last round counts
         */
        int indexOfHandToDoSomething = (noOfSyllables - 1) % hands.size();

        while (hands.size() > 1) {
            Hand currentHand = hands.get(indexOfHandToDoSomething);
            if (currentHand.getHandsState() == HandState.FOLDED) {
                currentHand.setHandState(HandState.FIST);
                hands.add(indexOfHandToDoSomething + 1, new Hand(currentHand.getPlayerNo(), HandState.FIST));
                // Minus 1 because you count your own hand
                indexOfHandToDoSomething = (indexOfHandToDoSomething + noOfSyllables - 1) % hands.size();
            } else if (currentHand.getHandsState() == HandState.FIST) {
                currentHand.setHandState(HandState.PALM_DOWN);
                // Do not minus 1 because you count from the next hand onwards
                indexOfHandToDoSomething = (indexOfHandToDoSomething + noOfSyllables) % hands.size();
            } else if (currentHand.getHandsState() == (HandState.PALM_DOWN)) {
                currentHand.setHandState(HandState.HAND_BEHIND_BACK); // not needed
                hands.remove(currentHand);
                // Minus 1 because you took into account the removal of the hand already
                indexOfHandToDoSomething = (indexOfHandToDoSomething + noOfSyllables - 1) % hands.size();
            }
        }
        pw.println(hands.get(0).getPlayerNo());
        pw.flush();
    }
}

enum HandState {
    FOLDED,
    FIST,
    PALM_DOWN,
    HAND_BEHIND_BACK
}

class Hand {
    private int playerNo;
    private HandState handState;

    Hand(int playerNo, HandState handState) {
        this.playerNo = playerNo;
        this.handState = handState;
    }

    public int getPlayerNo() {
        return this.playerNo;
    }

    public HandState getHandsState() {
        return this.handState;
    }

    public void setHandState(HandState handState) {
        this.handState = handState;
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
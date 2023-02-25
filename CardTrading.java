// Aaron Foo Yu Rong, A0230343W

/*
4 3 2 (number of cards, number of card types, number of combos (pairs))
1 3 2 1 (cards with their types)
1 50 (buying price for card 1, selling price for card 1)
50 20 (buying price for card 2, selling price for card 2)
40 30 (buying price for card 3, selling price for card 3)
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class CardTrading {

    public static void main(String[] argv) throws Exception {

        Kattio io = new Kattio(System.in, System.out);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int noOfCards = io.getInt();
        int noOfCardTypes = io.getInt();
        int noOfCombosRequired = io.getInt();
        ArrayList<Integer> cardTypeQuantity = new ArrayList<>();
        ArrayList<Card> cards = new ArrayList<>();
        int buyValue;
        int sellValue;
        long profit = 0;
        int combosAttained = 0;

        for (int i = 0; i < noOfCardTypes + 1; i++) {
            cardTypeQuantity.add(0);
        }

        for (int i = 0; i < noOfCards; i++) {
            int currentCardType = io.getInt();
            cardTypeQuantity.set(currentCardType, cardTypeQuantity.get(currentCardType) + 1);
        }

        for (int i = 1; i < cardTypeQuantity.size(); i++) {
            buyValue = io.getInt();
            sellValue = io.getInt();
            cards.add(new Card(i, cardTypeQuantity.get(i), buyValue, sellValue));
        }

        Collections.sort(cards, new compareCardsOppCost());

        for (int i = 0; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            if (combosAttained < noOfCombosRequired) {
                if (currentCard.getQuantity() == 2) {
                    combosAttained++;
                } else if (currentCard.getQuantity() == 1) {
                    profit -= currentCard.getBuyValue();
                    combosAttained++;
                } else {
                    profit -= currentCard.getBuyValue() * 2;
                    combosAttained++;
                }
            } else {
                profit += currentCard.getSellValue() * currentCard.getQuantity();
            }
        }
        pw.print(profit);
        pw.flush();
    }
}

class compareCardsOppCost implements Comparator<Card> {
    public int compare(Card a, Card b) {
        if (a.getOppCost() > b.getOppCost()) {
            return 1;
        } else if (a.getOppCost() < b.getOppCost()) {
            return -1;
        } else {
            return 0;
        }
    }
}

class Card {
    private int cardType;
    private int quantity;
    private int buyValue;
    private int sellValue;
    private int oppCost;

    Card(int cardType, int quantity, int buyValue, int sellValue) {
        this.cardType = cardType;
        this.quantity = quantity;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
        this.oppCost = calculateOppCost();
    }

    public int calculateOppCost() {
        if (this.getQuantity() == 2) {
            return this.getSellValue() * 2;
        } else if (this.getQuantity() == 0) {
            return this.getBuyValue() * 2;
        } else {
            return this.getBuyValue() + this.getSellValue();
        }
    }

    public int getCardType() {
        return this.cardType;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getBuyValue() {
        return this.buyValue;
    }

    public int getSellValue() {
        return this.sellValue;
    }

    public int getOppCost() {
        return this.oppCost;
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
import java.util.ArrayList;

public class Player {


    private final int DEFAULT_CHIPS = 100;
    private String name;
    private ArrayList<Card> currentHand;
    private int valueOfHand;
    private int numberOfAces;
    private int chips;

    public Player(String name) {
        this.name = name;
        currentHand = new ArrayList<Card>();
        chips = DEFAULT_CHIPS;
        valueOfHand = 0;
    }

    public void addCardToHand(Card c) {
        currentHand.add(c);
        valueOfHand += c.getVALUE();
        if (c.getFACE().equals("Ace")) {
            numberOfAces++;
        }
        ;

    }

    public int getChips() {
        return chips;
    }

    public void changeChips(int a) {
        chips += a;
    }

    public void clearHand() {
        currentHand.clear();
        numberOfAces = 0;
        valueOfHand = 0;
    }

    public ArrayList<Card> getCurrentHand() {
        return currentHand;
    }

    public void decreaseAcesHeld() {
        numberOfAces--;
    }

    public void describeHand() {
        if (currentHand.isEmpty()) {
            throw new IndexOutOfBoundsException("Empty hand");
        } else {
            System.out.print(name + " is holding a ");
            for (int i = 0; i < (currentHand.size() - 1); i++) {
                if (i == (currentHand.size() - 2)) {
                    System.out.print(currentHand.get(i).getCardAndSuit() + " and a ");
                } else {
                    System.out.print(currentHand.get(i).getCardAndSuit() + ", ");
                }
            }
            System.out.print(currentHand.get(currentHand.size() - 1).getCardAndSuit() + ".");
            System.out.println();
        }
    }

    public String getName() {
        return name;
    }

    public int getValueOfHand() {
        return valueOfHand;
    }

    public int getNumberOfAces() {
        return numberOfAces;
    }

    public void aceAsOne() {
        valueOfHand = valueOfHand - 10;
        decreaseAcesHeld();
    }
}

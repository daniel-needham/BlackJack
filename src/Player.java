import java.util.ArrayList;

public class Player {


    private String name;
    private ArrayList<Card> currentHand;
    private final int DEFAULT_MONEY = 100;
    private int valueOfHand;
    private int numberOfAces;
    private int money;

    public Player(String name) {
        this.name = name;
        currentHand = new ArrayList<Card>();
        money = DEFAULT_MONEY;
        valueOfHand = 0;
    }

    public void addCardToHand(Card c) {
        currentHand.add(c);
        valueOfHand += c.getVALUE();
        if (c.getFACE().equals("Ace")) {
            numberOfAces++;
        } ;

    }

    public void clearHand() {
        currentHand.clear();
        numberOfAces = 0;
    }

    public ArrayList<Card> getCurrentHand() {
        return currentHand;
    }

    public void decreaseAcesHeld(){
        numberOfAces--;
    }

    public void describeHand(){
        System.out.print(name + " is holding a ");
        for (int i = 0; i < (currentHand.size() - 1); i++) {
            System.out.print(currentHand.get(i).getCardAndSuit() + ", ");
        }
        System.out.print(currentHand.get(currentHand.size() - 1).getCardAndSuit() + ".");
    }

    public String getName() {
        return name;
    }
}

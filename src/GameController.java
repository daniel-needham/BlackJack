import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

    private static final int DEFAULT_AMOUNT_OF_PACKS = 4;
    public static Deck playDeck;
    static Scanner In;
    static Player player;
    static Player dealer;

    public static void main(String[] args) {

        In = new Scanner(System.in);



        playDeck.shuffleDeck();
        Player player1 = new Player("Dan");
        for (int i = 0; i < 7; i++) {
            player1.addCardToHand(playDeck.getCard());
        }
        player1.describeHand();


    }

    public static void init() {
        System.out.println("Welcome to the Command Line Casino.");
        System.out.println("Today we are playing Blackjack.");
        System.out.println("Please enter your name to continue:");
        String name = In.nextLine();
        player = new Player(name);
        dealer = new Dealer("Dealer");
        playDeck = new Deck(DEFAULT_AMOUNT_OF_PACKS);
    };

    public static void play() {

    }

    public void drawCard(Player p) {
        p.addCardToHand(playDeck.getCard());
    }

    public void drawCard(Player p, int amount) {
        for (int i = 0; i < amount; i++) {
            p.addCardToHand(playDeck.getCard());
        }
    }

    public void clearPlayerDeck(Player p) {
        for (Card c : p.getCurrentHand()) {
            playDeck.addCardBackToDeck(c);
        }
        p.clearHand();
    }
}

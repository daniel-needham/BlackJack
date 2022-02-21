import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController {

    private static final int DEFAULT_AMOUNT_OF_PACKS = 4;
    public static Deck playDeck;
    static Scanner In;
    static Player player;
    static Dealer dealer;

    public static void main(String[] args) throws InterruptedException {
        playBlackJack();

    }

    public static void playBlackJack() throws InterruptedException {
        boolean keepPlaying = true;
        int iter = 0;
        In = new Scanner(System.in);


        init();
        TimeUnit.SECONDS.sleep(2);

        while (keepPlaying && player.getChips() > 0) {
            if (iter % 10 == 0) {
                playDeck.shuffleDeck();
            }
            playHand();
            TimeUnit.SECONDS.sleep(3);

            /*Continue*/
            String response;
            Pattern patt = Pattern.compile("[^yYNn]");
            Matcher matt;
            do {
                System.out.println("Would you like to continue playing? Y or N.");
                response = In.nextLine();
                matt = patt.matcher(response);
                response.toLowerCase();
            } while (matt.matches());

            if (response.equals("n")) {
                keepPlaying = false;
            }

            iter++;

        }
        if (player.getChips() == 0) {
            System.out.println("You're broke, you can leave now!");
        }
        System.out.println("Thank you for coming to the Command Line Casino.");
    }

    public static void init() {
        System.out.println("Welcome to the Command Line Casino.");
        System.out.println("Today we are playing Blackjack.");
        System.out.println("Please enter your name to continue:");
        String name = In.nextLine();
        player = new Player(name);
        dealer = new Dealer("Dealer");
        playDeck = new Deck(DEFAULT_AMOUNT_OF_PACKS);
        playDeck.shuffleDeck();
        System.out.println("Welcome " + player.getName() + ", lets play!");

    }

    ;

    public static void playHand() throws InterruptedException {

        /*betting phase*/
        int bet = 0;
        System.out.println("How many chips would you like to bet on this hand? You currently have " + player.getChips() + ".");
        try {
            bet = Integer.valueOf(In.nextLine());
        } catch (NumberFormatException ne) {
            System.out.println("Invalid input!");
        }

        if (bet > player.getChips()) {
            bet = player.getChips();
        }
        player.changeChips(-(bet));
        System.out.println("You have bet " + bet + " chips on this hand.");
        TimeUnit.SECONDS.sleep(2);


        /*Dealing*/
        System.out.println("The dealer deals the cards.");
        drawCard(player, 2);
        drawCard(dealer, 2);
        player.describeHand();
        dealer.describeDealerHand();

        /*next stage*/
        System.out.println("Would you like to Hit or Stand? Type either 'H' or 'S'.");
        String play = In.nextLine();
        Pattern p = Pattern.compile("[^hsHS]");
        Matcher m = p.matcher(play);
        while ((m.matches())) {
            System.out.println("Would you like to Hit or Stand? Type either 'H' or 'S'.");
            play = In.nextLine();
            play.toLowerCase();
            m = p.matcher(play);
        }
        while (play.equals("h") && !bust(player)) {
            System.out.println("You are dealt another card");
            drawCard(player);
            player.describeHand();
            if (player.getValueOfHand() == 21) {
                System.out.println("You have achieved 21.");
                break;
            }
            if (bust(player) && player.getNumberOfAces() == 0) {
                System.out.println("You have bust with " + player.getValueOfHand() + ".");
                break;
            }
            if (bust(player) && player.getNumberOfAces() > 0) {
                int oldHandValue = player.getValueOfHand();
                player.aceAsOne();
                System.out.println("You have a soft hand of " + oldHandValue + ". Your ace will now count as 1, making your hand a value of " + player.getValueOfHand() + ".");
            }

            do {
                System.out.println("Would you like to Hit or Stand? Type either 'H' or 'S'.");
                play = In.nextLine();
                play.toLowerCase();
                m = p.matcher(play);
            } while (m.matches());
        }

        /*dealers turn*/
        TimeUnit.SECONDS.sleep(2);
        System.out.println("The dealer reveals his unturned card...");
        dealer.describeHand();
        TimeUnit.SECONDS.sleep(3);
        /*dealer logic*/
        while (dealer.getValueOfHand() < 17 && !bust(dealer) && !bust(player)) {
            System.out.println("The Dealer hits ..  and draws another card");
            drawCard(dealer);
            dealer.describeHand();
            TimeUnit.SECONDS.sleep(2);
            if (bust(dealer) && dealer.getNumberOfAces() == 0) {
                System.out.println("The Dealer has bust with a hand of " + player.getValueOfHand() + ".");
                break;
            }
            if (bust(dealer) && dealer.getNumberOfAces() > 0) {
                int oldHandValue = dealer.getValueOfHand();
                dealer.aceAsOne();
                System.out.println("The Dealer had a soft hand of " + oldHandValue + ". Their ace will now count as 1, making the hand a value of " + player.getValueOfHand() + ".");
            }
        }
        TimeUnit.SECONDS.sleep(2);

        /*outcomes*/

        if (bust(player) && bust(dealer)) {
            System.out.println("Both you and the Dealer have bust, you lose this round.");
            clearPlayerandDealerDeck();
            return;
        }

        if (bust(player) && !bust(dealer)) {
            System.out.println("As you have bust, the Dealer wins this round.");
            clearPlayerandDealerDeck();
            return;
        }

        if (!bust(player) && bust(dealer)) {
            System.out.println("You win the round with a hand of " + player.getValueOfHand() + ". The Dealer has bust.");
            clearPlayerandDealerDeck();
            return;
        }

        if (player.getValueOfHand() == dealer.getValueOfHand()) {
            if (player.getValueOfHand() == 21 && player.getCurrentHand().size() == 2) {
                if (dealer.getCurrentHand().size() == 2) {
                    System.out.println("You and the Dealer both have Blackjack, this round is a push.");
                    clearPlayerandDealerDeck();
                    return;
                }
                System.out.println("You beat the dealer with Blackjack.");
                clearPlayerandDealerDeck();
                return;
            }
            System.out.println("You and the Dealer are tied at " + dealer.getValueOfHand() + ", this round is a push.");
            clearPlayerandDealerDeck();
            return;

        }

        if (player.getValueOfHand() < dealer.getValueOfHand()) {
            System.out.println("The Dealer's hand of " + dealer.getValueOfHand() + " wins, you lose this round.");
            clearPlayerandDealerDeck();
            return;
        }

        if (player.getValueOfHand() > dealer.getValueOfHand()) {
            System.out.println("Your hand beats the dealer, you win this round with " + player.getValueOfHand() + ".");
            clearPlayerandDealerDeck();
            return;
        }


    }

    public static void drawCard(Player p) {
        p.addCardToHand(playDeck.getCard());
    }

    public static void drawCard(Player p, int amount) {
        for (int i = 0; i < amount; i++) {
            p.addCardToHand(playDeck.getCard());
        }
    }

    public static void clearPlayerandDealerDeck() {
        clearPlayerDeck(dealer);
        clearPlayerDeck(player);
    }

    public static void clearPlayerDeck(Player p) {
        for (Card c : p.getCurrentHand()) {
            playDeck.addCardBackToDeck(c);
        }
        p.clearHand();
    }

    public static boolean bust(Player p) {
        if (p.getValueOfHand() > 21) {
            return true;
        } else {
            return false;
        }
    }


}

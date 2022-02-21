import LinkedList.LinkedList;
import LinkedList.QueueLinkedList;

import java.util.Random;

public class Deck {

    private QueueLinkedList playDeck;

    public Deck(int packsOfCards) {
        this.playDeck = createDeck(packsOfCards);
    }

    public static void main(String[] args) {
        Deck c = new Deck(4);
        c.shuffleDeck();
        c.displayDeck();


    }

    private QueueLinkedList createDeck(int packsOfCards) {
        QueueLinkedList playDeck = new QueueLinkedList();
        enum Suits {
            Spades, Hearts, Clubs, Diamonds
        }
        enum Face {
            Ace, King, Queen, Jack, Ten, Nine, Eight, Seven, Six, Five,
            Four, Three, Two
        }
        for (int i = 0; i < packsOfCards; i++) {
            for (Suits theSuit : Suits.values()) {
                for (Face theFace : Face.values()) {
                    Card c = new Card(theSuit.name(), theFace.name());
                    playDeck.enqueue(c);
                }
            }
        }
        return playDeck;
    }

    public void displayDeck() {
        if (playDeck.isEmpty()) {
            System.out.println("Deck is Empty");
            return;
        }
        for (int i = 1; i <= playDeck.size(); i++) {
            Card c = (Card) playDeck.dequeue();
            playDeck.enqueue(c);
        }
    }

    public void shuffleDeck() {
        if (playDeck.isEmpty()) {
            System.out.println("Deck is Empty");
            return;
        }
        LinkedList holdingList = new LinkedList();
        int runs = playDeck.size();
        for (int i = 0; i < runs; i++) {
            holdingList.add(playDeck.dequeue());
        }

        Random rand = new Random();
        for (int i = 0; i < runs; i++) {
            int randomIndex = rand.nextInt(holdingList.getSize());
            Card c = (Card) holdingList.remove(randomIndex + 1);
            if (c.equals(null)) {
                throw new RuntimeException("Null");
            } else {
                playDeck.enqueue(c);
            }
        }
    }

    public Card getCard(){
        return (Card) playDeck.dequeue();
    }

    public void addCardBackToDeck(Card c){
        playDeck.enqueue(c);
    }
}


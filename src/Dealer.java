import java.sql.SQLOutput;

public class Dealer extends Player{
    public Dealer(String name) {
        super(name);
    }

    public void describeDealerHand(){
        System.out.print(getName() + " is holding a ");
        System.out.print(getCurrentHand().get(0).getCardAndSuit() + ".");
        System.out.println("Their other card is face down for now.");
    }
}

public class Dealer extends Player{
    public Dealer(String name) {
        super(name);
    }

    public void describeDealerHand(){
        if (getCurrentHand().isEmpty()) {
            throw new IndexOutOfBoundsException("Empty hand");
        } else {
            System.out.print(getName() + " is holding a ");
            System.out.print(getCurrentHand().get(0).getCardAndSuit() + ". ");
            System.out.println("Their other card is face down for now.");
            System.out.println();
        }
    }
}

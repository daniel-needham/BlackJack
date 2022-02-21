public class Card {
    private final String SUIT;
    private final int VALUE;
    private final String FACE;

    public Card(String SUIT, String FACE) {
        this.SUIT = SUIT;
        this.FACE = FACE;
        this.VALUE = assignValue(FACE);

    }

    private int assignValue(String face) {
        int result = 0;
        switch(face) {
            case "Ace":
                result = 11;
                break;
            case "King":
            case "Queen":
            case "Jack":
            case "Ten":
                result = 10;
                break;
            case "Nine":
                result = 9;
                break;
            case "Eight":
                result = 8;
                break;
            case "Seven":
                result = 7;
                break;
            case "Six":
                result = 6;
                break;
            case "Five":
                result = 5;
                break;
            case "Four":
                result = 4;
                break;
            case "Three":
                result = 3;
                break;
            case "Two":
                result = 2;
                break;
        }
        return result;
    }

    public String getSUIT() {
        return SUIT;
    }

    public int getVALUE() {
        return VALUE;
    }

    public String getFACE() {
        return FACE;
    }

    public String getCardAndSuit(){
        return FACE + " of " + SUIT;
    }
}

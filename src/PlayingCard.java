public class PlayingCard {
    private String suit;
    private String name;
    private int number;

    public PlayingCard(String suit, String name, int number) {
        this.number = number;
        this.suit = suit;
        this.name = name;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getName(){
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

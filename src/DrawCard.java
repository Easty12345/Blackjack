import java.util.*;
import java.util.concurrent.TimeUnit;

public class DrawCard {
    PlayingCard playingCard;
    Random random = new Random();
    ArrayList<PlayingCard> deck = new ArrayList<>();
    int cardRank;
    String cardName;
    PlayingCard currentCard;
    public static ArrayList<PlayingCard> playerHand = new ArrayList<>();
    public static ArrayList<PlayingCard> dealerHand = new ArrayList<>();

    public void createDeck() {
        String[] suits = {"Spades", "Clubs", "Diamonds", "Hearts"};
        for (String currentSuit : suits) {
            for (int i = 1; i < 14; i++) {
                cardRank = i;
                if (i > 10) {
                    cardRank = 10;
                }
                switch (i) {
                    case 11:
                        cardName = "Jack";
                        break;
                    case 12:
                        cardName = "Queen";
                        break;
                    case 13:
                        cardName = "King";
                        break;
                    case 1:
                        cardName = "Ace";
                        cardRank = 11;
                        break;
                    default:
                        cardName = Integer.toString(cardRank);
                }
                deck.add(new PlayingCard(currentSuit, cardName, cardRank));
            }
        }
    }

    public void initialDeal() {
        for (int i = 0; i < 2; i++) {
            playingCard = deck.get(random.nextInt(deck.size()));
            playerHand.add(playingCard);
            currentCard = playingCard;
            deck.remove(currentCard);
        }
        for (int i = 0; i < 2; i++) {
            playingCard = deck.get(random.nextInt(deck.size()));
            dealerHand.add(playingCard);
            currentCard = playingCard;
            deck.remove(currentCard);
        }
    }

    public void hit(ArrayList<PlayingCard> hand) throws InterruptedException {
        playingCard = deck.get(random.nextInt(deck.size()));
        deck.remove(playingCard);
        hand.add(playingCard);
        System.out.println("Hit! It's a " + hand.get(hand.size() - 1).getName() + " of " + hand.get(hand.size() - 1).getSuit());
        TimeUnit.SECONDS.sleep(2);
    }

    public void stick() throws InterruptedException {
        int dealerTotal = checkTotal(dealerHand);
        int playerTotal = checkTotal(playerHand);
        System.out.println("You stick! Dealer will reveal his cards");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("The dealer's hand contains " + dealerHand.get(0).getName() + " of " + dealerHand.get(0).getSuit() + " and " + dealerHand.get(1).getName() + " of " + dealerHand.get(1).getSuit() + ". A total of " + dealerTotal);
        TimeUnit.SECONDS.sleep(2);
        while (dealerTotal < 17) {
            hit(dealerHand);
            dealerTotal = checkTotal(dealerHand);
            System.out.println("The dealer has " + dealerTotal);
            TimeUnit.SECONDS.sleep(2);
        }
        if (dealerTotal <= 21) {
            System.out.println("The dealer sticks!");
            TimeUnit.SECONDS.sleep(2);
            if (dealerTotal >= playerTotal) {
                System.out.println("The dealer's " + dealerTotal + " beats your " + playerTotal + ". The dealer wins");
                TimeUnit.SECONDS.sleep(2);
                System.exit(0);
            } else {
                System.out.println("Your " + playerTotal + " beats the dealer's " + dealerTotal + ". You win!");
                TimeUnit.SECONDS.sleep(2);
                System.exit(0);
            }
        } else if (dealerTotal > 21) {
            System.out.println("The dealer is bust. You win!");
            TimeUnit.SECONDS.sleep(2);
            System.exit(0);
        }
    }

    public void displayHand() {
        System.out.print("Your hand contains ");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.print(playerHand.get(i).getName() + " of " + playerHand.get(i).getSuit() + ", ");
        }
        System.out.println("Your total is " + checkTotal(playerHand));
        System.out.println("The dealer's hand contains " + dealerHand.get(0).getName() + " of " + dealerHand.get(0).getSuit() + " and a hidden card");
    }

    public int checkTotal(ArrayList<PlayingCard> hand) {
        int total = 0;
        ArrayList<Integer> aceIndex = new ArrayList<>();

        for (int i = 0; i < (hand.size()); i++) {
            total += hand.get(i).getNumber();
        }
        for (int i = 0; i < (hand.size()); i++) {
            String name = hand.get(i).getName();
            if (name.equals("Ace")) {
                aceIndex.add(i);
            }
        }
        while (aceIndex.size() > 0 && total > 21) {
            total -= 10;
            aceIndex.remove(aceIndex.size() - 1);
        }
        return total;
    }
}

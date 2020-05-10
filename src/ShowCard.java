import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ShowCard {

    public static void main(String args[]) throws InterruptedException {
        DrawCard game = new DrawCard();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Welcome to Blackjack. Type 'exit' at any point to finish");
        game.createDeck();
        game.initialDeal();
        System.out.println("Your hand contains " + game.playerHand.get(0).getName() + " of " + game.playerHand.get(0).getSuit() + " and " + game.playerHand.get(1).getName() + " of " + game.playerHand.get(1).getSuit() + ". Your total is " + game.checkTotal(game.playerHand));
        if (game.checkTotal(game.playerHand) == 21 && game.checkTotal(game.dealerHand) != 21) {
            System.out.println("You have Blackjack! You win!");
            TimeUnit.SECONDS.sleep(2);
            System.exit(0);
        } else if (game.checkTotal(game.playerHand) == 21 && game.checkTotal(game.dealerHand) == 21) {
            System.out.println("You have Blackjack! Oh no, the dealer also has it! The dealer wins and you lose lol");
            TimeUnit.SECONDS.sleep(2);
            System.exit(0);
        }
        System.out.println("The dealer hand contains " + game.dealerHand.get(0).getName() + " of " + game.dealerHand.get(0).getSuit() + " and a hidden card");
        System.out.println("Hit or Stick?");
        String input = keyboard.nextLine().toLowerCase();

        while (!(input.equals("exit"))) {
            switch (input) {
                case "hit":
                    game.hit(game.playerHand);
                    if (game.checkTotal(game.playerHand) > 21) {
                        System.out.println("You lose lol");
                        TimeUnit.SECONDS.sleep(2);
                        System.exit(0);
                    }
                    break;
                case "stick":
                    game.stick();
                    break;
                default:
                    System.out.println("Hit or Stick?");
            }
            game.displayHand();
            System.out.println("Hit or Stick?");
            input = keyboard.nextLine();
        }
    }
}

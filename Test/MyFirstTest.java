import org.junit.Assert;
import org.junit.Test;

public class MyFirstTest {

    @Test
    public void firstTest() {
        DrawCard game = new DrawCard();
        PlayingCard aceOne = new PlayingCard("Hearts", "Ace", 11);
        PlayingCard aceTwo = new PlayingCard("Spades", "Ace", 11);
        PlayingCard aceThree = new PlayingCard("Clubs", "Ace", 11);
        PlayingCard aceFour = new PlayingCard("Diamonds", "Ace", 11);
        game.playerHand.add(aceOne);
        game.playerHand.add(aceTwo);
        game.playerHand.add(aceThree);
        game.playerHand.add(aceFour);
        int total = game.checkTotal(game.playerHand);

        Assert.assertTrue(total != 44);
        Assert.assertTrue(total != 4);
        Assert.assertTrue(total == 14);
        Assert.assertTrue(total != 21);
        Assert.assertTrue(total != 22);
        Assert.assertTrue(1 == 1);
    }
}
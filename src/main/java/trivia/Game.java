package trivia;

import java.util.ArrayList;


// REFACTOR ME
public class Game implements IGame {
    /**
     * The ArrayList element types should be specified.
     * private access modifiers were used for class attributes (Encapsulation).
     */
    private ArrayList<Player> players = new ArrayList<>();

    private Question question;

    private int currentPlayerIdx = 0;
    private boolean isGettingOutOfPenaltyBox;

    public Game() {
        this.question = new Question();
    }

    /** isPlayable() method has no usage and can be removed. */

    /**
     * this keyword was added to refer the current object attributes.
     */
    @Override
    public boolean add(String playerName) {
        try {
            this.players.add(new Player(playerName));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid name: " + e.getMessage());
            playerName = "Player" + (this.players.size() + 1);
            System.out.println("Generated name: " + playerName);
            this.players.add(new Player(playerName));
        }
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + this.players.size());
        return true;
    }

    private void newCategory(Player currentPlayer, int roll) {
        currentPlayer.setPlace(roll);
        System.out.println(currentPlayer.getName()
                + "'s new location is "
                + currentPlayer.getPlace());
        System.out.println("The category is " + question.getCategory(currentPlayer.getPlace()));
        askQuestion(currentPlayer);
    }

    @Override
    public void roll(int roll) {
        Player currentPlayer = this.players.get(this.currentPlayerIdx);
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.inPenaltyBox()) {
            if (roll % 2 != 0) {
                this.isGettingOutOfPenaltyBox = true;
                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                newCategory(currentPlayer, roll);
            } else {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                this.isGettingOutOfPenaltyBox = false;
            }
        } else {
            newCategory(currentPlayer, roll);
        }

    }

    /**
     * askQuestion() method is called only inside the class so the access was changed to private.
     * if conditions can be replaced by switch statement
     */
    private void askQuestion(Player currentPlayer) {
        System.out.println(question.getQuestion(currentPlayer.getPlace()));
    }

    /**
     * a typo was found in the printed message. The typo was fixed also in GameOld class
     * to avoid the GameTest failing.
     */
    private boolean findWinner(Player currentPlayer) {
        System.out.println("Answer was correct!!!!");
        currentPlayer.incrementPurses();
        System.out.println(currentPlayer.getName()
                + " now has "
                + currentPlayer.getPurses()
                + " Gold Coins.");
        boolean winner = didPlayerWin();
        this.currentPlayerIdx++;
        if (this.currentPlayerIdx == this.players.size()) this.currentPlayerIdx = 0;
        return winner;
    }

    @Override
    public boolean wasCorrectlyAnswered() {
        Player currentPlayer = this.players.get(this.currentPlayerIdx);
        if (currentPlayer.inPenaltyBox()) {
            if (this.isGettingOutOfPenaltyBox) {
                return findWinner(currentPlayer);
            } else {
                this.currentPlayerIdx++;
                if (this.currentPlayerIdx == this.players.size()) this.currentPlayerIdx = 0;
                return true;
            }

        } else {
            return findWinner(currentPlayer);
        }
    }

    /**
     * The message that suggest the player 'was sent to the penalty box' should be called after
     * the value for related player was set to true
     */
    @Override
    public boolean wrongAnswer() {
        Player currentPlayer = this.players.get(this.currentPlayerIdx);
        System.out.println("Question was incorrectly answered");
        currentPlayer.setInPenaltyBox(true);
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        this.currentPlayerIdx++;
        if (this.currentPlayerIdx == this.players.size()) this.currentPlayerIdx = 0;
        return true;
    }

    /**
     * didPlayerWin() method is called only inside the class so the access was changed to private.
     */
    private boolean didPlayerWin() {
        return !(this.players.get(this.currentPlayerIdx).getPurses() == 6);
    }
}

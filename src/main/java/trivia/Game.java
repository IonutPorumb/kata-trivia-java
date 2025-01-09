package trivia;

import java.util.ArrayList;
import java.util.LinkedList;


// REFACTOR ME
public class Game implements IGame {
    // *** The ArrayList element types should be specified.
    // *** private access modifiers were used for class attributes (Encapsulation).
    private ArrayList<Player> players = new ArrayList<>();

//    private int[] places = new int[6];
//    private int[] purses = new int[6];
//    private boolean[] inPenaltyBox = new boolean[6];

    private Question question;
    // *** The LinkedList element types should be specified.
//    private LinkedList<String> popQuestions = new LinkedList<>();
//    private LinkedList<String> scienceQuestions = new LinkedList<>();
//    private LinkedList<String> sportsQuestions = new LinkedList<>();
//    private LinkedList<String> rockQuestions = new LinkedList<>();
    private int currentPlayerIdX = 0;
    private boolean isGettingOutOfPenaltyBox;
    // *** NO_OF_QUESTIONS constant was defined to specify the max question no.
//    private static final int NO_OF_QUESTIONS = 50;

    // A specific private method should be created for each question for better readability.
    public Game() {
        this.question = new Question();
//        for (int i = 0; i < NO_OF_QUESTIONS; i++) {
//            popQuestions.addLast(createQuestions("Pop", i));
//            scienceQuestions.addLast(createQuestions("Science", i));
//            sportsQuestions.addLast(createQuestions("Sports", i));
//            rockQuestions.addLast(createQuestions("Rock", i));
//        }
    }

    // *** Create a single method to create questions and call it in the class constructor with relevant argument.

//    private String createQuestions(String boardPosition, int index) {
//        return boardPosition + " Question " + index;
//    }

    // *** isPlayable() method has no usage and can be removed.

    // *** this keyword was added to refer the current object attributes.
    @Override
    public boolean add(String playerName) {
        try {
            if (playerName.trim().length() < 2) {
                throw new IllegalArgumentException("playerName should contain at least 2 characters");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid name: " + e.getMessage());
            playerName = "Player" + (this.howManyPlayers() + 1);
            System.out.println("Generated name: " + playerName);
        }
        this.players.add(new Player(playerName));
//        this.places[this.howManyPlayers()] = 0;
//        this.purses[this.howManyPlayers()] = 0;
//        this.inPenaltyBox[this.howManyPlayers()] = false;
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + this.players.size());
        return true;
    }

    private int howManyPlayers() {
        return this.players.size();
    }

    @Override
    public void roll(int roll) {
        Player currentPlayer = this.players.get(this.currentPlayerIdX);
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.inPenaltyBox()) {
            if (roll % 2 != 0) {
                this.isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                currentPlayer.setPlace(roll);
                System.out.println(currentPlayer.getName()
                        + "'s new location is "
                        + currentPlayer.getPlace());
                System.out.println("The category is " + question.getQuestion(currentPlayer.getPlace()));
                askQuestion(currentPlayer);
            } else {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                this.isGettingOutOfPenaltyBox = false;
            }

        } else {

//            this.places[this.currentPlayer] = this.places[this.currentPlayer] + roll;
//            if (this.places[this.currentPlayer] > 12)
//                this.places[this.currentPlayer] = this.places[this.currentPlayer] - 12;

            System.out.println(currentPlayer.getName()
                    + "'s new location is "
                    + currentPlayer.getPlace());
            System.out.println("The category is " + question.getQuestion(currentPlayer.getPlace()));
            askQuestion(currentPlayer);
        }

    }

    // *** askQuestion() method is called only inside the class so the access was changed to private.
//   *** if conditions can be replaced by switch statement
    private void askQuestion(Player currentPlayer) {
        System.out.println(question.getQuestion(currentPlayer.getPlace()));
    }

    // *** currentCategory() method is called only inside the class so the access was changed to private.
// *** if conditions can be replaced by switch statement
//    private String currentCategory() {
//        switch (this.places[this.currentPlayer]) {
//            case 1, 5, 9:
//                return "Pop";
//            case 2, 6, 10:
//                return "Science";
//            case 3, 7, 11:
//                return "Sports";
//            default:
//                return "Rock";
//        }
//    }

    @Override
    public boolean wasCorrectlyAnswered() {
        Player currentPlayer = this.players.get(this.currentPlayerIdX);
        if (currentPlayer.inPenaltyBox()) {
            if (this.isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                currentPlayer.incrementPurses();
                System.out.println(currentPlayer.getName()
                        + " now has "
                        + currentPlayer.getPurses()
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                this.currentPlayerIdX = (this.currentPlayerIdX + 1) % this.players.size();

                return winner;
            } else {
                this.currentPlayerIdX = (this.currentPlayerIdX + 1) % this.players.size();
                return true;
            }


        } else {
// *** a typo was found in the printed message. The typo was fixed also in GameOld class to avoid the GameTest failing.
            System.out.println("Answer was correct!!!!");
            currentPlayer.incrementPurses();
            System.out.println(currentPlayer.getName()
                    + " now has "
                    + currentPlayer.getPurses()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            this.currentPlayerIdX = (this.currentPlayerIdX + 1) % this.players.size();

            return winner;
        }
    }

    //   *** The message that suggest the player 'was sent to the penalty box' should be called after the value for related player was set to true
    @Override
    public boolean wrongAnswer() {
        Player currentPlayer = this.players.get(this.currentPlayerIdX);
        System.out.println("Question was incorrectly answered");
        currentPlayer.setInPenaltyBox(true);
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        this.currentPlayerIdX = (this.currentPlayerIdX + 1) % this.players.size();
        return true;
    }

    // *** didPlayerWin() method is called only inside the class so the access was changed to private.
    private boolean didPlayerWin() {
        return !(this.players.get(this.currentPlayerIdX).getPurses() == 6);
    }
}

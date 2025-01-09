package trivia;

public class Player implements IPlayer {
    private String playerName;
    private int purses = 0;
    private int places = 0;
    private boolean inPenaltyBox;

    public Player(String playerName) {
        this.playerName = playerName;
    }
    @Override
    public int getPlace() {
        return this.places;
    }
    @Override
    public void setPlace(int roll) {
        this.places = this.places + roll;
        if (this.places > 12) {
            this.places -= 12;
        }
    }
    @Override
    public int getPurses() {
        return this.purses;
    }
    @Override
    public String getName() {
        return this.playerName;
    }
    @Override
    public void incrementPurses() {
        this.purses++;
    }
    @Override
    public boolean inPenaltyBox(){
        return this.inPenaltyBox;
    }
    @Override
    public void setInPenaltyBox(boolean inPenaltyBox){
        this.inPenaltyBox = inPenaltyBox;
    }

}
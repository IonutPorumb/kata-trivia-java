package trivia;

public interface IPlayer {
    public void setPlace(int roll);
    public int getPlace();
    public int getPurses();
    public String getName();
    public void incrementPurses();
    public boolean inPenaltyBox();
    public void setInPenaltyBox(boolean inPenaltyBox);

}

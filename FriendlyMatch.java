public class FriendlyMatch extends Match {
   public FriendlyMatch() {
      FriendlyMatch(new Team(), new Team());
   }

   public FriendlyMatch(Team team1, Team team2) {
      super(team1, team2);

      setType(FRIENDLY);
   }

   public boolean playMatch() {
      playNinetyMinutes();
   }

   private boolean processMatch() {
      getTeam1().processMatch(getScore1(), getScore2());
      getTeam2().processMatch(getScore2(), getScore1());
      return true;
   }
}

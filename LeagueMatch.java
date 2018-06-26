public class LeagueMatch extends Match {
   public static final int LEAGUE_WIN_POINTS = 3;
   public static final int LEAGUE_DRAW_POINTS = 1;
   public static final int LEAGUE_LOSS_POINTS = 0;

   public LeagueMatch() {
      this(null, null);
   }

   public LeagueMatch(Team team1, Team team2) {
      super(team1, team2);

      setType(Match.MATCH_TYPE.LEAGUE);
   }

   public boolean playMatch() {
      return this.playNinetyMinutes() && this.processMatch();
   }

   protected boolean processMatch() {
      return this.getTeam1().processMatch(this) && this.getTeam2().processMatch(this);
   }
}

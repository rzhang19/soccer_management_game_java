/*
 * LeagueMatch.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * League Match class, child of Match class
 */

public class LeagueMatch extends Match {
   // initial variables
   public static final int INIITAL_WEEK = 0;

   // static variables dictating how much a win, draw, loss is worth
   public static final int LEAGUE_WIN_POINTS = 3;
   public static final int LEAGUE_DRAW_POINTS = 1;
   public static final int LEAGUE_LOSS_POINTS = 0;

   // week number
   private int m_weekNum;

   /*
    * LeagueMatch();
    * Default constructor
    * Delegates construction up one level
    */
   public LeagueMatch() {
      this(null, null);
   }

   /*
    * LeagueMatch(Team,Team);
    * Constructor
    * Delegates construction up one level
    */
   public LeagueMatch(Team team1, Team team2) {
      this(team1, team2, INITIAL_WEEK);
   }

   /*
    * LeagueMatch(Team,Team,int);
    * Main constructor
    * Calls main constructor of parent class
    */
   public LeagueMatch(Team team1, Team team2, int weekNum) {
      super(team1, team2);

      setType(Match.MATCH_TYPE.LEAGUE);
      m_weekNum = weekNum;
   }

   /*
    * playMatch();
    * Plays 90 minutes and processes the results
    *
    * @return - boolean, true if plays and processes League Match successfully,
    *          false otherwise
    */
   public boolean playMatch() {
      return this.playNinetyMinutes() && this.processMatch();
   }

   /*
    * processMatch();
    * Processes results of match by calling processMatch() from Match parent class
    *
    * @return - boolean, true if successfully processes this League Match for both Teams
    */
   protected boolean processMatch() {
      return this.getTeam1().processMatch(this) && this.getTeam2().processMatch(this);
   }
}

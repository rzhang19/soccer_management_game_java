/*
 * ClubTeam.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * ClubTeam class, child of Team class
 */

public class ClubTeam extends Team {
   // default values
   private static final String DEFAULT_NAME = "";
   private static final String DEFAULT_CODE = "";
   private static final String DEFAULT_LEAGUE = "";
   private static final String DEFAULT_CONTINENT = "";
   private static final int DEFAULT_WAGE_BUDGET = 0;
   private static final int DEFAULT_TRANSFER_BUDGET = 0;
   private static final int INITIAL_LEAGUE_WINS = 0;
   private static final int INITIAL_LEAGUE_DRAWS = 0;
   private static final int INITIAL_LEAGUE_LOSSES = 0;
   private static final int INITIAL_LEAGUE_POINTS = 0;
   private static final int INITIAL_LEAGUE_GOALS_FOR = 0;
   private static final int INITIAL_LEAGUE_GOALS_AGAINST = 0;

   // constraint values
   private static final String MINIMUM_WAGE_BUDGET;
   private static final String MINIMUM_TRANSFER_BUDGET;

   // League instance this ClubTeam is in
   private League m_league;

   // financials, transfer and wage budgets
   private int m_transferBudget;
   private int m_wageBudget;

   // stats about the current season
   private int m_leagueWins;
   private int m_leagueDraws;
   private int m_leagueLosses;
   private int m_leaguePoints;
   private int m_leagueGoalsFor;
   private int m_leagueGoalsAgainst;

   /*
    * ClubTeam();
    * Default constructor
    * Delegates construction up one level
    */
   public ClubTeam() {
      this(DEFAULT_NAME);
   }

   /*
    * ClubTeam(String);
    * Constructor
    * Delegates construction up one level
    */
   public ClubTeam(String name) {
      this(name, DEFAULT_CODE);
   }

   /*
    * ClubTeam(String,String);
    * Constructor
    * Delegates construction up one level
    */
   public ClubTeam(String name, String code) {
      this(name, code, DEFAULT_LEAGUE);
   }

   /*
    * ClubTeam(String,String,String);
    * Constructor
    * Delegates construction up one level
    */
   public ClubTeam(String name, String code, String league) {
      this(name, code, league, DEFAULT_CONTINENT);
   }

   /*
    * ClubTeam(String,String,String,String);
    * Main constructor
    * Calls main constructor of Team parent class
    * Sets all statistics to 0 as start values
    * Set financials to -1, indicating need to set those
    */
   public ClubTeam(String name, String code, String league, String continent) {
      super(name, code, continent);

      m_league = league;

      m_transferBudget = DEFAULT_TRANSFER_BUDGET;
      m_wageBudget = DEFAULT_WAGE_BUDGET;

      m_leagueWins = INITIAL_LEAGUE_WINS;
      m_leagueDraws = INITIAL_LEAGUE_DRAWS;
      m_leagueLosses = INITIAL_LEAGUE_LOSSES;
      m_leaguePoints = INITIAL_LEAGUE_POINTS;
      m_leagueGoalsFor = INITIAL_LEAGUE_GOALS_FOR;
      m_leagueGoalsAgainst = INITIAL_LEAGUE_GOALS_AGAINST;
   }

   /*
    * setLeague(League);
    * Sets the league of the ClubTeam to the parameter
    *
    * @args (1) - League league contains value for m_league, must not be null
    * @return - boolean, true if league is not null and m_league set successfully,
    *          false otherwise
    */
   public boolean setLeague(League league) {
      if (league == null) {
         System.err.println("Error, invalid league");
         return false;
      }

      m_league = league;
      return true;
   }

   /*
    * getLeague();
    * Returns the League of this ClubTeam
    *
    * @return - String, returns m_league
    */
   public League getLeague() {
      return m_league;
   }

   /*
    * getLeagueName();
    * Returns the name of the League of this ClubTeam
    */
   public String getLeagueName() {
      return m_league.getName();
   }

   /*
    * getTransferBudget();
    * Returns the current transfer budget
    *
    * @return - int, returns m_transferBudget
    */
   public int getTransferBudget() {
      return m_transferBudget;
   }

   /*
    * getWageBudget();
    * Returns the current wage budget
    *
    * @return - int, returns m_wageBudget
    */
   public int getWageBudget() {
      return m_wageBudget;
   }

   /*
    * setFinancials(int,int);
    * Attempts to set values for the wage and transfer budgets
    *
    * Note: we check for negative values in either because if wage>0,transfer<0,
    * then wage will be set already before we try to set transfer and return
    * false, so must check for both before we write anything
    *
    * @args (1) - int containing wage budget, must be non-negative (>= 0)
    *       (2) - int containing transfer budget, must be non-negative (>= 0)
    * @return - boolean, true if parameters are valid and wage and transfer
    *           budgets successfully set
    */
   public boolean setFinancials(int wageBudget, int transferBudget) {
      if (wageBudget < MINIMUM_WAGE_BUDGET || transferBudget < MINIMUM_TRANSFER_BUDGET) {
         System.err.println("Error, invalid budget");
         return false;
      }

      return setWageBudget(wageBudget) && setTransferBudget(transferBudget);
   }

   /*
    * setWageBudget(int);
    * Sets the wage budget to the parameter value
    *
    * @args (1) - int containing wage budget, must be non-negative (>= 0)
    * @return - boolean, true if parameter is valid and wage budget
    *           successfully set
    */
   private boolean setWageBudget(int wageBudget) {
      if (wageBudget < MINIMUM_WAGE_BUDGET) {
         System.err.println("Error, wage budget must be non-negative!");
         return false;
      }

      m_wageBudget = wageBudget;
      return true;
   }

   /*
    * setTransferBudget(int);
    * Sets the transfer budget to the parameter value
    *
    * @args (1) - int containing transfer budget, must be non-negative (>= 0)
    * @return - boolean, true if parameter is valid and transfer budget
    *           successfully set
    */

   private boolean setTransferBudget(int transferBudget) {
      if (transferBudget < MINIMUM_TRANSFER_BUDGET) {
         System.err.println("Error, transfer budget must be non-negative!");
         return false;
      }

      m_transferBudget = transferBudget;
      return true;
   }

   /*
    * processMatch(Match);
    * Processes the match by updating latest figures, which includes all total
    * values that the Match parent class updates, and all league values, which
    * this method updates
    *
    * Note: we don't need to check for negative scores or nonexisting this Team
    * because calling super.processMatch() already does the checking for us
    *
    * @args (1) - Match that was just played, holds result, must have one of
    *          its two teams be this Team (checked by super.processMatch())
    * @return - boolean, true if updates all values successfully, false if
    *          super.processMatch() fails and returns false
    */
   public boolean processMatch(Match match) {
      if (!super(match)) {
         System.err.println("Error with processing match");
         return false;
      }

      if (match.getMatchType() == Match.MATCH_TYPE.LEAGUE) {
         int myScore = 0;
         int otherScore = 0;

         if (match.getTeam1().equals(this)) {
            myScore = match.getScore1();
            otherScore = match.getScore2();
         }

         else {
            myScore = match.getScore2();
            otherScore = match.getScore1();
         }

         if (myScore > otherScore) {
            m_leagueWins++;
            m_leaguePoints += LeagueMatch.LEAGUE_WIN_POINTS;
         }

         else if (myScore == otherScore) {
            m_leagueDraws++;
            m_leaguePoints += LeagueMatch.LEAGUE_DRAW_POINTS;
         }

         else {
            m_leagueLosses++;
            m_leaguePoints += LeagueMatch.LEAGUE_LOSS_POINTS;
         }

         m_leagueGoalsFor += myScore;
         m_leagueGoalsAgainst += otherScore;
      }

      return true;
   }
}

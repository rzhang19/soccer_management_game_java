/*
 * Team.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * Abstract Team object holds all values for a generic football team
 */

public abstract class Team {
   // static ID counter
   private static int idCount = 0;

   // constant values for maximum and minimum players in Team
   public static final int MAX_PLAYERS = 99;
   public static final int MIN_PLAYERS = 23;

   // enum types for different Teams
   public static enum TEAM_TYPE { CLUB, NATIONAL };

   // Default team is Free Agency
   public static final String FREE_AGENCY = "Free Agents";

   // identifier variables
   private int m_id;
   private String m_name;
   private String m_code;
   private String m_continent;

   // player information
   private Player[] m_players;
   private int m_size;
   // must hold m_size separately, as m_players.length will return a fixed value

   // attributes of Team
   private int m_overall;
   private int m_attack;
   private int m_midfield;
   private int m_defense;
   private int m_gk;

   // stats of Team
   private int m_totalWins;
   private int m_totalDraws;
   private int m_totalLosses;
   private int m_totalCupWins;
   private int m_totalGoalsFor;
   private int m_totalGoalsAgainst;

   /*
    * Team();
    * Default constructor
    * Delegates construction up one level
    */
   public Team() {
      this("");
   }

   /*
    * Team(String);
    * Constructor
    * Delegates construction up one level
    */
   public Team(String name) {
      this(name, "");
   }

   /*
    * Team(String,String);
    * Constructor
    * Delegates construction up one level
    */
   public Team(String name, String code) {
      this(name, code, "");
   }

   /*
    * Team(String,String,String);
    * Main constructor
    * Sets all variables to parameters, starting values, or invalid values
    * Those with invalid values must be later set using member functions
    */
   public Team(String name, String code, String continent) {
      m_name = name;
      m_code = code;
      m_continent = continent;

      m_players = new Player[MAX_PLAYERS];

      m_id = idCount;
      idCount++;

      m_size = 0;

      m_overall = -1;
      m_attack = -1;
      m_midfield = -1;
      m_defense = -1;
      m_gk = -1;

      m_totalWins = 0;
      m_totalDraws = 0;
      m_totalLosses = 0;
      m_totalCupWins = 0;
      m_totalGoalsFor = 0;
      m_totalGoalsAgainst = 0;
   }

   /*
    * equals(Team);
    * Override of Object.equals()
    * Checks if this Team is the same Team as other
    * If both Free Agency, true
    * If not Free Agency, IDs must match
    *
    * @args (1) - Team other is being checked for equality against this Team
    * @return - boolean, true if same teams, false otherwise
    */
   public boolean equals(Team other) {
      return (m_name.equals(FREE_AGENCY) && other.getName().equals(FREE_AGENCY)) ||
            (m_id == other.getID());
   }

   /*
    * isValid();
    * Checks if this Team is valid
    * Valid if Team contains a valid number of Players and the Team code is valid
    *
    * @return - boolean, true if valid, false otherwise
    */
   public boolean isValid() {
      return m_size >= MIN_PLAYERS && m_size < MAX_PLAYERS && m_code.length() <= 3;
   }

   /*
    * getID();
    * Returns the ID of this Team
    *
    * @return - int, the ID of this Team
    */
   public int getID() {
      return m_id;
   }

   /*
    * setName(String);
    * Sets the name of this Team to the parameter value
    *
    * @args (1) - String containing new value for name of this Team, length > 0
    * @return - boolean, true if name set successfully, false otherwise
    */
   public boolean setName(String name) {
      if (name.length() <= 0)
         return false;

      m_name = name;
      return true;
   }

   /*
    * getName();
    * Returns the name of this Team
    *
    * @return - String, the name of this Team
    */
   public String getName() {
      return m_name;
   }

   /*
    * setCode(String);
    * Sets the code of this Team to the parameter value
    *
    * @args (1) - String containing new value for name of this Team, 0 < length <= 3
    * @return - boolean, true if code set successfully, false otherwise
    */
   public boolean setCode(String code) {
      if (0 >= code.length() || code.length() > 3)
         return false;

      m_code = code;
      return true;
   }

   /*
    * getCode();
    * Returns the code of this Team
    *
    * @return - String, the code of this Team
    */
   public String getCode() {
      return m_code;
   }

   /*
    * setContinent(String);
    * Sets the continent of thsi Team to the parameter value
    *
    * @args (1) - String containing new value for continent of this Team, length > 0
    * @return - boolean, true if continent set successfully, false otherwise
    */
   public boolean setContinent(String continent) {
      if (continent.length() <= 0)
         return false;

      m_continent = continent;
      return true;
   }

   /*
    * getContinent();
    * Returns the continent of this Team
    *
    * @return - String, the continent of this Team
    */
   public String getContinent() {
      return m_continent;
   }

   /*
    * addPlayer(Player);
    * Adds the parameter Player into this Team
    * If Player already exists in Team or if at maximum size, do not add Player
    *
    * @args (1) - Player to be added into Team if possible
    * @return - boolean, true if Player successfully added into team, false otherwise
    */
   public boolean addPlayer(Player addMe) {
      if (m_size >= MAX_PLAYERS) {
         System.err.println("Error adding player, at maximum capacity already");
         return false;
      }

      if (findPlayer(addMe)) {
         System.err.println("Error adding player, already exists in Team");
         return false;
      }

      m_players[m_size] = addMe;
      addMe.setClubTeam(this);
      m_size++;
      return true;
   }

   /*
    * removePlayer(Player);
    * Removes the parameter Player from this Team
    * If Player does not exist in this Team or if at minimum size, do not remove Player
    *
    * @args (1) - Player to be removed from Team if possible
    * @return - boolean, true if Player successfulled removed from Team, false otherwise
    */
   public boolean removePlayer(Player removeMe) {
      if (m_size <= MIN_PLAYERS || !findPlayer(removeMe))
         return false;

      for (int x = 0; x < m_size; x++) {
         if (m_players[x].equals(removeMe)) {
            if(!(m_players[x].setClubTeam(new ClubTeam(FREE_AGENCY)) &&
               m_players[x].setContractLength(0) &&
               m_players[x].setWage(0)))
               return false;

            for (int y = x; y < m_size - 1; y++) {
               m_players[y] = m_players[y+1];
            }

            m_size--;
         }
      }

      return true;
   }

   /*
    * findPlayer(Player);
    * Finds the parameter Player in this Team
    *
    * @args (1) - Player to find
    * @return - boolean, true if Player exists in this Team, false otherwise
    */
   public boolean findPlayer(Player findMe) {
      for (int x = 0; x < m_size; x++) {
         if (m_players[x].equals(findMe))
            return true;
      }

      return false;
   }

   /*
    * getPlayers();
    * Returns the current array of Players
    * Warning: Recommended to be used with getSize();
    *
    * @return - array of Players of this Team
    */
   public Player[] getPlayers() {
      return m_players;
   }

   /*
    * getSize();
    * Returns the current number of Players in this Team
    * m_players holds a fixed, large number of Players, including null Players
    * m_size keeps track of the actual number of non-null Players
    *
    * @return - int, the number of Players on this Team
    */
   public int getSize() {
      return m_size;
   }

   /*
    * getOverall();
    * Returns the overall of this Team
    * First must recalculate to set m_overall to the most updated value
    *
    * @return - int, the overall of this Team, -1 if unable to recalculate overall
    */
   public int getOverall() {
      if (calculateOverall())
         return m_overall;
      return -1;
   }

   /*
    * calculateOverall();
    * Calculates the overall by averaging overalls of all Players
    *
    * @return - boolean, true if able to successfully calculate overall, false
    *          if m_players is empty (division by zero)
    */
   private boolean calculateOverall() {
      if (m_size == 0) {
         System.err.println("Error, no players to calculate overall");
         return false;
      }

      int sum = 0;
      for (int x = 0; x < m_size; x++) {
         sum += m_players[x].getOverall();
      }

      m_overall = sum / m_size;
      return true;
   }

   /*
    * getAttack();
    * Returns the attack attribute of this Team
    * First must recalculate attack of this Team
    *
    * @return - int, the attack of this Team, -1 if unable to recalculate value
    */
   public int getAttack() {
      if (calculateAttack())
         return m_attack;

      return -1;
   }

   /*
    * calculateAttack();
    * Calculates the attack of this Team by averaging attack values of all
    * attacking Players on this Team
    *
    * @return - boolean, true if able to successfully calculate attack, false if
    *          number of attackers is zero (divison by zero)
    */
   private boolean calculateAttack() {
      int sum = 0;
      int count = 0;

      for (int x = 0; x < m_size; x++) {
         if (m_players[x].getRole() == Player.ROLE.ATT) {
            sum += m_players[x].getOverall();
            count++;
         }
      }

      if (count == 0) {
         System.err.println("Error, no attackers to calculate attack");
         return false;
      }

      m_attack = sum / count;
      return true;
   }

   /*
    * getMidfield();
    * Returns the midfield attribute of this Team
    * First must recalculate midfield of this Team
    *
    * @return - int, the midfield of this Team, -1 if unable to recalculate value
    */
   public int getMidfield() {
      if (calculateMidfield())
         return m_midfield;

      return -1;
   }

   /*
    * calculateMidfield();
    * Calculates the midfield of this Team by averaging midfield values of all
    * midfield Players on this Team
    *
    * @return - boolean, true if able to successfully calculate midfield, false if
    *          number of midfielders is zero (divison by zero)
    */
   private boolean calculateMidfield() {
      int sum = 0;
      int count = 0;

      for (int x = 0; x < m_size; x++) {
         if (m_players[x].getRole() == Player.ROLE.MID) {
            sum += m_players[x].getOverall();
            count++;
         }
      }

      if (count == 0) {
         System.err.println("Error, no midfielders to calculate midfield");
         return false;
      }

      m_midfield = sum / count;
      return true;
   }

   /*
    * getDefense();
    * Returns the defense attribute of this Team
    * First must recalculate defense of this Team
    *
    * @return - int, the defense of this Team, -1 if unable to recalculate value
    */
   public int getDefense() {
      if (calculateDefense())
         return m_defense;

      return -1;
   }

   /*
    * calculateDefense();
    * Calculates the defense of this Team by averaging defense values of all
    * defense Players on this Team
    *
    * @return - boolean, true if able to successfully calculate defense, false if
    *          number of defenders is zero (divison by zero)
    */
   private boolean calculateDefense() {
      int sum = 0;
      int count = 0;

      for (int x = 0; x < m_size; x++) {
         if (m_players[x].getRole() == Player.ROLE.DEF) {
            sum += m_players[x].getOverall();
            count++;
         }
      }

      if (count == 0) {
         System.err.println("Error, no defenders to calculate defense");
         return false;
      }

      m_defense = sum / count;
      return true;
   }

   /*
    * getGK();
    * Returns the goalkeeping attribute of this Team
    * First must recalculate goalkeeping of this Team
    *
    * @return - int, the goalkeeping of this Team, -1 if unable to recalculate value
    */
   public int getGK() {
      if (calculateGK())
         return m_gk;

      return -1;
   }

   /*
    * calculateGK();
    * Calculates the goalkeeping of this Team by averaging goalkeeping values of all
    * goalkeeper Players on this Team
    *
    * @return - boolean, true if able to successfully calculate goalkeeping, false if
    *          number of goalkeepers is zero (divison by zero)
    */
   private boolean calculateGK() {
      int sum = 0;
      int count = 0;

      for (int x = 0; x < m_size; x++) {
         if (m_players[x].getRole() == Player.ROLE.GK) {
            sum += m_players[x].getOverall();
            count++;
         }
      }

      if (count == 0) {
         System.err.println("Error, no goalkeepers to calculate goalkeeping");
         return false;
      }

      m_gk = sum / count;
      return true;
   }

   /*
    * getTotalWins();
    * Returns the number of total wins in this Team's history
    *
    * @return - int, number of total wins
    */
   public int getTotalWins() {
      return m_totalWins;
   }

   /*
    * getTotalDraws();
    * Returns the number of total draws in this Team's history
    *
    * @return - int, number of total draws
    */
   public int getTotalDraws() {
      return m_totalDraws;
   }

   /*
    * getTotalLosses();
    * Returns the number of total losses in this Team's history
    *
    * @return - int, number of total losses
    */
   public int getTotalLosses() {
      return m_totalLosses;
   }

   /*
    * getTotalCupWins();
    * Returns the number of total cup wins in this Team's history
    *
    * @return - int, number of total cup wins
    */
   public int getTotalCupWins() {
      return m_totalCupWins;
   }

   /*
    * getTotalGoalsFor();
    * Returns the number of total goals scored in this Team's history
    *
    * @return - int, number of total goals scored
    */
   public int getTotalGoalsFor() {
      return m_totalCupWins;
   }

   /*
    * getTotalGoalsAgainst();
    * Returns the number of total goals conceded in this Team's history
    *
    * @return - int, number of total goals conceded
    */
   public int getTotalGoalsAgainst() {
      return m_totalGoalsAgainst;
   }

   /*
    * processMatch(Match);
    * Processes the match that was just played by processing score and result
    * Fails to process if this Team was not a Team in the Match, or if the scores
    * are invalid (negative values)
    *
    * @args (1) - Match to be processed
    * @return - boolean, true if able to successfully process Match, false otherwise
    */
   public boolean processMatch(Match match) {
      if (match.getScore1() < 0 || match.getScore2() < 0) {
         System.err.println("Error, invalid scores")
         return false;
      }

      int myScore = 0;
      int otherScore = 0;

      if (match.getTeam1().equals(this)) {
         myScore = match.getScore1();
         otherScore = match.getScore2();
      }

      else if (match.getTeam2().equals(this)) {
         myScore = match.getScore2();
         otherScore = match.getScore1();
      }

      else {
         System.err.println("Error, match does not contain team");
         return false;
      }

      if (myScore > otherScore)
         m_totalWins++;
      else if (myScore == otherScore)
         m_totalDraws++;
      else
         m_totalLosses++;

      m_totalGoalsFor += myScore;
      m_totalGoalsAgainst += otherScore;
      return true;
   }

   /*
    * endSeason();
    * Ends the season by resetting season values. Total (historical) values are
    * not reset
    *
    * @return - boolean, true if able to successfully reset season, false otherwise
    */
   public boolean endSeason() {
      return true;
   }
}

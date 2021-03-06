/*
 * League.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * League class, holds data for a League
 */

public class League {
   // default variables
   private static final String DEFAULT_NAME = "";
   private static final String DEFAULT_CONTINENT = "";
   private static final int DEFAULT_LEVEL = 0;
   private static final int DEFAULT_NUM_PROMOTED = 0;
   private static final int DEFAULT_NUM_RELEGATED = 0;
   private static final boolean DEFAULT_PROMOTED_PLAYOFF = false;
   private static final boolean DEFAULT_RELEGATED_PLAYOFF = false;
   private static final int DEFAULT_NUM_PLAYED = 2;
   private static final boolean DEFAULT_RANDOM = true;
   private static final int DEFAULT_WEIGHT_WEEK = 0;

   // initial variables
   private static final int INITIAL_SIZE = 0;

   // constant variables for League
   private static final int HIGHEST_LEVEL = 1;
   private static final int MINIMUM_PROMOTED = 0;
   private static final int MINIMUM_RELEGATED = 0;
   private static final int MINIMUM_SIZE = 2;
   private static final int MINIMUM_NAME_LENGTH = 1;

   // static ID counter
   private static int idCount = 0;

   // general identification information
   private String m_name;
   private int m_id;

   // interleague information
   private String m_continent;
   private int m_level;

   // promotion/relegation information
   private int m_numPromoted;
   private int m_numRelegated;
   private boolean m_promotionPlayoff;
   private boolean m_relegationPlayoff;

   // current teams
   private Team[] m_teams;
   private int m_size;
   private int m_maxSize;

   // matches information
   private LeagueMatch[] m_matches;
   private int m_numTotalMatches;
   private int m_currentNumMatches;
   private int m_currentWeek;

   /*
    * League();
    * Default constructor
    * Delegates construction up one level
    */
   public League() {
      this(DEFAULT_NAME);
   }

   /*
    * League(String);
    * Constructor
    * Delegates construction up one level
    */
   public League(String name) {
      this(name, DEFAULT_CONTINENT);
   }

   /*
    * League(String,String);
    * Constructor
    * Delegates construction up one level
    */
   public League(String name, String continent) {
      this(name, continent, DEFAULT_LEVEL);
   }

   /*
    * League(String,String,int);
    * Constructor
    * Delegates construction up one level
    */
   public League(String name, String continent, int level) {
      this(name, continent, level, DEFAULT_NUM_PROMOTED, DEFAULT_NUM_RELEGATED);
   }

   /*
    * League(String,String,int,int,int);
    * Constructor
    * Delegates construction up one level
    */
   public League(String name, String continent, int level, int numPromoted, int numRelegated) {
      this(name, continent, level, numPromoted, numRelegated, MINIMUM_SIZE);
   }

   /*
    * League(String,String,int,int,int,int);
    * Main constructor
    * Sets all values to parameters, if possible
    * Sets other values to default values
    * Sets ID value
    */
   public League(String name, String continent, int level, int numPromoted, int numRelegated, int maxSize) {
      m_name = name;
      m_continent = continent;
      m_level = level;
      m_numPromoted = numPromoted;
      m_numRelegated = numRelegated;
      m_maxSize = maxSize;

      m_promotionPlayoff = DEFAULT_PROMOTED_PLAYOFF;
      m_relegationPlayoff = DEFAULT_RELEGATED_PLAYOFF;
      m_size = INITIAL_SIZE;

      m_id = idCount;
      idCount++;

      m_teams = new Team[maxSize];
   }

   /*
    * equals(League);
    * Checks whether this League is equal to the parameter League by comparing IDs
    *
    * @args (1) - League that this League is being compared to
    * @return - boolean, true if Leagues are same, false otherwise
    */
   public boolean equals(League other) {
      return m_id == other.getID();
   }

   /*
    * setName(String);
    * Sets the name of this League to the parameter
    * Fails if name is an empty String
    *
    * @args (1) - String that contains new name value
    * @return - boolean, true if name is set successfully, false otherwise
    */
   public boolean setName(String name) {
      if (name.length() < MINIMUM_NAME_LENGTH) {
         System.err.println("Error, name cannot be empty");
         return false;
      }

      m_name = name;
      return true;
   }

   /*
    * getName();
    * Returns the name of this League
    *
    * @return - String, name of this League
    */
   public String getName() {
      return m_name;
   }

   /*
    * getID();
    * Returns the ID of this League
    *
    * @return - int, ID of this League
    */
   public int getID() {
      return m_id;
   }

   /*
    * setContinent(String);
    * Sets the continent of this League to the parameter, length > 0
    *
    * @args (1) - String containing new value for continent
    * @return - boolean, true if continent successfully, false otherwise
    */
   public boolean setContinent(String continent) {
      if (continent.length() < MINIMUM_NAME_LENGTH) {
         System.err.println("Error, continent cannot be empty");
         return false;
      }

      m_continent = continent;
      return true;
   }

   /*
    * getContinent();
    * Returns the continent of this League
    *
    * @return - String, continent of this League
    */
   public String getContinent() {
      return m_continent;
   }

   /*
    * setLevel(int);
    * Sets the level of this League to the parameter
    * Lower levels are better levels in the League
    * Level 1 is the top (highest) League of the continent
    *
    * @args (1) - int containing level of this League
    * @return - boolean, true if set successfully, false otherwise
    */
   public boolean setLevel(int level) {
      if (level < HIGHEST_LEVEL) {
         System.err.println("Error, invalid level");
         return false;
      }

      m_level = level;
      return true;
   }

   /*
    * getLevel();
    * Returns the level of this League
    *
    * @return - int, level of this League
    */
   public int getLevel() {
      return m_level;
   }

   /*
    * setNumPromoted(int);
    * Sets the number of promotions of this League
    * The top League cannot have any promotions
    * There also cannot be a negative number of promotions
    *
    * @args (1) - int containing new number of promotions in this League
    * @return - boolean, true if number of promotions set successfully, false otherwise
    */
   public boolean setNumPromoted(int numPromoted) {
      if (m_level == 1 && numPromoted > 0) {
         System.err.println("Error, top league cannot have promotions");
         return false;
      }

      if (numPromoted < MINIMUM_PROMOTED) {
         System.err.println("Error, number promoted is invalid");
         return false;
      }

      m_numPromoted = numPromoted;
      return true;
   }

   /*
    * getNumPromoted();
    * Returns the number of promotions of this League
    *
    * @return - int, number of promotions in this League
    */
   public int getNumPromoted() {
      return m_numPromoted;
   }

   /*
    * setNumRelegated(int);
    * Sets the number of relegations of this League
    * There cannot be a negative number of relegations
    *
    * @args (1) - int containing new number of relegations in this League
    * @return - boolean, true if number of relegations set successfully, false otherwise
    */
   public boolean setNumRelegated(int numRelegated) {
      if (numRelegated < MINIMUM_RELEGATED) {
         System.err.println("Error, number relegated is invalid");
         return false;
      }

      m_numRelegated = numRelegated;
      return true;
   }

   /*
    * getNumRelegated();
    * Returns the number of relegations in this League
    *
    * @return - int, number of relegations in this League
    */
   public int getNumRelegated() {
      return m_numRelegated;
   }

   /*
    * setPromotionPlayoff(boolean);
    * Sets the promotion playoff boolean
    * Cannot be set to true if number of promotions is zero
    *
    * @args (1) - boolean containing value to be stored
    * @return - boolean, true if promotion playoff boolean set successfully,
    * false otherwise
    */
   public boolean setPromotionPlayoff(boolean promotionPlayoff) {
      if (m_numPromoted <= MINIMUM_PROMOTED && promotionPlayoff) {
         System.err.println("Error, cannot set promotion playoffs with zero promoted");
         return false;
      }

      m_promotionPlayoff = promotionPlayoff;
      return true;
   }

   /*
    * getPromotionPlayoff();
    * Returns the promotion playoff boolean
    *
    * @return - boolean, whether there are promotion playoffs
    */
   public boolean getPromotionPlayoff() {
      return m_promotionPlayoff;
   }

   /*
    * setRelegationPlayoff(boolean);
    * Sets the relegation playoff boolean
    * Cannot be set to true if number of relegations is zero
    *
    * @args (1) - boolean containing value to be stored
    * @return - boolean, true if relegation playoff boolean set successfully,
    * false otherwise
    */
   public boolean setRelegationPlayoff(boolean relegationPlayoff) {
      if (m_numRelegated <= MINIMUM_RELEGATED && relegationPlayoff) {
         System.err.println("Error, cannot set relegation playoffs with zero relegated");
         return false;
      }

      m_relegationPlayoff = relegationPlayoff;
      return true;
   }

   /*
    * getRelegationPlayoff();
    * Returns the relegation playoff boolean
    *
    * @return - boolean, whether there are relegation playoffs
    */
   public boolean getRelegationPlayoff() {
      return m_relegationPlayoff;
   }

   /*
    * addTeam(Team);
    * Adds the parameter Team to the League, if possible
    * Cannot add if already at maximum size, or if Team already exists in League
    */
   public boolean addTeam(Team addMe) {
      if (m_size >= m_maxSize) {
         System.err.println("Error adding Team, League already at maximum capacity");
         return false;
      }

      if (findTeam(addMe)) {
         System.err.println("Error adding Team, already exists in League");
      }

      m_teams[m_size] = addMe;
      m_size++;
      return true;
   }

   /*
    * findTeam(Team);
    * Finds if the parameter Team exists in this League
    *
    * @args (1) - Team to be found in this League
    * @return - boolean, true if Team exists in this League, false otherwise
    */
   public boolean findTeam(Team findMe) {
      for (int x = 0; x < m_size; x++) {
         if (m_teams[x].equals(findMe))
            return true;
      }

      return false;
   }

   /*
    * removeTeam(Team);
    * Removes the parameter Team from this League
    * Team must exist in League
    *
    * @args (1) - Team to be removed
    * @return - boolean, true if Team successfully removed, false otherwise
    */
   public boolean removeTeam(Team removeMe) {
      if (!findTeam(removeMe)) {
         System.err.println("Error removing team, cannot find team in league");
         return false;
      }

      for (int x = 0; x < m_size; x++) {
         if (m_teams[x].equals(removeMe)) {
            for (int y = x; y < m_size - 1; y++) {
               m_teams[y] = m_teams[y + 1];
            }

            m_teams[m_size] = null;

            return true;
         }
      }
   }

   /*
    * getTeams();
    * Returns the Teams in this League
    *
    * @return - array of Teams, Teams in this League
    */
   public Team[] getTeams() {
      return m_teams;
   }

   /*
    * getSize();
    * Return the current size of the League
    * Note: This is not the maximum, fixed size of the League
    *
    * @return - int, current size of League
    */
   public int getSize() {
      return m_size;
   }

   /*
    * setMaximumSize(int);
    * Sets the maximum size of the League, maxSize > 1
    *
    * @args (1) - int containing maximum size of the League
    * @return - boolean, true if maximum size set successfully, false otherwise
    */
   public boolean setMaximumSize(int maxSize) {
      if (maxSize < MINIMUM_SIZE) {
         System.err.println("Error, size not set");
         return false;
      }

      m_maxSize = maxSize;
      return true;
   }

   /*
    * getMaximumSize();
    * Return the maximum size of the League
    *
    * @return - int, maximum size of League
    */
   public int getMaximumSize() {
      return m_maxSize;
   }

   /*
    * generateSchedule();
    *
    * Creates a schedule using the Round Robin tournament structure
    * Calls the more specific overloaded version of this method
    */
   public boolean generateSchedule() {
      return generateSchedule(DEFAULT_NUM_PLAYED);
   }

   /*
    * generateSchedule(int);
    *
    * Creates a schedule using the Round Robin tournament structure
    * Calls the more specific, overloaded version of this method
    */
   public boolean generateSchedule(int numPlayed) {
      return generateSchedule(DEFAULT_NUM_PLAYED, DEFAULT_RANDOM, DEFAULT_WEIGHT_WEEK);
   }

   /*
    * generateSchedule(int,boolean,int);
    *
    * Creates a schedule using the Round Robin tournament structure
    * Currently does not implement any additional settings
    */
   public boolean generateSchedule(int numPlayed, boolean random, int weightWeek) {
      if (random && weightWeek != DEFAULT_WEIGHT_WEEK) {
         System.err.println("Error, can't be both random and have weighted week");
         return false;
      }

      if (m_size != m_maxSize) {
         System.err.println("Error, league Teams not fully established");
         return false;
      }

      m_matches = new Match[m_size * (m_size - 1) / 2];

      Team tempTeam1 = m_teams[0];
      Team tempTeam2 = m_teams[1];

      boolean finished = false;

      boolean evenTeams = m_maxSize % 2 == 0 ? true : false;
      int evenSize = evenTeams ? m_maxSize : m_maxSize + 1;

      if (evenSize < 2) {
         System.err.println("Error, not enough Teams to create a League");
         return false;
      }

      Team[] m_tempTeams = new Team[evenSize];

      for (int x = 0; x < m_maxSize; x++) {
         m_tempTeams[x] = m_teams[x];
      }

      m_tempTeams[evenSize - 1] = null;

      int week = 1;
      int count = 0;

      while (!finished) {
         for (int x = 0; x < evenSize; x += 2) {
            if (!(!evenTeams && (m_tempTeams[x] == null || m_tempTeams[x + 1] == null))) {
               m_matches[count] = new Match(m_tempTeams[x], m_tempTeams[x + 1], week);
               count++;
            }
         }

         Team temp = m_tempTeams[evenSize - 1];

         if (evenSize > 2) {
            for (int x = evenSize - 1; x > 2; x -= 2) {
               m_tempTeams[x] = m_tempTeams[x - 2];
            }

            m_tempTeams[1] = m_tempTeams[2];

            for (int x = 2; x < evenSize - 2; x += 2) {
               mtempTeams[x] = m_tempTeams[x + 2];
            }

            m_tempTeams[evenSize - 2] = temp;
         }

         if ((m_tempTeams[0].equals(tempTeam1) && m_tempTeams[1].equals(tempTeam2)) || (m_tempTeams[0].equals(tempTeam2) & m_tempTeams[1].equals(tempTeam1))) {
            finished = true;
         }

         week++;
      }

      return true;
   }
}

/*
 * Match.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * Abstract Match class holds all values for a generic football match
 */

import java.util.Random;

public abstract class Match implements Game {
   // random number generator for Match simulation
   public static Random rand = new Random();

   // default variables
   private static final boolean DEFAULT_COMMENTARY = false;
   private static final int DEFAULT_ATTRIBUTE = -1;

   // initial variables
   private static final int INITIAL_SCORES = 0;
   private static final int INITIAL_PENALITIES = 0;

   // goal counts as one point
   private static final int GOAL = 1;

   // constant values for Match simulation details
   private static final int START_MINUTE = 1;
   private static final int FIRST_HALF_LENGTH = 45;
   private static final int SECOND_HALF_LENGTH = 45;
   private static final int FIRST_EXTRA_HALF_LENGTH = 15;
   private static final int SECOND_EXTRA_HALF_LENGTH = 15;
   private static final int MIN_RANDOM_ADDED = 1;
   private static final int MAX_RANDOM_ADDED = 5;

   // ID values for certain halfs of a Match
   private static final int FIRST_HALF = 1;
   private static final int SECOND_HALF = 2;
   private static final int FIRST_EXTRA_HALF = 3;
   private static final int SECOND_EXTRA_HALF = 4;

   // penalty kicks constants
   private static final int PENALTY_START = 1;
   private static final int PENALTIES_TO_TAKE = 5;

   // enum types for different types of Matches
   public static enum MATCH_TYPE { LEAGUE, CUP_GROUP, CUP_KNOCKOUT, FRIENDLY, TWO_LEG_FIRST, TWO_LEG_SECOND };
   private MATCH_TYPE m_type;

   // identification values
   private static int idCount = 0;
   private int m_id;

   // score values
   private int m_score1;
   private int m_score2;
   private int m_penalties1;
   private int m_penalties2;

   // Teams to play
   private Team m_team1;
   private Team m_team2;

   // whether m_team1 has possession
   private boolean m_team1Poss;

   // attribute values of teams so we don't have to keep fetching them
   private int m_attack1;
   private int m_attack2;
   private int m_midfield1;
   private int m_midfield2;
   private int m_defense1;
   private int m_defense2;
   private int m_gk1;
   private int m_gk2;

   // whether we print out commentary for the match
   private boolean m_printCommentary;

   /*
    * Match();
    * Default constructor
    * Delegates construction up one level
    */
   public Match() {
      this(null, null);
   }

   /*
    * Match(Team,Team);
    * Main constructor
    * Sets all values to zero
    * Sets all Team attributes to respective values (if Teams defined and not null)
    * or -1 if respective Team is null
    */
   public Match(Team team1, Team team2) {
      m_score1 = INITIAL_SCORES;
      m_score2 = INITIAL_SCORES;
      m_penalties1 = INITIAL_PENALITIES;
      m_penalties2 = INITIAL_PENALITIES;
      m_team1 = team1;
      m_team2 = team2;

      m_printCommentary = DEFAULT_COMMENTARY;

      if (!setAttributes()) {
         System.err.println("Error with setting attributes");
      }
   }

   /*
    * equals(Match);
    * Checks whether this Match is equal to the parameter Match by comparing IDs
    *
    * @args (1) - Match that this Match is being compared to
    * @return - boolean, true if Matches are same, false otherwise
    */
   public boolean equals(Match other) {
      return m_id == other.getID();
   }

   /*
    * setTeams(Team,Team);
    * Sets the Teams to the parameter values
    * If any Team is null, it is not set (this allows adding in one team at a time)
    * Sets the attributes as well, fails if unable to set attributes
    *
    * @args (1) - Team to be added into m_team1
    * @args (2) - Team to be added into m_team2
    * @return - boolean, true if able to set Teams and attributes successfully,
    *       false otherwise
    */
   public boolean setTeams(Team team1, Team team2) {
      if (team1 != null)
         m_team1 = team1;
      if (team2 != null)
         m_team2 = team2;

      m_id = idCount;
      idCount++;

      if (!setAttributes()) {
         System.err.println("Error setting attributes");
         return false;
      }

      return true;
   }

   /*
    * setAttributes();
    * Sets the member variable attributes based off of m_team1 and m_team2
    * Sets to -1 if respective team is not defined
    *
    * @return - boolean, true if successfully set attributes
    */
   private boolean setAttributes() {
      if (m_team1 != null) {
         m_attack1 = m_team1.getAttack();
         m_midfield1 = m_team1.getMidfield();
         m_defense1 = m_team1.getDefense();
         m_gk1 = m_team1.getGK();
      }

      else {
         m_attack1 = DEFAULT_ATTRIBUTE;
         m_midfield1 = DEFAULT_ATTRIBUTE;
         m_defense1 = DEFAULT_ATTRIBUTE;
         m_gk1 = DEFAULT_ATTRIBUTE;
      }

      if (m_team2 != null) {
         m_attack2 = m_team2.getAttack();
         m_midfield2 = m_team2.getMidfield();
         m_defense2 = m_team2.getDefense();
         m_gk2 = m_team2.getGK();
      }

      else {
         m_attack2 = DEFAULT_ATTRIBUTE;
         m_midfield2 = DEFAULT_ATTRIBUTE;
         m_defense2 = DEFAULT_ATTRIBUTE;
         m_gk2 = DEFAULT_ATTRIBUTE;
      }

      return true;
   }

   /*
    * getID();
    * Returns the ID of this Match
    *
    * @return - int, ID of this Match
    */
   public int getID() {
      return m_id;
   }

   /*
    * The enum Match.MATCH_TYPE will be used by the subclasses to set a value for
    * the m_type variable, which this class will be able to retrieve at any time
    *
    * It is HIGHLY RECOMMENDED that the Match type be set within the main
    * constructor of the subclass
    */

   /*
    * setMatchType(MATCH_TYPE);
    * Sets the Match type of this Match to the parameter
    *
    * @args (1) - MATCH_TYPE value to be stored
    * @return - boolean, true if Match type set successfully
    */
   public boolean setMatchType(MATCH_TYPE type) {
      m_type = type;
      return true;
   }

   /*
    * getMatchType();
    * Returns the Match type
    *
    * @return - enum Match.MATCH_TYPE of this particular Match
    */
   public MATCH_TYPE getMatchType() {
      return m_type;
   }

   /*
    * getScore1();
    * Returns the score of m_team1
    *
    * @return - int, score of m_team1
    */
   public int getScore1() {
      return m_score1;
   }

   /*
    * getScore2();
    * Returns the score of m_team2
    *
    * @return - int, score of m_team2
    */
   public int getScore2() {
      return m_score2;
   }

   /*
    * getTeam1();
    * Returns m_team1
    *
    * @return - Team, m_team1
    */
   public Team getTeam1() {
      return m_team1;
   }

   /*
    * getTeam2();
    * Returns m_team2
    *
    * @return - Team, m_team2
    */
   public Team getTeam2() {
      return m_team2;
   }

   /*
    * toggleCommentary(boolean);
    * Sets printing commentary to the parameter
    *
    * @args (1) - boolean value to be stored
    * @return - boolean, true if commentary successfully set
    */
   public boolean toggleCommentary(boolean set) {
      m_printCommentary = set;
      return true;
   }

   /*
    * incrementScore1();
    * Increments score of Team 1 by GOAL amount
    *
    * @return - boolean, true if incremented successfully
    */
   private boolean incrementScore1() {
      m_score1 += GOAL;
      return true;
   }

   /*
    * incrementScore2();
    * Increments score of Team 2 by GOAL amount
    *
    * @return - boolean, true if incremented successfully
    */
   private boolean incrementScore2() {
      m_score2 += GOAL;
      return true;
   }

   /*
    * playHalf(int);
    * Plays one half of football, depending on the section of the Match
    * Only difference between sections (halfs and extra time halfs) is starting minute
    *
    * @args (1) - int containing section to be simulated
    * @return - boolean, true if simulated successfully, false otherwise
    */
   private boolean playHalf(int section) {
      if (m_team1 == null || m_team2 == null) {
         System.err.println("Error, teams for Match not initialized");
         return false;
      }

      int minute = 0;

      if (section == FIRST_HALF)
         minute = START_MINUTE;
      else if (section == SECOND_HALF)
         minute = FIRST_HALF_LENGTH;
      else if (section == FIRST_EXTRA_HALF)
         minute = FIRST_HALF_LENGTH + SECOND_HALF_LENGTH;
      else if (section == SECOND_EXTRA_HALF)
         minute = FIRST_HALF_LENGTH + SECOND_HALF_LENGTH + FIRST_EXTRA_HALF_LENGTH;

      m_team1Poss = (rand.nextInt(2) == 0);

      while ((section == FIRST_HALF && minute < FIRST_HALF_LENGTH) ||
            (section == SECOND_HALF && minute < FIRST_HALF_LENGTH + SECOND_HALF_LENGTH) ||
            (section == FIRST_EXTRA_HALF && minute < FIRST_HALF_LENGTH + SECOND_HALF_LENGTH + FIRST_EXTRA_HALF_LENGTH) ||
            (section == SECOND_EXTRA_HALF && minute < FIRST_HALF_LENGTH + SECOND_HALF_LENGTH +
            FIRST_EXTRA_HALF_LENGTH + SECOND_EXTRA_HALF_LENGTH)) {
         minute += rand.nextInt(MAX_RANDOM_ADDED - MIN_RANDOM_ADDED) + MIN_RANDOM_ADDED;

         if (m_team1Poss) {
            if (rand.nextInt(m_midfield1) > rand.nextInt(m_midfield2)) {
               if (m_printCommentary)
                  System.out.println(m_team1.getName() + "\'s midfield moves the ball across the pitch.");
               if (rand.nextInt(m_attack1) > rand.nextInt(m_defense2)) {
                  if (m_printCommentary)
                     System.out.println(m_team1.getName() + "\'s attack beats the defense!");
                  if (rand.nextInt(m_attack1) > rand.nextInt(m_gk2)) {
                     if (m_printCommentary)
                        System.out.println("In minute " + minute + ", " + m_team1.getName() + " scores!");
                     incrementScore1();
                     m_team1Poss = false;
                  }

                  else {
                     if (m_printCommentary)
                        System.out.println(m_team2.getName() + "\'s goalkeeper saves the shot!");

                     m_team1Poss = false;
                  }
               }

               else {
                  if (m_printCommentary)
                     System.out.println(m_team2.getName() + "\'s defense clears the ball.");

                  m_team1Poss = false;
               }
            }

            else {
               if (m_printCommentary)
                  System.out.println(m_team1.getName() + "\'s midfield loses the ball.");

               m_team1Poss = false;
            }
         }

         else {
            if (rand.nextInt(m_midfield2) > rand.nextInt(m_midfield1)) {
               if (m_printCommentary)
                  System.out.println(m_team2.getName() + "\'s midfield moves the ball across the pitch.");
               if (rand.nextInt(m_attack2) > rand.nextInt(m_defense1)) {
                  if (m_printCommentary)
                     System.out.println(m_team2.getName() + "\'s attack beats the defense!");
                  if (rand.nextInt(m_attack2) > rand.nextInt(m_gk1)) {
                     if (m_printCommentary)
                        System.out.println("In minute " + minute + ", " + m_team2.getName() + " scores!");
                     incrementScore2();
                     m_team1Poss = true;
                  }

                  else {
                     if (m_printCommentary)
                        System.out.println(m_team1.getName() + "\'s goalkeeper saves the shot!");

                     m_team1Poss = true;
                  }
               }

               else {
                  if (m_printCommentary)
                     System.out.println(m_team1.getName() + "\'s defense clears the ball.");

                  m_team1Poss = true;
               }
            }

            else {
               if (m_printCommentary)
                  System.out.println(m_team2.getName() + "\'s midfield loses the ball.");

               m_team1Poss = true;
            }
         }
      }

      return true;
   }

   /*
    * playNinetyMinutes();
    * Plays a normal football Match of 90 minutes
    *
    * @return - boolean, true if both halfs are simulated successfully
    */
   public boolean playNinetyMinutes() {
      return playHalf(FIRST_HALF) && playHalf(SECOND_HALF);
   }

   /*
    * playExtraTime();
    * Plays 30 minutes total of extra time
    *
    * @return - boolean, true if both extra time halfs are simulated successfully
    */
   public boolean playExtraTime() {
      return playHalf(FIRST_EXTRA_HALF) && playHalf(SECOND_EXTRA_HALF);
   }

   private boolean kickPenalty(int team, String name, int attack, int gk) {
      if (rand.nextInt(attack) > rand.nextInt(gk)) {
         if (team == 1)
            m_penalties1++;
         else
            m_penalties2++;

         if (m_printCommentary)
            System.out.println(name + " scores!");
      }

      else {
         if (m_printCommentary)
            System.out.println(name + " misses!");
      }

      return true;
   }

   /*
    * playPenalties();
    * Simulates penalty kicks with sudden death
    *
    * @return - boolean, true if simulated successfully, false otherwise
    */
   private boolean playPenalties() {
      int number = PENALTY_START;
      boolean team1First = (rand.nextInt(2) == 0);
      boolean firstTeamUp = true;

      boolean clinched = false;
      boolean suddenDeath = false;

      while (!clinched) {
         if (firstTeamUp && team1First) {
            if(!kickPenalty(1, m_team1.getName(), m_attack1, m_gk2)) {
               System.err.println("Error with penalty kick");
               return false;
            }

            if (!suddenDeath &&
               (m_penalties2 + (PENALTIES_TO_TAKE - number + 1) < m_penalties1)) {
               if (m_printCommentary)
                  System.out.println(m_team1.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (!suddenDeath &&
               (m_penalties1 + (PENALTIES_TO_TAKE - number) < m_penalties2)) {
               if (m_printCommentary)
                  System.out.println(m_team2.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            firstTeamUp = false;
         }

         else if (firstTeamUp && !team1First) {
            if(!kickPenalty(2, m_team2.getName(), m_attack2, m_gk1)) {
               System.err.println("Error with penalty kick");
               return false;
            }

            if (!suddenDeath &&
               (m_penalties1 + (PENALTIES_TO_TAKE - number + 1) < m_penalties2)) {
               if (m_printCommentary)
                  System.out.println(m_team2.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (!suddenDeath &&
               (m_penalties2 + (PENALTIES_TO_TAKE - number) < m_penalties1)) {
               if (m_printCommentary)
                  System.out.println(m_team1.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            firstTeamUp = false;
         }

         else if (!firstTeamUp && !team1First) {
            if (!kickPenalty(1, m_team1.getName(), m_attack1, m_gk2)) {
               System.err.println("Error with penalty kick");
               return false;
            }

            if (!suddenDeath &&
               (m_penalties2 + (PENALTIES_TO_TAKE - number) < m_penalties1)) {
               if (m_printCommentary)
                  System.out.println(m_team1.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (!suddenDeath &&
               (m_penalties1 + (PENALTIES_TO_TAKE - number) < m_penalties2)) {
               if (m_printCommentary)
                  System.out.println(m_team2.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (suddenDeath) {
               if (m_penalties1 == m_penalties2 + 1) {
                  if (m_printCommentary)
                     System.out.println(m_team2.getName() + " wins the penalty shootout!");

                  clinched = true;
               }

               else if (m_penalties1 + 1 == m_penalties2) {
                  if (m_printCommentary)
                     System.out.println(m_team1.getName() + " wins the penalty shootout!");

                  clinched = true;
               }

               else if (m_penalties1 != m_penalties2) {
                  System.err.println("Error with penalty count");
                  return false;
               }
            }

            if (number == PENALTIES_TO_TAKE)
               suddenDeath = true;

            number++;
            firstTeamUp = true;
         }

         else {
            if (!kickPenalty(2, m_team2.getName(), m_attack2, m_gk1)) {
               System.err.println("Penalty kick failed");
               return false;
            }

            if (!suddenDeath &&
               (m_penalties1 + (PENALTIES_TO_TAKE - number) < m_penalties2)) {
               if (m_printCommentary)
                  System.out.println(m_team2.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (!suddenDeath &&
               (m_penalties2 + (PENALTIES_TO_TAKE - number) < m_penalties1)) {
               if (m_printCommentary)
                  System.out.println(m_team1.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (suddenDeath) {
               if (m_penalties1 == m_penalties2 + 1) {
                  if (m_printCommentary)
                     System.out.println(m_team2.getName() + " wins the penalty shootout!");

                  clinched = true;
               }

               else if (m_penalties1 + 1 == m_penalties2) {
                  if (m_printCommentary)
                     System.out.println(m_team1.getName() + " wins the penalty shootout!");

                  clinched = true;
               }

               else if (m_penalties1 != m_penalties2) {
                  System.err.println("Error with penalty count");
                  return false;
               }
            }

            if (number == PENALTIES_TO_TAKE)
               suddenDeath = true;

            number++;
            firstTeamUp = true;
         }
      }

      return true;
   }

   /*
    * playMatch();
    * Abstract method to play a Match
    * Because each Match type has a different format, this must be abstract, and
    * each subclass of each Match type must specifically call any of the above:
    * (1) - playNinetyMinutes();
    * (2) - playExtraTime();
    * (3) - playPenalties();
    *
    * @return - boolean, true if simulated Match successfully, false otherwise
    */
   public abstract boolean playMatch();

   /*
    * processMatch();
    * Processes the Match result for both Teams
    *
    * @return - boolean, true if both Teams successfully process results, false otherwise
    */
   protected abstract boolean processMatch() {
      return m_team1.processMatch(this) && m_team2.processMatch(this);
   }
}

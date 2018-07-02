/*
 * Cup.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * Cup class holds data for any cup competition
 */
public class Cup {
   // static ID counter
   private static int idCount = 0;

   // static limit constants
   private static final int MINIMUM_KNOCKOUT_TEAMS = 2;
   private static final int MINIMUM_GROUP_TEAMS = 3;

   // other static constants
   private static final boolean DEFAULT_GROUPS = false;
   private static final boolean DEFAULT_TWO_LEGS = false;
   private static final int DEFAULT_NUM_GROUPS = 4;
   private static final int DEFAULT_GROUP_SIZE = 4;
   private static final int DEFAULT_GROUP_TIMES_PLAY = 1;
   private static final int DEFAULT_QUALIFY_FROM_GROUP = 2;
   private static final boolean DEFAULT_THIRD_PLACE = true;
   private static final boolean DEFAULT_NUM_KNOCKOUT = 32;

   // identification information
   private String m_name;
   private int m_id;

   // group stage information
   private Team[][] m_groups;
   private boolean m_groups;
   private int m_numGroups;
   private int m_groupSize;
   private int m_groupTimesPlay;
   private int m_qualifyFromGroups;

   // knockout information
   private Team[] m_knockout;
   private boolean m_thirdPlace;
   private boolean m_twoLegs;
   private int m_numKnockoutTeams;

   // general information
   private int m_totalMatches;

   // cup running mechanics
   private int m_started;

   public Cup() {
      this("");
   }

   public Cup(String name) {
      this(name, DEFAULT_TWO_LEGS);
   }

   public Cup(String name, boolean twoLegs) {
      this(name, twoLegs, DEFAULT_THIRD_PLACE);
   }

   public Cup(String name, boolean twoLegs, boolean thirdPlace) {
      this(name, twoLegs, thirdPlace, DEFAULT_GROUPS);
   }

   public Cup(String name, boolean twoLegs, boolean thirdPlace, boolean groups) {
      this(name, twoLegs, thirdPlace, groups, DEFAULT_NUM_GROUPS);
   }

   public Cup(String name, boolean twoLegs, boolean thirdPlace, int numKnockoutTeams) {
      m_name = name;
      m_twoLegs = twoLegs;
      m_thirdPlace = thirdPlace;

      int numKnockout = numKnockoutTeams;
      boolean goodNumber = true;
      while (numKnockout != 0 && goodNumber) {
         numKnockout /= 2;
         if (numKnockout == 1) {
            goodNumber = false;
         }
      }

      if (goodNumber) {
         m_numKnockoutTeams = numKnockoutTeams;
      }

      else {
         m_numKnockoutTeams = DEFAULT_NUM_KNOCKOUT;
      }

      m_totalMatches = 0;
      if (!calculateTotalMatches()) {
         System.err.println("Error, failed to calculate total matches");
      }
   }

   public Cup(String name, boolean twoLegs, boolean thirdPlace, boolean groups,
            int numGroups) {
      this(name, twoLegs, thirdPlace, groups, numGroups, DEFAULT_GROUP_SIZE);
   }

   public Cup(String name, boolean twoLegs, boolean thirdPlace, boolean groups,
            int numGroups, int groupSize) {
      this(name, twoLegs, thirdPlace, groups, numGroups, groupSize,
            DEFAULT_GROUP_TIMES_PLAY);
   }

   public Cup(String name, boolean twoLegs, boolean thirdPlace, boolean groups,
            int numGroups, int groupSize, int groupTimesPlay) {
      this(name, twoLegs, thirdPlace, groups, numGroups, groupSize,
            groupTimesPlay, DEFAULT_QUALIFY_FROM_GROUP);
   }

   public Cup(String name, boolean twoLegs, boolean thirdPlace, boolean groups,
            int numGroups, int groupSize, int groupTimesPlay, int qualifyFromGroups) {
      m_name = name;
      m_twoLegs = twoLegs;
      m_thirdPlace = thirdPlace;
      m_groups = groups;

      m_numGroups = m_numGroups;
      m_groupSize = groupSize;
      m_groupTimesPlay = groupTimesPlay;
      m_qualifyFromGroups = qualifyFromGroups;

      m_totalMatches = 0;
      if (!calculateTotalMatches()) {
         System.err.println("Error, failed to calculate total matches");
      }
   }

   private boolean calculateTotalMatches() {
      if (groups) {
         m_totalMatches += m_numGroups * (m_groupSize * (m_groupSize - 1) * m_groupTimesPlay / 2);
      }

      m_totalMatches += m_numGroups * m_qualifyFromGroups;

      if (!m_thirdPlace)
         m_totalMatches--;

      return true;
   }

   public boolean setNumKnockoutTeams(int numKnockoutTeams) {
      if (numKnockoutTeams < MINIMUM_KNOCKOUT_TEAMS) {
         System.err.println("Error, invalid number of knockout teams");
         return false;
      }

      m_numKnockoutTeams = numKnockoutTeams;
      return true;
   }

   public boolean setName(String name) {
      if (name.length() <= 0) {
         System.err.println("Error, invalid name to set");
         return false;
      }

      m_name = name;
      return true;
   }

   public String getName() {
      return m_name;
   }
}

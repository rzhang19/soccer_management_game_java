import java.util.Random;

public abstract class Match implements Game {
   public static Random rand = new Random();

   private static final int GOAL = 1;

   public static final enum MATCH_TYPE { LEAGUE, CUP_GROUP, CUP_KNOCKOUT, FRIENDLY, TWO_LEG_FIRST, TWO_LEG_SECOND };
   private static final int START_MINUTE = 1;
   private static final int FIRST_HALF_LENGTH = 45;
   private static final int SECOND_HALF_LENGTH = 45;
   private static final int FIRST_EXTRA_HALF_LENGTH = 15;
   private static final int SECOND_EXTRA_HALF_LENGTH = 15;
   private static final int MIN_RANDOM_ADDED = 1;
   private static final int MAX_RANDOM_ADDED = 5;

   private static final int FIRST_HALF = 1;
   private static final int SECOND_HALF = 2;
   private static final int FIRST_EXTRA_HALF = 3;
   private static final int SECOND_EXTRA_HALF = 4;

   private static final int PENALTY_START = 1;
   private static final int PENALTIES_TO_TAKE = 5;

   private MATCH_TYPE m_type;

   private int m_score1;
   private int m_score2;
   private int m_penalties1;
   private int m_penalties2;
   private Team m_team1;
   private Team m_team2;
   private boolean m_team1Poss;

   private boolean m_printCommentary;

   public Match() {
      Match(new Team(), new Team());
   }

   public Match(Team team1, Team team2) {
      m_score1 = 0;
      m_score2 = 0;
      m_penalties1 = 0;
      m_penalties2 = 0;
      m_team1 = team1;
      m_team2 = team2;

      printCommentary = false;
   }

   public boolean setTeams(Team team1, Team team2) {
      m_team1 = team1;
      m_team2 = team2;
      return true;
   }

   public MATCH_TYPE getMatchType() {
      return m_type;
   }

   public boolean setMatchType(MATCH_TYPE type) {
      m_type = type;
      return true;
   }

   public int getScore1() {
      return m_score1;
   }

   public int getScore2() {
      return m_score2;
   }

   public Team getTeam1() {
      return m_team1;
   }

   public Team getTeam2() {
      return m_team2;
   }

   public boolean setType(MATCH_TYPE type) {
      m_type = type;
      return true;
   }

   public boolean toggleCommentary(boolean set) {
      printCommentary = set;
      return true;
   }

   public boolean incrementScore1() {
      m_score1 += GOAL;
      return true;
   }

   public boolean incrementScore2() {
      m_score2 += GOAL;
      return true;
   }

   private boolean playHalf(int section) {
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
                  System.out.println(team1.getName() + "\'s midfield moves the ball across the pitch.");
               if (rand.nextInt(m_attack1) > rand.nextInt(m_defense2)) {
                  if (m_printCommentary)
                     System.out.println(team1.getName() + "\'s attack beats the defense!");
                  if (rand.nextInt(m_attack1) > rand.nextInt(m_gk2)) {
                     if (m_printCommentary)
                        System.out.println("In minute " + minute + ", " + team1.getName() + " scores!");
                     m_score1++;
                     m_team1Poss = false;
                  }

                  else {
                     if (m_printCommentary)
                        System.out.println(team2.getName() + "\'s goalkeeper saves the shot!");

                     m_team1Poss = false;
                  }
               }

               else {
                  if (m_printCommentary)
                     System.out.println(team2.getName() + "\'s defense clears the ball.");

                  m_team1Poss = false;
               }
            }

            else {
               if (m_printCommentary)
                  System.out.println(team1.getName() + "\'s midfield loses the ball.");

               m_team1Poss = false;
            }
         }

         else {
            if (rand.nextInt(m_midfield2) > rand.nextInt(m_midfield1)) {
               if (m_printCommentary)
                  System.out.println(team2.getName() + "\'s midfield moves the ball across the pitch.");
               if (rand.nextInt(m_attack2) > rand.nextInt(m_defense1)) {
                  if (m_printCommentary)
                     System.out.println(team2.getName() + "\'s attack beats the defense!");
                  if (rand.nextInt(m_attack2) > rand.nextInt(m_gk1)) {
                     if (m_printCommentary)
                        System.out.println(team2.getName() + " scores!");
                     m_score2++;
                     m_team1Poss = true;
                  }

                  else {
                     if (m_printCommentary)
                        System.out.println(team1.getName() + "\'s goalkeeper saves the shot!");

                     m_team1Poss = true;
                  }
               }

               else {
                  if (m_printCommentary)
                     System.out.println(team1.getName() + "\'s defense clears the ball.");

                  m_team1Poss = true;
               }
            }

            else {
               if (m_printCommentary)
                  System.out.println(team2.getName() + "\'s midfield loses the ball.");

               m_team1Poss = true;
            }
         }
      }

      return true;
   }

   public boolean playNinetyMinutes() {
      playHalf(FIRST_HALF);
      playHalf(SECOND_HALF);
      return true;
   }

   public boolean playExtraTime() {
      playHalf(FIRST_EXTRA_HALF);
      playHalf(SECOND_EXTRA_HALF);
      return true;
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

      firstTeamUp = false;
   }

   private boolean playPenalties() {
      int number = PENALTY_START;
      boolean team1First = (rand.nextInt(2) == 0);
      boolean firstTeamUp = true;

      boolean clinched = false;
      boolean suddenDeath = false;

      while (!clinched) {
         if (firstTeamUp && team1First) {
            if(!kickPenalty(1, team1.getName(), m_attack1, m_gk2))
               return false;

            if (!suddenDeath &&
               (m_penalties2 + (PENALTIES_TO_TAKE - number + 1) < m_penalties1)) {
               if (m_printCommentary)
                  System.out.println(team1.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (!suddenDeath &&
               (m_penalties1 + (PENALTIES_TO_TAKE - number) < m_penalties2)) {
               if (m_printCommentary)
                  System.out.println(team2.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            firstTeamUp = false;
         }

         else if (firstTeamUp && !team1First) {
            if(!kickPenalty(2, team2.getName(), m_attack2, m_gk1))
               return false;

            if (!suddenDeath &&
               (m_penalties1 + (PENALTIES_TO_TAKE - number + 1) < m_penalties2)) {
               if (m_printCommentary)
                  System.out.println(team2.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (!suddenDeath &&
               (m_penalties2 + (PENALTIES_TO_TAKE - number) < m_penalties1)) {
               if (m_printCommentary)
                  System.out.println(team1.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            firstTeamUp = false;
         }

         else if (!firstTeamUp && !team1First) {
            if (!kickPenalty(1, team1.getName(), m_attack1, m_gk2)) {
               System.err.println("Penalty kick failed");
               return false;
            }

            if (!suddenDeath &&
               (m_penalties2 + (PENALTIES_TO_TAKE - number) < m_penalties1)) {
               if (m_printCommentary)
                  System.out.println(team1.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (!suddenDeath &&
               (m_penalties1 + (PENALTIES_TO_TAKE - number) < m_penalties2)) {
               if (m_printCommentary)
                  System.out.println(team2.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (suddenDeath) {
               if (m_penalties1 == m_penalties2 + 1) {
                  if (m_printCommentary)
                     System.out.println(team2.getName() + " wins the penalty shootout!");

                  clinched = true;
               }

               else if (m_penalties1 + 1 == m_penalties2) {
                  if (m_printCommentary)
                     System.out.println(team1.getName() + " wins the penalty shootout!");

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
            if (!kickPenalty(2, team2.getName(), m_attack2, m_gk1)) {
               System.err.println("Penalty kick failed");
               return false;
            }

            if (!suddenDeath &&
               (m_penalties1 + (PENALTIES_TO_TAKE - number) < m_penalties2)) {
               if (m_printCommentary)
                  System.out.println(team2.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (!suddenDeath &&
               (m_penalties2 + (PENALTIES_TO_TAKE - number) < m_penalties1)) {
               if (m_printCommentary)
                  System.out.println(team1.getName() + " wins the penalty shootout!");

               clinched = true;
            }

            else if (suddenDeath) {
               if (m_penalties1 == m_penalties2 + 1) {
                  if (m_printCommentary)
                     System.out.println(team2.getName() + " wins the penalty shootout!");

                  clinched = true;
               }

               else if (m_penalties1 + 1 == m_penalties2) {
                  if (m_printCommentary)
                     System.out.println(team1.getName() + " wins the penalty shootout!");

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
   }

   public abstract boolean playMatch();
   private abstract boolean processMatch();
}

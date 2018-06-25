import java.util.Random;

public abstract class Match implements Game {
   Random rand = new Random();

   public static final enum MATCH_TYPE { LEAGUE, CUP_GROUP, CUP_KNOCKOUT, FRIENDLY, TWO_LEG_FIRST, TWO_LEG_SECOND };

   private Team m_team1;
   private Team m_team2;
   private MATCH_TYPE m_type;

   public Match() {

   }

   public Match(Team team1, Team team2, MATCH_TYPE type) {
      m_team1 = team1;
      m_team2 = team2;
      m_type = type;
   }

   public abstract boolean playMatch();
}

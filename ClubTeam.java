public class ClubTeam extends Team {
   private String m_league;

   private int m_transferBudget;
   private int m_wageBudget;

   private int m_leagueWins;
   private int m_leagueDraws;
   private int m_leagueLosses;
   private int m_leaguePoints;
   private int m_leagueGoalsFor;
   private int m_leagueGoalsAgainst;

   public ClubTeam() {
      ClubTeam("");
   }

   public ClubTeam(String name) {
      ClubTeam(name, "");
   }

   public ClubTeam(String name, String code) {
      ClubTeam(name, code, "");
   }

   public ClubTeam(String name, String code, String league) {
      ClubTeam(name, code, league, "");
   }

   public ClubTeam(String name, String code, String league, String continent) {
      super(name, code, continent);

      m_league = league;

      m_transferBudget = -1;
      m_wageBudget = -1;

      m_leagueWins = 0;
      m_leagueDraws = 0;
      m_leagueLosses = 0;
      m_leaguePoints = 0;
      m_leagueGoalsFor = 0;
      m_leagueGoalsAgainst = 0;
   }

   public boolean setLeague(String league) {
      m_league = league;
      return true;
   }

   public String getLeague() {
      return m_league;
   }

   public int getTransferBudget() {
      return m_transferBudget;
   }

   public int getWageBudget() {
      return m_wageBudget;
   }

   public boolean setFinancials(int wageBudget, int transferBudget) {
      return setWageBudget(wageBudget) && setTransferBudget(transferBudget);
   }

   private boolean setWageBudget(int wageBudget) {
      m_wageBudget = wageBudget;
      return true;
   }

   private boolean setTransferBudget(int transferBudget) {
      m_transferBudget = transferBudget;
      return true;
   }
}

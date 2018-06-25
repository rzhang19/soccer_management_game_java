public class ClubTeam extends Team {
   private String m_league;
   private String m_continent;

   private int m_transferBudget;
   private int m_wageBudget;

   private int m_leagueWins;
   private int m_leagueDraws;
   private int m_leagueLosses;
   private int m_leaguePoints;
   private int m_leagueGoalsFor;
   private int m_leagueGoalsAgainst;

   public ClubTeam() {
      super();

      m_league = "";
      m_continent = "";

      m_transferBudget = -1;
      m_wageBudget = -1;

      m_leagueWins = 0;
      m_leagueDraws = 0;
      m_leagueLosses = 0;
      m_leaguePoints = 0;
      m_leagueGoalsFor = 0;
      m_leagueGoalsAgainst = 0;
   }

   public ClubTeam(String name) {
      super(name);

      m_league = "";
      m_continent = "";

      m_transferBudget = -1;
      m_wageBudget = -1;

      m_leagueWins = 0;
      m_leagueDraws = 0;
      m_leagueLosses = 0;
      m_leaguePoints = 0;
      m_leagueGoalsFor = 0;
      m_leagueGoalsAgainst = 0;
   }

   public ClubTeam(String name, String code) {
      super(name, code);

      m_league = "";
      m_continent = "";

      m_transferBudget = -1;
      m_wageBudget = -1;

      m_leagueWins = 0;
      m_leagueDraws = 0;
      m_leagueLosses = 0;
      m_leaguePoints = 0;
      m_leagueGoalsFor = 0;
      m_leagueGoalsAgainst = 0;
   }

   public ClubTeam(String name, String code, String league) {
      super(name, code);

      m_league = league;
      m_continent = "";

      m_transferBudget = -1;
      m_wageBudget = -1;

      m_leagueWins = 0;
      m_leagueDraws = 0;
      m_leagueLosses = 0;
      m_leaguePoints = 0;
      m_leagueGoalsFor = 0;
      m_leagueGoalsAgainst = 0;
   }

   public ClubTeam(String name, String code, String league, String continent) {
      super(name, code);

      m_league = league;
      m_continent = continent;

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

   public boolean setContinent(String continent) {
      m_continent = continent;
      return true;
   }

   public String getContinent() {
      return m_continent;
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

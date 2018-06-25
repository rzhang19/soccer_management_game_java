public abstract class Team {
   private static int idCount = 0;

   private static final int MAX_PLAYERS = 99;
   private static final int MIN_PLAYERS = 23;

   public static final enum TEAM_TYPE { CLUB, NATIONAL };

   private int m_id;
   private String m_name;
   private String m_code;

   private Player[] m_players;
   private int m_size;

   private int m_overall;

   private int m_totalWins;
   private int m_totalDraws;
   private int m_totalLosses;
   private int m_totalCupWins;
   private int m_totalGoalsFor;
   private int m_totalGoalsAgainst;

   public Team() {
      m_name = "";
      m_code = "";
      m_id = idCount;
      idCount++;
      m_size = 0;
      m_overall = -1;

      m_totalWins = 0;
      m_totalDraws = 0;
      m_totalLosses = 0;
      m_totalCupWins = 0;
      m_totalGoalsFor = 0;
      m_totalGoalsAgainst = 0;
   }

   public Team(String name) {
      m_name = name;
      m_code = "";
      m_id = idCount;
      idCount++;
      m_size = 0;
      m_overall = -1;

      m_totalWins = 0;
      m_totalDraws = 0;
      m_totalLosses = 0;
      m_totalCupWins = 0;
      m_totalGoalsFor = 0;
      m_totalGoalsAgainst = 0;
   }

   public Team(String name, String code) {
      m_name = name;
      m_code = code;
      m_id = idCount;
      idCount++;
      m_size = 0;
      m_overall = -1;

      m_totalWins = 0;
   }

   public boolean isValid() {
      return m_size >= MIN_PLAYERS && m_size < MAX_PLAYERS && m_code.length <= 3;
   }

   public int getID() {
      return m_id;
   }

   public boolean setName(String name) {
      m_name = name;
      return true;
   }

   public String getName() {
      return m_name;
   }

   public boolean setCode(String code) {
      if (code.length() > 3)
         return false;

      m_code = code;
      return true;
   }

   public String getCode() {
      return m_code;
   }

   public boolean addPlayer(Player addMe) {
      if (findPlayer(addMe))
         return false;

      m_players[m_size] = addMe;
      m_size++;
      return true;
   }

   public boolean removePlayer(Player removeMe) {
      if (!findMe(removeMe))
         return false;

      for (int x = 0; x < m_size; x++) {
         if (m_players[x].equals(removeMe)) {
            if(!(m_players[x].setClubTeam(FREE_AGENCY) &&
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

   public boolean findPlayer(Player findMe) {
      for (int x = 0; x < m_size; x++) {
         if (m_players[x].equals(findMe))
            return true;
      }

      return false;
   }

   public int getSize() {
      return m_size;
   }

   private boolean calculateOverall() {
      sum = 0;
      for (int x = 0; x < m_size; x++) {
         sum += m_players[x].getOverall();
      }

      m_overall = sum / m_size;
      return true;
   }
}

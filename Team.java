public abstract class Team {
   private static int idCount = 0;

   private static final int MAX_PLAYERS = 99;

   private int m_id;
   private String m_name;

   private Player[] m_players;
   private int m_size;

   public Team() {
      m_id = idCount;
      idCount++;
      m_size = 0;
   }

   public Team(String name) {
      m_name = name;
      idCount++;
      m_size = 0;
   }

   public int getID() {
      return m_id;
   }

   public String getName() {
      return m_name;
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
}

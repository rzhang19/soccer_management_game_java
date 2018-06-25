public class ClubTeam extends Team {
   private String m_league;
   private String m_continent;

   private int m_transferBudget = -1;
   private int m_wageBudget = -1;

   public ClubTeam() {
      super();
   }

   public ClubTeam(String name) {
      super(name);
   }

   public ClubTeam(String name, String code) {
      super(name, code);
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

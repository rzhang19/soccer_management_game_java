public class ClubTeam extends Team {
   private int m_transferBudget = -1;
   private int m_wageBudget = -1;

   public ClubTeam() {
      super();
   }

   public ClubTeam(String name) {
      super(name);
   }

   public int getTransferBudget() {
      return m_transferBudget;
   }

   public int getWageBudget() {
      return m_wageBudget;
   }
}

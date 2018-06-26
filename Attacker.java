public class Attacker extends Player {
   private static final int SHO_WEIGHT = 5;
   private static final int ANT_WEIGHT = 5;
   private static final int BCT_WEIGHT = 3;
   private static final int PAS_WEIGHT = 3;
   private static final int TAC_WEIGHT = 2;
   private static final int INT_WEIGHT = 1;
   private static final int GKR_WEIGHT = 1;
   private static final int GKB_WEIGHT = 1;
   private static final int SPE_WEIGHT = 4;
   private static final int STA_WEIGHT = 3;
   private static final int SUM = SHO_WEIGHT + ANT_WEIGHT + BCT_WEIGHT + PAS_WEIGHT + TAC_WEIGHT + INT_WEIGHT + GKR_WEIGHT +
                                 GKB_WEIGHT + SPE_WEIGHT + STA_WEIGHT;

   public Attacker() {
      Attacker("", "", "", new Date(), 0, new NationalTeam());
   }

   public Attacker(String firstName, String lastName, String nickName, Date birthday,
                  int jerseyNum, Team nationalTeam) {
      super(firstName, lastName, nickName, birthday, jerseyNum, nationalTeam);
   }

   public ROLE getRole() {
      return ATT;
   }

   private boolean calculateOverall() {
      m_overall = SHO_WEIGHT * m_shooting + ANT_WEIGHT * m_anticipation + BCT_WEIGHT * m_ballControl + PAS_WEIGHT * m_passing +
                  TAC_WEIGHT * m_tackling + INT_WEIGHT * m_interceptions + GKR_WEIGHT * m_gkReactions + GKB_WEIGHT * m_gkBlocking +
                  SPE_WEIGHT * m_speed + STA_WEIGHT * m_stamina;

      return true;
   }
}

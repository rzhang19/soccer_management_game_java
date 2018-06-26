/*
 * Attacker.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * Attacker class, child of Player class
 */

public class Attacker extends Player {
   // weight of attributes on overall, specific attribute listed
   private static final int SHO_WEIGHT = 5;  // shooting weight
   private static final int ANT_WEIGHT = 5;  // anticipation weight
   private static final int BCT_WEIGHT = 3;  // ball control weight
   private static final int PAS_WEIGHT = 3;  // passing weight
   private static final int TAC_WEIGHT = 2;  // tackling weight
   private static final int INT_WEIGHT = 1;  // interceptions weight
   private static final int GKR_WEIGHT = 1;  // goalkeeping reactions weight
   private static final int GKB_WEIGHT = 1;  // goalkeeping blocking weight
   private static final int SPE_WEIGHT = 4;  // speed weight
   private static final int STA_WEIGHT = 3;  // stamina weight

   // get the sum of the weights
   private static final int SUM = SHO_WEIGHT + ANT_WEIGHT + BCT_WEIGHT + PAS_WEIGHT + TAC_WEIGHT + INT_WEIGHT + GKR_WEIGHT +
                                 GKB_WEIGHT + SPE_WEIGHT + STA_WEIGHT;

   /*
    * Attacker();
    * Default constructor
    * Delegates construction up one level
    */
   public Attacker() {
      this("", "", "", new Date(), 0, new NationalTeam());
   }

   /*
    * Attacker(String,String,String,Date,int,Team);
    * Constructor
    * Delegates construction up one level
    */
   public Attacker(String firstName, String lastName, String nickName, Date birthday,
                  int jerseyNum, Team nationalTeam) {
      this(firstName, lastName, nickName, birthday, jerseyNum, nationalTeam,
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
   }

   /*
    * Attacker(String,String,String,Date,int,Team,int,int,int,int,int,int,int,int,int,int);
    * Main Constructor
    * Calls main constructor of Player parent class with all variables
    */
   public Attacker(String firstName, String lastName, String nickName, Date birthday,
                  int jerseyNum, Team nationalTeam,
                  int shooting, int anticipation, int ballControl, int passing,
                  int tackling, int interceptions, int gkReactions, int gkBlocking,
                  int speed, int stamina) {
      super(firstName, lastName, nickName, birthday, jerseyNum, nationalTeam,
         shooting, anticipation, ballControl, passing, tackling, interceptions,
         gkReactions, gkBlocking, speed, stamina);
   }

   /*
    * getRole();
    * @return - ROLE enum of Player, notably Attacker
    */
   public ROLE getRole() {
      return Player.ROLE.ATT;
   }

   /*
    * calculateOverall();
    * @return - boolean, true if successfully sets overall, false otherwise
    *
    * Overrides abstract method from Player parent class.
    */
   protected boolean calculateOverall() {
      if(!this.setOverall(SHO_WEIGHT * this.getShooting() + ANT_WEIGHT * this.getAnticipation() + BCT_WEIGHT * this.getBallControl() + PAS_WEIGHT * this.getPassing() +
                  TAC_WEIGHT * this.getTackling() + INT_WEIGHT * this.getInterceptions() + GKR_WEIGHT * this.getGKReactions() + GKB_WEIGHT * this.getGKBlocking() +
                  SPE_WEIGHT * this.getSpeed() + STA_WEIGHT * this.getStamina())) {
         System.err.println("Error with setting overall");
         return false;
      }

      return true;
   }
}

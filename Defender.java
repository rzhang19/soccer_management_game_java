/*
 * Defender.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * Defender class, child of Player class
 */

public class Defender extends Player {
   // weight of attributes on overall, specific attribute listed
   private static final int SHO_WEIGHT = 1;  // shooting weight
   private static final int ANT_WEIGHT = 3;  // anticipation weight
   private static final int BCT_WEIGHT = 2;  // ball control weight
   private static final int PAS_WEIGHT = 3;  // passing weight
   private static final int TAC_WEIGHT = 5;  // tackling weight
   private static final int INT_WEIGHT = 5;  // interceptions weight
   private static final int GKR_WEIGHT = 1;  // goalkeeping reactions weight
   private static final int GKB_WEIGHT = 1;  // goalkeeping blocking weight
   private static final int SPE_WEIGHT = 2;  // speed weight
   private static final int STA_WEIGHT = 3;  // stamina weight

   // get the sum of the weights
   private static final int SUM = SHO_WEIGHT + ANT_WEIGHT + BCT_WEIGHT + PAS_WEIGHT + TAC_WEIGHT + INT_WEIGHT + GKR_WEIGHT +
                                 GKB_WEIGHT + SPE_WEIGHT + STA_WEIGHT;

   /*
    * Defender();
    * Default constructor
    * Delegates construction up one level
    */
   public Defender() {
      this("", "", "", new Date(), 0, new NationalTeam());
   }

   /*
    * Defender(String,String,String,Date,int,Team);
    * Constructor
    * Delegates construction up one level
    */
   public Defender(String firstName, String lastName, String nickName, Date birthday,
                  int jerseyNum, Team nationalTeam) {
      this(firstName, lastName, nickName, birthday, jerseyNum, nationalTeam,
         Player.DEFAULT_ATTRIBUTE, Player.DEFAULT_ATTRIBUTE, Player.DEFAULT_ATTRIBUTE,
         Player.DEFAULT_ATTRIBUTE, Player.DEFAULT_ATTRIBUTE, Player.DEFAULT_ATTRIBUTE,
         Player.DEFAULT_ATTRIBUTE, Player.DEFAULT_ATTRIBUTE, Player.DEFAULT_ATTRIBUTE,
         Player.DEFAULT_ATTRIBUTE);
   }

   /*
    * Defender(String,String,String,Date,int,Team,int,int,int,int,int,int,int,int,int,int);
    * Main constructor
    * Calls main constructor of Player parent class with all variables
    */
   public Defender(String firstName, String lastName, String nickName, Date birthday,
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
    * Returns the ROLE of this Player, hardcoded as Defender

    * @return - ROLE enum of Player
    */
   public ROLE getRole() {
      return Player.ROLE.DEF;
   }

   /*
    * calculateOverall();
    * Overrides abstract method from Player parent class.
    *
    * @return - boolean, true if successfully sets overall, false otherwise
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

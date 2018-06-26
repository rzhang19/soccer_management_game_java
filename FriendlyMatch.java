/*
 * FriendlyMatch.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * Friendly Match class, child of Match class
 */

public class FriendlyMatch extends Match {
   // no member variables to date

   /*
    * FriendlyMatch();
    * Default constructor
    * Delegates construction up one level
    */
   public FriendlyMatch() {
      this(null, null);
   }

   /*
    * FriendlyMatch(Team,Team);
    * Main constructor
    * Calls main constructor of Match parent class with both variables
    * Also sets the enum Match Type to be a Friendly type
    */
   public FriendlyMatch(Team team1, Team team2) {
      super(team1, team2);

      setType(Match.MATCH_TYPE.FRIENDLY);
   }

   /*
    * playMatch();
    * Plays 90 minutes and processes the results
    *
    * @return - boolean, true if plays and processes Friendly Match successfully,
    *          false otherwise
    */
   public boolean playMatch() {
      return this.playNinetyMinutes() && this.processMatch();
   }

   /*
    * processMatch();
    * Processes results of match by calling processMatch() from Match parent class
    *
    * @return - boolean, true if successfully processes this Friendly Match for both Teams
    */
   protected boolean processMatch() {
      return this.getTeam1().processMatch(this) && this.getTeam2().processMatch(this);
   }
}

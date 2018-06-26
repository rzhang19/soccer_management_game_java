/*
 * Game.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * The Game interface contains methods that all types of games must implement.
 * Current requirements include:
 * (1) - play a Match
 * (2) - process the Match played
 */

public interface Game {
   /*
    * playMatch();
    * Plays the match
    *
    * @return - boolean, true if successfully played, false otherwise
    */
   public boolean playMatch();

   /*
    * processMatch();
    * Processes the results of the Match
    *
    * @return - boolean, true if successfully processed results, false otherwise
    */
   protected boolean processMatch();
}

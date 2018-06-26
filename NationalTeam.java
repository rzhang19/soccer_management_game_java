/*
 * NationalTeam.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * NationalTeam class, child of Team class
 */

public class NationalTeam extends Team {
   // maximum size of team
   public int MAX_NATIONAL_TEAM_SIZE = 23;

   // adjective description of nationality
   private String m_nationality;

   /*
    * NationalTeam();
    * Default constructor
    * Delegates construction up one level
    */
   public NationalTeam() {
      this("");
   }

   /*
    * NationalTeam(String);
    * Constructor
    * Delegates construction up one level
    */
   public NationalTeam(String name) {
      this(name, "");
   }

   /*
    * NationalTeam(String,String);
    * Constructor
    * Delegates construction up one level
    */
   public NationalTeam(String name, String code) {
      this(name, code, "");
   }

   /*
    * NationalTeam(String,String,String);
    * Constructor
    * Delegates construction up one level
    */
   public NationalTeam(String name, String code, String continent) {
      this(name, code, continent, "");
   }

   /*
    * NationalTeam(String,String,String,String);
    * Main constructor
    * Calls main constructor of Team parent class
    */
   public NationalTeam(String name, String code, String continent, String nationality) {
      super(name, code, continent);

      m_nationality = nationality;
   }

   /*
    * setNationality(String);
    * Sets the nationality to the parameter value
    *
    * @args (1) - String contains the nationality value
    * @return - boolean, true if nationality stored successfully, false otherwise
    */
   public boolean setNationality(String nationality) {
      m_nationality = nationality;
      return true;
   }

   /*
    * getNationality();
    * Returns the nationality of this NationalTeam
    *
    * @return - String, returns m_nationality
    */
   public String getNationality() {
      return m_nationality;
   }
}

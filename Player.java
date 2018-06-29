/*
 * Player.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * Abstract Player class holds all values for a generic football player
 */

public abstract class Player {
   // static ID counter
   private static int id_count = 0;

   // constant values
   private static final int MIN_JERSEY = 0;
   private static final int MAX_JERSEY = 99;
   private static final int MIN_WAGE = 0;
   private static final int MIN_CONTRACT_YEARS = 0;
   private static final int MAX_CONTRACT_YEARS = 6;
   private static final int MIN_ATTRIBUTE = 0;
   private static final int MAX_ATTRIBUTE = 99;

   // enum types for different types of Players
   public static enum ROLE { ATT, MID, DEF, GK, NONE };

   // identification variables
   private int m_id;
   private String m_firstName;
   private String m_lastName;
   private String m_nickName;
   private Date m_birthday;
   private int m_age;

   // team and financial variables
   private int m_jerseyNum;
   private int m_wage;
   private int m_contractLength;
   private int m_value;

   // teams of Player
   private Team m_clubTeam;
   private Team m_nationalTeam;

   // attributes of Player
   private int m_overall;
   private int m_shooting;
   private int m_anticipation;
   private int m_ballControl;
   private int m_passing;
   private int m_tackling;
   private int m_interceptions;
   private int m_gkReactions;
   private int m_gkBlocking;
   private int m_speed;
   private int m_stamina;

   /*
    * Player();
    * Default constructor
    * Delegates construction up one level
    */
   public Player() {
      this("", "", "", new Date(), 0, new NationalTeam());
   }

   /*
    * Player(String,String,String,Date,int,Team);
    * Constructor
    * Delegates construction up one level
    */
   public Player(String firstName, String lastName, String nickName, Date birthday,
                  int jerseyNum, Team nationalTeam) {
      this(firstName, lastName, nickName, birthday, jerseyNum, nationalTeam,
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
   }

   /*
    * Player(String,String,String,Date,int,Team,int,int,int,int,int,int,int,int,int,int);
    * Main constructor
    * All constructors for any Player type or subclass calls this constructor eventually
    * Sets all values to parameter values
    * Any values that require values are set to invalid values, which forces the
    * value to be set later
    */
   public Player(String firstName, String lastName, String nickName, Date birthday,
                  int jerseyNum, Team nationalTeam,
                  int shooting, int anticipation, int ballControl, int passing,
                  int tackling, int interceptions, int gkReactions, int gkBlocking,
                  int speed, int stamina) {
      m_firstName = firstName;
      m_lastName = lastName;
      m_nickName = nickName;
      m_birthday = birthday;
      m_jerseyNum = jerseyNum;
      m_nationalTeam = nationalTeam;

      m_age = -1;
      m_wage = 0;
      m_contractLength = 0;
      m_clubTeam = null;

      m_shooting = shooting;
      m_anticipation = anticipation;
      m_ballControl = ballControl;
      m_passing = passing;
      m_tackling = tackling;
      m_interceptions = interceptions;
      m_gkReactions = gkReactions;
      m_gkBlocking = gkBlocking;
      m_speed = speed;
      m_stamina = stamina;

      m_id = id_count;
      id_count++;
   }

   /*
    * getID();
    * Returns the ID of this Player
    *
    * @return - int, ID of this Player
    */
   public int getID() {
      return m_id;
   }

   /*
    * equals(Player);
    * Override of Object.equals()
    * Checks if this Player is the same as other by comparing ID values
    *
    * @args (1) - Player to be checked for equality against
    * @return - boolean, true if same Player, false otherwise
    */
   public boolean equals(Player other) {
      return m_id == other.getID();
   }

   /*
    * setFirstName(String);
    * Sets the first name of this Player to the parameter value
    *
    * @args (1) - String containing new first name for this Player, can be empty
    * @return - boolean, true if first name is successfully set, false otherwise
    */
   public boolean setFirstName(String name) {
      m_firstName = name;
      return true;
   }

   /*
    * setLastName(String);
    * Sets the last name of this Player to the parameter value
    *
    * @args (1) - String containing new last name for this Player, length > 0
    * @return - boolean, true if last name is successfully set, false otherwise
    */
   public boolean setLastName(String name) {
      if (name.length() <= 0)
         return false;

      m_lastName = name;
      return true;
   }

   /*
    * setNickName(String);
    * Sets the nickname of this Player to the parameter value
    *
    * @args (1) - String containing new nickname for this Player, can be empty
    * @return - boolean, true if last name successfully set, false otherwise
    */
   public boolean setNickName(String name) {
      m_nickName = name;
      return true;
   }

   /*
    * setName(String,String);
    * Sets the first and last names of this Player to the parameter values, respectively
    *
    * @args (1) - String containing new first name for this Player, can be empty
    * @args (2) - String containing new last name for this Player, length > 0
    * @return - boolean, true if both names set successfully, false otherwise
    */
   public boolean setName(String first, String last) {
      if (last.length() <= 0) {
         System.err.println("Error, last name cannot be empty");
         return false;
      }

      m_firstName = first;
      m_lastName = last;
      return true;
   }

   /*
    * setName(String,String,String);
    * Sets the first, last, and nick names to the parameter values, respectively
    *
    * @args (1) - String containing new first name for this Player, can be empty
    * @args (2) - String containing new last name for this Player, length > 0
    * @args (3) - String containing new nickname for this Player, can be empty
    * @return - boolean, true if all names set successfully, false otherwise
    */
   public boolean setName(String first, String last, String nickname) {
      if (last.length() <= 0) {
         System.err.println("Error, last name cannot be empty");
         return false;
      }

      m_firstName = first;
      m_lastName = last;
      m_nickName = nickName;
      return true;
   }

   /*
    * getFirstName();
    * Returns the first name of this Player
    *
    * @return - String, first name of this Player
    */
   public String getFirstName() {
      return m_firstName;
   }

   /*
    * getLastName();
    * Returns the last name of this Player
    *
    * @return - String, last name of this Player
    */
   public String getLastName() {
      return m_lastName;
   }

   /*
    * getFullName();
    * Returns the full name of this Player with a space in between
    *
    * @return - String, containing first name, space, last name of this Player
    */
   public String getFullName() {
      return m_firstName + " " + m_lastName;
   }

   /*
    * getCommaName();
    * Returns the full name of this Player with a comma in between, last name first
    *
    * @return - String, containing last name, comma, last name of this Player
    */
   public String getCommaName() {
      return m_lastName + ", " + m_firstName;
   }

   /*
    * getNickName();
    * Returns the nickname of this Player
    *
    * @return - String, nickname of this Player
    */
   public String getNickName() {
      return m_nickName;
   }

   /*
    * setBirthday(Date);
    * Sets the birthday of this Player to the parameter value
    * Fails if parameter Date is an invalid Date
    *
    * @args (1) - Date containing new birthday value
    * @return - boolean, true if successfully set, false otherwise (if invalid Date)
    */
   public boolean setBirthday(Date birthday) {
      return m_birthday.setDate(birthday.getMonth(), birthday.getDay(), birthday.getYear());
   }

   /*
    * getBirthday();
    * Returns the birthday of this Player
    *
    * @return - Date, the birthday of this Player
    */
   public Date getBirthday() {
      return m_birthday;
   }

   /*
    * calculateAge(Date);
    * Calculates the age of this Player and stores it in age variable
    * Fails if parameter Date is invalid
    *
    * @args (1) - Date containing the current Date to be calculated against
    * @return - boolean, true if age is successfully calculated, false otherwise
    */
   public boolean calculateAge(Date current) {
      if (!Date.isValidDate(current)) {
         System.err.println("Error, invalid current Date");
         return false;
      }

      m_age = m_birthday.getAge(current);
      return true;
   }

   /*
    * getAge(Date);
    * Returns the age of this Player
    *
    * @args (1) - Date containing current Date to be calculated against
    * @return - int, age of Player, or -1 if invalid current Date
    */
   public int getAge(Date current) {
      if (calculateAge(current))
         return m_age;

      return -1;
   }

   /*
    * getRole();
    * Abstract method, must be implemented by subclasses
    * Returns the Role of this Player
    *
    * @return - enum Player.ROLE, role of this Player
    */
   public abstract ROLE getRole();

   /*
    * setJerseyNum(int);
    * Sets the jersey number of this Player to the parameter value
    * Jersey number must be between limits as defined by constant values above
    *
    * @args (1) - int containing new jersey number value
    * @return - boolean, true if set successfully, false otherwise
    */
   public boolean setJerseyNum(int jerseyNum) {
      if (jerseyNum < MIN_JERSEY || jerseyNum > MAX_JERSEY)
         return false;

      m_jerseyNum = jerseyNum;
      return true;
   }

   /*
    * getJerseyNum();
    * Returns the jersey number of this Player
    *
    * @return - int, jersey number of this Player
    */
   public int getJerseyNum() {
      return m_jerseyNum;
   }

   /*
    * setWage(int);
    * Sets the wage of this Player to the parameter value
    * Wage must be above the minimum wage defined by constant value above
    *
    * @args (1) - int containing new wage value
    * @return - boolean, true if set successfully, false otherwise
    */
   public boolean setWage(int wage) {
      if (m_wage < MIN_WAGE)
         return false;

      m_wage = wage;
      return true;
   }

   /*
    * getWage();
    * Returns the wage of this Player
    *
    * @return - int, wage of this Player
    */
   public int getWage() {
      return m_wage;
   }

   /*
    * setContractLength(int);
    * Sets the contract length of this Player to the parameter
    * Contract length must be between limits as defined by constant values above
    *
    * @args (1) - int containing new contract length for this Player
    * @return - boolean, true if contract length set successfully, false otherwise
    */
   public boolean setContractLength(int contractLength) {
      if (contractLength < MIN_CONTRACT_YEARS || contractLength > MAX_CONTRACT_YEARS)
         return false;

      m_contractLength = contractLength;
      return true;
   }

   /*
    * getContractLength();
    * Returns the contract length of this Player to the parameter
    *
    * @return - int, contract length of this Player
    */
   public int getContractLength() {
      return m_contractLength;
   }

   /*
    * calculateValue();
    * Calculates the value of this Player
    *
    * @return - boolean, true if successfully calculated value, false otherwise
    */
   public boolean calculateValue() {
      return true;
   }

   /*
    * getValue();
    * Returns the value of this Player
    * Must first recalculate value
    *
    * @return - int containing value of Player
    */
   public int getValue() {
      if (calculateValue())
         return m_value;

      return false;
   }

   /*
    * signContract(int,int);
    * Sets both the contract length and the wage of this Player
    * We must check at least wage first, otherwise contract length will be set and
    * wage will not
    *
    * @args (1) - int containing new value for contract length
    * @args (2) - int containing new value for wage
    * @return - boolean, true if both values set successfully, false otherwise
    */
   public boolean signContract(int contractLength, int wage) {
      return setContractLength(contractLength) && setWage(wage);
   }

   /*
    * setClubTeam(Team);
    * Sets the Club Team of this Player to the parameter
    *
    * @args (1) - Team, new Club Team for this Player
    * @return - boolean, true if set successfully
    */
   public boolean setClubTeam(Team clubTeam) {
      m_clubTeam = clubTeam;
      return true;
   }

   /*
    * getClubTeam();
    * Returns the Club Team of this Player
    *
    * @return - Club Team of this Player
    */
   public Team getClubteam() {
      return m_clubTeam;
   }

   /*
    * setNationalTeam(Team);
    * Sets the National Team of this Player to the parameter
    *
    * @args (1) - Team, new National Team for this Player
    * @return - boolean, true if set successfully
    */
   public boolean setNationalTeam(Team nationalTeam) {
      m_nationalTeam = nationalTeam;
      return true;
   }

   /*
    * getNationalTeam();
    * Returns the National Team of this Player
    *
    * @return - National Team of this Player
    */
   public Team getNationalTeam() {
      return m_nationalTeam;
   }

   /*
    * getNationality();
    * Returns the nationality of this Player
    * Nationality is the adjective form of the National Team
    *
    * @return - nationality of this Player
    */
   public String getNationality() {
      return m_nationalTeam.getNationality();
   }

   /*
    * setOverall(int);
    * Sets the overall of this Player to the parameter
    *
    * @args (1) - int containing new overall value
    * @return - boolean, true if overall set successfully
    */
   public boolean setOverall(int overall) {
      m_overall = overall;
      return true;
   }

   /*
    * calculateOverall();
    * Abstract method, must be implemented by subclasses
    * Calculates and sets the overall of this player
    *
    * @return - boolean, true if overall set successfully, false otherwise
    */
   protected abstract boolean calculateOverall();

   /*
    * getOverall();
    * Returns the overall of this Player
    * Must first calculate and set overall
    *
    * @return - int, overall of Player, -1 if unable to calculate and set overall
    */
   public int getOverall() {
      if(calculateOverall())
         return m_overall;

      return -1;
   }

   /*
    * setShooting(int);
    * Sets the shooting attribute to the parameter
    * New attribute value must be within the limits defined by constant values above
    *
    * @args (1) - int containing new shooting value
    * @return - boolean, true if shooting set successfully, false otherwise
    */
   public boolean setShooting(int shooting) {
      if (shooting < MIN_ATTRIBUTE || shooting > MAX_ATTRIBUTE)
         return false;

      m_shooting = shooting;
      return true;
   }

   /*
    * getShooting();
    * Returns the shooting value of this Player
    *
    * @return - int, shooting value of this Player
    */
   public int getShooting() {
      return m_shooting;
   }

   /*
    * setAnticipation(int);
    * Sets the anticipation attribute to the parameter
    * New attribute value must be within the limits defined by constant values above
    *
    * @arts (1) - int containing new anticipation value
    * @return - boolean, true if anticipation set successfully, false otherwise
    */
   public boolean setAnticipation(int anticipation) {
      if (anticipation < MIN_ATTRIBUTE || anticipation > MAX_ATTRIBUTE)
         return false;

      m_anticipation = anticipation;
      return true;
   }

   /*
    * getAnticipation();
    * Returns the anticipation value of this Player
    *
    * @return - int, anticipation value of this Player
    */
   public int getAnticipation() {
      return m_anticipation;
   }

   /*
    * setBallControl(int);
    * Sets the ball control attribute to the parameter
    * New attribute value must be within the limits defined by constant values above
    *
    * @args (1) - int containing new ball control value
    * @return - boolean, true if ball control set successfully, false otherwise
    */
   public boolean setBallControl(int ballControl) {
      if (ballControl < MIN_ATTRIBUTE || ballControl > MAX_ATTRIBUTE)
         return false;

      m_ballControl = ballControl;
      return true;
   }

   /*
    * getBallControl();
    * Returns the ball control value of this Player
    *
    * @return - int, ball control value of this Player
    */
   public int getBallControl() {
      return m_ballControl;
   }

   /*
    * setPassing(int);
    * Sets the passing attribute to the parameter
    * New attribute value must be within the limits defined by constant values above
    *
    * @args (1) - int containing new passing value
    * @return - boolean, true if passing set successfully, false otherwise
    */
   public boolean setPassing(int passing) {
      if (passing < MIN_ATTRIBUTE || passing > MAX_ATTRIBUTE)
         return false;

      m_passing = passing;
      return true;
   }

   /*
    * getPassing();
    * Returns the passing value of this Player
    *
    * @return - int, passing value of this Player
    */
   public int getPassing() {
      return m_passing;
   }

   /*
    * setTackling(int);
    * Sets the tackling attribute to the parameter
    * New attribute value must be within the limits defined by constant values above
    *
    * @args (1) - int containing new tackling value
    * @return - boolean, true if tackling set successfully, false otherwise
    */
   public boolean setTackling(int tackling) {
      if (tackling < MIN_ATTRIBUTE || tackling > MAX_ATTRIBUTE)
         return false;

      m_tackling = tackling;
      return true;
   }

   /*
    * getTackling();
    * Returns the tackling value of this Player
    *
    * @return - int, tackling value of this Player
    */
   public int getTackling() {
      return m_tackling;
   }

   /*
    * setInterceptions(int);
    * Sets the interceptions attribute to the parameter
    * New attribute value must be within the limits defined by constant values above
    *
    * @args (1) - int containing new interceptions value
    * @return - boolean, true if interceptions set successfully, false otherwise
    */
   public boolean setInterceptions(int interceptions) {
      if (interceptions < MIN_ATTRIBUTE || interceptions > MAX_ATTRIBUTE)
         return false;

      m_interceptions = interceptions;
      return true;
   }

   /*
    * getInterceptions();
    * Returns the interceptions value of this Player
    *
    * @return - int, interceptions value of this Player
    */
   public int getInterceptions() {
      return m_interceptions;
   }

   /*
    * setGKReactions(int);
    * Sets the GK reactions attribute to the parameter
    * New attribute value must be within the limits defined by constant values above
    *
    * @args (1) - int containing new GK reactions value
    * @return - boolean, true if GK reactions set successfully, false otherwise
    */
   public boolean setGKReactions(int gkReactions) {
      if (gkReactions < MIN_ATTRIBUTE || gkReactions > MAX_ATTRIBUTE)
         return false;

      m_gkReactions = gkReactions;
      return true;
   }

   /*
    * getGKReactions();
    * Returns the GK reactions value of this Player
    *
    * @return - int, GK reactions value of this Player
    */
   public int getGKReactions() {
      return m_gkReactions;
   }

   /*
    * setGKBlocking(int);
    * Sets the GK blocking attribute to the parameter
    * New attribute value must be within the limits defined by constant values above
    *
    * @args (1) - int containing new GK blocking value
    * @return - boolean, true if GK blocking set successfully, false otherwise
    */
   public boolean setGKBlocking(int gkBlocking) {
      if (gkBlocking < MIN_ATTRIBUTE || gkBlocking > MAX_ATTRIBUTE)
         return false;

      m_gkBlocking = gkBlocking;
      return true;
   }

   /*
    * getGKBlocking();
    * Returns the GK blocking value of this Player
    *
    * @return - int, GK blocking value of this Player
    */
   public int getGKBlocking() {
      return m_gkBlocking;
   }

   /*
    * setSpeed(int);
    * Sets the speed attribute to the parameter
    * New attribute value must be within the limits defined by constant values above
    *
    * @args (1) - int containing new speed value
    * @return - boolean, true if speed set successfully, false otherwise
    */
   public boolean setSpeed(int speed) {
      if (speed < MIN_ATTRIBUTE || speed > MAX_ATTRIBUTE)
         return false;

      m_speed = speed;
      return true;
   }

   /*
    * getSpeed();
    * Returns the speed value of this Player
    *
    * @return - int, speed value of this Player
    */
   public int getSpeed() {
      return m_speed;
   }

   /*
    * setStamina(int);
    * Sets the stamina attribute to the parameter
    * New attribute value must be within the limits defined by constant values above
    *
    * @args (1) - int containing new stamina value
    * @return - boolean, true if stamina set successfully, false otherwise
    */
   public boolean setStamina(int stamina) {
      if (stamina < MIN_ATTRIBUTE || stamina > MAX_ATTRIBUTE)
         return false;

      m_stamina = stamina;
      return true;
   }

   /*
    * getStamina();
    * Returns the stamina value of this Player
    *
    * @return - int, stamina value of this Player
    */
   public int getStamina() {
      return m_stamina;
   }
}

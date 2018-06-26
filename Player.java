public abstract class Player {
   private static int id_count = 0;

   private static final int MIN_JERSEY = 0;
   private static final int MAX_JERSEY = 99;
   private static final int MIN_WAGE = 0;
   private static final int MIN_CONTRACT_YEARS = 0;
   private static final int MAX_CONTRACT_YEARS = 6;
   private static final int MIN_ATTRIBUTE = 0;
   private static final int MAX_ATTRIBUTE = 99;

   public static final enum ROLE { ATT, MID, DEF, GK, NONE; }
   public static final String FREE_AGENCY = "Free Agents";

   private int m_id;
   private String m_firstName;
   private String m_lastName;
   private String m_nickName;
   private Date m_birthday;
   private int m_age;

   private int m_jerseyNum;
   private int m_wage;
   private int m_contractLength;
   private int m_value;

   private Team m_clubTeam;
   private Team m_nationalTeam;

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

   public Player() {
      Player("", "", "", new Date(), 0, new NationalTeam());
   }

   public Player(String firstName, String lastName, String nickName, Date birthday,
                  int jerseyNum, Team nationalTeam) {
      Player(firstName, lastName, nickName, birthday, jerseyNum, nationalTeam,
         -1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
   }

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
      m_clubTeam = NULL;

      m_shooting = shootingl
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

   public int getID() {
      return m_id;
   }

   public boolean equals(Player other) {
      return m_id == other.getID();
   }

   public boolean setFirstName(String name) {
      m_firstName = name;
      return true;
   }

   public boolean setLastName(String name) {
      m_lastName = name;
      return true;
   }

   public boolean setNickName(String name) {
      m_nickName = name;
   }

   public boolean setName(String first, String last) {
      m_firstName = first;
      m_lastName = last;
      return true;
   }

   public String getFirstName() {
      return m_firstName;
   }

   public String getLastName() {
      return m_lastName;
   }

   public String getFullName() {
      return m_firstName + " " + m_lastName;
   }

   public String getCommaName() {
      return m_lastName + ", " + m_firstName;
   }

   public String getNickName() {
      return m_nickName;
   }

   public boolean setBirthday(Date birthday) {
      return m_birthday.setDate(birthday.getMonth(), birthday.getDay(), birthday.getYear());
   }

   public Date getBirthday() {
      return m_birthday;
   }

   public boolean calculateAge(Date current) {
      m_age = m_birthday.getAge(current);
      return true;
   }

   public int getAge(Date current) {
      m_age = m_birthday.getAge(current);
      return m_age;
   }

   public ROLE getRole();

   public boolean setJerseyNum(int jerseyNum) {
      if (jerseyNum < MIN_JERSEY || jerseyNum > MAX_JERSEY)
         return false;

      m_jerseyNum = jerseyNum;
      return true;
   }

   public int getJerseyNum() {
      return m_jerseyNum;
   }

   public boolean setWage(int wage) {
      if (m_wage < MIN_WAGE)
         return false;

      m_wage = wage;
      return true;
   }

   public int getWage() {
      return m_wage;
   }

   public boolean setContractLength(int contractLength) {
      if (contractLength < MIN_CONTRACT_YEARS || contractLength > MAX_CONTRACT_YEARS)
         return false;

      m_contractLength = contractLength;
      return true;
   }

   public int getContractLength() {
      return m_contractLength;
   }

   public boolean signContract(int contractLength, int wage) {
      return setContractLength(contractLength) && setWage(wage);
   }

   public boolean setClubTeam(Team clubTeam) {
      m_clubTeam = clubTeam;
      return true;
   }

   public Team getClubteam() {
      return m_clubTeam;
   }

   public boolean setNationalTeam(Team nationalTeam) {
      m_nationalTeam = nationalTeam;
      return true;
   }

   public Team getNationalTeam() {
      return m_nationalTeam;
   }

   private abstract boolean calculateOverall();

   public int getOverall() {
      if(calculateOverall())
         return m_overall;
      return -1;
   }

   public boolean setShooting(int shooting) {
      if (shooting < MIN_ATTRIBUTE || shooting > MAX_ATTRIBUTE)
         return false;

      m_shooting = shooting;
      return true;
   }

   public int getShooting() {
      return m_shooting;
   }

   public boolean setAnticipation(int anticipation) {
      if (anticipation < MIN_ATTRIBUTE || anticipation > MAX_ATTRIBUTE)
         return false;

      m_anticipation = anticipation;
      return true;
   }

   public int getAnticipation() {
      return m_anticipation;
   }

   public boolean setBallControl(int ballControl) {
      if (ballControl < MIN_ATTRIBUTE || ballControl > MAX_ATTRIBUTE)
         return false;

      m_ballControl = ballControl;
      return true;
   }

   public int getBallControl() {
      return m_ballControl;
   }

   public boolean setPassing(int passing) {
      if (passing < MIN_ATTRIBUTE || passing > MAX_ATTRIBUTE)
         return false;

      m_passing = passing;
      return true;
   }

   public int getPassing() {
      return m_passing;
   }

   public boolean setTackling(int tackling) {
      if (tackling < MIN_ATTRIBUTE || tackling > MAX_ATTRIBUTE)
         return false;

      m_tackling = tackling;
      return true;
   }

   public int getTackling() {
      return m_tackling;
   }

   public boolean setInterceptions(int interceptions) {
      if (interceptions < MIN_ATTRIBUTE || interceptions > MAX_ATTRIBUTE)
         return false;

      m_interceptions = interceptions;
      return true;
   }

   public int getInterceptions() {
      return m_interceptions;
   }

   public boolean setGKReactions(int gkReactions) {
      if (gkReactions < MIN_ATTRIBUTE || gkReactions > MAX_ATTRIBUTE)
         return false;

      m_gkReactions = gkReactions;
      return true;
   }

   public int getGKReactions() {
      return m_gkReactions;
   }

   public boolean setGKBlocking(int gkBlocking) {
      if (gkBlocking < MIN_ATTRIBUTE || gkBlocking > MAX_ATTRIBUTE)
         return false;

      m_gkBlocking = gkBlocking;
      return true;
   }

   public int getGKBlocking() {
      return m_gkBlocking;
   }

   public boolean setSpeed(int speed) {
      if (speed < MIN_ATTRIBUTE || speed > MAX_ATTRIBUTE)
         return false;

      m_speed = speed;
      return true;
   }

   public int getSpeed() {
      return m_speed;
   }

   public boolean setStamina(int stamina) {
      if (stamina < MIN_ATTRIBUTE || stamina > MAX_ATTRIBUTE)
         return false;

      m_stamina = stamina;
      return true;
   }

   public int getStamina() {
      return m_stamina;
   }
}

public abstract class Player {
   private static int id_count = 0;

   private int m_id;
   private String m_name;
   private Date m_birthday;
   private int m_age;

   private int m_jerseyNum;
   private int m_wage;
   private int m_contractLength;

   private Team clubTeam;

   public Player() {
      m_name = "";
      m_birthday = new Date();
      m_age = -1;
      m_jerseyNum = 0;
      m_wage = 0;
      m_contractLength = 0;
   }
}

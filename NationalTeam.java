public class NationalTeam extends Team {
   public int MAX_NATIONAL_TEAM_SIZE = 23;

   private String m_nationality;

   public NationalTeam() {
      NationalTeam("");
   }

   public NationalTeam(String name) {
      NationalTeam(name, "");
   }

   public NationalTeam(String name, String code) {
      NationalTeam(name, code, "");
   }

   public NationalTeam(String name, String code, String continent) {
      NationalTeam(name, code, continent, "");
   }

   public NationalTeam(String name, String code, String continent, String nationality) {
      super(name, code, continent);
   }

   public boolean setNationality(String nationality) {
      m_nationality = nationality;
      return true;
   }

   public String getNationality() {
      return m_nationality;
   }
}

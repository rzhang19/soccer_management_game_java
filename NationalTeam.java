public class NationalTeam extends Team {
   public int MAX_NATIONAL_TEAM_SIZE = 23;

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
      super(name, code, continent);
   }
}

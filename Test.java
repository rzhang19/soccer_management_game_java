import java.util.Random;

public class Test {
   public static void main (String[] args) {
      //Random num = new Random(); // random number generator

      // create two club teams
      Team team1 = new ClubTeam("Bayern Munich", "BAY", "Bundesliga", "Europe");
      Team team2 = new ClubTeam("Borussia Dortmund", "DOR", "Bundesliga", "Europe");

      // create two national teams to keep track of nationality
      Team team3 = new NationalTeam("Germany", "GER", "Europe", "German");
      Team team4 = new NationalTeam("Mexico", "MEX", "North America", "Mexican");

      // shooting, anticipation, ball control, passing, tackling, interceptions, reactions, blocking, speed, stamina
      Player[] m_players1 = { new Goalkeeper("Jan","Mueller","",  new Date(06,07,1990),1,team3,20,75,70,80,50,25,90,95,10,50),
                              new Defender("Luca","Schneider","", new Date(10,11,1984),2,team3,45,70,55,65,95,95,25,40,55,65),
                              new Defender("Tim","Wolf","",       new Date(01,05,1991),3,team3,50,60,35,45,90,85,10,15,85,90),
                              new Defender("Frank","Zimmerman","",new Date(12,31,1998),4,team3,60,55,50,50,80,85,15,15,95,90),
                              new Defender("David","Krueger","",  new Date(05,06,1995),5,team3,45,85,20,40,95,85,20,10,75,80),
                              new Midfielder("Thomas","Bauer","", new Date(07,15,2000),6,team3,45,65,80,70,85,90,10,10,75,90),
                              new Midfielder("Gary","Lange","",   new Date(10,20,1996),7,team3,65,35,70,65,45,35,10,10,95,70),
                              new Midfielder("Ed","Hoffman","",   new Date(05,17,1989),8,team3,80,45,75,75,55,50,10,10,85,90),
                              new Attacker("Alex","Kaiser","",    new Date(01,07,1992),9,team3,95,85,80,65,35,30,10,10,80,90),
                              new Attacker("Jonas","Fuchs","",   new Date(02,18,1995),10,team3,85,75,85,80,25,15,10,10,95,75),
                              new Midfielder("Hans","Herrman","",new Date(03,25,1996),11,team3,85,55,90,80,15,35,15,15,95,60) };

      Player[] m_players2 = { new Goalkeeper("Javier","Ramos","",    new Date(07,19,1985),1,team4,15,50,25,55,25,25,75,75,25,55),
                              new Defender("Diego","Rodriguez","",   new Date(11,25,1990),2,team4,35,65,45,75,95,95,25,15,65,80),
                              new Defender("Lorenzo","Martinez","",  new Date(12,06,1989),3,team4,55,50,40,65,85,80,15,15,70,70),
                              new Defender("Pablo","Diaz","",        new Date(04,15,1994),4,team4,35,55,25,85,90,85,10,10,65,75),
                              new Midfielder("Angel","Ruiz","",      new Date(10,10,1994),5,team4,45,75,40,65,75,90,10,10,55,80),
                              new Midfielder("Alberto","Ortega","",  new Date(07,14,1996),6,team4,75,40,70,65,35,40,15,10,80,75),
                              new Midfielder("Marcos","Pas","",      new Date(06,27,1992),7,team4,55,40,75,90,15,15,15,10,95,90),
                              new Midfielder("Gabriel","Vega","",    new Date(03,15,1996),8,team4,95,60,85,90,65,55,10,10,60,90),
                              new Attacker("Paco","Cortes","",       new Date(07,18,1995),9,team4,90,90,85,65,35,25,10,10,85,85),
                              new Midfielder("Jon","Bravo","",      new Date(05,05,1997),10,team4,80,75,90,95,15,25,10,10,75,60),
                              new Attacker("Bruno","Romero","",     new Date(04,22,1999),11,team4,75,90,85,70,15,20,10,10,75,75) };

      // set current date
      Date today = new Date(6,25,2018);

      for (int x = 0; x < m_players1.length; x++) {
         // calculate age for every player
         if(!(m_players1[x].calculateAge(today)))
            System.err.println("Error with calculating age");

         // sign players to contract
         if(!(m_players1[x].signContract(5,10)))
            System.err.println("Error signing contract");

         // add players to club team
         if(!(team1.addPlayer(m_players1[x])))
            System.err.println("Error adding player to team");
      }

      for (int x = 0; x < m_players2.length; x++) {
         if(!(m_players2[x].calculateAge(today)))
            System.err.println("Error with calculating age");

         if(!(m_players2[x].signContract(5,10)))
            System.err.println("Error signing contract");

         if(!(team2.addPlayer(m_players2[x])))
            System.err.println("Error adding player to team");
      }

      // to test, get nationality of first player on each club team
      System.out.println((team1.getPlayers())[0].getFullName() + " is " + (team1.getPlayers())[0].getNationality());
      System.out.println((team2.getPlayers())[0].getFullName() + " is " + (team2.getPlayers())[0].getNationality());
   }
}

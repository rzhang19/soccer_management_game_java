/*
 * Date.java
 * Robin Zhang, 2018
 * Soccer Management Game, Java
 *
 * Date class, holds functionality for a Date (MM-DD-YYYY) object
 */

public class Date {
   // base variables - month, day, year
   private int m_month;
   private int m_day;
   private int m_year;

   /*
    * Date();
    * Default constructor
    * Sets default date to January 1, 1970, Unix Epoch time
    */
   public Date() {
      this(1, 1, 1970);
   }

   /*
    * Date(int,int,int);
    * Main constructor
    * Sets month, day, year values to parameters
    */
   public Date(int month, int day, int year) {
      if (isValidDate(month, day, year)) {
         m_month = month;
         m_day = day;
         m_year = year;
      }

      else {
         System.err.println("Invalid Date constructed, resetting to defaults");
         m_month = 1;
         m_day = 1;
         m_year = 1;
      }
   }

   /*
    * setDate(int,int,int);
    * Sets a new Date to the parameters
    * @args (1) - int containing month value, to be stored in m_month
    *       (2) - int containing day value, to be stored in m_day
    *       (3) - int containing year value, to be stored in m_year
    * @return - boolean, true if all set correctly, false if not valid date
    */
   public boolean setDate(int month, int day, int year) {
      if (isValidDate(month, day, year)) {
         m_month = month;
         m_day = day;
         m_year = year;
         return true;
      }

      System.err.println("Error setting date");
      return false;
   }

   /*
    * getMonth();
    * Returns the month of this Date
    *
    * @return - int containing month of this Date
    */
   public int getMonth() {
      return m_month;
   }

   /*
    * getDay();
    * Returns the day of this Date
    *
    * @return - int containing day of this Date
    */
   public int getDay() {
      return m_day;
   }

   /*
    * getYear();
    * Returns the year of this Date
    *
    * @return - int containing year of this Date
    */
   public int getYear() {
      return m_year;
   }

   /*
    * isValidDate();
    * Checks if this Date is valid
    * Calls static function for validating any Date
    *
    * @return - boolean, true if valid date, false otherwise
    */
   public boolean isValidDate() {
      return Date.isValidDate(m_month, m_day, m_year);
   }

   /*
    * isValidDate(int,int,int);
    * Checks if Date formed by int parameters is valid
    *
    * @args (1) - int containing value for month
    *       (2) - int containing value for day
    *       (3) - int containing value for year
    * @return - returns whether the Date formed is a valid date, also
    *          checking for leap days
    */
   public static boolean isValidDate(int month, int day, int year) {
      if (day < 1 || month < 1 || year < 1970)
         return false;
      return !(((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day > 31) ||
            ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) ||
            (month == 2 && (((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && day > 29) ||
            (year % 100 == 0 && year % 400 != 0 && day > 28))));
   }

   /*
    * getAge(Date);
    * Returns the wage of someone with birthday of this Date
    *
    * @args (1) - Date contains the current date that we check this Date against
    * @return - returns the age of someone with birthday of this Date
    */
   public int getAge(Date other) {
      if (other.getYear() >= m_year) {
         if (other.getMonth() > m_month) {
            return other.getYear() - m_year;
         }
         else if (other.getMonth() == m_month) {
            if (other.getDay() >= m_day)
               return other.getYear() - m_year;
            else
               return other.getYear() - m_year - 1;
         }
         else if (other.getYear() == m_year)
            return -1;
         else
            return other.getYear() - m_year - 1;
      }
      else
         return -1;
   }

   /*
    * nextDay();
    * Moves this Date onto the next day
    * Calls the static function for all Dates
    *
    * @return - boolean, true if incremented Date successfully, false otherwise
    */
   public boolean nextDay() {
      return Date.nextDay(this);
   }

   /*
    * nextDay(Date);
    * Moves to the next day
    *
    * @args (1) - Date containing date to increment
    * @return - boolean, true if incremented parameter Date successfully,
    *          false if current Date or next Date is invalid
    */
   public static boolean nextDay(Date date) {
      if (date.getDay() < 28)
         return (date.setDate(date.getMonth(), date.getDay() + 1, date.getYear()));
      else if (date.getDay() == 28 && date.getMonth() == 2 &&
               ((date.getYear() % 100 == 0 && date.getYear() % 400 != 0) ||
               (date.getYear() % 4 != 0)))
         return (date.setDate(date.getMonth() + 1, 1, date.getYear()));
      else if (date.getDay() == 28)
         return (date.setDate(date.getMonth(), date.getDay() + 1, date.getYear()));
      else if (date.getDay() == 29 && date.getMonth() == 2 &&
               (date.getYear() % 400 == 0 ||
               (date.getYear() % 4 == 0 && date.getYear() % 100 != 0)))
         return (date.setDate(date.getMonth() + 1, 1, date.getYear()));
      else if (date.getDay() == 29)
         return (date.setDate(date.getMonth(), date.getDay() + 1, date.getYear()));
      else if (date.getDay() == 30 && (date.getMonth() == 4 || date.getMonth() == 6 ||
               date.getMonth() == 9 || date.getMonth() == 11))
         return (date.setDate(date.getMonth() + 1, 1, date.getYear()));
      else if (date.getDay() == 30)
         return (date.setDate(date.getMonth(), date.getDay() + 1, date.getYear()));
      else if (date.getDay() == 31 && (date.getMonth() == 1 || date.getMonth() == 3 ||
               date.getMonth() == 5 || date.getMonth() == 7 || date.getMonth() == 8 ||
               date.getMonth() == 10))
         return (date.setDate(date.getMonth() + 1, 1, date.getYear()));
      else if (date.getDay() == 31 && date.getMonth() == 12)
         return (date.setDate(1,1,date.getYear() + 1));
      else {
         System.err.println("Error advancing day");
         return false;
      }
   }

   /*
    * isAnniversary(Date);
    * Checks whether this Date is anniversary given current Date
    *
    * @args (1) - Date containing the current Date
    * @return - boolean, true if this Date is anniversary, false otherwise
    */
   public boolean isAnniversary(Date today) {
      return today.getDay() == m_day && today.getMonth() == m_month;
   }
}

public class Date {
   private int m_month;
   private int m_day;
   private int m_year;

   public Date() {
      m_month = 1;
      m_day = 1;
      m_year = 1970;
   }

   public Date(int month, int day, int year) {
      m_month = month;
      m_day = day;
      m_year = year;
   }

   public void setDate(int month, int day, int year) {
      m_month = month;
      m_day = day;
      m_year = year;
   }

   public int getMonth() {
      return m_month;
   }

   public int getDay() {
      return m_day;
   }

   public int getYear() {
      return m_year;
   }

   public boolean isValidDate() {
      if (m_day < 1 || m_month < 1 || m_year < 1970)
         return false;
      return !(((m_month == 1 || m_month == 3 || m_month == 5 || m_month == 7 || m_month == 8 || m_month == 10 || m_month == 12) && m_day > 31) ||
            ((m_month == 4 || m_month == 6 || m_month == 9 || m_month == 11) && m_day > 30) ||
            (m_month == 2 && (((m_year % 400 == 0 || (m_year % 4 == 0 && m_year % 100 != 0)) && m_day > 29) ||
            (m_year % 100 == 0 && m_year % 400 != 0 && m_day > 28))))
   }

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
}

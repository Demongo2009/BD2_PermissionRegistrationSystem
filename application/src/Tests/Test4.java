package Tests;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class Test4 extends Test
{

    @Override
    boolean performTest()
    {
        try
        {
            connect();

            //sprawdzamy ile jest dostepnych czynnosci w systemie
            PreparedStatement activitiesGetter = conn.prepareStatement("SELECT COUNT(*) FROM CZYNNOSCI");
            ResultSet rs = activitiesGetter.executeQuery();
            rs.next();
            int activitiesCount = rs.getInt(1);
            //zapamietujemy ich liczbe w zmiennej


            int [][]activitiesInMonthCount = new int[12][activitiesCount];
            String []activities = new String[activitiesCount];
            int []popularCount = new int[12];
            int []popularIndex = new int[12];

            PreparedStatement ps = conn.prepareStatement("SELECT CZYNNOSC_NUMER, COUNT(ID)\n" +
                    "FROM HISTORIE_WYKONANIA_CZYNNOSCI\n" +
                    "WHERE EXTRACT(MONTH FROM DATA)=?\n" +
                    "GROUP BY CZYNNOSC_NUMER\n" +
                    "ORDER BY 1");
            PreparedStatement czynn = conn.prepareStatement("SELECT NAZWA_CZYNNOSCI, NUMER\n" +
                    "FROM CZYNNOSCI\n" +
                    "ORDER BY 2");
            rs = czynn.executeQuery();
            //Pobieramy nazwy czynnosci
            for(int i=0;i<activitiesCount;i++)
            {
                rs.next();
                activities[i] = rs.getString(1);
            }
            System.out.println("\tCzynnsoci w systemie wraz z numerami:");
            for(int i =0;i<activitiesCount;i++)
            {
                System.out.println("["+(i+1)+"] "+activities[i]);
            }
            System.out.println("\n\n\n");

            //miesiace
            for(int i =1;i<=12;i++)
            {
                ps.setInt(1, i);
                rs = ps.executeQuery();
                int j =0;
                while(rs.next())
                {
                    activitiesInMonthCount[i-1][j++] = rs.getInt(2);
                }
            }
            for(int i =1;i<=12;i++)
            {
                int max=0;
                int maxIndex=1;
                for(int j = 0;j<activitiesCount;j++)
                {
                    if(activitiesInMonthCount[i-1][j]>max)
                    {
                        max = activitiesInMonthCount[i-1][j];
                        maxIndex=j;
                    }
                    popularCount[i-1]=max;
                    popularIndex[i-1]=maxIndex;
                }
                System.out.println(i+" miesiac najppopularniejsza:\t"+activities[maxIndex]);
            }
            //dla kazdego miesiaca wiemy jaka najpopularniejsza i ile razy wykonana czynnosc byla
            Random random = new Random();
            int selectedMonth = random.nextInt(12)+1; //losujemy jakis miesiac
//            System.out.println("WYLOSOWANO:"+selectedMonth );
            int mostPopular = popularIndex[selectedMonth-1]; // w tym miesiacu ta czynnosc najpopularniejsza
            int otherActivity=mostPopular;

            while (otherActivity==mostPopular)
            {
                otherActivity = random.nextInt(18)+1;
            }
            String subs = String.format("2021/%02d/12 20:20:20",selectedMonth); // wybieramy ze wstawiamy w tym miesiacu

            czynn = conn.prepareStatement("INSERT INTO Historie_wykonania_Czynnosci(DATA,STATUS_WYKONANIA,UWAGI,CZYNNOSC_NUMER,RODZAJ_UPRAWNIENIA,KONTO_PRACOWNIKA_ID_KONTA)\n" +
                    "VALUES ( TO_DATE(?,?), 'wykonana', 'uwaga', ?, 1, 42)");

            czynn.setString(1,subs);
            czynn.setString(2,"yyyy/mm/dd hh24:mi:ss");

            czynn.setInt(3,otherActivity);

            System.out.println("\nWykonanie czynnosci "+otherActivity+ " bedzie wlozone "+popularCount[selectedMonth-1]+ " razy do miesiaca: "+ selectedMonth+"\n");

            for(int i=0;i<popularCount[selectedMonth-1]+1;i++) //wkladamy o 1 wiecej czynnosci innego rodzaju niz ta najpopularniejsza
            {
                czynn.executeQuery();
            }

            for(int i =1;i<=12;i++)
            {
                ps.setInt(1, i);
                rs = ps.executeQuery();
                int j =0;
                while(rs.next())
                {
                    activitiesInMonthCount[i-1][j++] = rs.getInt(2);
                }
            }
            for(int i =1;i<=12;i++)
            {
                int max=0;
                int maxIndex=1;
                for(int j = 0;j<activitiesCount;j++)
                {
                    if(activitiesInMonthCount[i-1][j]>max)
                    {
                        max = activitiesInMonthCount[i-1][j];
                        maxIndex=j;
                    }
                    popularCount[i-1]=max;
                    popularIndex[i-1]=maxIndex;
                }
                System.out.println(i+" miesiac najppopularniejsza:\t"+activities[maxIndex]);
            }
            disconnect();
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return  true;
    }


    public static void main(String[] args)
    {
        Test tmp = new Test4();
        tmp.performTest();
    }
}

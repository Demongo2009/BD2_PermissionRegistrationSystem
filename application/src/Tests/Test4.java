package tests;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class Test4 extends Test
{

    @Override
    boolean performTest()
    {
        try
        {
            connect();
            int [][]months = new int[12][6];
            String []czynnosci = new String[6];
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
            ResultSet rs = czynn.executeQuery();
            for(int i=0;i<6;i++)
            {
                rs.next();
                czynnosci[i] = rs.getString(1);
            }

            //miesiace
            for(int i =1;i<=12;i++)
            {
                ps.setInt(1, i);
                rs = ps.executeQuery();
                int j =0;
                while(rs.next())
                {
                    months[i-1][j++] = rs.getInt(2);
                }
            }
            for(int i =1;i<=12;i++)
            {
                int max=0;
                int maxIndex=1;
                for(int j = 0;j<6;j++)
                {
                    if(months[i-1][j]>max)
                    {
                        max = months[i-1][j];
                        maxIndex=j;
                    }
                    popularCount[i-1]=max;
                    popularIndex[i-1]=maxIndex;
                }
                System.out.println(i+" miesiac najppopularniejsza:\t"+czynnosci[maxIndex]);
            }
            //dla kazdego miesiaca wiemy jaka najpopularniejsza i ile razy wykonana czynnosc byla
            Random random = new Random();
            int selectedMonth = random.nextInt(12); //losujemy jakis miesiac
//            System.out.println("WYLOSOWANO:"+selectedMonth );
            int mostPopular = popularIndex[selectedMonth]; // w tym miesiacu ta czynnosc najpopularniejsza
            int otherActivity=mostPopular;
            while (otherActivity==mostPopular)
            {
                otherActivity = random.nextInt(6)+1;
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
                    months[i-1][j++] = rs.getInt(2);
                }
            }
            for(int i =1;i<=12;i++)
            {
                int max=0;
                int maxIndex=1;
                for(int j = 0;j<6;j++)
                {
                    if(months[i-1][j]>max)
                    {
                        max = months[i-1][j];
                        maxIndex=j;
                    }
                    popularCount[i-1]=max;
                    popularIndex[i-1]=maxIndex;
                }
                System.out.println(i+" miesiac najppopularniejsza:\t"+czynnosci[maxIndex]);
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

package Database;

import java.sql.*;

public class DatabaseHandler
{
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String nickname = "c##bd_acc";
    private static String password = "haslo";

    void add_permission()
    {

    }

    public static void main(String[] args)
    {

        try
        {
            Connection conn;
            conn = DriverManager.getConnection(url,nickname,password);
             String sql = "SELECT * FROM PRACOWNICY WHERE ID = ?";
             PreparedStatement statement = conn.prepareStatement(sql);
             statement.setInt(1,20);
             ResultSet rs = statement.executeQuery();
             while (rs.next())
             {
                 int id = rs.getInt(1);
                 String imie = rs.getString(2);
                 String nazwisko = rs.getString(3);
                 int przelozony = rs.getInt(4);
                 System.out.println("id: "+id +"\nIMIE: " + imie + "\nnazwisko: "+nazwisko+"\nprzelozony: "
                 +przelozony );
             }

            // conn.close();
            int [][]months = new int[12][6];
            PreparedStatement ps = conn.prepareStatement("SELECT CZYNNOSC_NUMER, COUNT(ID)\n" +
                    "FROM HISTORIE_WYKONANIA_CZYNNOSCI\n" +
                    "WHERE EXTRACT(MONTH FROM DATA)=?\n" +
                    "GROUP BY CZYNNOSC_NUMER\n" +
                    "ORDER BY 1");
//            ps.setInt(1,3);
//            rs = ps.executeQuery();
//            while (rs.next())
//            {
//                System.out.println(rs.getInt(1));
//            }
            for(int i =1;i<=12;i++)
            {
                ps.setInt(1, i);
                rs = ps.executeQuery();
                int j =0;
                while(rs.next())
                {
//                    System.out.println(rs.getInt(1));
                    months[i-1][j++] = rs.getInt(2);
                }
            }
            for(int i =1;i<=12;i++)
            {
                int max=0;
                int maxIndex=1;
                for(int j = 0;j<6;j++)
                {
//                    System.out.print(months[i-1][j]+ " ");
                    if(months[i-1][j]>max)
                    {
                        max = months[i-1][j];
                        maxIndex=j;
                    }

                }
                System.out.println("Najpopularniejsza w miesiacu "+i+"-tym czynnosc: "+maxIndex);
            }

            conn.close();


        } catch (Exception e)
        {
            //TODO: handle exception
        }


    }


    public void addEmployee(int id, String name, String surname)
    {
        PreparedStatement statement = null;
        Connection conn = null;
        ResultSet  rs = null;
        try {
            conn = DriverManager.getConnection(url);

            statement = conn.prepareStatement("INSERT INTO EMPLOYEES VALUES(?,?,?)");
            statement.setInt(1, id);
            statement.setString(2,name);
            statement.setString(3,surname);
            statement.executeUpdate();

            rs.close();
            statement.close();
            conn.close();
        }catch( Exception e){
            e.printStackTrace();
        }
    }




    public void printEmployees()
    {
//        PreparedStatement statement = null;
//        Connection conn = null;
//        ResultSet rs = null;
//        String employees = "";
//        try{
//            conn = DriverManager.getConnection(url);
//            statement = conn.prepareStatement("SELECT * FROM EMPLOYEES");
//            rs = statement.executeQuery();
//            while( rs.next() )
//            {
//                System.out.println(rs.getInt("employee_id") + " " +
//                        rs.getString("employee_name") + " " +
//                        rs.getString("employee_surname"));
//
//            }
//            rs.close();
//            statement.close();
//            conn.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        try {
            Connection connection = DriverManager.getConnection(url, nickname, password);
            String sql = "SELECT * FROM PRACOWNIK";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                int id = rs.getInt(1);
                String imie = rs.getString(2);
                String nazwisko = rs.getString(3);
                int przelozony = rs.getInt(4);
                System.out.println("id: "+id +"\nIMIE: " + imie + "\nnazwisko: "+nazwisko+"\nprzelozony: "
                +przelozony );
            }

            connection.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

        }
    }

}

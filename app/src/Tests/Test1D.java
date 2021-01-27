package Tests;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test1D extends Test{
    static boolean run(){
        prepareTest();
        connect();


        insertPositionPermission(perm1Id,pos1Id,pos1);
        insertEmployeePermission(emp3Id, perm1Id, true, pos3Id, pos3);
        // now we should have 2 employees with permission one given today
        //first got the permission since he has certain position
        //second got it personally

        try{
            PreparedStatement statement = null;
            ResultSet rs = null;

            statement = conn.prepareStatement("SELECT dokoncze to kurwa jutro, juz pozno a te testy są do pizdy XD");
            //statement.setString();
            //statement.executeQuery();
            //statement.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        disconnect();
        return true;
    }


    /**
     * after this method there is:
     * 1 department
     * 2 permission kinds
     * 3 position
     * 4 employees with account each
     *
     * */
    public static boolean prepareTest(){
        connect();

        insertDepartment(dep1);

        insertPermissionKind(perm1);
        insertPermissionKind(perm2);

        insertPosition(pos1, dep1Id);
        insertPosition(pos2, dep1Id);
        insertPosition(pos2, dep1Id);

        insertEmployee(emp1Id, name1, sur1);
        insertEmployee(emp2Id, name2, sur2);
        insertEmployee(emp3Id, name3, sur3);
        insertEmployee(emp4Id, name4, sur4);

        insertEmployeeAcc(acc1Id, emp1Id, log1, pas1, pos1Id, name1, sur1, pos1);
        insertEmployeeAcc(acc2Id, emp2Id, log2, pas2, pos2Id, name2, sur2, pos2);
        insertEmployeeAcc(acc3Id, emp3Id, log3, pas3, pos3Id, name3, sur3, pos3);
        insertEmployeeAcc(acc4Id, emp4Id, log4, pas4, pos3Id, name2, sur2, pos2);

        disconnect();
        return true;
    }


}

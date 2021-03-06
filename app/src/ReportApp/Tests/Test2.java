package ReportApp.Tests;

import ReportApp.Database.DatabaseHandler;

import java.util.Objects;

public class Test2 extends Test{

    public boolean performTest(){

        DatabaseHandler databaseHandler = new DatabaseHandler();
        String[] actionList = {"Potwierdzenie faktury","Wypelnienie dokument", "Wyslanie dokumentu do urzedu",
                "Wykonanie przelewu","Zakup na potrzeby firmy","Usunięcie konta użytkownika","Dodanie konta użytkownika",
                "Zawieszenie konta użytkownika","Zarawcie umowy z klientem","Przekazanie produktu klientowi",
                "Przeprowadzenie spotkania z kandydatem","Podpisanie umowy z nowym pracownikiem",
                "Awans pracownika","Zwolnienie pracownika","Podwyżka pensji pracownika"
        };
        String[] departmentList = {"IT","Finansowy","Zarzadzanie","Marketing"};
        String[] permissionTypeList = {"Zarzadzanie dokumentami finansowymi","Zarządzanie rachunkiem firmowym", "Dostęp do systemu X",
                "Dostęp do systemu Y","Dostęp do systemu X z pełnym zakresem","Dostęp do systemu Y z pełnym zakresem","Prowadzenie negocjacji z klientem",
                "Dostęp do tajemnicy X","Dostęp do tajemnicy Y","Zarządzanie rekturacją",
                "Zarządzanie zasobami ludzkimi","Zarządzanie aktywami firmy",
        };
        String[] positionList = {"Stażysta","Mlodszy programista frontend", "Mlodszy programista backend"
                ,"Starszy programista frontend"
                ,"Starszy programista backend"
                ,"Marketingowiec"
                ,"Asystent głównego ksiegowego"
                ,"Administrator"
                ,"Kierownik zespołu programistow"
                ,"Czlonek rady nadzorczej"
                ,"Główny ksiegowy"
                ,"Ekspert ds. rynku"
                ,"Prezes",
        };

        int actionIndex = 0;
        int departmentIndex = 0;
        int permissionTypeIndex = 0;
        int accountID = 0;
        int positionIndex = 0;
        int insertIterations = 1;

        String textBefore = databaseHandler.generateEmployeePermissionReport(
                false,true ,false,
                permissionTypeList[permissionTypeIndex], departmentList[departmentIndex]);
        System.out.println("Before insert: \n"+textBefore);

        for(int i = accountID; i<accountID+insertIterations; i++){
            Test.insertEmployee(i,"Jan", "Janowski"+i);

            Test.insertEmployeePermission(i, permissionTypeIndex, false, positionIndex, positionList[positionIndex]);
        }

        String textAfter = databaseHandler.generateEmployeePermissionReport(
                false,true ,false,
                permissionTypeList[permissionTypeIndex], departmentList[departmentIndex]);
        System.out.println("After insert: \n"+textAfter);

        if(Objects.equals(textBefore,textAfter))
            return false;


        return true;
    }

}

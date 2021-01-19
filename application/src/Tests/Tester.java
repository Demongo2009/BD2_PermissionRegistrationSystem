package tests;

public class Tester
{
    Test []tests = {new Test4(),new Test4()};
    //tutaj bedzie odpowiednio: new Test1(),new Test2(),new Test3(),new Test4(),new Test5(),new Test6(),

    public static void main(String[] args)
    {
        Tester tester = new Tester();
        tester.tests[0].performTest();
        System.out.println("\n\n");
        tester.tests[1].performTest();
    }
}

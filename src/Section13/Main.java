package Section13;

import Section13.Domain.Employee;
import Section13.Domain.StoreEmployee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001,"Ralph",2015),
                new Employee(10005,"Carole",2021),
                new Employee(10022,"Jane",2013),
                new Employee(13151,"Laura",2020),
                new Employee(10050,"Jim",2018)
        ));
        employees.sort(new Employee.EmployeeComparator<Employee>("yearstarted").reversed());
        for(var ob : employees){
            System.out.println(ob);
        }
        System.out.println("Store Members");
        var comparator = new Employee.EmployeeComparator<>();
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10001,"Ralph",2015,"Target"),
                new StoreEmployee(10005,"Carole",2021,"Walmart"),
                new StoreEmployee(10022,"Jane",2013,"Macys"),
                new StoreEmployee(13151,"Laura",2020,"Walmart"),
                new StoreEmployee(10050,"Jim",2018,"Target")
        ));
        storeEmployees.sort(new StoreEmployee().new StoreComparator<>());
        for(var ob : storeEmployees){
            System.out.println(ob);
        }
    }
}

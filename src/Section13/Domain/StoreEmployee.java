package Section13.Domain;

import java.util.Comparator;

public class StoreEmployee extends Employee {
    private String store;
    public StoreEmployee(){

    }

    public StoreEmployee(int employeeId, String name, int yearStarted, String store) {
        super(employeeId, name, yearStarted);
        this.store = store;
    }

    @Override
    public String toString() {
        return "%-8s%s".formatted(store,super.toString());
    }

    public class StoreComparator<T extends StoreEmployee> implements Comparator<T>{

        @Override
        public int compare(StoreEmployee o1, StoreEmployee o2) {
            int result = o1.store.compareTo(o2.store);
            return result!=0? result : new Employee.EmployeeComparator<>("yearStarted").compare(o1,o2);
        }
    }
}

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeCatalog {

    private List<Employee> employees;

    public EmployeeCatalog(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> findEmployeesWithFirstName(String firstName) {
	return employees.stream()
	    .filter(e -> e.getFirstName().equals(firstName))
	    .collect(Collectors.toList());
    }

    public List<Employee> findAllEmployeesWorkingInDepartment(Employee.Department department) {
	return employees.stream()
	    .filter(e -> e.getDepartment() == department)
	    .collect(Collectors.toList());
    }

    public Employee findEmployeeWithHighestSalary() {
	return employees.stream()
	    .reduce(null, (x, y) -> {
		    if (x == null) {
			return y;
		    }
		    if (x.getSalary() > y.getSalary()) {
			return x;
		    }
		    return y;;
		})
	    .get();
    }

    class MinMax {
	int min;
	int max;

	public MinMax(int value) {
	    min = value;
	    max = value;
	}

	public MinMax combine(MinMax other) {
	    MinMax ret = new MinMax();
	    ret.min = Math.min(min, other.min);
	    ret.max = Math.min(max, other.max);
	    return ret;
	}
    }

    public int getSalaryGapInDepartment(Employee.Department department) {
	MinMax mm =  employees.stream()
	    .filter(e -> e.getDepartment() == department)
	    .map(e -> new MinMax(e.getSalary()))
	    .reduce(null, (prev, elem) -> {
		    if (prev == null) {
			return elem;
		    }
		    return prev.combine(elem);
		});
	return mm.max - mm.min;
    }

    public Employee.Department findDepartmentWithHighestCumulativeSalary() {
	emp.stream().map(e -> {
		return new Pair(e.getDeparment(),
				emp.filter(x -> x.getDepartment() == e.getDepartment())
				.map(Employee::getSalary)
				.reduce(0, (x, y) -> x + y));
				})
	    .reduce(0, (x, y) -> Math.max(x, y)
		    .getDepartment();

	
	return employees.stream()
	    .map(e -> {
		    Map<Deparment, Integer> m = new HashMap<>();
		    m.put(e.getDepartment(), e.getSalary());
		    return m;
		})
	    .reduce(null, (prev, elem) -> {
		    if (prev == null) {
			return elem;
		    }
		    Map<Deparment, Integer> m = new HashMap<>();
		    m.putAll(prev);
		    for (Department d : elem) {
			m.put(d, m.getOrDefault(d, 0) + elem.get(d));
		    }
		    return m;
		})
	    .enrtySet().stream().reduce(null, (prev, elem) -> {
		    if (prev == null) {
			return elem;
		    }
		    if (prev.getValue() > elem.getValue()) {
			return prev;
		    }
		    return elem;
		})
	    .getKey();
    }
}

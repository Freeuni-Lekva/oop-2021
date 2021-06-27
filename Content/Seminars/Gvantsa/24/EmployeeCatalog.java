import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class EmployeeCatalog {

    private List<Employee> employees;

    public EmployeeCatalog(List<Employee> employees) {
        this.employees = employees;
    }

    private class MinMax{

        private int min;
        private int max;

        public MinMax(int min, int max){
            this.min = min;
            this.max = max;
        }

        public int getMin(){
            return min;
        }

        public int getMax(){
            return max;
        }
    }

    public Employee findEmployeeWithHighestSalary() {
	    return employees
                .stream()
                .reduce((currentResult, employee) -> {
                    if (currentResult == null)
                        return employee;
                    if (currentResult.getSalary() > employee.getSalary())
                        return currentResult;
                    return employee;
                }).get();
    }

    public int getSalaryGapInDepartment(Employee.Department department) {
        MinMax result = employees
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(employee -> new MinMax(employee.getSalary(), employee.getSalary()))
                .reduce((currentResult, minMax) -> {
                    if (currentResult == null)
                        return minMax;

                    int min = Math.min(currentResult.getMin(), minMax.getMin());
                    int max = Math.max(currentResult.getMax(), minMax.getMax());

                    return new MinMax(min, max);
                }).get();

	    return result.getMax() - result.getMin();
    }

//    stream = [e1 , e2, e3]
//
//    stream.reduce(reduceFunc)
//
//    reduceFunc(result, nextElement);
//
//    r1 = reduceFunc(null, e1);



    public Employee.Department findDepartmentWithHighestCumulativeSalary() {
        Map<Employee.Department, Integer> result = employees
                .stream()
                .map(employee -> Map.of(employee.getDepartment(), employee.getSalary()))
                .reduce((resultMap, employeeMap) -> {
                    Map<Employee.Department, Integer> newMap = new HashMap<>();
                    newMap.putAll(resultMap);

                    if (resultMap == null)
                        return employeeMap;

                    for( Map.Entry<Employee.Department, Integer> entry: employeeMap.entrySet()){
                        int value = entry.getValue();
                        if(resultMap.containsKey(entry.getKey())){
                            value += resultMap.get(entry.getKey());
                        }
                        newMap.put(entry.getKey(), value);
                    }
                    return newMap;
                }).get();

        return result
                .entrySet()
                .stream()
                .reduce((currentResult, entry) -> {
                    if (currentResult == null)
                        return entry;
                    if (currentResult.getValue() > entry.getValue())
                        return currentResult;
                    return entry;
                })
                .get()
                .getKey();
    }

//    [e1, e2, e3]
//
//    [Map<>(d1, s1), Map<>(d2, s2), Map<>(d3, s3)]
//
//    [Map<>([(d1, s1 + s2), (d3, s3)])]
//
//            (d1 , s1) (d1, s2)
//
//    map(d1, sum1)
}

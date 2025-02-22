package com.baeldung.streamcollectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class StreamGroupingByCollectorUnitTest
{

        static class Employee {

                Integer employeeId;
                String employeeName;
                Integer departmentId;

                Employee(Integer employeeId, String employeeName, Integer departmentId) {
                        this.employeeId = employeeId;
                        this.employeeName = employeeName;
                        this.departmentId = departmentId;
                }

                public Integer getEmployeeId() {
                        return employeeId;
                }

                public void setEmployeeId(Integer employeeId) {
                        this.employeeId = employeeId;
                }

                public String getEmployeeName() {
                        return employeeName;
                }

                public void setEmployeeName(String employeeName) {
                        this.employeeName = employeeName;
                }

                public Integer getDepartmentId() {
                        return departmentId;
                }

                public void setDepartmentId(Integer departmentId) {
                        this.departmentId = departmentId;
                }
        }

        @Test
        public void
        test_groupingBy_collector()  {
                List<String> list = new ArrayList<>(
                    Arrays.asList("Foo", "Bar", "Bar", "Foo", "Bar")
                );

                Map<String, Long> result = list.stream() .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                Assert.assertEquals(new Long(2), result.get("Foo"));
                Assert.assertEquals(new Long(3), result.get("Bar"));

        }


        @Test
        public void
        test_groupingBy_collector_custom_object_attributes()  {

                List<Employee> list = new ArrayList<>(
                    Arrays.asList(new Employee(1, "Joe", 1),
                                  new Employee(2, "Josh", 1),
                                  new Employee(3, "Jamie", 2),
                                  new Employee(4, "Jim", 2),
                                  new Employee(5, "Jack", 2))
                );

                Map<Integer, Long> result = list.stream() .collect(Collectors.groupingBy(Employee::getDepartmentId, Collectors.counting()));
                Assert.assertEquals(new Long(2), result.get(1));
                Assert.assertEquals(new Long(3), result.get(2));

        }
}

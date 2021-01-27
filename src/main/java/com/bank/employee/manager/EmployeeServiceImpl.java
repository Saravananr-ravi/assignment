package com.bank.employee.manager;

import com.bank.employee.bean.DnaCandidate;
import com.bank.employee.bean.Employee;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EmployeeServiceImpl {

    public void printValues(int start, int end) {
        IntStream.rangeClosed(start, end).forEach(System.out::print);
        /**
         * Written consumer separately instead providing Sysout directly.
        * */
        //Consumer<Integer> display=System.out::println;
        //IntStream.rangeClosed(start, end).forEach(display);
    }

    public void printTopFiveEmpDetails() {

        List<Employee> employeeList = getAllEmployee();

        List<Employee> sortedList = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getJoiningDate)).limit(5)
                .collect(Collectors.toList());
        sortedList.forEach(System.out::println);

        /**
         * Written function separately to get joining date instead calling with method ref
         * */
        /*
        Function<Employee,LocalDateTime> getJoiningDate=Employee::getJoiningDate;
        List<Employee> sortedList1 = employeeList.stream()
                .sorted(Comparator.comparing(getJoiningDate)).limit(5)
                .collect(Collectors.toList());
        sortedList.forEach(System.out::println);*/
        /**
         * Tried with sort method which would sort original object
         * */
        //changing original object
        //employeeList.sort(Comparator.comparing(Employee::getJoiningDate));
        //employeeList.stream().limit(5).forEach(System.out::println);
        /**
         * Tried with separate comparator for re-usability.
         * */
        //Comparator<Employee> customComparator= Comparator.comparing(Employee::getJoiningDate);
        //employeeList.sort(customComparator).reversed();
        //employeeList.stream().limit(5).forEach(System.out::println);
    }

    public void getEmployeeByJoiningDateTime() {
        List<Employee> employeeList = getAllEmployee();
        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.FEBRUARY, 8, 6, 30, 40, 50000);
        Predicate<Employee> predicate = s -> s.getJoiningDate().equals(localDateTime);
        employeeList.stream().filter(predicate).collect(Collectors.toList()).forEach(System.out::println);
    }

    public void categorizeEmployee() {
        List<Employee> employeeList = getAllEmployee();
        Predicate<Employee> predicate = s -> s.getIsManger().compareTo(Boolean.TRUE) == 0;
        employeeList.forEach(e -> {
            if (predicate.test(e)) {
                System.out.println("Manager:" + " " + e);
            } else {
                System.out.println("Regular:" + " " + e);
            }
        });
        /**
         * To print Manager records alone.
         * */
        //employeeList.stream().filter(predicate).collect(Collectors.toList()).forEach(System.out::println);
    }

    public List<DnaCandidate> getCandidateList() {
        List<Employee> employeeList = getAllEmployee();
        Predicate<Employee> predicate = s -> ChronoUnit.YEARS.between(s.getJoiningDate(), LocalDateTime.now()) > 7;
        return employeeList.stream().filter(predicate).map(temp->
                 new DnaCandidate(temp.getName(),temp.getJoiningDate(),temp.getIsManger())).collect(Collectors.toList());
    }

    public void getWorkingDays() {
        LocalDate firstDayNextWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);
        LocalDate lastDayOfNextWeek = firstDayNextWeek.plusDays(6);
        List<LocalDate> holiday = new ArrayList<>();
        holiday.add(LocalDate.of(2021, Month.JANUARY, 26));
        List<DayOfWeek> weekEnd = new ArrayList<>();
        weekEnd.add(DayOfWeek.SATURDAY);
        weekEnd.add(DayOfWeek.SUNDAY);
        long daysBetween=ChronoUnit.DAYS.between(firstDayNextWeek,lastDayOfNextWeek);
        Predicate<LocalDate> isWorkingDay = d -> !(weekEnd.contains(d.getDayOfWeek()) || holiday.contains(d));
        long workingDays=Stream.iterate(firstDayNextWeek,date->date.plusDays(1)).limit(daysBetween).filter(isWorkingDay).count();

        Stream.iterate(firstDayNextWeek,date->date.plusDays(1)).limit(daysBetween).filter(isWorkingDay).map(LocalDate::getDayOfWeek).forEach(System.out::println);
       /* for (LocalDate date = firstDayNextWeek; (date.isEqual(lastDayOfNextWeek) ||
                date.isBefore(lastDayOfNextWeek)); date = date.plusDays(1)) {
            if (isWorkingDay.test(date)) {
                ++workingDays;
                System.out.println(date.getDayOfWeek());
            }
        }*/
        System.out.println(workingDays+" working days");
    }

    private List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("saravanan", LocalDateTime.of(2011, Month.FEBRUARY, 3, 6, 30, 40, 50000), true));
        employeeList.add(new Employee("karthi", LocalDateTime.of(2011, Month.DECEMBER, 3, 6, 30, 40, 50000), false));
        employeeList.add(new Employee("ravi", LocalDateTime.of(2021, Month.JANUARY, 3, 6, 30, 40, 50000), false));
        employeeList.add(new Employee("sakthi", LocalDateTime.of(2021, Month.MARCH, 3, 6, 30, 40, 50000), true));
        employeeList.add(new Employee("ramesh", LocalDateTime.of(2021, Month.FEBRUARY, 8, 6, 30, 40, 50000), false));
        employeeList.add(new Employee("suresh", LocalDateTime.of(2021, Month.FEBRUARY, 3, 6, 30, 40, 50000), false));
        employeeList.add(new Employee("anand", LocalDateTime.of(2021, Month.MAY, 3, 6, 30, 40, 50000), false));
        return employeeList;
    }
}
package database.Tests;

import database.Employee;
import database.SalaryType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class EmployeeTest extends DBTestable{
    Employee employee;
    public EmployeeTest() throws SQLException, ClassNotFoundException {
        super();
    }

    @Before
    public void init() throws SQLException {
        connection.setAutoCommit(false);
        employee = new Employee(connection);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    @Test
    public void createEmployeeTest() throws SQLException {
        employee.setUserId(17);
        employee.setSalary(30);
        SalaryType salaryType = new SalaryType();
        employee.setSalaryType(salaryType.getSalaryType("monthly"));
        employee.createEmployee();
    }

    @Test
    public void createEmployeeTest2() {
        employee.setUserId(16);
        employee.setSalary(30);
        SalaryType salaryType = new SalaryType();
        employee.setSalaryType(salaryType.getSalaryType("monthly"));
        try {
            employee.createEmployee();
        }catch (Exception e){
            assertEquals("employee already exists!",e.getMessage());
        }
    }
    @Test
    public void getEmployeeIdTest() throws SQLException {
        Employee e = employee.getEmployeeId(15);
        assertEquals(2,e.getSalaryType());
        assertEquals(15,e.getSalary());
    }
    @Test
    public void updateSalaryByUserIdTest() throws SQLException {
        employee.setSalary(50);
        employee.setUserId(15);
        employee.updateSalaryByUserId();
    }
}

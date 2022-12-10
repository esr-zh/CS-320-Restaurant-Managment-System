package database.Tests;

import database.Employee;
import database.SalaryType;
import database.Shift;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

import static org.testng.AssertJUnit.assertEquals;

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
    public void getEmployeeIdTest() throws SQLException {
        Employee e = employee.getEmployeeId(15);
        assertEquals(2,e.getSalaryType());
        assertEquals(15,e.getSalary());
    }
}

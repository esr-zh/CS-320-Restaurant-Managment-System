package database.Tests;

import database.Shift;
import database.TransactionHistory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ShiftTest extends DBTestable{

    Shift shift;
    public ShiftTest() throws SQLException, ClassNotFoundException {
        super();
    }

    @BeforeEach
    public void setUp() throws SQLException {
        connection.setAutoCommit(false);
        shift = new Shift(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    @Test
    public void createShiftTimeTest() throws SQLException {
        shift.setUserId(15);
        shift.setWorkingFrom(8);
        shift.setWorkingTo(11);
        assertTrue(shift.createShiftTime());
    }

    @Test
    public void createShiftTimeTest2() throws SQLException {
        shift.setUserId(16);
        shift.setWorkingFrom(6);
        shift.setWorkingFrom(11);
        assertTrue(shift.createShiftTime());
    }

    @Test
    public void getHowManyWorkingHoursByUserIdTest() throws SQLException {
        int res = shift.getHowManyWorkingHoursByUserId(15);
        Assertions.assertEquals(8, res);
    }
}

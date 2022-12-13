package database.Tests;

import database.TransactionHistory;
import database.utils.Connect;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionHistoryTest extends database.Tests.DBTestable {

    TransactionHistory TH;
    public TransactionHistoryTest() throws SQLException, ClassNotFoundException {
        super();
    }

    @BeforeEach
    void setUp() throws SQLException {
        connection.setAutoCommit(false);
        TH = new TransactionHistory(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    @Test
    void getTransactionHistoryByUserId() throws SQLException, ClassNotFoundException {
        List<List<String>> result = TH.getTransactionHistoryByUserId(2);
        System.out.println(result);
        assertEquals(1, result.size());
    }
    @Test
    void getTransactionHistoryByUserIdWithDifferentId() throws SQLException, ClassNotFoundException {
        List<List<String>> result = TH.getTransactionHistoryByUserId(1);
        System.out.println(result);
        assertEquals(0, result.size());
    }

    @Test
    void getAddToCartTableByUserId() throws SQLException, ClassNotFoundException {
        List<List<String>> result = TH.getAddToCartTableByUserId(4);
        System.out.println(result);
        assertEquals(0,result.size());
    }

    @Test
    void updatePaidStatus() throws SQLException, ClassNotFoundException {
        TH.setId(2);
        TH.setUserId(4);
        TH.updatePaidStatus();
        assertTrue(TH.getHasPaid());
    }
    @Test
    void doesTransactionHistoryExistsTest() throws SQLException, ClassNotFoundException {
        assertTrue(TH.doesTransactionHistoryExists(2));
    }

    @Test
    void doesTransactionHistoryDoesntExistsTest()  {
        try {
            assertFalse(TH.doesTransactionHistoryExists(25));
        }catch (Exception e){
            assertEquals("transaction id does not exist",e.getMessage());
        }

    }

    @Test
    void createTransactionHistory() throws SQLException, ClassNotFoundException {
        TH.setUserId(3);
        TH.setHasPaid(false);
        assertTrue(TH.createTransactionHistory());
    }

    @Test
    void createTransactionHistoryButTHeUserDidntPay() {
        TH.setUserId(4);
        TH.setHasPaid(false);
        try {
            TH.createTransactionHistory();
        }catch (Exception e){
            assertEquals("user should not create any new history if the user didn't pay the previous one",e.getMessage());
        }

    }
}
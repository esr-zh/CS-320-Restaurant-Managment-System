package database.Tests;

import database.TransactionHistory;

import org.junit.*;
import org.junit.jupiter.api.Assertions;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TransactionHistoryOwnerNavBar extends DBTestable{
    TransactionHistory TH;
    public TransactionHistoryOwnerNavBar() throws SQLException, ClassNotFoundException {
        super();
    }

    @Before
    public void init() throws SQLException {
        connection.setAutoCommit(false);
        TH = new TransactionHistory(connection);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    @Test
    public void getTransactionHistoryByUserId() throws SQLException, ClassNotFoundException {
        System.out.println("testing transaction history table");
        List<List<String>> result = TH.getTransactionHistoryByUserId(2);
        System.out.println(result);
        Assertions.assertEquals(1, result.size());
    }
    @Test
    public void getTransactionHistoryByUserIdWithDifferentId() throws SQLException, ClassNotFoundException {
        List<List<String>> result = TH.getTransactionHistoryByUserId(1);
        System.out.println(result);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void getAddToCartTableByUserIdTest() throws SQLException, ClassNotFoundException {
        System.out.println("testing add to cart table");
        List<List<String>> result = TH.getAddToCartTableByUserId(4);
        Assertions.assertEquals(0,result.size());
    }

    @Test
    public void updatePaidStatus() throws SQLException, ClassNotFoundException {
        System.out.println("running test updatePaidStatus");
        TH.setId(2);
        TH.setUserId(4);
        TH.updatePaidStatus();
        Assertions.assertTrue(TH.getHasPaid());
    }
    @Test
    public void doesTransactionHistoryExistsTest() throws SQLException, ClassNotFoundException {
        Assertions.assertTrue(TH.doesTransactionHistoryExists(2));
    }

    @Test
    public void doesTransactionHistoryDoesntExistsTest()  {
        try {
            assertFalse(TH.doesTransactionHistoryExists(25));
        }catch (Exception e){
            Assertions.assertEquals("transaction id does not exist",e.getMessage());
        }

    }

    @Test
    public void createTransactionHistory() throws SQLException, ClassNotFoundException {
        TH.setUserId(3);
        TH.setHasPaid(false);
        TransactionHistory TH_ = TH.createTransactionHistory();
        Assertions.assertEquals(3,TH_.getUserId());
    }

    @Test
    public void createTransactionHistoryButTHeUserDidntPay() {
        TH.setUserId(4);
        TH.setHasPaid(false);
        try {
            TH.createTransactionHistory();
        }catch (Exception e){
            Assertions.assertEquals("user should not create any new history if the user didn't pay the previous one",e.getMessage());
        }

    }
}

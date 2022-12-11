package database.Tests;

import database.OrderDetails;
import database.utils.Connect;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDetailsTest extends database.Tests.DBTestable {
    OrderDetails OD;
    public OrderDetailsTest() throws SQLException, ClassNotFoundException {
       super();
    }
    @BeforeEach
    public void init() throws SQLException {
        connection.setAutoCommit(false);
        OD = new OrderDetails(connection);
    }
    @BeforeEach
    public void rollBack() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }
    @Test
    public void addOrderDetailsTest() throws SQLException, ClassNotFoundException {
        OD.setTransactionId(2);
        OD.setMenuId(5);
        OD.setQuantity(1);
        assertTrue(OD.addOrderDetails());
    }

    @Test
    public void addOrderDetailsWrongTransIdTest()  {
        OD.setTransactionId(52);
        OD.setMenuId(5);
        OD.setQuantity(1);
        try {
            OD.addOrderDetails();
        }catch (Exception e){
            assertEquals("transaction id does not exist",e.getMessage());
        }
    }

    @Test
    public void addOrderDetailsWrongMenuIdTest()  {
        OD.setTransactionId(2);
        OD.setMenuId(55);
        OD.setQuantity(1);
        try {
            OD.addOrderDetails();
        }catch (Exception e){
            assertEquals("menu id not found",e.getMessage());
        }
    }
    @Test
    public void getOrderDetailsByTransactionIdTest() throws SQLException, ClassNotFoundException {
        List<List<String>> result = OD.getOrderDetailsByTransactionId(2);
        System.out.println(result);
        assertEquals(2,result.size());
    }

    @Test
    public void deleteOrderDetailsByIdTest() throws SQLException {
        OD.setId(2);
        assertTrue(OD.deleteOrderDetailsById());
    }

    @Test
    public void deleteOrderDetailsByIdWrongIdTest()  {
        OD.setId(12);
        try {
            OD.deleteOrderDetailsById();
        }catch (Exception e){
            assertEquals("order details id not found",e.getMessage());
        }
    }

    @Test
    public void getTotalPriceByTransactionIdTest() throws SQLException {
        OD.setTransactionId(2);
        int result = OD.getTotalPriceByTransactionId();
        assertEquals(75+165,result);
    }

}
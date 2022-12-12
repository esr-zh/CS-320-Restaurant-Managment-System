package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Shift {
    private Connection conn;
    private long id;
    private long userId;
    private long workingFrom;
    private long workingTo;

    public Shift(Connection conn) {
        this.conn = conn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getWorkingFrom() {
        return workingFrom;
    }

    public void setWorkingFrom(long workingFrom) {
        this.workingFrom = workingFrom;
    }

    public long getWorkingTo() {
        return workingTo;
    }

    public void setWorkingTo(long workingTo) {
        this.workingTo = workingTo;
    }

    public boolean createShiftTime() throws SQLException {
        Employee employee = new Employee(conn).getEmployeeId(userId);
        System.out.println(employee.getSalaryType());
        if (workingFrom < 0 || workingFrom > 24)
            throw new RuntimeException("working from must be in 24 time");
        if (workingTo < 0 || workingTo > 24)
            throw new RuntimeException("working from must be in 24 time");
        if (workingFrom > workingTo)
            throw new RuntimeException("enter a valid time");
        String SQL_INSERT = "INSERT INTO shift(user_id,working_from,working_to) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
        statement.setLong(1, userId);
        statement.setLong(2, workingFrom);
        if (employee.getSalaryType() == 2) {// if is monthly it must 8 working hours
            if (workingTo == 0)
                throw new RuntimeException("enter a valid time for workingTo");
            statement.setLong(3, workingTo);
        }else
            statement.setLong(3, workingFrom + 8);

        statement.executeUpdate();
        return true;
    }
    public int getHowManyWorkingHoursByUserId(long userId) throws SQLException {
        new User(conn).getUserById(userId);
        String SQL_INSERT = "SELECT working_from,working_to from shift WHERE shift.user_id = ?";
        PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
        statement.setLong(1, userId);
        ResultSet rs = statement.executeQuery();
        if (!rs.next()){
            throw new SQLException("shift info not found!");
        }

        System.out.println(rs.getString(1) + " " + rs.getString(2));

        return rs.getInt(2) - rs.getInt(1);
    }

    // calculate expense per employee
    public int calculateExpensePerEmployeeMonthly(long userId) throws SQLException {
        Employee employee = new Employee(conn).getEmployeeId(userId);
        SalaryType salaryType = new SalaryType();
        if (employee.getSalaryType() == salaryType.getSalaryType("monthly"))
            return (int) (employee.getSalary() * 30);
        if (employee.getSalaryType() == salaryType.getSalaryType("hourly"))
            return (int) (getHowManyWorkingHoursByUserId(userId) * employee.getSalary() * 30);

        return 0;
    }
}

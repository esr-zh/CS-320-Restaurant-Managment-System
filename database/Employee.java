package database;

import database.utils.Connect;

import java.sql.*;
import java.util.List;

public class Employee {
    private Connection conn;
    private long id;
    private long salary;
    private long userId;
    private long salaryType;

    public Employee(Connection conn) {
        this.conn = conn;
    }

    public Employee(long userId,long salary,long salaryType){
        this.userId = userId;
        this.salary = salary;
        this.salaryType = salaryType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(long salaryType) {
        this.salaryType = salaryType;
    }

    public List<List<String>> getAllEmployees() throws SQLException {
//        String[] columnNames = {"ID", "Name", "Role", "Contract", "Working From","Working To", "Salary"};
        String SQL_INSERT = "SELECT user.id, user.username, user.user_role, employee.salary_type, shift.working_from, shift.working_to, employee.salary from employee join user on employee.user_id = user.id join shift on employee.user_id=shift.user_id";
        PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
        ResultSet rs = statement.executeQuery();
        return Connect.returnArraylist(rs);
    }

    public Employee getEmployeeId(long userId) throws SQLException {
        String SQL_INSERT = "SELECT * from employee WHERE employee.user_id = ?";
        PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
        statement.setLong(1, userId);
        ResultSet rs = statement.executeQuery();
        if (!rs.next()){
            throw new SQLException("employee not found!");
        }

        return new Employee(rs.getLong(3),
                rs.getLong(2),
                rs.getLong(4));
    }

    public Boolean deleteEmployee(String username) throws SQLException, ClassNotFoundException {
        User user = new User(conn);
        User employeeUser = user.getUserByUsername(username);
        user.deleteUserUsername(username);
        String SQL_INSERT = "DELETE FROM employee WHERE employee.user_id = ?";
        try (
                PreparedStatement statement = conn.prepareStatement(SQL_INSERT,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setLong(1,employeeUser.getId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            return true;
        }
    }

    public Employee createEmployee() throws SQLException {
        String SQL_INSERT = "INSERT INTO employee(salary,user_id,salary_type) VALUES (?, ?, ?)";
        if (!doesEmployeeExists(userId)) {
            try (
                    PreparedStatement statement = conn.prepareStatement(SQL_INSERT,
                            Statement.RETURN_GENERATED_KEYS)
            ) {
                statement.setLong(1, salary);
                statement.setLong(2, userId);
                statement.setLong(3, salaryType);

                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
            return this;
        }

        throw new SQLException("employee already exists!");


    }

    private boolean doesEmployeeExists(long userId) throws SQLException {
        String SQL_QUERY = "SELECT * from employee where employee.user_id = ?";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setLong(1,userId);
        ResultSet resultSet = statement.executeQuery();
        new User(conn).getUserById(userId);
        return resultSet.next();
    }

    // update salary by user id
    public boolean updateSalaryByUserId() throws SQLException {
        String SQL_QUERY = "UPDATE employee SET salary = ? WHERE employee.user_id = ?";
        try (
                PreparedStatement statement = conn.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setLong(1, salary);
            statement.setLong(2, userId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("updating salary failed, no rows affected.");
            }
            return true;
        }
    }

//    public boolean updateEmployee(String username) throws SQLException, ClassNotFoundException {
//        String SQL_QUERY = "UPDATE employee SET user.username = ? employee.user_id = ?, user.user_role = ?,  employee.salary = ?, employee.salary_type = ?," +
//                "shift.working_from = ?,shift.working_to = ? WHERE employee.user_id = ? from employee join user on employee.user_id = user.id join shift on employee.user_id=shift.user_id";
//        Employee current = getEmployeeId(id);
//        try (PreparedStatement statement = conn.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
//            statement.setLong(7, id);
//
//            if (user.username = null){
//
//            }
//            if (user.user_role = null){
//
//            }
//            if (shift.working_from = null){
//
//            }
//            if (shift.working_to = null){
//
//            }
//            if (salary == 0)
//                setSalary(current.getSalary());
//            if (salaryType == 0)
//                setSalaryType(current.getSalaryType());
//            if (userId == 0)
//                setUserId(current.getUserId());
//
//            System.out.println(this);
//            int affectedRows = execute(statement);
//            if (affectedRows == 0) {
//                throw new SQLException("deleting menu failed, no rows affected.");
//            }
//            return true;
//        }
//    }

    @Override
    public String toString() {
        return "Employee{" +
                "conn=" + conn +
                ", id=" + id +
                ", salary=" + salary +
                ", userId=" + userId +
                ", salaryType=" + salaryType +
                '}';
    }

    public boolean updateEmployee(String newUsername, long userRole, Shift newShift) throws SQLException, ClassNotFoundException {
        Shift shift = new Shift(conn);
        User user = new User(conn);
        // updating username or userRole
        user.setId(userId);// this should not be invalid
        user.setUsername(newUsername);// update username
        user.setUserRole(userRole);// update user role
        // -----------------------

        // updating shift
        shift.setWorkingTo(newShift.getWorkingTo());
        shift.setWorkingFrom(newShift.getWorkingFrom());
        // ---------

        String SQL_QUERY = "UPDATE employee SET salary = ? salary_type = ? WHERE employee.user_id = ?";
        Employee currentEmp = getEmployeeId(userId);
        try (PreparedStatement statement = conn.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            if (salary != currentEmp.getSalary()) {
                setSalary(salary);

            }else {
                setSalary(currentEmp.getSalary());
            }

            if (salaryType != currentEmp.getSalaryType()){
                setSalaryType(salaryType);
            }else {
                setSalaryType(currentEmp.getSalaryType());
            }

            statement.setLong(1,salary);
            statement.setLong(2,salaryType);
            statement.setLong(3,userId);
            System.out.println(this);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("updating employee failed, no rows affected.");
            }

            return user.updateUser() && shift.updateShift();
        }



    }

}


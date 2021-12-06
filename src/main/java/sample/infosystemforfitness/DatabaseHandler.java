package sample.infosystemforfitness;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.sql.ResultSet;

public class DatabaseHandler  extends Configs{
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(String firstName, String lastName, String userName, String password, String gender, String phone) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME + "," +
                Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + "," +
                Const.USERS_GENDER + "," + Const.USERS_PHONE + "," + Const.USERS_ROLE+ ")" +
                "VALUES(?,?,?,?,?,?,?)";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, firstName);
            prSt.setString(2, lastName);
            prSt.setString(3, userName);
            prSt.setString(4, password);
            prSt.setString(5, gender);
            prSt.setString(6, phone);
            prSt.setString(7, "user");


            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(String username,String password) {
        ResultSet resSet = null;

        String select = "SELECT * FROM users WHERE username = \"" + username + "\" AND password = \"" + password + "\";";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getTrains() {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.TRAIN_TABLE + " WHERE day >= CURRENT_DATE AND id NOT IN  (SELECT id_train FROM userTrainings WHERE id_users IN (SELECT idusers FROM users WHERE username = \"" + User.instance().getUserName() +"\" ));";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getRecordTrains() {
        ResultSet resSet = null;

        String select = "SELECT * FROM trains WHERE day >= CURRENT_DATE AND id IN (SELECT id_train FROM userTrainings WHERE id_users IN (SELECT idusers FROM users WHERE username = \"" + User.instance().getUserName() + "\"))";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public int getCountOfRecent() {
        ResultSet resSet = null;
        int counter = 0;

        String select = "SELECT * FROM trains WHERE day >= CURRENT_DATE AND id IN (SELECT id_train FROM userTrainings WHERE id_users IN (SELECT idusers FROM users WHERE username = \"" + User.instance().getUserName() + "\"))";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while (resSet.next()) {
                counter++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return counter;


    }

    public void addTrain(String name,String day){
        String insert = "INSERT INTO " + Const.TRAIN_TABLE + "(" +
                Const.TRAIN_NAME + "," + Const.TRAIN_DAY +  ")" +
                "VALUES(?,?)";


        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, name);
            prSt.setString(2, day);


            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getPastTrains() {
        ResultSet resSet = null;

        String select = "SELECT * FROM trains WHERE day < CURRENT_DATE AND id IN (SELECT id_train FROM userTrainings WHERE payed = false and id_users IN (SELECT idusers FROM users WHERE username = \"" + User.instance().getUserName() + "\"))";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public int getCountOfPayed() throws SQLException {
        ResultSet resSet = null;
        int counter = 0;

        String select = "SELECT * FROM trains WHERE day <= CURRENT_DATE AND id IN (SELECT id_train FROM userTrainings WHERE payed = true and id_users IN (SELECT idusers FROM users WHERE username = \"" + User.instance().getUserName() + "\"))";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (resSet.next()) {
            counter++;
        }

        return counter;
    }



    public int getCountOfPassed() throws SQLException {
        ResultSet resSet = null;
        int counter = 0;

        String select = "SELECT * FROM trains WHERE day < CURRENT_DATE AND id IN (SELECT id_train FROM userTrainings WHERE id_users IN (SELECT idusers FROM users WHERE username = \"" + User.instance().getUserName() + "\"))";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        while(resSet.next()) {
            counter++;
        }
        return counter;
    }

    public void setTraining(String id_training) throws SQLException {
        System.out.println(id_training + " " + getUserId());
        String insert = "INSERT INTO userTrainings VALUES( " + id_training + " , " + getUserId() + " , false)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setPayed(String id_training) throws SQLException {
        String update = "UPDATE userTrainings SET payed = true  WHERE id_train = " + id_training + " AND  id_users = " + getUserId() + ";";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void denyTraining(String id_training) throws SQLException {
        String delete = "DELETE FROM userTrainings WHERE id_train = " + id_training + " AND  id_users = " + getUserId() + ";";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String getUserId() throws SQLException {
        String id = "";
        ResultSet resSet = null;
        String select = " SELECT idusers FROM users WHERE username = \"" + User.instance().getUserName() + "\"";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        while(resSet.next()){
            id = resSet.getString(1);
        }
        return id;
    }


}



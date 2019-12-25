/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
 */
package simpledb;

import javafx.scene.control.PasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ilnar
 */
public class Controller implements Initializable {

    /**
     * Подключение
     */
    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Подключение к базе данных
     */
    public Connection getConnection() {
        Connection conn;
        try {
            System.out.println("Database connected");
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final String dbURL = "jdbc:mysql://localhost:3306/schooldb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String dbUser = "root";
    private static final String dbPassword = "12345";
    @FXML
    private Button SIGN_IN;
    @FXML
    private Button TEACHER_SAVE_BUTTON;
    @FXML
    private Button TEACHER_SHOW_BUTTON;
    @FXML
    private Button TEACHER_DELETE_BUTTON;
    @FXML
    private TextField USERNAME;
    @FXML
    private TextField TEACHER_FIRSTNAME;
    @FXML
    private TextField TEACHER_LASTNAME;
    @FXML
    private TextField TEACHER_ADRESS;
    @FXML
    private TextField TEACHER_TELEPHONE;
    @FXML
    private TextField TEACHER_SALARY;
    @FXML
    private TextField TEACHER_EXPIRIENCE;
    @FXML
    private TextField TEACHER_ID;
    @FXML
    private PasswordField PASSWORD;
    @FXML
    private TextArea TEACHER_DB;
    @FXML
    private Button CABINET_SAVE_BUTTON;
    @FXML
    private Button CABINET_SHOW_BUTTON;
    @FXML
    private Button CABINET_DELETE_BUTTON;
    @FXML
    private TextField CABINET_ID;
    @FXML
    private TextField CABINET_SEAT;
    @FXML
    private TextField CABINET_EQUPMENT;
    @FXML
    private TextArea CABINET_DB;
    @FXML
    private Button TEACHER_CABINET_SAVE_BUTTON;
    @FXML
    private Button TEACHER_CABINET_SHOW_BUTTON;
    @FXML
    private Button TEACHER_CABINET_DELETE_BUTTON;
    @FXML
    private TextField TEACHER_CABINET_TEACHER_ID;
    @FXML
    private TextField TEACHER_CABINET_CABINET_ID;
    @FXML
    private TextArea TEACHER_CABINET_DB;
    @FXML
    private Button STUDENT_SAVE_BUTTON;
    @FXML
    private Button STUDENT_SHOW_BUTTON;
    @FXML
    private Button STUDENT_DELETE_BUTTON;
    @FXML
    private TextField STUDENT_FIRSTNAME;
    @FXML
    private TextField STUDENT_LASTNAME;
    @FXML
    private TextField STUDENT_TELEPHONE;
    @FXML
    private TextField STUDENT_GROUP_ID;
    @FXML
    private TextField STUDENT_DOB;
    @FXML
    private TextField STUDENT_ID;
    @FXML
    private TextField STUDENT_ADRESS;
    @FXML
    private TextArea STUDENT_DB;
    @FXML
    private Button GROUP_SAVE_BUTTON;
    @FXML
    private Button GROUP_SHOW_BUTTON;
    @FXML
    private Button GROUP_DELETE_BUTTON;
    @FXML
    private TextField GROUP_ID;
    @FXML
    private TextField GROUP_STUDENT_COUNT;
    @FXML
    private TextArea GROUP_DB;
    @FXML
    private Button CLASS_SAVE_BUTTON;
    @FXML
    private Button CLASS_SHOW_BUTTON;
    @FXML
    private Button CLASS_DELETE_BUTTON;
    @FXML
    private TextField CLASS_TEACHER_ID;
    @FXML
    private TextField CLASS_GROUP_ID;
    @FXML
    private TextField CLASS_NAME;
    @FXML
    private TextArea CLASS_DB;
    @FXML
    private TextArea PRIME_DB;
    @FXML
    private Button PRIME_SHOW_BUTTON;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        /**
         * Кнопка входа
         */
        if (event.getSource() == SIGN_IN) {
            if ((USERNAME.getText().equals("Ilnar")) && (PASSWORD.getText().equals("12345"))) {
                try {
                    SIGN_IN.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setTitle("MySQL");
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException ex) {
                }

            } else {

                USERNAME.setText("");
                PASSWORD.setText("");

            }

        }
        /**
         * Кнопка записи данных в базу данных
         */
        if (event.getSource() == TEACHER_SAVE_BUTTON) {
            String insert = "INSERT INTO teacher VALUES(" + TEACHER_ID.getText() + ", '" + TEACHER_FIRSTNAME.getText() + "', '" + TEACHER_LASTNAME.getText() + "', '" + TEACHER_ADRESS.getText()
                    + "', " + TEACHER_TELEPHONE.getText() + ", " + TEACHER_SALARY.getText() + ", " + TEACHER_EXPIRIENCE.getText() + ")";
            executeQuery(insert);

        }
        /**
         * Кнопка вывода базы данных
         */
        if (event.getSource() == TEACHER_SHOW_BUTTON) {
            TEACHER_DB.setText(null);
            TEACHER_DB.appendText(String.format("%20s%20s%20s%20s%20s%20s%20s%n", "ID", "First name", "Last name", "Adres", "Phone number", "Salary", "Expirience"));
            TEACHER_DB.appendText(String.format("%s%n", "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
            String select = "SELECT * FROM teacher";
            try {
                Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                Statement st = dbConnection.createStatement();
                ResultSet rs = st.executeQuery(select);
                while (rs.next()) {
                    TEACHER_DB.appendText(String.format("%20s%20s%20s%20s%20s%20s%20s%n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                    TEACHER_DB.setEditable(false);
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        /**
         * Кнопка удаления из базы данных
         */
        if (event.getSource() == TEACHER_DELETE_BUTTON) {
            String delete = "DELETE FROM teacher WHERE teacher_firstname= '" + TEACHER_FIRSTNAME.getText() + "';";
            executeQuery(delete);

        }
        /**
         * Кнопка записи данных в базу данных
         */
        if (event.getSource() == TEACHER_CABINET_SAVE_BUTTON) {
            String insert = "INSERT INTO teacher_cabinet VALUES(" + TEACHER_CABINET_TEACHER_ID.getText() + ", " + TEACHER_CABINET_CABINET_ID.getText() + ")";
            executeQuery(insert);

        }
        /**
         * Кнопка вывода базы данных
         */
        if (event.getSource() == TEACHER_CABINET_SHOW_BUTTON) {
            TEACHER_CABINET_DB.setText(null);
            TEACHER_CABINET_DB.appendText(String.format("%50s%50s%n", "Teacher ID", "Cabinet ID"));
            TEACHER_CABINET_DB.appendText(String.format("%s%n", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
            String select = "SELECT * FROM teacher_cabinet";
            try {
                Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                Statement st = dbConnection.createStatement();
                ResultSet rs = st.executeQuery(select);
                while (rs.next()) {
                    TEACHER_CABINET_DB.appendText(String.format("%50s%50s%n", rs.getString(1), rs.getString(2)));
                    TEACHER_CABINET_DB.setEditable(false);
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        /**
         * Кнопка удаления из базы данных
         */
        if (event.getSource() == TEACHER_CABINET_DELETE_BUTTON) {
            String delete = "DELETE FROM teacher_cabinet WHERE teacher_id= " + TEACHER_CABINET_TEACHER_ID.getText() + ";";
            executeQuery(delete);
        }
        /**
         * Кнопка записи данных в базу данных
         */
        if (event.getSource() == CABINET_SAVE_BUTTON) {
            String insert = "INSERT INTO cabinet VALUES(" + CABINET_ID.getText() + ", " + CABINET_SEAT.getText() + ", " + CABINET_EQUPMENT.getText() + ")";
            executeQuery(insert);

        }
        /**
         * Кнопка вывода базы данных
         */
        if (event.getSource() == CABINET_SHOW_BUTTON) {
            CABINET_DB.setText(null);
            CABINET_DB.appendText(String.format("%40s%40s%40s%n", "ID", "Seat count", "Equpment count"));
            CABINET_DB.appendText(String.format("%s%n", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
            String select = "SELECT * FROM cabinet";
            try {
                Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                Statement st = dbConnection.createStatement();
                ResultSet rs = st.executeQuery(select);
                while (rs.next()) {
                    CABINET_DB.appendText(String.format("%40s%40s%40s%n", rs.getString(1), rs.getString(2), rs.getString(3)));
                    CABINET_DB.setEditable(false);
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        /**
         * Кнопка удаления из базы данных
         */
        if (event.getSource() == CABINET_DELETE_BUTTON) {
            String delete = "DELETE FROM cabinet WHERE cabinet_id= " + CABINET_ID.getText() + ";";
            executeQuery(delete);
        }
        /**
         * Кнопка записи данных в базу данных
         */
        if (event.getSource() == STUDENT_SAVE_BUTTON) {
            String insert = "INSERT INTO student VALUES(" + STUDENT_ID.getText() + ", '" + STUDENT_FIRSTNAME.getText() + "', '" + STUDENT_LASTNAME.getText() + "', '" + STUDENT_ADRESS.getText() + "', " + STUDENT_TELEPHONE.getText() + ", " + STUDENT_GROUP_ID.getText() + ")";
            executeQuery(insert);

        }
        /**
         * Кнопка вывода базы данных
         */
        if (event.getSource() == STUDENT_SHOW_BUTTON) {
            STUDENT_DB.setText(null);
            STUDENT_DB.appendText(String.format("%20s%20s%20s%20s%20s%20s%n", "ID", "First name", "Last name", "Adress", "Phone number", "Droup ID"));
            STUDENT_DB.appendText(String.format("%s%n", "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
            String select = "SELECT * FROM student";
            try {
                Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                Statement st = dbConnection.createStatement();
                ResultSet rs = st.executeQuery(select);
                while (rs.next()) {
                    STUDENT_DB.appendText(String.format("%20s%20s%20s%20s%20s%20s%n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
                    STUDENT_DB.setEditable(false);
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        /**
         * Кнопка удаления из базы данных
         */
        if (event.getSource() == STUDENT_DELETE_BUTTON) {
            String delete = "DELETE FROM student WHERE student_firstname= '" + STUDENT_FIRSTNAME.getText() + "';";
            executeQuery(delete);

        }
        /**
         * Кнопка записи данных в базу данных
         */
        if (event.getSource() == GROUP_SAVE_BUTTON) {
            String insert = "INSERT INTO groups VALUES(" + GROUP_ID.getText() + ", " + GROUP_STUDENT_COUNT.getText() + ")";
            executeQuery(insert);

        }
        /**
         * Кнопка вывода базы данных
         */
        if (event.getSource() == GROUP_SHOW_BUTTON) {
            GROUP_DB.setText(null);
            GROUP_DB.appendText(String.format("%50s%50s%n", "ID", "Student count"));
            GROUP_DB.appendText(String.format("%s%n", "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
            String select = "SELECT * FROM groups";
            try {
                Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                Statement st = dbConnection.createStatement();
                ResultSet rs = st.executeQuery(select);
                while (rs.next()) {
                    GROUP_DB.appendText(String.format("%50s%50s%n", rs.getString(1), rs.getString(2)));
                    GROUP_DB.setEditable(false);
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        /**
         * Кнопка удаления из базы данных
         */
        if (event.getSource() == GROUP_DELETE_BUTTON) {
            String delete = "DELETE FROM groups WHERE groups_id= " + GROUP_ID.getText() + ";";
            executeQuery(delete);

        }

        /**
         * Кнопка записи данных в базу данных
         */
        if (event.getSource() == CLASS_SAVE_BUTTON) {
            String insert = "INSERT INTO class VALUES(" + CLASS_TEACHER_ID.getText() + ", " + CLASS_GROUP_ID.getText() + ", '" + CLASS_NAME.getText() + "')";
            executeQuery(insert);

        }
        /**
         * Кнопка вывода базы данных
         */
        if (event.getSource() == CLASS_SHOW_BUTTON) {
            CLASS_DB.setText(null);
            CLASS_DB.appendText(String.format("%40s%40s%40s%n", "Teacher ID", "Group ID", "Class name"));
            CLASS_DB.appendText(String.format("%s%n", "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
            String select = "SELECT * FROM class";
            try {
                Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                Statement st = dbConnection.createStatement();
                ResultSet rs = st.executeQuery(select);
                while (rs.next()) {
                    CLASS_DB.appendText(String.format("%40s%40s%40s%n", rs.getString(1), rs.getString(2), rs.getString(3)));
                    CLASS_DB.setEditable(false);
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        /**
         * Кнопка удаления из базы данных
         */
        if (event.getSource() == CLASS_DELETE_BUTTON) {
            String delete = "DELETE FROM class WHERE class_name= '" + CLASS_NAME.getText() + "';";
            executeQuery(delete);

        }
        /**
         * Кнопка вывода базы данных
         */
        if (event.getSource() == PRIME_SHOW_BUTTON) {
            PRIME_DB.setText(null);
            PRIME_DB.appendText(String.format("%30s%30s%30s%30s%30s%n", "Student ID", "First name", "Last name", "Phone number", "Number students in a group"));
            PRIME_DB.appendText(String.format("%s%n", "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
        /**Формирования запроса для отображения связи таблиц*/
String query = "SELECT student.student_id, student.student_firstname, student.student_lastname, student.student_telephone, groups.`student_count` as `group_id` FROM student INNER JOIN groups ON (student.`group_id`= groups.`group_id`)";            try {
                Connection dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                Statement st = dbConnection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {

                    PRIME_DB.appendText(String.format("%30s%30s%30s%30s%30s%n",rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                    PRIME_DB.setEditable(false);
                }

            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
    }
}

//stylesheets="@light_theme.css"

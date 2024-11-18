import java.sql.*;

public class JDBCdemo {
    public static void main(String[] args) {
        System.out.println("JDBC demo!");
        selectAllDemo();
        insertStudentDemo("Name des Studenten", "Email@prov.at");
        selectAllDemo();
        uptdateStudentDemo(4, "Neuer Name", "neueemail@provider.at");
        selectAllDemo();
        deleteStudentDemo(2);
        findAllByNameLike("Roph");

    }

    private static void findAllByNameLike(String namePattern) {
        System.out.println("Select Demo mit JDBC");
        String sqlselectAllPersons = "SELECT * FROM  student";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "roph";
        try(Connection conn = DriverManager.getConnection(connectionUrl,user,password))
        {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM student WHERE name LIKE ?");
            preparedStatement.setString(1, "%"+namePattern+"%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Student aus der DB: [ID]: " + id + ", [Name]: " + name + ", [Email]: " + email);
            }
        }catch (SQLException e){
            System.out.println("Fehler beim Erreichen der DB: " + e.getMessage());
        }
    }

    public static void deleteStudentDemo(int StudenId) {
        System.out.println("DELETE Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "";
        try(Connection conn = DriverManager.getConnection(connectionUrl,user,password))
        {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM `Student` WHERE `id` = ?");
            try {
                preparedStatement.setInt(1, StudenId);
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Anzahl der gelöschten Datensätze: " + rowsAffected);


            }catch (SQLException e) {
                System.out.println("Fehler in der SQL UPDATE Statement: " + e.getMessage());
            }
        }catch (SQLException e){
            System.out.println("Fehler beim Erreichen der DB: " + e.getMessage());
        }
    }
    public static void uptdateStudentDemo(int id, String neuerName, String neueEmail)
    {
        System.out.println("Update Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "";
        try(Connection conn = DriverManager.getConnection(connectionUrl,user,password))
        {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `Student` SET `name` = ?, `email`= ? WHERE `id` = ?");
            try {
                preparedStatement.setString(1,neuerName);
                preparedStatement.setString(2,neueEmail);
                preparedStatement.setInt(3,id);
                int affectedRows = preparedStatement.executeUpdate();
                System.out.println("Anzahl der aktualliersten Datensätze: " + affectedRows);

            }catch (SQLException e) {
                System.out.println("Fehler in der SQL UPDATE Statement: " + e.getMessage());
            }
        }catch (SQLException e){
            System.out.println("Fehler beim Erreichen der DB: " + e.getMessage());
        }
    }

    public static void insertStudentDemo(String Name, String Email)
    {
        System.out.println("Insert Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "";
        try(Connection conn = DriverManager.getConnection(connectionUrl,user,password))
        {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, ?, ?)");
            try {
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, Email);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("Anzahl der Datensätze eingefügt: " + rowAffected);
            }catch (SQLException e) {
                System.out.println("Fehler in der SQL INSERT Statement: " + e.getMessage());
            }
        }catch (SQLException e){
            System.out.println("Fehler beim Erreichen der DB: " + e.getMessage());
        }
    }

    public static void selectAllDemo(){
        System.out.println("Select Demo mit JDBC");
        String sqlselectAllPersons = "SELECT * FROM  student";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "";
        try(Connection conn = DriverManager.getConnection(connectionUrl,user,password))
        {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(sqlselectAllPersons);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Student aus der DB: [ID]: " + id + ", [Name]: " + name + ", [Email]: " + email);
            }
        }catch (SQLException e){
            System.out.println("Fehler beim Erreichen der DB: " + e.getMessage());
        }
    }
}

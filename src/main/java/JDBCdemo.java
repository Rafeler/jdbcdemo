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

        selectAllCourses();
        insertCourseDemo("Java Programming", 5);
        selectAllCourses();
        updateCourseDemo(1, "Advanced Java Programming", 6);
        selectAllCourses();
        deleteCourseDemo(1);
    }

    // --- Methoden für die Tabelle "Course" ---
    public static void selectAllCourses() {
        System.out.println("Select Demo for Course Table");
        String sqlSelectAllCourses = "SELECT * FROM Course";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectAllCourses);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int credits = rs.getInt("credits");
                System.out.println("Course aus der DB: [ID]: " + id + ", [Name]: " + name + ", [Credits]: " + credits);
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Erreichen der DB: " + e.getMessage());
        }
    }

    public static void insertCourseDemo(String name, int credits) {
        System.out.println("Insert Demo for Course Table");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Course (name, credits) VALUES (?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, credits);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Anzahl der eingefügten Datensätze: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Fehler in der SQL INSERT Statement: " + e.getMessage());
        }
    }

    public static void updateCourseDemo(int id, String newName, int newCredits) {
        System.out.println("Update Demo for Course Table");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Course SET name = ?, credits = ? WHERE id = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, newCredits);
            preparedStatement.setInt(3, id);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Anzahl der aktualisierten Datensätze: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Fehler in der SQL UPDATE Statement: " + e.getMessage());
        } //
    }

    public static void deleteCourseDemo(int courseId) {
        System.out.println("Delete Demo for Course Table");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM Course WHERE id = ?");
            preparedStatement.setInt(1, courseId);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Anzahl der gelöschten Datensätze: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Fehler in der SQL DELETE Statement: " + e.getMessage());
        }
    }

    private static void findAllByNameLike(String namePattern) {
        System.out.println("Select Demo mit JDBC");
        String sqlselectAllPersons = "SELECT * FROM  student";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String password = "";
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

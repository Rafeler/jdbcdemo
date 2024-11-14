import java.sql.*;

public class JDBCdemo {
    public static void main(String[] args) {
        System.out.println("JDBC demo!");
        //INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'Raphael Hackl ', 'raphihack0816@gmail.com'), (NULL, 'Musa', 'musa@gmail.com');
        selectAllDemo();
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

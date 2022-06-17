import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyJDBC {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdb", "root", "Fintrak@123");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from products");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("price"));

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

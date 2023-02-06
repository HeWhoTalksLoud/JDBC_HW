import java.sql.*;

public class Application {
    public static void main(String[] args) {
        final String user = "postgres";
        final String pass = "minuscan";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, pass)) {

            // Задание 1.
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM employees WHERE id = (?)"
            );

            statement.setInt(1, 2);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = "Name " + resultSet.getString("first_name");
                String lastName = "Last name " + resultSet.getString("last_name");

                System.out.println(name);
                System.out.println(lastName);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
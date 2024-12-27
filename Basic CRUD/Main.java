import java.sql.*;

class DatabaseConnection {
    private String url;
    private String user;
    private String password;

    public DatabaseConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}

class DatabaseOperations extends DatabaseConnection {
    public DatabaseOperations(String url, String user, String password) {
        super(url, user, password);
    }

    public void selectAll() {
        String query = "SELECT * FROM user";
        try (Connection conn = super.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(String id, String name, String password) {
        if (password.length() >= 8) {
            String query = "INSERT INTO user (id, name, password) VALUES (?, ?, ?)";
            try (Connection conn = super.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, password);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: Password length must be at least 8 characters.");
        }
    }

    public void delete(String name) {
        String query = "DELETE FROM user WHERE name = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            int rowDelete = pstmt.executeUpdate();
            if (rowDelete > 0) {
                System.out.println("A row delete successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(PreparedStatement query) {
        try (Connection conn = super.getConnection()){
            int rowUpdate = query.executeUpdate();
            if (rowUpdate > 0) {
                System.out.println("A row update successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateName(String name, String id) {
        String query =  "UPDATE user SET name = ? WHERE id = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setString(2, id);

            update(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(String password, String id) {
        String query =  "UPDATE user SET password = ? WHERE id = ?";
        if (password.length() >= 8) {
            try (Connection conn = super.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, password);
                pstmt.setString(2, id);

                update(pstmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: Password length must be at least 8 characters.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        DatabaseOperations database = new DatabaseOperations(
                "jdbc:mysql://localhost:3306/java", "root", ""
        );

        database.add("2", "Anjana", "12345678");
        database.selectAll();
        database.delete("Anjana");
        database.selectAll();
        database.updateName("chamilka mihiraj", "1");
        database.selectAll();
    }
}

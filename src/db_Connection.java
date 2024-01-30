import java.sql.*;

public class db_Connection {
    public Connection con=null;
    public static Statement st = null;
    public static ResultSet rs = null;
    public Connection getConnection() throws Exception{
        try {
            String url = "jdbc:mysql://localhost:3306/estate";
            String uname = "root";
            String pass = "@iiitg123";
            con = DriverManager.getConnection(url, uname, pass);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }
}

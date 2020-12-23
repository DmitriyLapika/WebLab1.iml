package servlets.jdbc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class SubjectsServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "1234");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT stan ,name , diagnos  from subjects");
            while (resultSet.next()) {
                pw.println(resultSet.getString("name"));
                pw.println(resultSet.getString("stan"));
                pw.println(resultSet.getString("diagnos"));
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables){

                throwables.printStackTrace();
    }

}
}
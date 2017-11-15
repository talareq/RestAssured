package SQLDev;

import Utils.DBconnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import static Utils.DBconnect.connection;

/**
 * Created by Grzegorz Gałuszka on 08.08.2017.
 */
public class DBOperations {

    private void init() throws SQLException {
        DBconnect.initConnection();
    }

    private void close() throws SQLException {
        DBconnect.closeConnection();
    }

    public static void main(String Args[]) throws SQLException {
        new DBOperations().changeCorrelID();
    }

    public String changeCorrelID() throws SQLException {
        init();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM (SELECT * FROM CORRELATIONMAP order by createdts desc) where rownum = 1");
        resultSet.next();
        String pk = resultSet.getString("pk");
        Random r = new Random();
        String randomCorrID = String.valueOf(r.nextInt(899999) + 100000);
        String sql = "Update CORRELATIONMAP set P_CORRELATIONID=? where PK= ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, randomCorrID); // set parameter value for first_name
        ps.setString(2, pk);
        ps.executeUpdate();
        ps.close();
        close();

        return randomCorrID;
    }


}



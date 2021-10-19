package framework.dbmethods;

import framework.utils.DbConnection;

import java.sql.*;

public class TestTableMethods {

    private final Statement statement = DbConnection.connectMySqlLocalServer().createStatement();

    public TestTableMethods() throws SQLException {
    }

    public void add(
                    String name,
                    String method_name,
                    int project_id,
                    int session_id,
                    String time,
                    String env,
                    String browser) throws SQLException {

        statement.executeUpdate(
                "INSERT test(name,method_name,project_id,session_id,start_time,env,browser)" +
                        " VALUES('"+ name +"'," + "'"+method_name+"'," +
                        "'"+project_id+"','"+session_id+"','" + time+ "','"+env+"','"+browser+"');");
    }

    public void delete(int id) throws SQLException {

        statement.executeUpdate("DELETE FROM test WHERE id>"+id+";");
    }

    public void edit(int status_id,String time,int id) throws SQLException {

        statement.executeUpdate("UPDATE test SET status_id = "+status_id+",end_time = '"+time+"' WHERE id > "+id+";");
    }

    public boolean getIsPresent(int id) throws SQLException {
        boolean isExists = false;
        try(PreparedStatement ps = DbConnection.connectMySqlLocalServer().prepareStatement("SELECT * from test WHERE id="+id+";")){
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    isExists = true;
                }
            }
        }
        if(isExists){
            System.out.println("The entry in the database is present !");
        }
        return isExists;
    }

    public void getAndCopyAmountOfData(int amount) throws SQLException {

        Statement statementForCopy = DbConnection.connectMySqlLocalServer().createStatement();
        ResultSet resultSet = statementForCopy.executeQuery("SELECT * FROM test LIMIT "+amount+";");

        while (resultSet.next()) {
             add(resultSet.getString("name"),
                    resultSet.getString("method_name"),
                    resultSet.getInt("project_id"),
                    resultSet.getInt("session_id"),
                    resultSet.getString("start_time"),
                    resultSet.getString("env"),
                    resultSet.getString("browser"));
        }
    }

    public boolean getLastResultIsPresent(int amount) throws SQLException {
        boolean isExistsResult = false;
        try(PreparedStatement ps = DbConnection.connectMySqlLocalServer().prepareStatement("SELECT * FROM union_reporting.test ORDER BY id DESC LIMIT "+amount+";")){
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    isExistsResult = true;
                }
            }
        }
        if(isExistsResult){
            System.out.println("The entry in the database is present !");
        }
        return isExistsResult;
    }

    public void editTime(String time,int id) throws SQLException {

        statement.executeUpdate("UPDATE test SET start_time = '"+time+"' ,end_time = '"+time+"' WHERE id > "+id+";");
    }
}

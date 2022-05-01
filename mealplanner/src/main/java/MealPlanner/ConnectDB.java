/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MealPlanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class ConnectDB {

	public static Connection setupConnection() {
		/*
         Specify the database you would like to connect with:
         - protocol (e.g., jdbc)
         - vendor (e.g., oracle)
         - driver (e.g., thin)
         - server (e.g., 157.89.28.31)
         - port number (e.g., default 1521)
         - database instance name (e.g., cscdb)
		 */

		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String jdbcUrl = "jdbc:oracle:thin:@157.89.28.31:1521:cscdb";  // URL for the database

		/*
         Specify the user account you will use to connect with the database:
         - user name (e.g., myName)
         - password (e.g., myPassword)
		 */
		String username = "C##MichaelH545";
		String password = "Pa$$3343";

		try {
			// Load jdbc driver.            
			Class.forName(jdbcDriver);

			// Connect to the Oracle database
			Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
			return conn;
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Throwable whatever) {
			}
		}
	}

	public static void close(OraclePreparedStatement st) {
		if (st != null) {
			try {
				st.close();
			} catch (Throwable whatever) {
			}
		}
	}

	public static void close(OracleResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Throwable whatever) {
			}
		}
	}
	
	public static void runPreparedStatement(
		String sqlQuery,
		Consumer<OraclePreparedStatement> prepareStatement,
		Consumer<OracleResultSet> result
	){
		Connection connection = null;
		OraclePreparedStatement preparedStatement = null;
		OracleResultSet resultSet = null;
		try {
			connection = ConnectDB.setupConnection();
			preparedStatement = (OraclePreparedStatement)connection.prepareStatement(sqlQuery);
			if (prepareStatement != null){
				prepareStatement.accept(preparedStatement);
			}
			resultSet = (OracleResultSet)preparedStatement.executeQuery();
			
			result.accept(resultSet);
		} catch (SQLException e) {
			result.accept(null);
		} finally {
			ConnectDB.close(connection);
			ConnectDB.close(preparedStatement);
			ConnectDB.close(resultSet);

		}
	}
	
	public static void runStatement(String sqlQueryString, Consumer<OracleResultSet> result){
		runPreparedStatement(sqlQueryString, null, result);
	}

//	public static void runStatement(String sqlQueryString, Consumer<OracleResultSet> result) {
//		Connection connection = null;
//		OraclePreparedStatement preparedStatement = null;
//		OracleResultSet resultSet = null;
//
//		try {
//			connection = ConnectDB.setupConnection();
//			preparedStatement = (OraclePreparedStatement) connection.prepareStatement(sqlQueryString);
//			resultSet = (OracleResultSet) preparedStatement.executeQuery();
//			result.accept(resultSet);
//		} catch (Exception e) {
//			result.accept(null);
//		} finally {
//			ConnectDB.close(connection);
//			ConnectDB.close(preparedStatement);
//			ConnectDB.close(resultSet);
//		}
//	}
}

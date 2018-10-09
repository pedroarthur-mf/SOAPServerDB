package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

@WebService(endpointInterface = "service.Access")
public class QueryExecutor implements Access {
	private Connection connection;

	@Override
	public ResultQuery querySelect(String query) throws SQLException {
		ResultQuery queryResult = null;
		try (Statement stmt = this.connection.createStatement()) {

			ResultSet result = stmt.executeQuery(query);
			queryResult = new ResultQuery(result);
			return queryResult;
		} catch (SQLException e) {
//			return Response.status(400).build();
			throw new SQLException("Erro ao executar query de busca.");

		}
	}

	@Override
	public Response queryExecute(String query) throws SQLException {
		try (Statement stmt = this.connection.createStatement()) {

			stmt.executeUpdate(query);

			return Response.ok("Operação concluida.").build();
		} catch (SQLException e) {
//			return Response.status(400).build();
			throw new SQLException("Erro ao executar query.");
		}

	}

	@Override
	public void connectDB(String url, String user, String password) throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, user, password);

//			return Response.ok("Banco conectado.").build();
		} catch (SQLException e) {
//			return Response.status(400).build();
			throw new SQLException("Erro ao tentar conectar com o Banco de dados.");
		} catch (ClassNotFoundException e) {
//			return Response.serverError().build();
			throw new ClassNotFoundException("Erro ao Conectar com drive JDBC.");
		}
	}
}

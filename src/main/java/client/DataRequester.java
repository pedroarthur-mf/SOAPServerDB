package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Scanner;

import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import service.Access;
import service.ResultQuery;

public class DataRequester {
	
	public static void main(String[] args) throws SQLException, InterruptedException {
		String url, user, password, query;
		
		URL endpoint = null;
		try {
			endpoint = new URL("http://localhost:8080/SoapBDRemote/QueryExecutor?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		QName qualifiedName = new QName("http://service/", "QueryExecutorService");
		Service service = Service.create(endpoint, qualifiedName);
		Access stub = service.getPort(Access.class);
		
		
		
		try(Scanner sc = new Scanner(System.in)) {

			System.out.println("Insira a url do banco de dados: ");
			url = sc.nextLine();
			System.out.println("\nInsira o nome do usuario: ");
			user = sc.nextLine();
			System.out.println("\nInsira senha do usuario: ");
			password = sc.nextLine();
			
			DataBaseInfo db = new DataBaseInfo(url, user, password);
			stub.connectDB(db);
			
			System.out.println("\nInsira a query a ser executada: ");
			query = sc.nextLine();
			
			ResultQuery result = null;
			if (query.contains("SELECT")){
					result = stub.querySelect(query);
			}
			else {
				Response resultresponse = stub.queryExecute(query);
				System.out.println(resultresponse);
			}			
			System.out.println(result);
			
//			stub.connectDB("jdbc:mysql://localhost:3306/testdb", "admin", "senha");
//			System.out.println(stub.querySelect("select * from testdb.user;;").getResult());
//			System.out.println(stub.querySelect("select * from Offer_Contact").returnList());
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Client Exception: " + e.toString());
			e.printStackTrace();
		}
//		finally {
//			stub.closeDB();
//		}
		
//		System.out.println("Executando normalmente.");
	}
}

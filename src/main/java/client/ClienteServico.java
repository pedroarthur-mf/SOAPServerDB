package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import service.Access;

public class ClienteServico {
	public static void main(String[] args) throws SQLException, InterruptedException {		
		URL endpoint = null;
		try {
			endpoint = new URL("http://localhost:8080/SoapBDRemote/QueryExecutor?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		QName qualifiedName = new QName("http://service/", "QueryExecutorService");
		Service service = Service.create(endpoint, qualifiedName);
		Access stub = service.getPort(Access.class);
		
		
		
		try {
			stub.connectDB("jdbc:mysql://localhost:3306/sakila", "admin", "senha");
			System.out.println(stub.querySelect("SELECT * FROM sakila.actor;").getResult());
//			System.out.println(stub.querySelect("select * from Offer_Contact").returnList());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stub.closeDB();
		}
		
		System.out.println("Executando normalmente.");
	}
}

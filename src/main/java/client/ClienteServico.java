package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import service.Access;

public class ClienteServico {
	public static void main(String[] args) {		
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
			stub.connectDB("jdbc:mysql://localhost:3306/sigopdb", "admin", "senha");
			System.out.println(stub.querySelect("select * from Offer_Contact").getResult());
//			System.out.println(stub.querySelect("select * from Offer_Contact").returnList());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Executando normalmente.");
//		double temperaturaC = 27.0;
//		double temperaturaF = stub.celsiusToFahrenheit(temperaturaC);
//		System.out.println(temperaturaC + "°C = " + temperaturaF + "°F");
	}
}

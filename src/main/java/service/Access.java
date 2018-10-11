package service;

import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.ws.rs.core.Response;

import client.DataBaseInfo;

@WebService
@SOAPBinding(style = Style.RPC)
public interface Access {
	@WebMethod
	public ResultQuery querySelect(String query) throws SQLException, InterruptedException;
	
	@WebMethod
	public Response queryExecute(String query) throws SQLException, InterruptedException;
	
	@WebMethod
	public void connectDB(DataBaseInfo db) throws SQLException, ClassNotFoundException;

	@WebMethod
	public void closeDB() throws SQLException;
}



package service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * 
 * @author Pedro Arthur and Gabriel Victor
 * 
 *         Serializable class that stores the result of the operation done in
 *         the database.
 *
 */

//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "Person")
@XmlRootElement
@XmlSeeAlso({ ResultQuery.class })
public class ResultQuery {

	private ArrayList<HashMap<String, String>> result;

	public ResultQuery() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * Constructor that converts the result set from the query in the database to a
	 * list of hashmap with the data as String.
	 * 
	 * @param resultSet
	 * @throws SQLException
	 */

	public ResultQuery(ResultSet resultSet) throws SQLException {
		this.result = new ArrayList<>();

		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		while (resultSet.next()) {
			HashMap<String, String> resultMap = new HashMap<>();
			for (int i = 1; i <= columnsNumber; i++) {
				String columnValue = resultSet.getString(i);
				resultMap.put(rsmd.getColumnName(i), columnValue);
			}
			this.result.add(resultMap);
		}
	}

	// XmLElementWrapper generates a wrapper element around XML representation
	@XmlElementWrapper(name = "queryResult")
	// XmlElement sets the name of the entities
	@XmlElement(name = "map")
	public ArrayList<HashMap<String, String>> getResult() {
		return this.result;
	}

//	public ArrayList<String> returnList() {
//		ArrayList<String > list = new ArrayList<>();
//		list.add("result1");
//		list.add("result2");
//		list.add("result3");
//		return list;
//	}

	public void setResult(ArrayList<HashMap<String, String>> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return this.result.toString();
	}
}

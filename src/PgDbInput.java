import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class PgDbInput implements MyDbInput {
	private final String SEPARATOR = ",";
	private String connectionString;
	private int fetchSize = 1000;
	private String query;
    private String user;
    private String password;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private ResultSetMetaData metadata;
    private int columnCount;
    
	
    // Constructor, Init DB settings and connecting 
	public PgDbInput(String connectionString, int fetchSize, String query, String user, String password) throws SQLException {
		super();
		this.connectionString = connectionString;
		this.fetchSize = fetchSize;
		this.query = query;
		this.user = user;
		this.password = password;
        try{
        	//Connect to DB
        	con = DriverManager.getConnection(this.connectionString, this.user, this.password);
        	st = con.createStatement();
        	//Set fetch size for Query
    		st.setFetchSize(fetchSize);
    		rs = st.executeQuery(this.query);
    		metadata = rs.getMetaData();;
    		columnCount = metadata.getColumnCount();
    		System.out.println("DB Connected Successfuly");
        } catch(SQLException e) {
        	System.out.println("Can't connect to DB");
        	close();
        	throw e;
        }
	}
    
	public int getFetchSize() {
		return fetchSize;
	}

	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	public Iterator<String> iterator() {
	    return this;
	}
	
	public String readHeaders() {
		StringBuilder rowHeader = new StringBuilder();
        for (int i = 1; i <= columnCount; i++)
			try {
				rowHeader.append(metadata.getColumnName(i) + SEPARATOR);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		System.out.println("Finish reading headers");
        return rowHeader.substring(0,rowHeader.length()-1) + '\n';
		
	}
    
	// Read next row
	public String next(){
		try {
			    StringBuilder row = new StringBuilder();
		        for (int i = 1; i <= columnCount; i++)
		            row.append(rs.getString(i) + SEPARATOR);
		        return row.substring(0,row.length()-1) + '\n'; // Remove last separator
			
		} catch (SQLException e) {
        	System.out.println("Can't get next row");
        	e.printStackTrace();
        	return null;
		}
	}
	
	// Close connections
	public void close() {
    	try {
    		st.close();
    		con.close();
    		System.out.println("Connection closed");
    	} catch (SQLException e) {
    		System.out.println("Can't close DB");
		}
	}
	
	// Check if new row exist
	public boolean hasNext() {
		try {
			return rs.next();
		} catch (SQLException e) {
        	System.out.println("Can't get next");
        	return false;
		}
	}
}

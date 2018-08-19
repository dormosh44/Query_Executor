import java.sql.SQLException;


public class Main{
	static MyDbInput dbInput;
	static MyFileOutput fileOutput;
	static String connectionString = "jdbc:postgresql://localhost:5432/postgres";
	static int fetchSize = 1000;
	static String query = "select * from public.test";
	static String user = "postgres";
    static String password = "qwe123!@#";
    static String fileName = "myDbOutput";
    
	public static void main(String[] args) {
		try{
			dbInput = new PgDbInput(connectionString, fetchSize, query, user, password);
			fileOutput = new CsvFileOutput(fileName);
			start();
			close();
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("exit");
			return;
		}
	}
	
	private static void close() {
		dbInput.close();
		fileOutput.close();
	}

	public static void start() {
		String headers = dbInput.readHeaders();
		fileOutput.writeHeaders(headers);
		// for each loop, read and write row by row.
		for(String line : dbInput) { 
			fileOutput.write(line);
		}
	}

// bad
//		int writeTo;
//		final int CSV = 1;
//		final int TXT = 2;
		
//	public void write(String line) {
//		switch(writeTo) {
//		case CSV:
//			
//			break;
//		case TXT:
//			
//			break;
//		}
//	}
}

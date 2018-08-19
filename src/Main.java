import java.sql.SQLException;
import java.util.Scanner;


public class Main{
	static MyDbInput dbInput;
	static MyFileOutput fileOutput;
	static String connectionString = "jdbc:postgresql://localhost:5432/postgres";
	static int fetchSize = 1000;
	static String query = "";
	static String user = "postgres";
    static String password = "qwe123!@#";
    static String fileName = "myDbOutput";
    
	public static void main(String[] args) {
		try{
			System.out.println("Please enter your query: ");
			Scanner scanner = new Scanner(System.in);
			query = scanner.nextLine();
			
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
	
	// Read and write data
	public static void start() {
		String headers = dbInput.readHeaders();
		fileOutput.writeHeaders(headers);
		// for each loop, read and write row by row.
		System.out.println("Start reading data");
		for(String line : dbInput) { 
			fileOutput.write(line);
		}
		System.out.println("Finish writing Data");
	}

	// Closing connections
	private static void close() {
	dbInput.close();
	fileOutput.close();
	}
	
}

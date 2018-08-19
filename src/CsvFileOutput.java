import java.io.FileWriter;
import java.io.IOException;

public class CsvFileOutput implements MyFileOutput {
	
	 String csvFile = "TestOutput.csv";
	 FileWriter writer;
	public CsvFileOutput(String fileName) {
		// Open file 
		try {
			writer = new FileWriter(csvFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Write file headers
	public void writeHeaders(String headers) {
		try {
			writer.write(headers);
			System.out.println("Finish writing headers");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Write line to file
	public void write(String line){
		try {
			writer.write(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// close file 
	public void close() {
		try {
			writer.close();
			System.out.println("Output file closed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}	

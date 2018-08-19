import java.io.FileWriter;
import java.io.IOException;

public class CsvFileOutput implements MyFileOutput {
	
	 String csvFile = "TestOutput.csv";
	 FileWriter writer;
	public CsvFileOutput(String fileName) {
		// open file here
		try {
			writer = new FileWriter(csvFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeHeaders(String headers) {
		try {
			writer.write(headers);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String line){
		System.out.println(line);
		try {
			writer.write(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void close() {
		// close file here
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}	

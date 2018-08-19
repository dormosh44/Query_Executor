
public interface MyFileOutput {
	
	public void writeHeaders(String headers);
	public void write(String line);
	public void close();
}

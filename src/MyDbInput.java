import java.util.Iterator;

public interface MyDbInput extends Iterable<String>, Iterator<String>  {
	
	public String readHeaders();
	public void close();

	
}

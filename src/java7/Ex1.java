package java7;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Reference:
 * https://www.oreilly.com/learning/java7-features
 * @author joellobo@gmail.com
 */
public class Ex1 {

	public static void main(String[] args) {
		// LANGUAGE ENHANCEMENTS
		
		// Diamond Operator
		Map<String, List<String>> trades = new TreeMap<>();
		System.out.println(trades);

		// Automatic resource management
		try (FileOutputStream fos = new FileOutputStream("movies.txt");
				DataOutputStream dos = new DataOutputStream(fos)) {
				dos.writeUTF("Java 7 Block Buster");
		} catch (IOException e) {
		}
		
		// Numeric literals with underscores
		int thousand =  1_000;
		int million  =  1_000_000;
		System.out.println(thousand + " ; " + million);

		
	}

}

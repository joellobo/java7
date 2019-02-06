package java7;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Reference: https://www.oreilly.com/learning/java7-features
 * 
 * @author joellobo@gmail.com
 */
public class Main {

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
		int thousand = 1_000;
		int million = 1_000_000;
		System.out.println(thousand + " ; " + million);

		// Improved exception handling
		try {
			Integer age = Integer.getInteger("AAAA");
			System.out.println(age);
		} catch (IllegalArgumentException | NullPointerException e) {
		}

		// NEW FILE SYSTEM API (NIO 2.0)

		// Working with Path
		Path path = Paths.get("/tmp/file.txt");
		System.out.println("Number of Nodes:" + path.getNameCount());
		System.out.println("File Name:" + path.getFileName());
		System.out.println("File Root:" + path.getRoot());
		System.out.println("File Parent:" + path.getParent());

		try {
			Files.delete(path);
		} catch (IOException e) {
		}

		// File change notifications
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			Path pathWatch = Paths.get("/tmp/");
			pathWatch.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,
					StandardWatchEventKinds.ENTRY_DELETE);
			
			while (true) {
				WatchKey key = watchService.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					Kind<?> kind = event.kind();
					System.out.println("Event on " + event.context().toString() + " is " + kind);
				}
			}
		} catch (IOException | InterruptedException e) {
		}

		// FORK AND JOIN

		// SUPPORTING DYNAMISM
	}

}

import edu.duke.FileResource;

public class HelloWorld {
	public static void main(String[] args) {
		runHello();
	}
	public static void runHello() {
		FileResource res = new FileResource("src/com/zemoso/hello/hello_unicide.txt");
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}
}

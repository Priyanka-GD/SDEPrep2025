package amazoninternal.lld.inmemoryfilesystem;
import java.util.List;

public class MainDemo {
    public static void main(String[] args) {
        FileOperation fs = new FileOperation();
        System.out.println("--- Starting File System Demo ---");
        // 1. Test mkdir (Recursive creation)
        fs.mkdir("/code/java/projects");
        System.out.println("Created directory: /code/java/projects");
        // 2. Test ls on a directory
        List<String> rootContents = fs.ls("/");
        System.out.println("Contents of /: " + rootContents); // Expected: ["code"]
        // 3. Test addContentToFile (Creation)
        fs.addContentToFile("/code/README.md", "Project Root");
        // 4. Test addContentToFile (Appending)
        fs.addContentToFile("/code/README.md", " - Updated 2026");
        System.out.println("File /code/README.md created and updated.");
        // 5. Test readContentFromFile
        String content = fs.readContentFromFile("/code/README.md");
        System.out.println("Content of /code/README.md: " + content);
        // Expected: "Project Root - Updated 2026"
        // 6. Test ls Sorting
        fs.mkdir("/code/alpha");
        fs.mkdir("/code/zeta");
        List<String> codeContents = fs.ls("/code");
        System.out.println("Contents of /code (Sorted): " + codeContents);
        // Expected: ["README.md", "alpha", "java", "zeta"]
        // 7. Test ls on a specific file
        List<String> fileLs = fs.ls("/code/README.md");
        System.out.println("ls on a file path: " + fileLs);
        // Expected: ["README.md"]
        System.out.println("--- Demo Completed Successfully ---");
    }
}
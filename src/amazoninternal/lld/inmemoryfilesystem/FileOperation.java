package amazoninternal.lld.inmemoryfilesystem;

import java.util.*;

public class FileOperation {
    private final FileNode root;

    public FileOperation() {
        this.root = new FileNode();
    }

    public void mkdir(String path) {
        // reuse the traversal logic with 'autoCreate' set to true
        traverse(path, true);
    }

    public void addContentToFile(String filePath, String content) {
        FileNode node = traverse(filePath, true);
        if (node != null) {
            node.setIsFile(true);
            node.appendContent(content);
        }
    }

    public String readContentFromFile(String filePath) {
        FileNode node = traverse(filePath, false);
        if (node == null || !node.isFile()) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }
        return node.getContent();
    }

    public List<String> ls(String path) {
        FileNode node = traverse(path, false);
        if (node == null)
            return new ArrayList<>();

        if (node.isFile()) {
            // Requirement: Return only the file name in a list
            String[] parts = path.split("/");
            return Collections.singletonList(parts[parts.length - 1]);
        }

        List<String> result = new ArrayList<>(node.getChildrenNames());
        Collections.sort(result);
        return result;
    }

    /**
     * Centralized traversal logic.
     *
     * @param autoCreate if true, behaves like 'mkdir -p'
     */
    private FileNode traverse(String path, boolean autoCreate) {
        if (path == null || path.isEmpty() || path.equals("/")) {
            return root;
        }

        String[] parts = path.split("/");
        FileNode curr = root;

        for (String part : parts) {
            if (part.isEmpty())
                continue; // Handles leading or double slashes

            if (!curr.hasChild(part)) {
                if (autoCreate) {
                    curr.addChild(part, new FileNode());
                } else {
                    return null;
                }
            }
            curr = curr.getChild(part);
        }
        return curr;
    }
}
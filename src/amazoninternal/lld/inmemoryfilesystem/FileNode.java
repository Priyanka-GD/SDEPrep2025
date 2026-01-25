package amazoninternal.lld.inmemoryfilesystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class FileNode {
    private final Map<String, FileNode> children = new HashMap<>();
    private final StringBuilder content = new StringBuilder();
    private boolean isFile = false;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public boolean isFile() {
        return isFile;
    }

    public void setIsFile(boolean val) {
        this.isFile = val;
    }

    /*
    It allows unlimited concurrent readers.
    If 100 threads want to call ls("/"), they can all do it simultaneously.
    The only time threads are blocked is when a writer (like mkdir) needs to modify the structure.
    */
    public void appendContent(String text) {
        lock.writeLock().lock();
        try {
            content.append(text);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String getContent() {
        lock.readLock().lock(); // Shared access
        try {
            return content.toString();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void addChild(String name, FileNode node) {
        children.put(name, node);
    }

    public FileNode getChild(String name) {
        return children.get(name);
    }

    public boolean hasChild(String name) {
        return children.containsKey(name);
    }

    public Set<String> getChildrenNames() {
        return children.keySet();
    }
}
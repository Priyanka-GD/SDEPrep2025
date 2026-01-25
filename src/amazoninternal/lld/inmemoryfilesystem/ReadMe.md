The Problem: Design an In-Memory File System
The Task
Design a data structure that simulates an in-memory file system. You need to support the following operations:

-- ls(path):

If path is a file path, return a list containing just this file's name.

If path is a directory path, return the list of file and directory names in this directory, sorted alphabetically.

-- mkdir(path):

Create a new directory according to the path. If the intermediate directories in the path do not exist, you should create them as well.

-- addContentToFile(filePath, content):

If filePath does not exist, create that file containing given content.

If filePath already exists, append the given content to original content.

-- readContentFromFile(filePath):

Return the content in the file.
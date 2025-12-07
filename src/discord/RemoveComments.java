package discord;

import java.util.ArrayList;
import java.util.List;

public class RemoveComments {
    public List<String> removeComments(String[] source) {
        List<String> answer = new ArrayList<>();
        boolean isBlockComment = false;
        StringBuilder newLine = new StringBuilder();

        for (String line : source) {
            char[] lineArr = line.toCharArray();
            int idx = 0;
            if (!isBlockComment)
                newLine = new StringBuilder();
            while (idx < lineArr.length) {
                if (!isBlockComment && idx + 1 < lineArr.length && lineArr[idx] == '/' && lineArr[idx + 1] == '*') {
                    isBlockComment = true;
                    idx++;
                } else if (isBlockComment && idx + 1 < lineArr.length && lineArr[idx] == '*' && lineArr[idx + 1] == '/') {
                    isBlockComment = false;
                    idx++;
                } else if (!isBlockComment && idx + 1 < lineArr.length && lineArr[idx] == '/' && lineArr[idx + 1] == '/') {
                    break;
                } else if (!isBlockComment)
                    newLine.append(lineArr[idx]);
                idx++;
            }
            if (!isBlockComment && newLine.length() > 0)
                answer.add(new String(newLine));
        }
        return answer;
    }
}

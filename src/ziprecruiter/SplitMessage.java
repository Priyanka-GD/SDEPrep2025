package ziprecruiter;

public class SplitMessage {
    public String[] splitMessage(String message, int limit) {
        int len = message.length();
        int part = 0;
        int partsAdded = 0;

        for (part = 1; part <= len; part++) {
            int numOfChars = 0;
            numOfChars = part * 3; // for </>
            // Next how many extra characters needs to be added to fill a and b
            int extraChars = Integer.toString(part).length() * part;
            if (part < 10)
                partsAdded++;
            else
                partsAdded += Integer.toString(part).length();
            float totalChars = len + partsAdded + extraChars + numOfChars;
            if (totalChars / (float) part <= ((float) limit))
                break;
        }

        if (part > len)
            return new String[]{};
        String result[] = new String[part];
        int idx = 0;

        for (int p = 1; p <= part; p++) {
            StringBuilder suffix = new StringBuilder();
            suffix.append("<").append(Integer.toString(p)).append("/").append(Integer.toString(part)).append(">");
            int suffixLen = suffix.length();
            StringBuilder prefix = new StringBuilder();
            if (idx + limit - suffixLen <= len)
                prefix.append(message.substring(idx, idx + limit - suffixLen));
            else
                prefix.append(message.substring(idx, len));
            idx += prefix.length();
            prefix.append(suffix);
            result[p - 1] = prefix.toString();
        }
        return result;

    }
}

package Top.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SimplifyPath {
    /**
     * No.70 简化path
     */
    public String simplifyPath(String path) {
        Stack<String> words = new Stack<>();
        String[] p = path.split("/");
        for (String s : p) {
            if (!words.isEmpty() && "..".equals(s)) {
                words.pop();
            }
            if (!".".equals(s) && !"..".equals(s) && !"".equals(s)) {
                words.push(s);
            }
        }
        List<String> list = new ArrayList<>(words);
        return "/" + String.join("/", list);
    }

    public String simplifyPathOld(String path) {
        StringBuilder sb = new StringBuilder();
        Stack<String> words = new Stack<>();
        int index = 1;
        while (index < path.length()) {
            if (path.charAt(index) == '/') {
                index++;
                if (sb.length() > 0) {
                    words.push(sb.toString());
                    sb = new StringBuilder();
                }
                continue;
            }
            if (path.charAt(index) == '.' && path.charAt(index - 1) == '/') {
                index++;
                if (index < path.length() && path.charAt(index) == '.') {
                    index++;
                    if (index == path.length() || path.charAt(index) == '/') {
                        // /..为前一级
                        if (!words.isEmpty()) {
                            words.pop();
                        }
                    } else {
                        // ..视作word的一部分
                        sb.append("..");
                    }
                } else if (index < path.length() && path.charAt(index) != '/'){
                    // .视作word的一部分
                    sb.append('.');
                }
            } else {
                sb.append(path.charAt(index));
                index++;
            }
        }
        if (sb.length() > 0) {
            words.push(sb.toString());
            sb = new StringBuilder();
        }
        if (words.isEmpty()) {
            return "/";
        } else {
            for (String word : words) {
                sb.append('/');
                sb.append(word);
            }
            return sb.toString();
        }
    }
}

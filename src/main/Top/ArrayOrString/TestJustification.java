package Top.ArrayOrString;

import java.util.ArrayList;
import java.util.List;

/**
 * No.68 平衡words，在每行最多maxWidth的情况下，放下尽可能多的word
 *
 * 主要是扣细节
 */
public class TestJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        // 初始化-1，n个词最少会存在n-1个空格
        int length = -1;
        for (String word : words) {
            if (length + word.length() + 1 > maxWidth) {
                int spaceTotal = maxWidth - length;
                for (int i = temp.size() - 1; i >= 1; i--) {
                    if (spaceTotal % i == 0) {
                        sb.append(temp.get(temp.size() - i - 1));
                        repeatAdd(sb, ' ', spaceTotal / i);
                        spaceTotal -= spaceTotal / i;
                    } else {
                        sb.append(temp.get(temp.size() - i - 1));
                        repeatAdd(sb, ' ', spaceTotal / i + 1);
                        spaceTotal -= spaceTotal / i + 1;
                    }
                }
                sb.append(temp.get(temp.size() - 1));
                if (sb.length() < maxWidth) {
                    repeatAdd(sb, ' ', maxWidth - sb.length());
                }
                result.add(sb.toString());
                temp.clear();
                temp.add(word);
                sb = new StringBuilder();
                length = word.length();
            } else {
                length += word.length() + 1;
                temp.add(word);
            }
        }
        // 处理文末
        for (String s : temp) {
            sb.append(s);
            if (sb.length() < maxWidth) {
                sb.append(' ');
            }
        }
        repeatAdd(sb, ' ', maxWidth - sb.length());
        result.add(sb.toString());
        return result;
    }


    private void repeatAdd(StringBuilder sb, char value, int len) {
        sb.append(String.valueOf(value).repeat(Math.max(0, len)));
    }
}

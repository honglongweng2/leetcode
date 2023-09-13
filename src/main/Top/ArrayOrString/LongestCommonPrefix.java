package Top.ArrayOrString;

/**
 * No.14 最长相同前缀
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int length = strs.length;
        if(length == 0){
            return "";
        }
        String prefix = strs[0];
        for(int i = 1; i<length; i++)
        {
            while(strs[i].indexOf(prefix) !=0)
            {
                prefix = prefix.substring(0,prefix.length()-1);
            }
        }
        return prefix;
    }
}

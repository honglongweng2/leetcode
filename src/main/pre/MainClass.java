package pre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class MainClassSolution {
    int min = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0 || amount == 0) return 0;
        Arrays.sort(coins);
        backtrack(amount, coins.length - 1, coins, 0);
        return (min == Integer.MAX_VALUE)? -1 : min;
    }
    private void backtrack(int remain, int end, int[] coins, int count) {
        if (remain == 0) min = Math.min(min, count);
        if (remain < 0 || end < 0) return;
        for (int i = remain / coins[end]; i >= 0; i--) {
            int t = remain - i * coins[end];
            if (end - 1 >= 0 && t / coins[end - 1] >= min - count - i)
                break;
            backtrack(t, end - 1, coins, count + i);
        }
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] coins = stringToIntegerArray(line);
            line = in.readLine();
            int amount = Integer.parseInt(line);

            int ret = new MainClassSolution().coinChange(coins, amount);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
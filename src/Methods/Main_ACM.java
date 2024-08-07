package Methods;

import java.util.Arrays;
import java.util.Scanner;

public class Main_ACM {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String meStr = in.nextLine();
            String enemyStr = in.nextLine();
            int[] me = Arrays.stream(meStr.split(",")).mapToInt(Integer::parseInt).toArray();
            int[] enemy = Arrays.stream(enemyStr.split(",")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(me);
            Arrays.sort(enemy);

            int i = 0, j = 0;
            int totalStrength = 0;

            while (i < me.length && j < enemy.length) {
                if (me[i] > enemy[j]) {
                    totalStrength += me[i];
                    j++;
                }
                i++;
            }

            System.out.println(totalStrength);

        }
    }
}
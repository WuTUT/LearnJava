package com.wutut.lecode;

import java.util.Scanner;

class Solution {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1.0;
        if (x == 0)
            return 0.0;
        if (n > 0) {
            double ret = 1.0;
            while (n != 0) {
                if ((n & 1) != 0) {
                    ret *= x;
                }
                x *= x;
                n >>= 1;
            }
            return ret;
        } else if (n < 0) {
            long nn = -(long) n;
            // System.out.println(n);
            double ret = 1.0;
            x = 1.0 / x;
            while (nn != 0) {
                if ((nn & 1) != 0) {
                    ret *= x;
                }
                x *= x;
                nn >>= 1;
            }
            return ret;
        }
        return 0.0;
    }

}

public class Lecode {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        int n = scanner.nextInt();
        Solution solution = new Solution();
        double ans = solution.myPow(x, n);
        System.out.println(ans);
    }
}

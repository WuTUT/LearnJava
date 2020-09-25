package com.wutut.lecode;

import java.util.ArrayList;
import java.util.Scanner;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    ArrayList<Integer> ans = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        dfs(root);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    int num = 0;
    int cnt = 0;
    int maxcnt = 0;

    void dfs(TreeNode root) {
        if (root.left != null) {
            dfs(root.left);
        }
        if (cnt == 0) {
            num = root.val;
            cnt++;
        } else if (num != root.val) {
            if (maxcnt < cnt) {
                ans.clear();
                ans.add(root.val);
                maxcnt = cnt;
            } else if (maxcnt == cnt) {
                ans.add(root.val);
            }
            cnt = 1;
            num = root.val;
        } else {
            cnt++;
        }
        if (root.right != null) {
            dfs(root.right);
        }
    }

}

public class Lecode {

    public static void main(String[] args) {

    }
}

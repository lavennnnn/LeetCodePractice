package Methods;

import Structures.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeMethod {

    //中序遍历
    public static List<Integer> InorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

   public static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    // 层序遍历 双数组法
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        //新建链表来存放最终数据
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;

        //cur链表用于存放当前层的节点
        List<TreeNode> cur = List.of(root);

        //判断当前层是否为空，不为空的话才进行下一步操作
        while (!cur.isEmpty()) {

            //nxt用于存放cur的下一层节点
            List<TreeNode> nxt = new ArrayList<>();

            //vals用于存放cur里的节点的值
            List<Integer> vals = new ArrayList<>();

            //遍历cur中的节点
            for (TreeNode node : cur) {
                vals.add(node.val);
                if (node.left != null) nxt.add(node.left);
                if (node.right != null) nxt.add(node.right);
            }

            //迭代到nxt链表
            cur = nxt;
            list.add(vals);
        }
        return list;
    }

    //层序遍历 队列法
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        //用栈来维护
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> vals = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                vals.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            list.add(vals);
        }
        return list;
    }

    //104. 二叉树的最大深度 递归
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

   //226. 翻转二叉树
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;
    }
}

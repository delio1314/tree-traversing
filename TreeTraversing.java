package treeToLinkedList;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversing {
	/**
	 * 以下为树的遍历方法
	 */

	public static void visit(Node<Integer> root) {
		System.out.println(root.value);
	}

	/* 递归实现前序遍历 */
	public static void preTraverse(Node<Integer> node) {
		if (node != null) {
			visit(node);
			preTraverse(node.l_child);
			preTraverse(node.r_child);
		}
	}

	/* 递归实现中序遍历 */
	public static void inTraverse(Node<Integer> node) {
		if (node != null) {
			inTraverse(node.l_child);
			visit(node);
			inTraverse(node.r_child);
		}
	}

	/* 递归实现后序遍历 */
	public static void postTraverse(Node<Integer> node) {
		if (node != null) {
			postTraverse(node.l_child);
			postTraverse(node.r_child);
			visit(node);
		}
	}

	/* 非递归实现前序遍历, 方法1（比较特殊，针对前序遍历的特殊性的做法） */
	public static void nonRecursionPreTraverse(Node<Integer> root) {
		Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
		if (root != null) {
			stack.push(root);
			while (!stack.isEmpty()) {
				Node<Integer> node = stack.pop();
				visit(node);
				if (node.r_child != null) {
					stack.push(node.r_child);
				}
				if (node.l_child != null) {
					stack.push(node.l_child);
				}
			}
		}
	}

	/* 非递归实现前序遍历 方法2(一般性方法) */
	public static void nonRecursionPreTraverse2(Node<Integer> node) {
		Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
		while (node != null | !stack.empty()) {
			while (node != null) {
				visit(node);
				stack.push(node);
				node = node.l_child;
			}
			node = stack.pop();
			node = node.r_child;
		}
	}

	/* 非递归实现中序遍历 */
	public static void nonRecursionInTraverse(Node<Integer> node) {
		Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
		while (node != null | !stack.empty()) {
			while (node != null) {
				stack.push(node);
				node = node.l_child;
			}
			node = stack.pop();
			visit(node);
			node = node.r_child;
		}
	}

	/* 非递归实现后序遍历 */
	public static void nonRecursionPostTraverse(Node<Integer> node) {
		int max = 1000;
		Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
		// 用一个数组来记录右节点是否被访问过
		int[] flag = new int[max];

		while (node != null) {
			stack.push(node);
			flag[stack.size()] = 0;
			node = node.l_child;
		}

		while (!stack.empty()) {
			node = stack.peek();
			if (node.r_child != null && flag[stack.size()] == 0) {
				node = node.r_child;
				flag[stack.size()] = 1;
				while (node != null) {
					stack.push(node);
					flag[stack.size()] = 0;
					node = node.l_child;
				}
				node = stack.peek();
			}
			node = stack.pop();
			visit(node);
		}
	}

	// 非递归层次遍历，层次遍历不存在递归方法
	public static void levelTraverse(Node<Integer> node) {
		Queue<Node<Integer>> queue = new LinkedList<Node<Integer>>();
		queue.add(node);
		while (queue.size() != 0) {
			node = queue.poll();
			visit(node);
			if (node.l_child != null) {
				queue.add(node.l_child);
			}
			if (node.r_child != null) {
				queue.add(node.r_child);
			}
		}
	}
}

class Node<T> {
	public T value;
	public Node<T> l_child;
	public Node<T> r_child;

	public Node(T value) {
		this.value = value;
	}
}

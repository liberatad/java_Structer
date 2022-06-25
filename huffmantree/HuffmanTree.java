package huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
	public static void main(String[] args) {
		int[] arr = { 13, 7, 8, 3, 29, 6, 1 };
		Node root = createHuffmanTree(arr);
		preOrder(root);
		
	}
	//写一个前序遍历方法
	public static void preOrder(Node root) {
		if(root == null) {
			System.out.println("空树，不能遍历");
			return;
		}else {
			root.preOrder();
		}
	}

	// 创建哈夫曼树的方法
	public static Node createHuffmanTree(int[] arr) {
		// 为了方便排序，将用数组元素创建的Node对象放在数组中便于管理；
		List<Node> nodes = new ArrayList<Node>();
		for (int value : arr) {
			nodes.add(new Node(value));
		}
		while (nodes.size() > 1) {
			// 用集合的工具类直接进行排序
			Collections.sort(nodes);
			// 去除权值最小的两个节点组成二叉树
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;
			// 将处理过的两个节点删除
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			// 将新的节点加入到ArrayList中
			nodes.add(parent);
			// 到此第一步已经完成，接下来用循环处理所有的过程；
		}
//		System.out.println("nodes="+nodes);
		return nodes.get(0);
	}

}

//创建节点
class Node implements Comparable<Node> {
	int value;// 节点的权值
	Node left;// 左节点
	Node right;// 右节点

	public Node(int value) {
		this.value = value;
	}
	//写一个前序遍历
	public void preOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	// 为实现每次创建树时的节点之间的排序，实现Comparable接口；
	@Override
	public int compareTo(Node o) {
		return this.value - o.value;// 表示从小到大进行排序；
	}

}

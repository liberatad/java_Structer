package tree;

public class ArrayBinaryTreeDemo {
	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
		arrayBinaryTree.preOrder();// 这里传入根节点1,2,4,5,3,6,7
		System.out.println();
		arrayBinaryTree.infixOrder();//4,2,5,1,6,3,7
		System.out.println();
		arrayBinaryTree.postOrder();//4,5,2,6,7,3,1
	}

}

class ArrayBinaryTree {
	private int[] arr;

	public ArrayBinaryTree(int[] arr) {
		this.arr = arr;
	}
	//重载三个方法
	public void preOrder() {
		this.preOrder(0);
	}
	public void infixOrder() {
		this.infixOrder(0);
	}
	public void postOrder() {
		this.postOrder(0);
	}
	// 编写用二叉树的前序遍历遍历数组
	public void preOrder(int index) {
		// 如果数组为空，或arr.length=0则不能遍历
		if (arr == null || arr.length == 0) {
			System.out.println("数组为空，不能遍历");
			return;
		}
		// 输出当前这个元素
		System.out.print(arr[index]+"\t");
		// 每个节点的左节点角标为2 * n +1;右节点的角标为2*n+2
		if ((2 * index + 1) < arr.length) {
			preOrder(2 * index + 1);
		}
		if ((2 * index + 2) < arr.length) {
			preOrder(2 * index + 2);
		}
	}

	// 中序遍历数组
	public void infixOrder(int index) {
		if (arr == null || arr.length == 0) {
			System.out.println("数组为空，不能遍历");
			return;
		}
		if ((2 * index + 1) < arr.length) {
			infixOrder(2 * index + 1);
		}
		System.out.print(arr[index]+"\t");
		if ((2 * index + 2) < arr.length) {
			infixOrder(2 * index + 2);
		}
	}

	// 后序遍历数组
	public void postOrder(int index) {
		if (arr == null || arr.length == 0) {
			System.out.println("数组为空，不能遍历");
			return;
		}
		if ((2 * index + 1) < arr.length) {
			postOrder(2 * index + 1);
		}
		if ((2 * index + 2) < arr.length) {
			postOrder(2 * index + 2);
		}
		System.out.print(arr[index]+"\t");
	}
}

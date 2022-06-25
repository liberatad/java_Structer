package binarysorttree;

public class BinarySortTreeDemo {
	public static void main(String[] args) {
		int[] arr = { 7, 3, 10, 12, 5, 1, 9, 2 };
		BinarySortTree binarySortTree = new BinarySortTree();
		for (int i = 0; i < arr.length; i++) {
			binarySortTree.add(new Node(arr[i]));
		}
		System.out.println("中序遍历");
		binarySortTree.infixOrder();// 1,2,3,5,7,9,10,12
		// 测试一下删除叶子节点
//		binarySortTree.del(2);
//		binarySortTree.del(5);
//		binarySortTree.del(9);
		// binarySortTree.del(1);
		binarySortTree.del(7);
		System.out.println("删除节点后");
		binarySortTree.infixOrder();

	}

}

//创建二叉排序树
class BinarySortTree {
	private Node root;

	/**
	 * 
	 * @param node 传入的节点（作为根节点）
	 * @return 返回最小的节点的值，并将其删除
	 */
	public int delRightTreeMin(Node node) {
		Node temp = node;// 辅助变量
		while (temp.left != null) {
			temp = temp.left;
		}
		// 循环结束时，已经达到最小value的节点
		del(temp.value);// 删除节点
		return temp.value;// 返回最小的value值
	}

	// 删除节点的方法
	public void del(int value) {
		if (root == null) {
			return;
		} else {
			Node targetNode = search(value);// 寻找节点
			if (targetNode == null) {// 查找的节点不存在
				return;
			}
			if (root.left == null && root.right == null) {// 只有一个节点
				root = null;
				return;
			}
			Node parent = searchParent(value);// 寻找父节点
			// 下面判断如果要删除的节点是叶子节点
			if (targetNode.left == null && targetNode.right == null) {
				// 判断targetNode是父节点的左子节点还是右子节点
				if (parent.left != null && parent.left.value == value) {// 左子节点
					parent.left = null;
				} else if (parent.right != null && parent.right.value == value) {
					parent.right = null;
				}
			} else if (targetNode.left != null && targetNode.right != null) {// 第三种情况
				int minVal = delRightTreeMin(targetNode.right);
				targetNode.value = minVal;// 重置targetValue的值

			} else {// 第二种情况
				if (targetNode.left != null) {// 要删除的节点有左子节点
					if (parent != null) {
						if (parent.left.value == value) {// 要删除左节点
							parent.left = targetNode.left;
						} else {
							parent.right = targetNode.left;
						}
					} else {
						root = targetNode.left;
					}
				} else {// 要删除的节点有右节点
					if (parent != null) {
						if (parent.left.value == value) {// 要删除左节点
							parent.left = targetNode.right;
						} else {
							parent.right = targetNode.right;
						}
					} else {
						root = targetNode.right;
					}
				}
			}

		}
	}

	// 查找要删除的节点
	public Node search(int value) {
		if (root == null) {
			return null;
		} else {
			return root.search(value);
		}
	}

	// 查找要删除节点的父节点
	public Node searchParent(int value) {
		if (root == null) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}

	// 添加节点
	public void add(Node node) {
		if (root == null) {
			root = node;
		} else {
			root.add(node);
		}
	}

	// 中序遍历
	public void infixOrder() {
		if (root != null) {
			root.infixOrder();
		} else {
			System.out.println("二叉树为空，不能遍历");
		}
	}
}

//创建节点
class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	/**
	 * 
	 * @param value 要查找节点的值
	 * @return 如果找到，返回要删除的节点，否则返回Null
	 */
	public Node search(int value) {
		if (this.value == value) {
			return this;
		} else if (value < this.value) {
			if (this.left == null) {
				return null;
			}
			return this.left.search(value);
		} else {
			if (this.right == null) {
				return null;
			}
			return this.right.search(value);
		}

	}

	/**
	 * 
	 * @param value 要查找的节点
	 * @return 要查找结点的父节点
	 */
	public Node searchParent(int value) {
		if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) {
			return this;
		} else {
			if (this.left != null && value < this.value) {
				return this.left.searchParent(value);
			} else if (this.right != null && value >= this.value) {
				return this.right.searchParent(value);
			} else {
				return null;
			}
		}
	}

	// 添加节点的方法
	public void add(Node node) {
		if (node == null) {
			return;
		}
		if (node.value < this.value) {
			if (this.left == null) {// 左子树为空，直接添加
				this.left = node;
			} else {// 递归遍历
				this.left.add(node);
			}
		} else {
			if (this.right == null) {
				this.right = node;
			} else {
				this.right.add(node);
			}
		}
	}

	// 中序遍历的方法
	public void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}
}

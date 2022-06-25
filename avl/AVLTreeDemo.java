package avl;

public class AVLTreeDemo {
	public static void main(String[] args) {
		int[] arr = { 10, 12, 8, 9, 7, 6 };
		AVLTree avlTree = new AVLTree();
		for (int i = 0; i < arr.length; i++) {
			avlTree.add(new Node(arr[i]));
		}
		System.out.println("中序遍历");
		avlTree.infixOrder();
		System.out.println("右旋转之后");
		System.out.println("树的高度为：" + avlTree.getRoot().height());
		System.out.println("左子树的高度为：" + avlTree.getRoot().leftHeigth());
		System.out.println("右子树的高度为：" + avlTree.getRoot().rightHeight());
		System.out.println(avlTree.getRoot());//8
	}

}

//创建AVL树
class AVLTree {
	private Node root;

	public Node getRoot() {
		return this.root;
	}

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

	// 以当前节点为根节点进行左旋转
	public void leftRotate() {
		// 以当前节点为创建新的节点
		Node newNode = new Node(this.value);
		// 新节点的左子树为当前节点的左子树
		newNode.left = this.left;
		// 新节点的右子树为当前节点的右子树的左子树
		newNode.right = this.right.left;
		// 把当前节点的值替换为右子树的值
		this.value = this.right.value;
		// 把当前节点的右子树替换为当前节点的右子树的右子树
		this.right = this.right.right;
		// 把当前节点的左子树替换为新的节点
		this.left = newNode;
	}

	// 右旋转，与左旋转对称
	public void rightRotate() {
		Node newNode = new Node(this.value);
		newNode.right = this.right;
		newNode.left = this.left.right;
		this.value = this.left.value;
		this.left = this.left.left;
		this.right = newNode;
	}

	// 返回左子树的高度
	public int leftHeigth() {
		if (left == null) {
			return 0;
		} else {
			return this.left.height();
		}
	}

	// 返回右子树的高度
	public int rightHeight() {
		if (right == null) {
			return 0;
		} else {
			return this.right.height();
		}
	}

	// 编写一个方法，返回树的高度,以当前节点为根节点
	public int height() {
		return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
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
		// 当添加完一个节点时，如果：(右子树的高度-左子树的高度) > 1;进行左旋转
		if (this.rightHeight() - this.leftHeigth() > 1) {
			//如果右子树的左子树的高度大于右子树的右子树的高度
			if(this.right != null && this.right.leftHeigth() > this.right.rightHeight()) {
				//先对右子树进行右旋转，再进行左旋转
				this.right.rightRotate();
				this.rightRotate();
			}else {//否则直接左旋转
				leftRotate();
			}
			return;//由于每添加一个节点都需要平衡，本次平衡后不需要进行下次的平衡
		}
		if (this.leftHeigth() - this.rightHeight() > 1) {
			//如果左子树的右子树的高度大于左子树的左子树的高度
			if(this.left != null && this.left.rightHeight() > this.left.leftHeigth()) {
				//先对左子树进行左旋转，再进行右旋转
				this.left.leftRotate();
				this.rightRotate();
			}else {//直接右旋转
				rightRotate();
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

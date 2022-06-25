package tree;

public class BinaryTreeDemo {
	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();// 创建二叉树
		// 创建节点
		HeroNode root = new HeroNode(1, "宋江");
		HeroNode node2 = new HeroNode(2, "吴用");
		HeroNode node3 = new HeroNode(3, "卢俊义");
		HeroNode node4 = new HeroNode(4, "林冲");
		HeroNode node5 = new HeroNode(5,"关胜");
		binaryTree.setRoot(root);
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
//		System.out.println("前序遍历");
//		binaryTree.preOrder();// 1,2,3,4
//		System.out.println("中序遍历");
//		binaryTree.infixOrder();// 2,1,3,4
//		System.out.println("后序遍历");
//		binaryTree.postOrder();// 2,4,3,1
		//前序遍历测试
//		HeroNode resNode = binaryTree.preOrderSearch(5);//共查找4次
//		if(resNode != null) {
//			System.out.printf("找到英雄，no=%d,name=%s",resNode.getNo(),resNode.getName());
//		}else {
//			System.out.printf("没有找到编号no=%d的英雄",5);
//		}
		//中序遍历查找，共查找三次
//		HeroNode resNode = binaryTree.infixOrderSearch(5);//共查找4次
//		if(resNode != null) {
//			System.out.printf("找到英雄，no=%d,name=%s",resNode.getNo(),resNode.getName());
//		}else {
//			System.out.printf("没有找到编号no=%d的英雄",5);
//		}
		//后序遍历查找，共查找2次
//		HeroNode resNode = binaryTree.postOrderSearch(5);//共查找4次
//		if(resNode != null) {
//			System.out.printf("找到英雄，no=%d,name=%s",resNode.getNo(),resNode.getName());
//		}else {
//			System.out.printf("没有找到编号no=%d的英雄",5);
//		}
		System.out.println("删除前，前序遍历");
		binaryTree.preOrder();//1,2,3,5,4
		binaryTree.delNode(5);
		System.out.println("删除后，前序遍历");
		binaryTree.preOrder();//1,2,3,4
	}

}

class BinaryTree {
	HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
	}

	// 前序遍历
	public void preOrder() {
		if (this.root != null) {
			this.root.preOrder();
		} else {
			System.out.println("二叉树为空，不能遍历");
		}
	}

	// 中序遍历
	public void infixOrder() {
		if (this.root != null) {
			this.root.infixOrder();
		} else {
			System.out.println("二叉树为空，不能遍历");
		}
	}

	// 后序遍历
	public void postOrder() {
		if (this.root != null) {
			this.root.postOrder();
		} else {
			System.out.println("二叉树为空，不能遍历");
		}
	}

	// 前序遍历查找
	public HeroNode preOrderSearch(int no) {
		if (this.root != null) {
			return this.root.preOrderSearch(no);
		} else {
			return null;
		}
	}

	// 中序遍历查找
	public HeroNode infixOrderSearch(int no) {
		if (this.root != null) {
			return this.root.infixOrderSearch(no);
		} else {
			return null;
		}

	}
	//后序遍历查找
	public HeroNode postOrderSearch(int no) {
		if(this.root != null) {
			return this.root.postOrderSearch(no);
		}else {
			return null;
		}
	}
	//删除节点操作
	public void delNode(int no) {
		if(this.root != null) {
			if(this.root.getNo() == no) {
				root = null;
			}else {
				this.root.delNode(no);
			}
		}else {
			System.out.println("空树，不能删除");
		}
	}
}

class HeroNode {
	private int no;
	private String name;
	private HeroNode left;// 左节点，默认null
	private HeroNode right;// 右节点，默认null

	public HeroNode(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HeroNode getLeft() {
		return left;
	}

	public void setLeft(HeroNode left) {
		this.left = left;
	}

	public HeroNode getRight() {
		return right;
	}

	public void setRight(HeroNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}

	// 前序遍历的方法
	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	// 中序遍历方法
	public void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	// 后序遍历方法
	public void postOrder() {
		if (this.left != null) {
			this.left.postOrder();
		}
		if (this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}

	// 前序遍历查找
	public HeroNode preOrderSearch(int no) {
		System.out.println("进入前序遍历查找");
		if (this.no == no) {
			return this;
		}
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		if (this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}

	// 中序遍历查找
	public HeroNode infixOrderSearch(int no) {
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		System.out.println("进入中序遍历查找");
		if (this.no == no) {
			return this;
		}
		if (this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
	}

	// 后序遍历查找
	public HeroNode postOrderSearch(int no) {
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.postOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		if (this.right != null) {
			resNode = this.right.postOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		System.out.println("进入后序遍历查找");
		if (this.no == no) {
			return this;
		}
		return resNode;
	}
	//删除节点操作：若是叶节点则直接删除，若是子树，直接删除字数
	public void delNode(int no) {
		if(this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		if(this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		if(this.left != null) {
			this.left.delNode(no);
		}
		if(this.right != null) {
			this.right.delNode(no);
		}
	}
}

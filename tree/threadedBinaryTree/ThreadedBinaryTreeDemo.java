package tree.threadedBinaryTree;



public class ThreadedBinaryTreeDemo {
	public static void main(String[] args) {
		HeroNode root = new HeroNode(1, "Tom");
		HeroNode node2 = new HeroNode(3, "Jack");
		HeroNode node3 = new HeroNode(6, "Smith");
		HeroNode node4 = new HeroNode(8, "Jerry");
		HeroNode node5 = new HeroNode(10, "King");
		HeroNode node6 = new HeroNode(14, "Jim");
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
		threadedBinaryTree.setRoot(root);
		threadedBinaryTree.threadedNode();
		//以十号节点为例测试正确性
		HeroNode leftNode = node5.getLeft();
		HeroNode rightNode = node5.getRight();
		System.out.println("十号节点的前驱节点为："+leftNode);
		System.out.println("十号节点的后继节点为："+rightNode);
		System.out.println("线索化后的输出结果为：");
		threadedBinaryTree.threadedList();//8,3,10,1,4,6与中序遍历结果相同；
	}
	
}
//创建线索化二叉树
class ThreadedBinaryTree {
	HeroNode root;
	HeroNode pre = null;//指向被线索化对象的前一个对象的指针
	public void setRoot(HeroNode root) {
		this.root = root;
	}
	//重载threadedNode方法，方便调用
	public void threadedNode() {
		this.threadedNode(root);
	}
	/**
	 * 由于原来的二叉树线索化后添加了许多的指针，因此需要重新遍历
	 */
	public void threadedList() {
		HeroNode node = root;
		//循环找到leftType == 1的节点，第一个找到的就是8号节点
		//说明这是线索化后的有效节点，并输出
		while(node != null) {
			while(node.getLeftType() == 0) {
				node = node.getLeft();
			}
			System.out.println(node);
			//接下来判断node的rightType是否为1，若为1，则持续输出
			while(node.getRightType() == 1) {
				node = node.getRight();
				System.out.println(node);
			}
			//退出循环后说明此时node.getRightType()!=1,再将node替换为node.getRight()
			node = node.getRight();
		}
	}
	/**
	 * 中序线索化二叉树
	 * @param heroNode当前被线索化的对象
	 */
	public void threadedNode(HeroNode heroNode) {
		//首先若当前节点为空，直接返回
		if(heroNode == null) {
			return;
		}
		//1.线索化左子树，递归
		threadedNode(heroNode.getLeft());
		//2.线索化当前节点

		//如果左指针为空，则指向pre节点
		if(heroNode.getLeft() == null) {
			heroNode.setLeft(pre);
			heroNode.setLeftType(1);//表示已经指向前驱节点
		}
		//接下来，由于一个节点不能同时完成指向前驱节点和后继节点，
		//因此指向后继节点的操作由pre节点来完成,下面的判断第一次无法完成；
		if(pre != null && pre.getRight() == null) {//显然第一次pre=null,这一步的操作当
			//node递归到上一步并且pre移动到当前heroNode时来完成，后面以此类推
			pre.setRight(heroNode);//这时的heroNode已经递归回到上一个方法
			pre.setRightType(1);
		}
		//!!!这里pre的移动一定要在当前方法内完成
		pre = heroNode;//Pre的移动由此语句完成，heroNode的移动由递归回溯自动完成，保证了pre始终在当前heroNode的前面；
		//3.线索化右节点递归
		threadedNode(heroNode.getRight());
		
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

//创建节点
class HeroNode {
	private int no;
	private String name;
	private HeroNode left;// 左节点，默认null
	private HeroNode right;// 右节点，默认null
	private int leftType;//leftType=0表示左子树，为1表示前驱节点
	private int rightType;//rightType=0表示右子树，为1表示后驱节点

	public HeroNode(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	

	public int getLeftType() {
		return leftType;
	}


	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}


	public int getRightType() {
		return rightType;
	}


	public void setRightType(int rightType) {
		this.rightType = rightType;
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


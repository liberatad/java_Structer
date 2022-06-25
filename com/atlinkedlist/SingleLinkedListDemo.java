package com.atlinkedlist;

import java.util.Stack;

/*
 * 单链表
 * 英雄排名
 *总结：
 *单链表的添加，删除操作需要找到前一个节点，
 *遍历，修改的操作只需要找到本节点；
 */
public class SingleLinkedListDemo {
	public static void main(String[] args) {
		//测试
		HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
		HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
		HeroNode hero3 = new HeroNode(3,"智多星","Q吴用");
		HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
		SingleLinkedList singlelist = new SingleLinkedList();
		singlelist.add(hero1);
		singlelist.add(hero2);
		singlelist.add(hero3);
		singlelist.add(hero4);
		//单链表反转的测试
		System.out.println("原来的链表为//");
		singlelist.list();
		//单链表的逆序打印测试
		System.out.println("反转后的链表");
		reversePrint(singlelist.getHead());
		
//		reverseList(singlelist.getHead());
//		System.out.println("反转后的链表为//");
//		singlelist.list();
//		//按照英雄编号添加
//		singlelist.addByOrder(hero2);
//		singlelist.addByOrder(hero3);
//		singlelist.addByOrder(hero1);
//		singlelist.addByOrder(hero4);
//		singlelist.list();
//		//修改操作
//		HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟//");
//		singlelist.update(newHeroNode);
//		System.out.println("修改后的链表");
//		singlelist.list();
//		singlelist.del(1);
//		singlelist.del(3);
//		System.out.println("删除后的链表");
//		singlelist.list();
//		//打印节点的个数
//		System.out.println(getlength(singlelist.getHead()));
//		//测试：
//		HeroNode res = getLatIndex(singlelist.getHead(), 2);
//		System.out.println("res="+res);
		
		
	}
	//获取节点总数；
	public static int getlength(HeroNode head) {
		int length = 0;
		HeroNode cur = head.next;
		while(cur != null) {
			length++;
			cur = cur.next;
		}
		return length;
	}
	//返回倒数第index个节点；
	public static HeroNode getLatIndex(HeroNode head,int index) {
		if(head.next == null) {//链表为空
			return null;
		}
		//得到链表的长度，即节点数
		int size = getlength(head);
		if(index <= 0 || index > size) {
			return null;
		}
		HeroNode cur = head.next;
		//定义辅助变量，for循环定位到index位置
		for(int i = 0;i<size - index;i++) {
			cur = cur.next;
		}
		return cur;
	}
	//单链表的反转
	public static void reverseList(HeroNode head) {
		if(head.next == null || head.next.next == null) {//此时没有元素或只有一个元素
			return;
		}
		//定义辅助变量，帮助遍历
		HeroNode cur = head.next;
		HeroNode next = null;//指向当前节点的下一个节点；
		HeroNode reverseHead = new HeroNode(0,"","");
		//遍历原来的链表，每遍历一个，将其取出并放在reverseHead的最前端；
		while(cur != null) {
			next = cur.next;//保存当前节点的下一个节点；后面使用；
			cur.next = reverseHead.next;//当前节点的下一个位置指向头结点的最前端；
			reverseHead.next = cur;//将整个链表连贯；
			cur = next;//辅助变量下移；
		}
		head.next = reverseHead.next;//完成反转；
	}
	//将单链表逆序打印：将节点压入栈中再打印；
	public static void reversePrint(HeroNode head) {
		Stack stack = new Stack<HeroNode>();//创建一个栈
		if(head.next == null) {
			System.out.println("没有节点");
		}
		HeroNode cur = head.next;
		while(cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		//打印节点
		while(stack.size() > 0) {
			System.out.println(stack.pop());
		}
		
	}
	
	
	

}
//定义SingleLinkedList管理我们的英雄；
class SingleLinkedList{
	//创建头节点，不能动，初始化信息为零
	HeroNode head = new HeroNode(0,"","");
	public HeroNode getHead() {
		return head;
	}
	//创建单链表
	//思路：找到单链表最后的位置，并将节点放到next中
	public void add(HeroNode heroNode) {
		//用辅助变量；
		HeroNode temp = head;//注意两个对象可以共同操作下一个数据；
		//遍历
		while(true) {
			if(temp.next == null) {
				break;
			}
			else {
				temp = temp.next;
			}
		}
		temp.next = heroNode;
	}
	//按照编号添加英雄
	public void addByOrder(HeroNode heroNode) {
		HeroNode temp = head;
		boolean flag = false;//记录能否添加；
		while(true) {
			if(temp.next == null) {//表示到达链表末尾，可以添加
				break;
			}
			if(temp.next.no > heroNode.no) {//表示可以添加
				break;
			}
			else if(temp.next.no == heroNode.no) {//英雄已存在，无法添加
				flag = true;
				break;
			}
			temp = temp.next;//指针一定要下移；
		}
		if(flag) {//无法添加
			System.out.printf("准备插入英雄编号%d已存在,无法添加",heroNode.no);
			System.out.println();
		}
		else {//表示可以添加
			heroNode.next = temp.next;//新的节点指向下一个节点；
			temp.next = heroNode;//temp指向新的节点；
		}
	}
	//修改英雄
	public void update(HeroNode newHeroNode) {
		HeroNode temp = head.next;
		boolean flag = false;//判断是否找到
		while(true) {
			if(temp == null) {
				//表示遍历完毕；
				break;
			}
			if(temp.no == newHeroNode.no) {//表示已经找到
				flag = true;
				break;
			}
			temp = temp.next;//指针下移；
		}
		if(flag) {//表示已经找到
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		}
		else {//表示未找到
			System.out.printf("未找到编号%d的英雄\n",newHeroNode.no);
		}
	}
	//删除操作
	public void del(int no) {//要删除的编号
		HeroNode temp = head;
		boolean flag = false;//表示是否找到
		while(true) {
			if(temp.next == null) {//到达链表最后
				break;
			}
			if(temp.next.no == no) {//表示找到节点的前一个位置
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {//已经找到
			temp.next = temp.next.next;
		}
		else {
			System.out.printf("未找到位置为%d的英雄",no);
		}
		
	}
	//显示列表【遍历】
	public void list() {
		//判断链表是否为空
		if(head.next == null) {
			System.out.println("链表为空，没有数据");
			return;
		}
		//由于头数据不能动，需要辅助变量；
		HeroNode temp = head.next;
		while(true) {
			if(temp == null) {
				break;
			}
			//输出节点信息；
			System.out.println(temp);
			//节点后移，否则死循环；
			temp = temp.next;
		}
	}
	//获取头结点的个数
	
	
	
	
}
class HeroNode{
	int no;
	String name;
	String nickname;
	HeroNode next;//指向下一个的节点
	public HeroNode(int no,String name,String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname  + "]";
	}
	
}

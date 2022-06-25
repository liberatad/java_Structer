package com.atDoubleLinkedList;

/*
 * 双向链表的测试；
 * 
 */
public class DoubleLinkedListDemo {
	public static void main(String[] args) {
		//双向链表的测试；
		System.out.println("双向链表的测试");
		HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
		HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
		HeroNode hero3 = new HeroNode(3,"吴用","智多星");
		HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
		HeroNode hero5 = new HeroNode(5,"公孙胜","入云龙");
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		doubleLinkedList.addOrder(hero3);
		doubleLinkedList.addOrder(hero4);
		doubleLinkedList.addOrder(hero1);
		doubleLinkedList.addOrder(hero5);
		doubleLinkedList.addOrder(hero2);
		doubleLinkedList.list();
		doubleLinkedList.del(2);
		System.out.println("删除后的链表");
		doubleLinkedList.list();
	}

}
class DoubleLinkedList{
	HeroNode head = new HeroNode(0,"","");
	//遍历
	public void list() {
		if(head.next == null) {
			System.out.println("链表为空");
		}
		HeroNode temp = head.next;
		while(true) {
			if(temp == null) {
				break;
			}
			System.out.println(temp);
			temp = temp.next;
		}
	}
	//添加到末尾；
	public void add(HeroNode heroNode) {
		HeroNode temp = head;
		while(true) {
			if(temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = heroNode;
		heroNode.pre = temp;
	}
	//修改
	public void update(HeroNode newheroNode) {
		if(head.next == null) {
			System.out.println("没有数据，不能修改");
		}
		HeroNode temp = head.next;
		boolean flag = false;
		while(true) {
			if(temp == null) {
				break;//已经到末尾；
			}
			if(temp.no == newheroNode.no) {//已经找到
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {//表示找到要修改的位置
			temp.name = newheroNode.name;
			temp.nickname = newheroNode.nickname;
		}
		else {
			System.out.printf("没有找到要修改的指定%d位置的元素\n",newheroNode.no);
		}
	}
	//删除指定位置的元素；
	public void del(int no) {
		if(head.next == null) {
			System.out.println("链表为空，不能修改");
		}
		HeroNode temp = head.next;
		boolean flag = false;
		while(true) {
			if(temp == null) {
				break;
			}
			if(temp.no == no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			temp.pre.next = temp.next;
			if(temp.next != null) {//这里防止修改的是最后一个元素，temp.next = null,空指针异常；
				temp.next.pre = temp.pre;
			}
		}
		else {
			System.out.printf("未找到指定位置%d的元素\n",no);
		}
	}
	//按照标号添加元素；
	public void addOrder(HeroNode heronode) {
		HeroNode temp = head;
		boolean flag = false;
		while(true) {
			if(temp.next == null) {
				break;
			}
			if(temp.next.no > heronode.no ) {
				break;
			}
			if(temp.next.no == heronode.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			System.out.printf("英雄编号%d已将存在，无法添加\n",heronode.no);
		}
		else {
			heronode.next = temp.next;
			heronode.pre = temp;
			if(temp.next != null) {
				temp.next.pre = heronode;
			}
			temp.next = heronode;//这里注意temp.next一定要最后修改；
		}
	}
	
	
}
class HeroNode{
	int no;
	String name;
	String nickname;
	HeroNode next;
	HeroNode pre;//只想前节点；
	public HeroNode(int no,String name,String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
	
}

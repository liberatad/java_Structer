package com.atDoubleLinkedList;
/*
 * 约瑟夫问题的环形单链表的实现；
 * 
 */
public class Josephu {
	public static void main(String[] args) {
		//测试一把
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.add(125);
		//circleSingleLinkedList.show();
		circleSingleLinkedList.countBoy(10, 7, 125);
	}

}

class CircleSingleLinkedList{
	Boy first = null;//创建第一个节点；
	public void add(int nums) {//表示添加小孩的数量
		if(nums < 1) {
			System.out.println("数据不正确");
			return;
		}
		Boy curboy = null;//辅助指针；
		for(int i = 1;i <= nums;i++) {
			Boy boy = new Boy(i);//创建要添加的小孩；
			if(i == 1) {//第一个小孩，创建自环
				first = boy;
				boy.setNext(boy);
				curboy = boy;//辅助指针指向第一个小孩；
			}
			else {//添加小孩
				//当前指针(curboy)指向下一个boy;boy指向first;curboy向后移动；
				curboy.setNext(boy);
				boy.setNext(first);
				curboy = boy;
			}
		}
	}
	//遍历
	public void show() {
		if(first == null) {
			System.out.println("没有小孩，不能遍历");
			return;
		}
		Boy curboy = first;//辅助指针遍历
		while(true) {
			System.out.printf("小孩的编号 %d \n",curboy.getNo());
			if(curboy.getNext() == first) {//遍历到尾部
				break;
			}
			curboy = curboy.getNext();//curboy后移；
		}
	}
	//小孩出圈的方法
	/**
	 * 
	 * @param startNo  从第startNo个小孩开始的位置
	 * @param countNum  数数的次数
	 * @param nums  一共有几个小孩
	 * 这里设置两个辅助指针first和helper，helper紧跟在first后面
	 */
	public void countBoy(int startNo,int countNum,int nums) {
		if(first == null || startNo < 1 || startNo > nums) {
			System.out.println("输入数据有误，请重新输入");
			return;
		}
		//创建辅助指针，帮助小孩出圈
		Boy helper = first;
		//将helper指针移动到first后面
		while(true) {
			if(helper.getNext() == first) {
				break;
			}
			helper = helper.getNext();
		}
		//小孩报数前，将first和helper移动k-1次，即移动到指定的startNo开始位置；
		for(int i = 0;i < startNo - 1;i++) {
			first = first.getNext();
			helper = helper.getNext();
		}
		//将first和helper同时移动countNum-1次到达要出圈的小孩
		//这里是一个循环操作
		while(true) {
			if(first == helper) {//这时只有一个小孩；
				break;
			}
			for(int i = 0;i < countNum-1;i++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			//这时到达要出圈的小孩
			System.out.printf("小孩%d出圈\n",first.getNo());
			//first继续指向下一个小孩；
			first = first.getNext();
			helper.setNext(first);//将helper下的next设置成first的位置；
		}
		System.out.printf("最后留在圈中的小孩编号为%d",first.getNo());
	}
}

class Boy{//节点类
	private int no;
	private Boy next;
	public Boy(int no) {
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Boy getNext() {
		return next;
	}
	public void setNext(Boy next) {
		this.next = next;
	}
	
}


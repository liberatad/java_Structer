package com.atstack;
/*
 * 考虑用链表实现栈，双向链表；
 * 只用在末尾添加数据，不用考虑大小；
 */
public class LinkedListStackDemo {
	public static void main(String[] args) {
		//测试一把；
		Dataadd data1 = new Dataadd(1,10);
		Dataadd data2 = new Dataadd(2,10);
		Dataadd data3 = new Dataadd(3,10);
		Dataadd data4 = new Dataadd(4,10);
		Dataadd data5 = new Dataadd(5,10);
		LinkedListStack stack = new LinkedListStack();
		stack.push(data1);
		stack.push(data2);
		stack.push(data3);
		stack.push(data4);
		stack.push(data5);
		stack.show();
		
	}

}


class LinkedListStack{
	
	Dataadd first = new Dataadd(0,0);//指明链表的第一个数据；
	//添加数据在末尾；
	public void push(Dataadd newdata) {
		Dataadd temp = first;//辅助指针；
		while(true) {
			if(temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = newdata;
		newdata.pre = temp;//双向的；
	}
	//读取数据(出栈)
	public Dataadd pop() {
		if(first.next == null) {
			throw new RuntimeException("没有数据，无法读取");
		}
		boolean flag = false;
		Dataadd temp = first;
		while(true) {
			if(temp.next == null) {
				flag = true;//找到最后一个位置，可以读取；
				break;
			}
			temp = temp.next;
		}
		if(temp != first) {
		Dataadd cur = temp;
		temp = null;
		return cur;
		}
		else {
			throw new RuntimeException("没有数据，无法读取");
		}
	}
	//遍历
	public void show() {
		if(first.next == null) {
			System.out.println("没有数据，无法遍历");
			return;
		}
		Dataadd temp = first;
		while(true) {
			if(temp.next == null) {
				break;
			}
		}
		while(true) {
			if(temp.pre == first) {//到达最后了；
				break;
			}
			System.out.println(temp);
			temp = temp.pre;
			temp.next = null;
		}
	}
	
	
}
class Dataadd{
	int no;
	int data;
	Dataadd next;//默认为null;
	Dataadd pre;//默认为null;
	public Dataadd(int no,int data) {
		this.no = no;
		this.data = data;
	}
	@Override
	public String toString() {
		return "Dataadd [no=" + no + ", data=" + data + "]";
	}
	
	
	
	
	
}

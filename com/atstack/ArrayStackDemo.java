package com.atstack;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

/*
 * 用数组模拟栈；
 * 
 */
public class ArrayStackDemo {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//测试一把；
		ArrayStack stack = new ArrayStack(4);//创建栈；
		String key = "";
		boolean loop = true;//控制退出条件；
		Scanner scanner = new Scanner(System.in);
		while(loop) {
			System.out.println("show: 展示栈");
			System.out.println("push: 添加数据");
			System.out.println("pop: 取数据出栈");
			System.out.println("exit: 退出程序");
			key = scanner.next();
			switch(key) {
			case "show":
				stack.show();
				break;
			case "push":
				System.out.println("请您输入一个数据");
				int value = scanner.nextInt();
				stack.push(value);
				break;
			case "pop":
				try {
					int res = stack.pop();
					System.out.printf("您获取的数据为：%d\n",res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					// TODO: handle exception
				}
				break;
			case "exit":
				scanner.close();
				loop = false;
				break;
			}
				
				
		}
		System.out.println("程序退出");
		
	}

}

class ArrayStack{
	private int maxsize;//表示栈的大小；
	private int[] stack;//用来存储数据；
	private int top = -1;//表示栈顶；
	public ArrayStack(int size) {
		this.maxsize = size;
		stack = new int[this.maxsize];
	}
	//判断栈是否满；
	public boolean isFull() {
		return top == maxsize-1;
	}
	//判断栈是否空
	public boolean isEmpty() {
		return top == -1;
	}
	//添加
	public void push(int n) {
		if(isFull()) {
			System.out.println("栈满，无法添加数据");
			return;
		}
		top++;
		stack[top] = n;
	}
	//取出数据；
	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("栈空，没有数据");
		}
		int value = stack[top];
		top--;
		return value;
	}
	//遍历；
	public void show() {
		if(isEmpty()) {
			System.out.println("没有数据，无法遍历");
		}
		for(int i = top;i >= 0;i--) {
			System.out.printf("stack[%d] = %d\n",i,stack[i]);
		}
	}
	
	
	
}

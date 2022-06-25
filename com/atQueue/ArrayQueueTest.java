package com.atQueue;

import java.util.Scanner;

import javax.management.RuntimeErrorException;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

//用数组模拟队列
//问题分析及优化：
//1.数组只能使用一次，不能复用；
//2.使用一个算法进行优化，模拟成环形队列，取模：%;
public class ArrayQueueTest {
	// 测试：
	public static void main(String[] args) {
		ArrayQueue queue = new ArrayQueue(3);// 创建队列
		Scanner scanner = new Scanner(System.in);
		char key = ' ';
		boolean loop = true;// 控制循环；
		while (loop) {
			// 设置用户界面；
			System.out.println("s(show) :显示队列");
			System.out.println("e(exit) :退出界面");
			System.out.println("a(add) :添加队列数据");
			System.out.println("g(get) :取出数据");
			System.out.println("h(head) :查看头数据");
			key = scanner.next().charAt(0);
			switch (key) {
			case 's':
				queue.showQueue();
				break;
			case 'a':
				System.out.println("请输入一个数");
				int value = scanner.nextInt();
				queue.addQueue(value);
				break;
			case 'g':// 取出数据
				try {
					int res = queue.getQueue();
					System.out.printf("取出的数据是%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'h':// 查看队列头数据
				try {
					int res = queue.headQueue();
					System.out.printf("队列头的数据是%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'e':
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}

		}
		System.out.println("程序退出");

	}

}

//创建队列的类
class ArrayQueue {
	private int maxsize;// 队列的容量
	private int front;// 只想队列头部前一个数据；
	private int rear;// 只想队列尾部（数据本身）
	private int[] arr;// 用于存储数据；
	// 给出创建队列的构造器

	public ArrayQueue(int maxsize) {
		this.maxsize = maxsize;
		arr = new int[maxsize];
		rear = -1;
		front = -1;
	}

	// 判断队列是否为满；
	public boolean isFull() {
		return rear == maxsize - 1;
	}

	// 判断队列是否为空
	public boolean isEmpty() {
		return front == rear;
	}

	// 添加队列数据
	public void addQueue(int n) {
		if (isFull()) {
			System.out.println("队列已满，无法添加数据");
			return;
		}
		rear++;
		arr[rear] = n;
	}

	// 得到队列数据
	public int getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空，不能取数据");
		}
		front++;
		return arr[front];
	}

	// 遍历队列
	public void showQueue() {
		if (isEmpty()) {
			System.out.println("队列空，没有数据");
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n", i, arr[i]);
		}
	}

	// 显示队列头数据；
	public int headQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列空，没有数据");
		}
		return arr[front + 1];
	}

}

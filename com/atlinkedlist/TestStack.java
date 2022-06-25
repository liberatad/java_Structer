package com.atlinkedlist;

import java.util.Stack;

/*
 * 栈Stack的基本使用；
 * 
 */
public class TestStack {
	public static void main(String[] args) {
		Stack<String> stack = new Stack();
		stack.add("jack");
		stack.add("jerry");
		stack.add("jim");
		while(stack.size() > 0) {
			//先进后出
			System.out.println(stack.pop());
		}
	}

}

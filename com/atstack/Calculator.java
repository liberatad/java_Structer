package com.atstack;

/*
 * 用栈实现一个对字符串的运算
 *这里是一个中缀表达式；
 * 
 */
public class Calculator {
	public static void main(String[] args) {
		String expression = "70+20*6-5";
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);
		int index = 0;// 用于扫描；
		int num1 = 0;
		int num2 = 0;
		int res = 0;
		int oper = 0;
		char ch = ' ';// 每次扫描的字符存放在ch中；
		String keepNum = "";
		// 开始while循环扫描
		while (true) {
			// 依次得到String中的每一个字符；
			ch = expression.substring(index, index + 1).charAt(0);
			// 如果ch是个oper
			if (operStack.isOper(ch)) {
				if (!operStack.isEmpty()) {// 栈不是空的
					// 当前优先级小于栈顶的优先级，进行计算；
					if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						// 将预算结果入数栈；
						numStack.push(res);
						// 将运算符入符号栈；
						operStack.push(ch);
					} else {// 当前优先级大于栈顶优先级；
						operStack.push(ch);
					}
				} else {// 栈空就直接入栈
					operStack.push(ch);
				}
			} else {// ch是数字
					// numStack.push(ch-48);//根据表，字符转数字
					// 当扫描到数字时不能直接入栈，因为后面可能还是数字，需要继续向后面扫描以为
					// 如果是字符就可以入栈，
					// 用字符串进行拼接；
				keepNum += ch;
				// 如果ch已经是expression最后一位，就直接入栈；
				if (index == (expression.length() - 1)) {
					numStack.push(Integer.parseInt(keepNum));
				} else {
					//判断下一个是否为字符；
					if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
						numStack.push(Integer.parseInt(keepNum));
						// keepNum一定要清空
						keepNum = "";
					}
				}
			}
			index++;
			if (index >= expression.length()) {
				break;// 扫描到了最后一个字符的后面；
			}
		}
		while (true) {
			if (operStack.isEmpty()) {// 符号栈为空就结束
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);// 入栈；
		}
		// 将数栈最后的数pop出来就是结果；
		System.out.printf("表达式%s = %d", expression, numStack.pop());

	}

}

class ArrayStack2 {// 使用前面的栈；
	private int maxsize;// 表示栈的大小；
	private int[] stack;// 用来存储数据；
	private int top = -1;// 表示栈顶；

	public ArrayStack2(int size) {
		this.maxsize = size;
		stack = new int[this.maxsize];
	}

	// 返回栈顶的值
	public int peek() {
		return stack[top];
	}

	// 判断栈是否满；
	public boolean isFull() {
		return top == maxsize - 1;
	}

	// 判断栈是否空
	public boolean isEmpty() {
		return top == -1;
	}

	// 添加
	public void push(int n) {
		if (isFull()) {
			System.out.println("栈满，无法添加数据");
			return;
		}
		top++;
		stack[top] = n;
	}

	// 取出数据；
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈空，没有数据");
		}
		int value = stack[top];
		top--;
		return value;
	}

	// 遍历；
	public void show() {
		if (isEmpty()) {
			System.out.println("没有数据，无法遍历");
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d] = %d\n", i, stack[i]);
		}
	}

	// 给出一个判断优先级大小的方法，数字越大，优先级越高；
	public int priority(int oper) {// oper是运算符，可以与字符混用
		if (oper == '+' || oper == '-') {
			return 0;
		} else if (oper == '*' || oper == '/') {
			return 1;
		} else {
			return -1;
		}
	}

	// 给出判断是否为运算符的方法；
	public boolean isOper(char oper) {
		return oper == '+' || oper == '-' || oper == '*' || oper == '/';
	}

	// 给出运算符的运算法则；
	public int cal(int num1, int num2, int oper) {
		int res = 0;// 存放计算结果；
		switch (oper) {
		case '+':
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1;// 注意顺序
			break;
		case '*':
			res = num1 * num2;
			break;
		case '/':
			res = num2 / num1;// 注意顺序；
			break;
		default:
			break;
		}
		return res;
	}

}

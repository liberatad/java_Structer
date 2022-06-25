package com.atstack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 逆波兰后缀表达式计数法
 * 
 */
public class PolandNotation {
	public static void main(String[] args) {
		// 为了说明方便，将逆波兰表达式数字和符号分开
//		String suffixExpression = "30 4 + 5 * 6 -";
//		List<String> listString = getListString(suffixExpression);
//		int res = calculate(listString);
//		System.out.println("运算的结果为"+res);
		String str = "1+((2+3)*4)-5";//expression=16;
		List<String> infixExpressionList = toInfixExpressionList(str);
		System.out.println("中缀表达式list="+infixExpressionList);
		List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
		System.out.println("后缀表达式list="+parseSuffixExpressionList);
		System.out.printf("运算的结果expression=%d",calculate(parseSuffixExpressionList));

	}

	// 方法：将中缀表达式的list转换为后缀表达式的list;
	public static List<String> parseSuffixExpressionList(List<String> ls) {
		Stack<String> s1 = new Stack<String>();
		// 由分析可知，第二个栈没有pop操作，所以可以用集合代替，操作比较方便；
		List<String> s2 = new ArrayList<String>();
		// 遍历ls
		for (String item : ls) {
			if (item.matches("\\d+")) {// 数字直接计入到s2
				s2.add(item);
			} else if (item.equals("(")) {// 左括号直接入栈；
				s1.push(item);
			} else if (item.equals(")")) {
				// 右括号时将左括号的内容逐个弹出到s2直到遇到左括号并消去；
				while (!s1.peek().equals("(")) {
					s2.add(s1.pop());

				}
				// 注意要消去括号
				s1.pop();
			} else {// 当item优先级小于等于栈顶优先级时，将s1pop加入到s2中直到
				while (s1.size() != 0 && Operation.getValue(s1.peek()) > Operation.getValue(item)) {
					s1.pop();
				}
				// 将item压入s1中；
				s1.push(item);
			}
		}
		//将s1中剩余的元素加入到s2中
		while(s1.size() != 0) {
			s2.add(s1.pop());
		}
		return s2;//由于s2是个list，本身将会逆序进行输出；
	}

	// 方法：将一个中缀表达式放到集合List中；
	public static List<String> toInfixExpressionList(String s) {
		List<String> ls = new ArrayList();
		int i = 0;// 控制循环；
		String str = "";// 用于拼接；
		char c;// 遍历的字符放到c中；一个指针；
		do {
			// 如果c不是数字，就直接放到ls中；
			if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
				ls.add("" + c);
				i++;
			} else {
				// c是一个数字，需要拼接；
				str = "";
				while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
					str += c;
					i++;
				}
				ls.add(str);
			}
		} while (i < s.length());
		return ls;
	}

	// 将表达式放到一个集合中；
	public static List<String> getListString(String suffixExpression) {
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for (String ele : split) {
			list.add(ele);// 增强for循环；
		}
		return list;
	}

	// 对逆波兰表达式进行运算；
	public static int calculate(List<String> ls) {
		// 创建一个栈，只需要一个栈；
		Stack<String> stack = new Stack<String>();
		// 遍历
		for (String item : ls) {
			if (item.matches("\\d+")) {// 判断表示一个多位数
				// 入栈
				stack.push(item);
			} else {
				// pop出两个数并运算，在入栈；
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;
				if (item.equals("+")) {
					res = num1 + num2;
				} else if (item.equals("-")) {
					res = num1 - num2;// 后面的数减去前面的数
				} else if (item.equals("*")) {
					res = num1 * num2;
				} else if (item.equals("/")) {
					res = num1 / num2;
				} else {
					throw new RuntimeException("运算符有误");
				}
				stack.push(res + "");
			}
		}
		// 最后留在里面的数据是结果；
		return Integer.parseInt(stack.pop());
	}

}

//创建一个类比较优先级；
class Operation {
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;

	// 定义一个方法，判断优先级；
	public static int getValue(String operation) {
		int result = 0;
		switch (operation) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			break;

		}
		return result;
	}

}

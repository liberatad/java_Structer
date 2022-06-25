package HashTable;

import java.util.Scanner;

public class HashTabDemo {
	public static void main(String[] args) {
		HashTab hashTab = new HashTab(8);
		//写一个菜单
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("add：添加雇员");
			System.out.println("list：遍历雇员");
			System.out.println("exit：退出系统");
			key = scanner.next();
			switch (key) {
			case "add":
				System.out.println("请输入员工id");
				int id = scanner.nextInt();
				System.out.println("请输入员工姓名");
				String name = scanner.next();
				Emp emp = new Emp(id, name);
				hashTab.add(emp);
				break;
			case "list":
				hashTab.list();
				break;
			case "exit":
				scanner.close();
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
}
/**
 * 管理多条链表
 * @author Bad
 *
 */
class HashTab{
	private EmpLinkedList[] empLinkedListArray;//用数组的形式
	private int size;//数组的大小
	public HashTab(int size) {
		this.size = size;
		empLinkedListArray = new EmpLinkedList[size];
		//这里一定要初始化，否则添加员工空指针异常
		for(int i = 0;i < size;i++) {
			empLinkedListArray[i] = new EmpLinkedList(); 
		}
	}
	/**
	 * 将员工添加到哈希表中
	 * @param emp 根据员工的id选择添加到哪一条链表
	 */
	public void add(Emp emp) {
		//获取添加位置
		int empLinkedListNum = hashFun(emp.id);
		//完成添加操作
		empLinkedListArray[empLinkedListNum].add(emp);
	}
	/**
	 * 对哈希表进行遍历
	 */
	public void list() {
		for(int i = 0;i < size;i++) {
			empLinkedListArray[i].list();
		}
	}
	/**
	 * 编写散列函数
	 * @param id
	 * @return
	 */
	public int hashFun(int id) {
		return id % size;//采用取模的方法
	}
}
/**
 * 员工表，是每一个链表的节点
 * @author Bad
 *
 */
class Emp{
	public int id;
	public String name;
	public Emp next;//用于指向下一个节点
	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
/**
 * 链表数组，每一个元素中含有头指针指向链表第一个节点
 * @author Bad
 *
 */
class EmpLinkedList{
	private Emp head = null;//这里面存放的都是头指针
	/**
	 * 链表添加方法
	 * @param emp
	 */
	public void add(Emp emp) {
		Emp curEmp = head;//创建一个辅助指针
		if(curEmp == null) {//头指针为空，直接添加
			curEmp = emp;
			return;
		}
		//头指针不为空，则需要找到下一个节点为空的节点
		while(true) {
			if(curEmp.next == null) {
				break;
			}
			curEmp = curEmp.next;
		}
		//退出循环时，该节点已经找到
		curEmp.next = emp;
		System.out.println("添加成功");
	}
	/**
	 * 链表遍历操作
	 */
	public void list() {
		if(head == null) {//说明链表为空
			System.out.println("链表为空，无法遍历");
			return;
		}
		Emp curEmp = head;
		System.out.println("链表信息为");
		while(true) {
			System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
			if(curEmp.next == null) {
				break;
			}
			curEmp = curEmp.next;
		}
		System.out.println();
	}
}
	

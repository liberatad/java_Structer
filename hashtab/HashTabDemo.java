package hashtab;

import java.util.Scanner;

/*
 * 哈希表的制作；
 * 
 */
public class HashTabDemo {
	public static void main(String[] args) {
		// 测试；
		HashTab hashTab = new HashTab(7);
		// 创建菜单；
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("add: 添加成员");
			System.out.println("list: 显示列表");
			System.out.println("exit: 退出系统");
			key = scanner.next();
			switch (key) {
			case "add":
				System.out.println("请输入id");
				int id = scanner.nextInt();
				System.out.println("请输入姓名name");
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

class HashTab {// 创建哈希表
	int size;
	EmpLinkedList[] empLinkedListArray = null;

	public HashTab(int size) {
		this.size = size;
		empLinkedListArray = new EmpLinkedList[size];
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}

	}

	// 添加雇员
	public void add(Emp emp) {
		// 根据雇员的id决定添加那条链表；
		// 需要得到雇员的哈希值
		int empLinkedListNo = fun(emp.no);
		// 注意，这里只定义了数组，数组中的元素都是null,需要初始化

		empLinkedListArray[empLinkedListNo].add(emp);
	}

	// 遍历哈希表
	public void list() {
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i].list(i);
		}
	}

	// 编写散列函数
	public int fun(int id) {
		return id % size;
	}
}

class Emp {// 记录员工的信息
	public int no;
	public String name;
	public Emp next;// 默认为空；

	public Emp(int no, String name) {
		this.no = no;
		this.name = name;
	}

}

class EmpLinkedList {// 创建员工链表
	Emp head = null;// 创建头指针，默认为空；
	// 添加员工

	public void add(Emp emp) {
		if (head == null) {
			head = emp;
		}
		Emp curEmp = head;
		while (true) {
			if (curEmp.next == null) {
				break;
			}
			curEmp = curEmp.next;
		}
		curEmp.next = emp;
	}

	// 遍历；
	public void list(int no) {
		if (head == null) {
			System.out.println("第" + (no + 1) + "条链表为空");
			return;
		}
		Emp curEmp = head;
		System.out.print("第" + (no + 1) + "条链表信息为");
		while (true) {
			System.out.printf("=>id=%d,name=%s", curEmp.no, curEmp.name);
			if (curEmp.next == null) {
				break;
			}
			curEmp = curEmp.next;
		}
		System.out.println();
	}
}

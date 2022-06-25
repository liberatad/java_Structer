package huffmancode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {
	public static void main(String[] args) {
		String str = "I love you but you do not love me !";
		byte[] strBytes = str.getBytes();
//		System.out.println(strBytes.length);
//		List<Node> nodes = getNodes(strBytes);
//		System.out.println(nodes);
//		Node root = createHuffmanTree(nodes);
//		// 前序遍历
//		preOrder(root);
//		Map<Byte, String> codes = getCodes(root);
//		System.out.println("生成的哈夫曼表为：" + codes);
//		byte[] huffmanCodeBytes = zip(strBytes, huffmanCodes);
//		System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));
//		// huffmanCodeBytes=[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14,
//		// -117, -4, -60, -90, 28]
		byte[] huffmanCodeBytes = huffmanZip(strBytes);
		System.out.println("压缩后的结果：" + Arrays.toString(huffmanCodeBytes) + "长度：" + huffmanCodeBytes.length);
		byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
		System.out.println("原来的字符串="+new String(sourceBytes));
	}

	/**
	 * 
	 * @param huffmanCodes 哈夫曼编码表
	 * @param huffmanBytes 压缩后的字节数组
	 * @return 原始的字符串字节数组
	 */
	private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
		// 1.先将压缩后的字节数组转换成01字符串序列
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < huffmanBytes.length; i++) {
			boolean flag = (i == huffmanBytes.length - 1);// 如果达到最后一位flag=true，不需要补高位
			stringBuilder.append(bytetoBitString(!flag, huffmanBytes[i]));
		}
		// 将哈夫曼编码表进行反转
		HashMap<String, Byte> map = new HashMap<>();
		for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		// 创建一个集合存放byte
		List<Byte> list = new ArrayList<>();
		for (int i = 0; i < stringBuilder.length();) {
			int count = 1;// 当i每次变化时count都是一个紧跟i的一个索引辅助找到对应的码值
			boolean flag = true;// 退出循环的标志
			Byte b = null;
			while (flag) {
				String key = stringBuilder.substring(i, i + count);
				b = map.get(key);
				if (b == null) {// 说明没有匹配到
					count++;
				} else {
					flag = false;
				}
			}
			list.add(b);
			i += count;
		}
		//for循环结束时存放所有的字符在List中
		byte[] b = new byte[list.size()];
		for(int i = 0;i < b.length;i++) {
			b[i] = list.get(i);
		}
		return b;
	}

	/**
	 * 
	 * @param flag 如果flag为true则需要补高位
	 * @param b    传入的字节
	 * @return 返回的01代码，为字节的补码
	 */
	private static String bytetoBitString(boolean flag, byte b) {
		int temp = b;
		if (flag) {
			temp |= 256;// 按位与
		}
		String str = Integer.toBinaryString(temp);
		if (flag) {
			return str.substring(str.length() - 8);
		} else {
			return str;
		}
	}

	// 使用一个方法将前面所有的方法封装起来
	/**
	 * 
	 * @param bytes 字符串的原始数组
	 * @return 经过哈夫曼编码压缩后的字节数组
	 */
	private static byte[] huffmanZip(byte[] bytes) {
		// 1.得到节点集合
		List<Node> nodes = getNodes(bytes);
		// 2.构造哈夫曼树
		Node root = createHuffmanTree(nodes);
		// 3.根据哈夫曼树得到哈夫曼表
		Map<Byte, String> codes = getCodes(root);
		// 4.根据哈夫曼表进行压缩得到数组
		byte[] huffmanCodeBytes = zip(bytes, codes);
		return huffmanCodeBytes;
	}

	// 编写一个方法，将字符串对应的byte[]经过huffmanCodes压缩成一个byte[]
	/**
	 * 
	 * @param bytes        字符串对应的字节数组
	 * @param huffmanCodes 哈夫曼编码
	 * @return 将字符串的字节值根据对应的哈夫曼编码会生成一系列01代码，八个为一组对应一个字节，八个bit中第一位是符号位
	 *         在机器中以补码存储，根据规则补码->反码->原码，原码在根据二进制->十进制得到每个字节对应的十进制数 存储在byte[]中并返回
	 */
	private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Byte b : bytes) {
			stringBuilder.append(huffmanCodes.get(b));
			// 统计返回的huffmanCodesByte的长度
			// 或者一句话：len = (stringBuilder.length()+7)/8;
		}
		int len;
		if (stringBuilder.length() % 8 == 0) {
			len = stringBuilder.length() / 8;
		} else {
			len = stringBuilder.length() / 8 + 1;
		}
		// 创建存储压缩后的byte数组
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;
		for (int i = 0; i < stringBuilder.length(); i += 8) {// 以8位为步长
			String strByte;
			if (i + 8 > stringBuilder.length()) {// 说明不是8的整数
				strByte = stringBuilder.substring(i);
			} else {
				strByte = stringBuilder.substring(i, i + 8);
			}
			huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
		}
		return huffmanCodeBytes;
	}

	public static List<Node> getNodes(byte[] bytes) {// 根据字节创建对应的集合
		ArrayList<Node> nodes = new ArrayList<Node>();
		// 接下来用HashMap统计每个字符的权重即weight
		HashMap<Byte, Integer> counts = new HashMap<>();
		for (Byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) {
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}
		}
		// 将counts中的每一个键值对转换成一个Node对象
		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}

	// 根据集合创建哈夫曼树
	public static Node createHuffmanTree(List<Node> nodes) {
		while (nodes.size() > 1) {
			Collections.sort(nodes);
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			Node parent = new Node(null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			nodes.add(parent);
		}
		return nodes.get(0);
	}

	// 生成哈夫曼树对应的哈夫曼编码
	// 将哈夫曼编码表存放在Map<Byte,String>中
	static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
	// 将01代码存放在StringBuilder中，方便管理
	static StringBuilder stringBuilder = new StringBuilder();

	// 为了调用方便，我们重载getCodes方法
	private static Map<Byte, String> getCodes(Node root) {
		if (root == null) {
			return null;
		}
		// 处理root的左子树
		getCodes(root.left, "0", stringBuilder);
		// 处理右子树
		getCodes(root.right, "1", stringBuilder);
		return huffmanCodes;
	}

	/**
	 * 
	 * @param node          传入的节点，也是递归的起始位置
	 * @param code          节点的路径 0或1
	 * @param stringBuilder 拼接的字符串
	 */
	private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
		// 下面的stringBuilder2需要每次递归时都创建，以便于递归回来的时候保留当前方法内的路径值
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		stringBuilder2.append(code);
		if (node != null) {// node==null不做讨论
			if (node.data == null) {// 说明是非叶子节点
				// 向左递归
				getCodes(node.left, "0", stringBuilder2);
				// 向右递归
				getCodes(node.right, "1", stringBuilder2);
			} else {// 说明是叶子节点
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
		}
	}

	// 前序遍历方法
	private static void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("哈夫曼树为空，不能遍历");
		}
	}

}

//创建节点
class Node implements Comparable<Node> {
	Byte data;// 存放数据（字符）
	int weight;// 权重
	Node left;
	Node right;

	public Node(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(Node o) {
		// 从小到大排序
		return this.weight - o.weight;
	}

	// 写一个前序遍历方法
	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

}

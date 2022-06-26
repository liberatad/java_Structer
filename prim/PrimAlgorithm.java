package prim;

import java.util.Arrays;

/**
 * 普林姆算法之最小生成树
 * 
 * @author Bad
 *
 */
public class PrimAlgorithm {
	public static void main(String[] args) {
		char[] data = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int verxs = data.length;
		int max = 10000;
		int[][] weight = { { max, 5, 7, max, max, max, 2 }, { 5, max, max, 9, max, max, 3 },
				{ 7, max, max, max, 8, max, max }, { max, 9, max, max, max, 4, max }, { max, max, 8, max, max, 5, 4 },
				{ max, max, max, 4, 5, max, 6 }, { 2, 3, max, max, 4, 6, max }, };
		Graph graph = new Graph(verxs);
		MinTree minTree = new MinTree();
		minTree.creatGraph(graph, verxs, data, weight);
		minTree.showGraph(graph);
		minTree.prim(graph, 3);
	}

}

//创建最小生成树
class MinTree {
	/**
	 * 创建图
	 * 
	 * @param graph  图对象
	 * @param verxs  图的节点个数
	 * @param data   节点数据
	 * @param weight 邻接矩阵
	 */
	public void creatGraph(Graph graph, int verxs, char[] data, int[][] weight) {
		for (int i = 0; i < verxs; i++) {
			graph.data[i] = data[i];
			for (int j = 0; j < verxs; j++) {
				graph.weight[i][j] = weight[i][j];
			}
		}
	}

	// 显示图的邻接矩阵
	public void showGraph(Graph graph) {
		for (int[] link : graph.weight) {
			System.out.println(Arrays.toString(link));
		}
	}

	/**
	 * 
	 * @param graph 传入的图的对象
	 * @param v     从那个节点开始
	 */
	public void prim(Graph graph, int v) {
		int[] isVisited = new int[graph.verxs];// 用于记录节点是否被访问过
		isVisited[v] = 1;// 第一个节点标记为1
		int h1 = -1;
		int h2 = -1;// 用于记录节点的下标
		int minweight = 10000;
		int sum = 0;
		for (int k = 1; k < graph.verxs; k++) {
			// 这个双层for循环的目的就是找到已经访问的节点的邻接的边的最小值
			for (int i = 0; i < graph.verxs; i++) {// i表示已经访问过的节点
				for (int j = 0; j < graph.verxs; j++) {// j表示未被访问过的节点
					if (isVisited[i] == 1 && isVisited[j] == 0 && graph.weight[i][j] < minweight) {
						// 将minqweight进行替换
						minweight = graph.weight[i][j];
						h1 = i;
						h2 = j;
					}
				}
			}
			// 当推出这个for循环之后，找到一条边是最小的
			System.out.println("边<" + graph.data[h1] + "，" + graph.data[h2] + ">权值：" + minweight);
			isVisited[h2] = 1;
			sum += minweight;
			System.out.println("总路长：" + sum);
			// minweight要重新设置成最大值
			minweight = 10000;
		}

	}

}

//创建图
class Graph {
	int verxs;// 顶点个数
	char[] data; // 存放节点数据
	int[][] weight; // 存放边

	public Graph(int verxs) {
		this.verxs = verxs;
		data = new char[verxs];
		weight = new int[verxs][verxs];
	}
}
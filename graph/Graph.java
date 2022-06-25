package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
	private ArrayList<String> vertexList;//存储顶点的集合
	private int[][] edges;//存储边的数组；
	private int numOfEdges;//边的数目
	private boolean[] isVisited;
	public static void main(String[] args) {
		//测试
		int n = 8;//节点的个数
		Graph graph = new Graph(8);
		//String[] Vertex = {"A","B","C","D","E"};
		String[] Vertex = {"1","2","3","4","5","6","7","8"};
		//添加节点
		for(String vertex : Vertex) {
			graph.insertVertex(vertex);
		}
		//添加边
		//A-B,A-C,B-C,B-D,B-E
//		graph.insertEdge(0, 1, 1);
//		graph.insertEdge(0, 2, 1);
//		graph.insertEdge(1, 2, 1);
//		graph.insertEdge(1, 3, 1);
//		graph.insertEdge(1, 4, 1);
//		graph.showGraph();
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		graph.insertEdge(3, 7, 1);
		graph.insertEdge(4, 7, 1);
		graph.insertEdge(2, 5, 1);
		graph.insertEdge(2, 6, 1);
		graph.insertEdge(5, 6, 1);
		graph.showGraph();
		System.out.println("深度优先");//1->2->4->8->5->3->6->7->
		graph.dfs();
		System.out.println();
		System.out.println("广度优先");//1=>2=>3=>4=>5=>6=>7=>8=>
		graph.bfs();
	}
	public Graph(int n) {
		edges = new int[n][n];
		numOfEdges = 0;
		vertexList = new ArrayList<String>(n);
	}
	//编写一个方法，返回节点的第一个邻接结点的下标
	/**
	 * 
	 * @param index 要查找的节点下标
	 * @return 要查找的节点下标index的第一个邻接结点的下标
	 */
	public int getFirstNeighbor(int index) {
		for(int j = 0;j < vertexList.size();j++) {
			if(edges[index][j] >0) {
				return j;
			}
		}
		return -1;
	}
	//编写一个方法，根据前一个邻接节点的下标获取来获取下一个邻接结点下标
	public int getNextNeighbor(int v1,int v2) {
		for(int j = v2+1;j < vertexList.size();j++) {
			if(edges[v1][j] > 0) {
				return j;
			}
		}
		return -1;
	}
	//深度优先遍历算法
	public void dfs(boolean[] isVisited,int i) {
		//首先我们访问该节点输出
		System.out.print(getValueByIndex(i)+"->");
		isVisited[i] = true;
		//得到该结点的第一个邻接结点
		int w = getFirstNeighbor(i);
		while(w != -1) {//说明该节点的第一个邻接结点存在
			if(!isVisited[w]) {//说明第一个邻接结点未被访问
				dfs(isVisited,w);//递归
			}
			//若第一个节点已经被访问，则直接跳到下一个节点
			//注意：当第一个节点即A深度访问过后到D时无法访问，这时会回溯到B，
			//以B为新的节点进行深度优先访问；
			w = getNextNeighbor(i, w);
		}
	}
	//对dfs方法进行重载，遍历所有的节点
	public void dfs() {
		isVisited = new boolean[getNumOfVertex()];
		for(int i = 0;i < getNumOfVertex();i++) {
			if(!isVisited[i]) {
				dfs(isVisited, i);
			}
		}
	}
	//对一个节点进行广度优先遍历
	public void bfs(boolean[] isVisited,int i) {
		int u;//表示队列头结点对应的下标
		int w;//邻接结点
		//创建队列，记录已经访问的顺序
		LinkedList<Object> queue = new LinkedList<Object>();
		//输出第一个节点
		System.out.print(getValueByIndex(i)+"=>");
		//标i已经被访问
		isVisited[i] = true;
		//将节点加入队列
		queue.addLast(i);
		while(!queue.isEmpty()) {
			u = (int) queue.removeFirst();
			w = getFirstNeighbor(u);
			while(w != -1) {//找到了节点
				if(!isVisited[w]) {//表示还未被访问
					System.out.print(getValueByIndex(w)+"=>");
					//标记已经被访问
					isVisited[w] = true;
					//入队列
					queue.addLast(w);
				}
				//如果已经被访问，则以u为前驱找到下个邻接点
				//这里与深度优先不同的是第一个节点A就会直接访问后面的节点，直至A没有邻接的节点
				w = getNextNeighbor(u, w);//这里对w进行了重新的赋值；广度优先的核心；
			}
		}
	}
	//对广度优先算法进行重载
	public void bfs() {
		isVisited = new boolean[getNumOfVertex()];
		for(int i = 0;i < getNumOfVertex();i++) {
			if(!isVisited[i]) {
				bfs(isVisited, i);
			}
		}
	}
	//图中常用的方法
	//显示二维数组
	public void showGraph() {
		for(int[] link : edges) {
			System.out.println(Arrays.toString(link));
		}
	}
	//返回结点的个数
	public int getNumOfVertex() {
		return vertexList.size();
	}
	//返回边的个数
	public int getNumOfEdges() {
		return numOfEdges;
	}
	//返回节点下标i对应的数据
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}
	//返回两个节点之间边的权值
	public int getWeight(int v1,int v2) {
		return edges[v1][v2];
	}
	
	//添加顶点的方法
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}
	//添加边的方法
	/**
	 * 
	 * @param v1 顶点的下标
	 * @param v2 顶点的下标
	 * @param weight 边的权值
	 */
	public void insertEdge(int v1,int v2,int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}

}

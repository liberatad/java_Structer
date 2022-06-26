package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法之集合覆盖问题
 * 
 * @author Bad
 *
 */
public class GreedyAlgorithm {
	public static void main(String[] args) {
		HashMap<String, HashSet<String>> broadcast = new HashMap<String, HashSet<String>>();
		HashSet<String> hashSet1 = new HashSet<String>();
		hashSet1.add("北京");
		hashSet1.add("上海");
		hashSet1.add("天津");
		HashSet<String> hashSet2 = new HashSet<String>();
		hashSet2.add("广州");
		hashSet2.add("北京");
		hashSet2.add("深圳");
		HashSet<String> hashSet3 = new HashSet<String>();
		hashSet3.add("成都");
		hashSet3.add("上海");
		hashSet3.add("杭州");
		HashSet<String> hashSet4 = new HashSet<String>();
		hashSet4.add("上海");
		hashSet4.add("天津");
		HashSet<String> hashSet5 = new HashSet<String>();
		hashSet5.add("杭州");
		hashSet5.add("大连");
		broadcast.put("K1", hashSet1);
		broadcast.put("K2", hashSet2);
		broadcast.put("K3", hashSet3);
		broadcast.put("K4", hashSet4);
		broadcast.put("K5", hashSet5);
		HashSet<String> allAreas = new HashSet<String>();
		allAreas.add("北京");
		allAreas.add("上海");
		allAreas.add("天津");
		allAreas.add("广州");
		allAreas.add("深圳");
		allAreas.add("成都");
		allAreas.add("杭州");
		allAreas.add("大连");
		//创建一个集合，存储选择的电台
		ArrayList<String> selects = new ArrayList<String>();
		//创建一个集合，求它与allAreas的最大的交集
		HashSet<String> tempSet = new HashSet<String>();
		String maxKey = null;//辅助指针，用于寻找最大交集对应的key值
		while(allAreas.size() != 0) {//说明还没有覆盖所有的地区
			//每进行一次while循环，都要将maxKey置空
			maxKey = null;
			for(String key : broadcast.keySet()) {
				//没进行一次for循环都要将tempSet置空；并且要放在取下一次交集之前
				tempSet.clear();
				HashSet<String> areas = broadcast.get(key);//当前集合能覆盖的地区
				tempSet.addAll(areas);
				tempSet.retainAll(allAreas);//求交集并放到tempSet中
				if(tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcast.get(maxKey).size())) {
					maxKey = key;//体现贪心
				}
			}
			//maxKey != null,就将其加入到selects中
			if(maxKey != null) {
				selects.add(maxKey);
				//并将allAreas中移除maxKey对应的areas;
				allAreas.removeAll(broadcast.get(maxKey));
			}
		}
		System.out.println("取得的集合为："+selects);//[K1, K2, K3, K5]
	}

}

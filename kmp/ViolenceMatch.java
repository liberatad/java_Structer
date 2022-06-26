package kmp;

public class ViolenceMatch {
	public static void main(String[] args) {
		//测试暴力匹配算法
		String str1 = "iloveloveyouyoudonotloveloveme";
		String str2 = "youdonotlove";
		int violenceMatch = violenceMatch(str1, str2);
		System.out.println(violenceMatch);

	}

	public static int violenceMatch(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int s1Len = s1.length;
		int s2Len = s2.length;
		int i = 0;// 指向str1的索引
		int j = 0;// 指向str2的索引
		while (i < s1Len && j < s2Len) {
			if (s1[i] == s2[j]) {
				i++;
				j++;
			} else {
				i = i - (j - 1);// 显然如果开始时不匹配，i变为1，若还不匹配，i变为2
				j = 0;
			}
		}
		if (j == s2Len) {// 说明匹配到了
			return i - j;
		}
		return -1;
	}

}

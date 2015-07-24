package com.liyuncong.sort.sort_linetime;

/**
 * 计数排序假设n个输入元素中的每一个都是介于0到k之间的某个整数，k为某个整数；它
 * 通过确定小于等于一个数的数的个数来确定这个数应该放在哪个位置
 * @author yuncong
 *
 */
public class CountSort {
	/**
	 * 
	 * @param a 排序前的数组
	 * @param b 排序后的数组
	 * @param k 排序数组中最大元素的值
	 */
	public void sort(int[] a, int[] b, int k){
		// 创建数组c，并初始化
		int[] c = new int[k + 1];
		for (int i = 0; i < c.length; i++) {
			c[i] = 0;
		}
		// 统计数组a中每个元素出现的次数
		for (int i = 0; i < a.length; i++) {
			c[a[i]]++;
		}
		/**
		 * 统计数组a中小于等于某一个数的数的个数;
		 * 因为小于等于0的数的个数就是等于0的数的个数，所迭代从1开始
		 */
		for (int i = 1; i < c.length; i++) {
			c[i] = c[i] + c[i - 1];
		}
		for (int i = 0; i < a.length; i++) {
			/**
			 * 小于等于a[i]的数的个数为x就应该将该数放在数组b的第x-1个位置，
			 * 因为数组的下标从0开始
			 */
			b[c[a[i]] - 1] = a[i];
			/**
			 * 下一个a[i]排在这个a[i]的前面;
			 * 下一个a[i]排在前面的原因是前面为所有小于等于a[i]的数留足了空间
			 */
			c[a[i]]--;
		}
	}
	
	public static void main(String[] args) {
		int[] a = new int[]{3, 1, 14, 5, 6};
		int[] b = new int[5];
		new CountSort().sort(a, b, 14);
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
	}

}

package com.liyuncong.sort.sort_linetime;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 基数排序的思想是多关键字排序
 * @author yuncong
 *
 */
public class RadixSort {
	/**
	 * 基于最低位优先方式对一个整型数组排序，数组中整数是d位数；
	 * 以3位整数排序为例，基于最低位优先方式排序的步骤是：创建十个队列，用于临时存储整数；
	 * 首先按个位排序，按照每个整数的个位数字把整数放进相应队列，比如120放进第0个队列，
	 * 124放进第4个队列，当把所有整数都放进相应队列后，依次从第零个队列到第9个队列收集整数，
	 * 收集得到的所有整数按照个位数大小从小到大排序了；
	 * 然后按十位数排序，以同样的方式把每个整数按十位数放进相应队列，对于十位数相同的整数，
	 * 个位数小的整数先放进队列，因为上一步按个位数的排序导致个位数小的数在前面，接下来，
	 * 依次从第零个队列到第9个队列收集整数，整体上先收集十位数小的数，局部到具体队列中，
	 * 也就是十位数相同的那些数，按照队列先进先出的特性，个位数小的数先被收集，于是，收集
	 * 得到的所有整数是按照十位和个位组合后的大小从小到大排列的；
	 * 最后按百位数排序，以同样的方式把每个整数按百位数放进相应队列，对于百位数相同的整数，
	 * 十位数和个位数组合后小的整数先被放进队列，因为上一步的排序导致十位数和个位数组合后小
	 * 的整数在前面，接下来，依次从第零个队列到第9个队列收集整数，整体上，先收集百位数小
	 * 的数，局部到具体队列中，也就是百位数相同的那些数，按照队列的先进先出特性，十位数和
	 * 个位数组合后小的整数先被收集，于是，收集得到的所有整数按从小到大排列。
	 * 一句话总结排序过程，整体上从小到大，局部上也是从小到大。
	 * @param integers 待排序整数数组
	 * @param d 待排序数组中整数的位数
	 */
	public void sort(Integer[] integers, int d) {
		/**
		 * 创建队列集合并初始化，集合中的队列用于存放相应的元素，比如按最低位排序，234应该
		 * 放在queues.get(4)这个队列中
		 */
		LinkedList<LinkedList<Integer>> queues = new LinkedList<LinkedList<Integer>>();
		for(int i = 0; i < 10; i++){
			LinkedList<Integer> queue = new LinkedList<Integer>();
			queues.add(queue);
		}
		for(int i = d - 1; i >= 0; i--) {
			// 根据排序key，把元素放进相应的队列里
			for (int j = 0; j < integers.length; j++) {
				Integer key = getValueByIndex(integers[j], i);
				queues.get(key).add(integers[j]);
			}
			/**
			 * 把元素收回来；
			 * for循环从头到尾便利集合
			 */
			int index = 0;
			for (LinkedList<Integer> linkedList : queues) {
				for (Integer integer : linkedList) {
					integers[index] = integer;
					index++;
				}
				// 清空queues里的所有队列，为二次利用做准备
				linkedList.clear();
			}
		}
	}
	/**
	 * 获得integer中第index + 1个数字
	 * @param integer
	 * @param index
	 * @return
	 */
	public Integer getValueByIndex(Integer integer, int index) {
		Objects.requireNonNull(integer);
		String iString = integer.toString();
		if (index < 0 || index >= iString.length()) {
			throw new IndexOutOfBoundsException();
		}
		String value = iString.substring(index, index + 1);
		return Integer.valueOf(value);
	}
	
	public static void main(String[] args) {
		Integer[] integers = new Integer[]{654, 122, 987, 123, 345, 234};
		new RadixSort().sort(integers, 3);
		for (int i = 0; i < integers.length; i++) {
			System.out.println(integers[i]);
		}
	}

}

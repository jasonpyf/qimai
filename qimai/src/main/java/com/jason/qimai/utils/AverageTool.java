package com.jason.qimai.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计算平均值，去除不合理的数据
 * 
 * @author Ro
 * 
 */
public class AverageTool
{
    /**
     * 主方法
     */
    public static int getAverage(String str_int_arr)
    {
        int[] int_arr = stringToIntWhitSort(str_int_arr.split(","));
        if (1 < int_arr.length)
        {
            return getAverageByGoodList(getGoodListByBaseList(getBaseListByIntArray(int_arr)));
        }
        return Integer.parseInt(str_int_arr);
    }

    /**
     * 将String数组转换为int数组
     */
    private static int[] stringToIntWhitSort(String[] arrs)
    {
        int[] ints = new int[arrs.length];
        for (int i = 0; i < arrs.length; i++)
        {
            ints[i] = Integer.parseInt(arrs[i]);
        }

        Arrays.sort(ints);
        return ints;
    }

    /**
     * 将int数组根据计算的基准值按块分离
     */
    private static List<List<Integer>> getBaseListByIntArray(int[] int_arr)
    {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        list.add(new ArrayList<Integer>());

        int list_index = 0;
        int average_flag = 0;
        int sum_temp = 0;

        for (int i : int_arr)
        {
            sum_temp += i;
        }
        average_flag = sum_temp / int_arr.length;

        for (int i = 0; i < int_arr.length - 1; i++)
        {
            if (average_flag >= Math.abs(int_arr[i] - int_arr[i + 1]))
            {
                list.get(list_index).add(int_arr[i]);
            }
            else
            {
                list.get(list_index++).add(int_arr[i]);
                list.add(new ArrayList<Integer>());
            }

            if ((i + 2) == int_arr.length)
            {
                list.get(list_index).add(int_arr[i + 1]);
            }
        }
        return list;
    }

    /**
     * 筛选留下数据最多的块
     */
    private static List<List<Integer>> getGoodListByBaseList(
            List<List<Integer>> list)
    {
        if (1 < list.size())
            for (int i = 1; i < list.size(); i++)
            {
                if (list.get(0).size() != list.get(i).size())
                {
                    if (list.get(0).size() < list.get(i).size())
                    {
                        list.remove(0);
                    }
                    else
                    {
                        list.remove(i);
                    }
                    i--;
                }
            }
        return list;
    }

    /**
     * 将块计算平均值，如果数据大于两位，则个位四舍五入
     */
    private static int getAverageByGoodList(List<List<Integer>> good_list)
    {
        int average = 0;
        int sum_temp = 0;
        int sum_index = 0;
        for (List<Integer> t_list : good_list)
        {
            for (int i : t_list)
            {
                sum_temp += i;
                sum_index += 1;
            }
        }
        average = sum_temp / sum_index;
        if (10 < average)
            average = (average + 5) / 10 * 10;
        return average;
    }
}
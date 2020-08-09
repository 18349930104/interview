package com.txj;

import javax.sound.midi.Soundbank;
import java.time.chrono.IsoChronology;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: tangx
 * @Date: 2020/8/9 14:16
 * @Description: com.txj
 */
public class Demo {
    public static void main(String[] args) {
        long re = System.currentTimeMillis();
        //创建线程池
        //返回线程池对象
        ExecutorService threadPool = Executors.newFixedThreadPool(1000);
        //初始化集合
        ArrayList<Integer> list = new ArrayList<>();
        //模拟
        for (int i=0;i<500000;i++){
            list.add(i);
        }
        //集合总条数
        int size = list.size();
        //集合段数
        int sunSum = 1000;
        int listStart,listEnd;
        //当总数不足1000时使用总条数作切分值
        if(sunSum>size){
            sunSum=size;
        }
        //定义Runnable子线程
        MyRunnale myRunnale;
        for(int i=1;i<=sunSum;i++){
            //计算切割
            if (i==1){
                listStart=0;
            }else {
                listStart = size/ sunSum*(i-1);
            }
            listEnd = size/sunSum*(i);
            if (i==sunSum){
                listEnd = size;
            }

            //线程切断
            List<Integer> subList = list.subList(listStart, listEnd);
            //子线程初始化
            myRunnale = new MyRunnale(i,subList);
            threadPool.submit(myRunnale);
        }
        //线程关闭
        threadPool.shutdown();
        System.out.println("时间="+(System.currentTimeMillis()-re));
    }
}

class MyRunnale implements Runnable{
    /**当前是属于第几段线程**/
    private int pageIndex;

    private List<Integer> list;
    public MyRunnale(int pageIndex,List<Integer> list){
        this.pageIndex = pageIndex;
        this.list = list;
    }
    @Override
    public void run() {
        if(null != list && list.size() >0){
            Random random = new Random();
            //生成0-500随机数
            HashSet<Integer> hashSet = new HashSet<>();
            while (hashSet.size()<100){
                hashSet.add(random.nextInt(500));
            }
            for (Integer num : hashSet) {
                System.out.println(list.get(num));
            }
        }
    }
}

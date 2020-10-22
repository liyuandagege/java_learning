package Negative_b_distribution;

import java.io.*;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.math3.distribution.PascalDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
//import java.awt.Graphics;
//import java.util.Arrays;
import java.util.Scanner;
//接口
interface Negative_b_distribution{
    public int negative_b(double pro, int r);

}
public class negative_b<T> implements Negative_b_distribution{
//    private static final int Max = 1 << 10;
    public static void main(String[] args) {
        int random_number = 2000;// 产生随机数的个数
        int max_num = 1000;// 预计的最大随机数
        int [] r_num_self = new int[max_num + 1];
        int [] r_num = new int[max_num + 1];
        double probility;
        String file_name;// String 类型来存储字符串数组

        for(int i = 1; i < max_num; i++){
            r_num[i] = 0;  // 下标代表随机数，数组值代表这个随机数的个数
            r_num_self[i] = 0;
        }// 全部赋值为0
        int pro ;
        int r = 8;// 负二项分布中失败的次数
        negative_b di = new negative_b();
        RandomGenerator randomGenerator = new JDKRandomGenerator();

        for(pro=1;pro<=9;pro+=1){
            // 这个是产生我们自己的随机数
            for(int i = 1; i < random_number; i++){
                probility = (double) pro * 0.1;
                int r_num_self_index = di.negative_b(probility ,  r );
                if(r_num_self_index< 1000)
                    r_num_self[r_num_self_index] += 1;  // 下标代表随机数，数组值代表这个随机数的个数

                file_name = "H:\\math_bigpy\\" + pro +"self.csv";
                write_csv(file_name, r_num_self);
                PascalDistribution pascalDistribution = new PascalDistribution(randomGenerator, r, probility);
//                System.out.println("the random number is:"+ pascalDistribution.sample());
                int r_num_index = pascalDistribution.sample();
                if(r_num_index< 1000)
                    r_num[r_num_index] += 1;  // 下标代表随机数，数组值代表这个随机数的个数
                file_name = "H:\\math_bigpy\\" + pro +".csv";
                write_csv(file_name, r_num);

            }
        }
    }

    public static void write_csv(String file_name, int [] r_num){
        //File writeFile = new File("H:\\math_big\\result_data\\");
        File writeFile = new File(String.valueOf(file_name));
        try{
            //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            for(int i=1;i< r_num.length;i++){
                //调用write的方法将字符串写到流中
                if(r_num[i]!=0){
                    writeText.write(r_num[i]+","+i); // r_num[i] 出现的次数 i 随机数
                    writeText.newLine();    //换行
                }
            }
            //使用缓冲区的刷新方法将数据刷到目的地中
            writeText.flush();
            //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
            //因此，此处的close()方法关闭的是被缓存的流对象
            writeText.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }
    }

    @Override
    public int negative_b(double pro, int r){
        Random r_1 = new Random();
        int rnd = 0, k= 0;
        while (true){
            k++;
            double r_num = r_1.nextDouble();
//            System.out.println("Helloworld!" + r_num);
            if(r_num  < pro){
                rnd++;
            }
            if(rnd > r)
                break;
        }
        return k;
    }
}

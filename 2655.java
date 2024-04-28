// https://www.acmicpc.net/problem/2655
// 가장 높은 탑 쌓기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, dp[], top[];
    static Brick bricks[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        bricks = new Brick[n];
        for(int i=0; i<n; i++)
            bricks[i] = new Brick(i, reader.readLine());

        // sort
        Brick[] bottomSort = bricks.clone();
        Arrays.sort(bottomSort, new Comparator<Brick>(){
            @Override
            public int compare(Brick o1, Brick o2){
                return o1.bottom - o2.bottom;
            }
        });
        Brick[] weightSort = bricks.clone();
        Arrays.sort(weightSort, new Comparator<Brick>(){
            @Override
            public int compare(Brick o1, Brick o2){
                return o1.weight - o2.weight;
            }
        });

        // solution
        dp = new int[n];
        top = new int[n];
        int max = 0, ansIdx = -1;
        for(int i=0; i<n; i++){
            if(dp[bottomSort[i].num] == 0){
                int num = getMaxDpNum(bottomSort, i-1, bottomSort[i]);
                top[bottomSort[i].num] = num;
                dp[bottomSort[i].num] = (num==-1 ? 0 : dp[num]) + bottomSort[i].height;
                if(dp[bottomSort[i].num] > max){
                    max = dp[bottomSort[i].num];
                    ansIdx = bottomSort[i].num;
                }
            }
            if(dp[weightSort[i].num] == 0){
                int num = getMaxDpNum(weightSort, i-1, weightSort[i]);
                top[weightSort[i].num] = num;
                dp[weightSort[i].num] = (num==-1 ? 0 : dp[num]) + weightSort[i].height;
                if(dp[weightSort[i].num] > max){
                    max = dp[weightSort[i].num];
                    ansIdx = weightSort[i].num;
                }
            }
        }

        // output
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while(ansIdx != -1){
            count++;
            sb.insert(0, ansIdx+1+"\n");
            ansIdx = top[ansIdx];
        }
        sb.insert(0, count+"\n");
        System.out.print(sb);

        // debugging
        /*System.out.println();
        print(bricks);
        print(bottomSort);
        print(weightSort);
        print(dp);
        print(top);
        System.out.println(max+", "+ansIdx); */
    }

    public static int getMaxDpNum(Brick bricks[], int end, Brick below){
        int max = 0, num = -1;
        for(int i=0; i<=end; i++){
            if(!below.isPutAble(bricks[i])) continue;
            if(dp[bricks[i].num] > max){
                max = dp[bricks[i].num];
                num = bricks[i].num;
            }
        }
        return num;
    }

    public static void print(Brick[] arr){
        for(int i=0; i<arr.length; i++)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
    public static void print(int[] arr){
        for(int i=0; i<arr.length; i++)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
}

class Brick{
    int num, bottom, height, weight;

    public Brick(int n, int b, int h, int w){
        num = n;
        bottom = b;
        height = h;
        weight = w;
    }

    public Brick(int n, String input){
        num = n;
        StringTokenizer st = new StringTokenizer(input);
        bottom = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        weight = Integer.parseInt(st.nextToken());
    }

    public boolean isPutAble(Brick top){
        return this.bottom > top.bottom && this.weight > top.weight;
    }

    @Override
    public String toString(){
        return "("+num+": "+bottom+", "+height+", "+weight+")";
    }
}

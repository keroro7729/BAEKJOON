// https://www.acmicpc.net/problem/2166
// 다각형의 면적

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n;
    private static List<Coordinate> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(reader.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            list.add(new Coordinate(x, y));
        }

        long sum = 0;
        for(int i=1; i<n-1; i++)
            sum += triangle(list.get(0), list.get(i), list.get(i+1));
        sum = Math.abs(sum);
        if(sum%2 == 0)
            System.out.print(sum/2+".0");
        else System.out.println(sum/2+".5");
    }

    private static long triangle(Coordinate a, Coordinate b, Coordinate c){
        return ((long)a.x*b.y + b.x*c.y + a.y*c.x - b.x*a.y - c.x*b.y - a.x*c.y);
    }
}
class Coordinate{
    long x, y;
    public Coordinate(long x, long y){
        this.x = x;
        this.y = y;
    }
}

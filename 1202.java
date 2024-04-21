// https://www.acmicpc.net/problem/1202
// 보석도둑

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, k, bags[];
    static Gem[] gems;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        gems = new Gem[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(reader.readLine());
            gems[i] = new Gem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        bags = new int[k];
        for(int i=0; i<k; i++)
            bags[i] = Integer.parseInt(reader.readLine());

        // 그리디: 가장 용량이 작은 가방부터 최대한 비싼 보석을 선택
        Arrays.sort(gems);
        Arrays.sort(bags);
        int idx = 0;
        long answer = 0;
        PriorityQueue<Gem> que = new PriorityQueue<>(new Comparator<Gem>(){
            @Override
            public int compare(Gem o1, Gem o2){
                return o2.price - o1.price;
            }
        });
        for(int i=0; i<k; i++){
            while(idx < n && gems[idx].weight <= bags[i])
                que.add(gems[idx++]);
            if(que.isEmpty()) continue;
            answer += que.poll().price;
        }
        System.out.println(answer);
    }
}

class Gem implements Comparable<Gem> {
    int weight, price;
    public Gem(int weight, int price){
        this.weight = weight;
        this.price = price;
    }

    @Override
    public int compareTo(Gem o){
        return this.weight - o.weight;
    }

    @Override
    public String toString(){
        return "("+weight+", "+price+")";
    }
}

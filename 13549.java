// https://www.acmicpc.net/problem/13549
// BOJ 13549 숨바꼭질 3

import java.io.*;
import java.util.*;

public class Main {
	static int n, k, SIZE, dp[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(scan.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		if(k <= n) {
			System.out.println(n-k);
			return;
		}
		SIZE = k+10;
		Queue<Node> que = new LinkedList<>();
		dp = new int[SIZE];
		for(int i=0; i<SIZE; i++)
			dp[i] = -1;
		que.add(new Node(n, 0));
		if(jump(n, 0)) {
			System.out.println(0);
			return;
		}
		while(!que.isEmpty()) {
			Node cur = que.poll();
			//System.out.println("test: "+cur.point);
			if(cur.point == 0) {
				if(k == 1) {
					System.out.println(cur.depth+1);
					return;
				}
				if(dp[1] == -1) {
					que.add(new Node(1, cur.depth+1));
					dp[1] = cur.depth+1;
				}
				continue;
			}
			
			for(int i=cur.point; i<SIZE; i*=2) {
				if(i+1 == k || i-1 == k) {
					System.out.println(cur.depth+1);
					return;
				}
				if(check(i+1)) {
					if(jump(i+1, cur.depth+1)) {
						System.out.println(cur.depth+1);
						return;
					}
					que.add(new Node(i+1, cur.depth+1));
					dp[i+1] = cur.depth+1;
				}
				if(check(i-1)) {
					if(jump(i-1, cur.depth+1)) {
						System.out.println(cur.depth+1);
						return;
					}
					que.add(new Node(i-1, cur.depth+1));
					dp[i-1] = cur.depth+1;
				}
			}
		}
	}
	
	public static boolean check(int index) {
		if(index < 0 || SIZE <= index)
			return false;
		if(dp[index] != -1)
			return false;
		return true;
	}
	
	public static boolean jump(int index, int depth) {
		if(index == 0) return false;
		for(int i=index; i<SIZE; i*=2) {
			if(i == k) return true;
			dp[i] = depth;
		}
		return false;
	}
	
	static class Node{
		int point, depth;
		public Node(int p, int d) {
			point = p;
			depth = d;
		}
	}
}

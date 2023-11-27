// https://www.acmicpc.net/problem/1035
/**
 * 처음에 bfs방식으로 풀어봤지만 탐색 depth가 7만 넘어가도 
 * que에 의미없는 경우가 너무 많아져서 시간이 오래걸렸다.
 * 
 * 수학적으로 접근해서 각 조각들의 중점을 구하고
 * 중점에서 가장 먼 조각을, 중점에 가까워지도록 한칸씩 이동시키는 방식으로 구현했다.
 * 중점에서 가장 먼 조각을 고르는 것이 문제가 될 수 있다.
 * 다른 조각에 의해서 중점으로 가는 길이 막히는 경우가 존재
 * + 중점으로 모이지 않고도 더 빠르게 연결되는 경우도 존재 할 수 있음
 */
import java.util.*;
import java.io.*;

public class Main {
	// 조각의 위치가 밀집되어 있는 곳이 유리하다.
	// 5*5맵에 5개의 조각을 움직으는 경우의 수
	// bfs로 탐색, 탐색에 필요한 5개돌의 좌표와 visit정보

	// 움직이는 최대 경우의수 20 ^ 최대 depth answer
	// 답이 12만 되어도 12제곱의 탐색수...
	
	// 문제를 다시 정의해보자
	// 조각들의 거리합이 최소가 되는 중점을 구해서 해당 방향으로만 움직이자.
	public static void main(String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		// input
		String[] map = new String[5];
		for(int i=0; i<5; i++)
			map[i] = scan.readLine();
			
		// solution
		int answer = 0;
		while(!is_connected(map)) {
			double[] mid = get_mid_point(map);
			map = move(map, mid[0], mid[1]);
			answer += 1;
			//print(map);
		}
		System.out.println(answer);
	}
	
	/*
	 * 중점에서 가장 먼 조각 하나를 한칸 이동시키는 함수
	 * 이동 후의 map을 리턴한다.
	 */
	public static String[] move(String[] map, double mx, double my) {
		// !! 가장 먼 조각이 더이상 이동할 수 없는 경우는 없나?
		
		// 가장 먼 조각을 구함
		double maxd = 0;
		int x = -1, y = -1;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(map[i].charAt(j) == '*') {
					double d = Math.sqrt(Math.pow(mx-i, 2) + Math.pow(my-j, 2));
					if(d > maxd) {
						maxd = d;
						x = i;
						y = j;
					}
				}
			}
		}
		
		// 이동할 수 있는 방향 중 가장 중점에 가까워 질 수 있는 방향을 구함
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		double dd = 0;
		int minx = -1, miny = -1;
		for(int i=0; i<4; i++) {
			int nx = dx[i]+x, ny = dy[i]+y;
			if(nx < 0 || ny < 0 || nx > 4 || ny > 4) continue;
			if(map[nx].charAt(ny) == '*') continue;
			
			double d = Math.sqrt(Math.pow(mx-nx, 2) + Math.pow(my-ny, 2));
			d = maxd - d;
			if(dd < d) {
				dd = d;
				minx = nx;
				miny = ny;
			}
		}
		
		if(minx == -1 || miny == -1) {
			//System.out.println(mx+","+my+" : "+x+","+y);
			return null;
		}
		
		// 해당 방향으로 이동한 결과 map을 리턴
		map[x] = map[x].substring(0,y) + '.' + map[x].substring(y+1);
		map[minx] = map[minx].substring(0, miny) + '*' + map[minx].substring(miny+1);
		return map;
	}
	
	public static boolean is_connected(String[] map) {
		Queue<Node> que = new LinkedList<>();
		boolean[][] visited = new boolean[5][5];
		int count = 0;
		boolean isFirst = true;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(map[i].charAt(j) == '*') {
					if(isFirst) {
						que.add(new Node(i,j));
						visited[i][j] = true;
						isFirst = false;
					}
					count++;
				}
			}
		}
		
		// bfs
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		while(!que.isEmpty()) {
			Node cur = que.poll();
			for(int i=0; i<4; i++) {
				int x = cur.x+dx[i], y = cur.y+dy[i];
				if(x < 0 || y < 0 || x > 4 || y > 4) continue;
				if(map[x].charAt(y) == '*' && !visited[x][y]) {
					que.add(new Node(x,y));
					visited[x][y] = true;
				}
			}
		}
		
		// check count
		for(int i=0; i<5; i++)
			for(int j=0; j<5; j++)
				if(visited[i][j]) count--;
		if(count == 0) return true;
		return false;
	}
	
	public static double[] get_mid_point(String[] map) {
		double[] mid = new double[2];
		int count = 0;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(map[i].charAt(j) == '*') {
					mid[0] += i;
					mid[1] += j;
					count++;
				}
			}
		}
		mid[0] /= count;
		mid[1] /= count;
		return mid;
	}
	
	public static void print(String[] map) {
		for(int i=0; i<5; i++) {
			System.out.println(map[i]);
		}System.out.println();
	}
}
class Node{
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

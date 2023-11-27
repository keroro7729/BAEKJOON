// https://www.acmicpc.net/problem/1035
import java.util.*;
import java.io.*;
/**
 * 브루트 포스로 모든 가능한 판의 경우를 탐색하는 방식으로 구현
 * 이동 횟수를 구할때는 백트래킹 방식의 dfs로 돌의 이동수를 구하고
 * 연결되어 있는지를 판단 할때는 그래프 완전탐색으로 모든 돌을 방문했는지 리턴한다.
 * 현재 판의 상태를 기준으로 다음 상태를 리턴하는 next_cur함수를 통해 
 * 순차적으로 모든경우를 탐색 할 수 있도록 구현하였다.
 */
public class Main {
	// 모든 가능한 map 에대해
	// is_connected를 만족하면 횟수를 구하고
	// 횟수 최소값을 저장해 출력한다.
	public static void main(String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		// input
		List<Coordinate> init = new ArrayList<>();
		for(int i=0; i<5; i++) {
			String line = scan.readLine();
			for(int j=0; j<5; j++)
				if(line.charAt(j) == '*')
					init.add(new Coordinate(i, j));
		}
		
		// solution
		int min_ans = Integer.MAX_VALUE;
		List<Coordinate> cur = new ArrayList<>();
		for(int i=0; i<init.size(); i++)
			cur.add(new Coordinate(0, i));
		while(cur != null) {
			//System.out.println(cur);
			int ans;
			if((ans=get_count(cur, init)) < min_ans && connected(cur))
				min_ans = ans;
			if(!next_cur(cur)) break;
		}
		System.out.println(min_ans);
	}
	
	// init에서 cur로 변화하는데 필요한 최소 이동 수를 리턴
	// dfs 백트래킹으로 구현
	static int answer, n;
	static boolean[] visited;
	static int[][] count;
	public static int get_count(List<Coordinate> cur, List<Coordinate> init) {
		n = cur.size();
		count = new int[n][n];
		visited = new boolean[n];
		answer = Integer.MAX_VALUE;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				count[i][j] += Math.abs(cur.get(i).x-init.get(j).x);
				count[i][j] += Math.abs(cur.get(i).y-init.get(j).y);
			}
		}
		
		dfs(0, 0);
		return answer;
	}
	public static void dfs(int c, int val) {
		if(val >= answer) return;
		if(c == n) {
			answer = Math.min(answer, val);
			//System.out.println(answer);
			return;
		}
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(c+1, val + count[c][i]);
				visited[i] = false;
			}
		}
	}
	
	// 모든 좌표가 서로 연결되어있는지 리턴
	// bfs로 인접한 조각을 방문하며 모든 조각을 방문했는지 리턴
	public static boolean connected(List<Coordinate> cur) {
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		Queue<Coordinate> que = new LinkedList<>();
		boolean[] visited = new boolean[25];
		que.add(cur.get(0));
		visited[cur.get(0).toIndex()] = true;
		int count = 1;
		while(!que.isEmpty()) {
			Coordinate c = que.poll();
			for(int i=0; i<4; i++) {
				int x = c.x+dx[i], y = c.y+dy[i];
				if(x < 0 || y < 0 || x > 4 || y > 4) continue;
				if(!visited[x*5+y] && check_list(cur, x, y)) {
					que.add(new Coordinate(x, y));
					visited[x*5+y] = true;
					count++;
				}
			}
		}
		if(cur.size() == count) return true;
		return false;
	}
	public static boolean check_list(List<Coordinate> list, int x, int y) {
		for(Coordinate c : list) {
			if(c.x == x && c.y == y) return true;
		}
		return false;
	}
	
	// cur이 주어졌을 때 다음 탐색할 cur로 수정
	public static boolean next_cur(List<Coordinate> cur){
		int last_idx = 4;
		for(int i=cur.size()-1; i>=0; i--) {
			if(cur.get(i).x == 4 && cur.get(i).y == last_idx--) continue;
			Coordinate o = cur.get(i);
			for(int j=i; j<cur.size(); j++) {
				o = add_one(o);
				cur.set(j, o);
			}
			return true;
		}
		return false;
	}
	public static Coordinate add_one(Coordinate c) {
		Coordinate n = new Coordinate(c.x, c.y);
		if(n.y != 4) {
			n.y += 1;
			return n;
		}
		if(n.x == 4) return null;
		n.x += 1;
		n.y = 0;
		return n;
	}
}
class Coordinate{
	int x, y;
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int toIndex() {
		return x*5+y;
	}
	@Override
	public String toString() {
		return "["+x+","+y+"]";
	}
}

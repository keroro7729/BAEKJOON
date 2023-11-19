import java.util.*;
import java.io.*;

public class 램프 {
	// 어차피 모든줄에 대해 같이 적용된다.
	// 한줄을 켜려면 꺼져있는 열을 switch해야한다.
	// k와 꺼져있는 부분의 갯수로 그 줄을 켤 수 있는 지 없는 지 알 수 있다.
	// !!특정 열을 켰을 때 켜지는 행은 똑같은 초기조건을 가진 행이다!!
	public static void main(String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(scan.readLine());
		
		// input
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String[] map = new String[n];
		for(int i=0; i<n; i++)
			map[i] = scan.readLine();
		int k = Integer.parseInt(scan.readLine());
		
		// solution
		int max = 0;
		boolean[] visited = new boolean[n];
		for(int i=0; i<n; i++) {
			if(visited[i]) continue;
			// 해당 행을 켜는데 필요한 swiching 수
			int count = 0;
			for(int j=0; j<m; j++)
				if(map[i].charAt(j) == '0') count++;
			
			// k회 스위치를 작동시킬 때 count를 만족 할 수 있는가?
			if(count <= k && count%2 == k%2) {
				// 해당 행과 초기조건이 같은 행 수 == 예비 답
				int answer = 1;
				for(int ii = i+1; ii<n; ii++)
					if(map[i].equals(map[ii])) {
						answer++;
						visited[ii] = true;
					}
				
				// 예비 답 중 최대값을 저장
				max = Math.max(max, answer);
			}
		}
		
		// output
		System.out.println(max);
	}
}

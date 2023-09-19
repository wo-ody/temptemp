import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_23971_ZOAC {
	static int H,W,N,M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		System.out.println(((W + M) / (M + 1)) * ((H + N) / (N + 1)));
		
		//System.out.println(BFS());
	}
	
	static int BFS() {
		int[] dx = {N+1,0};
		int[] dy = {0,M+1};
		Queue<Node> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[H][W];
		int result = 0;
	
		q.add(new Node(0,0));
		visited[0][0] = true;

		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			result++;
			for (int k = 0; k < 2; k++) {
				int nx = cur.x + dx[k];
				int ny = cur.y + dy[k];
				if(isCango(nx, ny) == false || visited[nx][ny]) continue;
				
				q.add(new Node(nx,ny));
				visited[nx][ny] = true;
			}
			
		}
		
		return result;
	}
	
	static boolean isCango(int x, int y) {
		if(x < 0 || x >= H || y < 0 || y >= W) return false;
		return true;
	}
	
	static class Node {
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

}

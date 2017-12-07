import java.util.*

public class NetworkConnect {
	static int n;
	static int[][] G;
	
	public static int[] BFS(int src, int[][] Gw){
		int[] q = new int[n], p = new int[n];
		int head = 0, tail = 1;
		q[0] = src;
		p[src] = -1;
		int mac;
		while(head != tail){
			mac = q[head];
			head++;
			int i = 1;
			while(true){
				int nbr = G[mac][i];
				if(nbr < 0)
					break;
				if(Gw[mac][nbr] >= 0 && p[nbr] != 0){
					p[nbr] = mac;
					q[tail] = nbr;
					tail++;
				}
				i++;
			}
		}
		return p;
	}
	
	
	public static int bottleneck(int[] p, int[][] Gw, int s, int t){
		double min = Double.POSITIVE_INFINITY;
		while(p[t] > 0){
			int u = p[t];
			min = (Gw[u][t] < min) ? Gw[u][t] : min;
			t = u;
		}
		return int(min);
	}
	
	public static void augment(int[] path, int[][] Gw, int s, int t){
		int b = bottleneck(path, Gw, s, t);
		while(t != s){
			int u = p[t];
			Gw[u][t] -= b;
			Gw[t][u] += b;
			t = u;
		}
	}
			
	public static void EDKP(int s, int t, int[][] Gw){
		int[] path = BFS(s, Gw);
		while(path[t] != 0){
			augment(path, Gw, s, t);
			path = BFS(s, Gw);
		}
	}
	
	public void printG(Gw){
		for(int i=1; i < n+1; i++){
			int j = 1;
			int v = G[i][j];
			System.out.print(i + ": ");
			while(v != 0){
				System.out.println("--" + Gw[i][v] + "--");
				System.out.println("--" + Gw[v][i] + "--");
				j++;
				v = G[i][j];
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = sc.nextInt();
		G = new int[n+1][m+1];
		int[][] Gw = new int[n+1][m+1];
		int s = sc.nextInt();
		int t = sc.nextInt();
		int u, v, w;
		for(int i = 0; i < m; i++){
			u = sc.nextInt();
			v = sc.nextInt();
			w = sc.nextInt();
			G[u][0] +=1;
			int c = G[u][0];
			G[u][c] = v;
			Gw[u][v] = w;
		}
		EDKP(s, t, Gw)
		printG(Gw);
	}
}
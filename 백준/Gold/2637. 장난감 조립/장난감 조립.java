import java.util.*;

class Part{
	int needParts;
	int weight;
	
	public Part(int needParts , int weight) {
		this.needParts = needParts;
		this.weight = weight;
	}
	
}

public class Main {
	static int toy;
	static int edge;
	static ArrayList<Part>[] list;
	static int[] result;
	static int[] temp;
	static int[] check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		toy = sc.nextInt();
		edge = sc.nextInt();
		
		list = new ArrayList[toy+1];
		result = new int[toy+1];	//결과 값을 저장할 배열
		temp = new int[toy+1];		//진입되는 노드들의 count 저장 배열
		check = new int[toy+1];		//기본 장난감을 검사하는 배열
		
		for(int i=0;i<toy+1 ;i++) {
			list[i] = new ArrayList();
		}
		
		for(int i=0;i<edge;i++) {
			int part = sc.nextInt();
			int needParts = sc.nextInt();
			int weight = sc.nextInt();
			
			list[part].add(new Part(needParts, weight));
			temp[needParts]++;
			check[part]++;
		}
		
		Queue<Integer> q = new ArrayDeque();
		q.offer(toy);
		result[toy]=1;

		//위상정렬 코드
		while(!q.isEmpty()) {
			int p = q.poll();
			
			for(Part next : list[p]) {
				int needParts = next.needParts;
				
				temp[needParts]--;
				if(temp[needParts]==0) {
					q.offer(needParts);
				}
				result[needParts] += result[p] * next.weight;
				
			}
		}
		StringBuilder sb = new StringBuilder();
		
		for(int i=1;i<toy+1;i++) {
			if(check[i]==0) {
				sb.append(i);
				sb.append(" ");
				sb.append(result[i]);
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

}

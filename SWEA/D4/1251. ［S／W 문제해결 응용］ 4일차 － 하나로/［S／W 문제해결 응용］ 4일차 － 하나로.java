import java.util.*;

class Ver implements Comparable<Ver> {
    int edge;
    double weight;

    Ver(int edge, double weight) {
        this.edge = edge;
        this.weight = weight;
    }

    @Override
    public int compareTo(Ver o) {
        return Double.compare(this.weight, o.weight);
    }
}

public class Solution{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int n = sc.nextInt();

            int[] xinput = new int[n];
            int[] yinput = new int[n];
            for (int i = 0; i < n; i++) {
                xinput[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                yinput[i] = sc.nextInt();
            }

            double e = sc.nextDouble();

            // 인접 리스트 생성
            List<List<Ver>> adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }

            // 간선 추가 (두 섬 간의 거리 계산)
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    double distance = Math.pow(xinput[i] - xinput[j], 2) +
                                      Math.pow(yinput[i] - yinput[j], 2);
                    double cost = e * distance;

                    adjList.get(i).add(new Ver(j, cost));
                    adjList.get(j).add(new Ver(i, cost));
                }
            }

            // 프림 알고리즘 시작
            double[] dist = new double[n];
            boolean[] visited = new boolean[n];
            Arrays.fill(dist, Double.MAX_VALUE);
            dist[0] = 0;

            PriorityQueue<Ver> pq = new PriorityQueue<>();
            pq.offer(new Ver(0, 0));

            double result = 0;

            while (!pq.isEmpty()) {
                Ver current = pq.poll();

                if (visited[current.edge]) {
                    continue;
                }

                visited[current.edge] = true;
                result += current.weight;

                // 인접한 노드 검사
                for (Ver neighbor : adjList.get(current.edge)) {
                    if (!visited[neighbor.edge] && neighbor.weight < dist[neighbor.edge]) {
                        dist[neighbor.edge] = neighbor.weight;
                        pq.offer(new Ver(neighbor.edge, neighbor.weight));
                    }
                }
            }

            System.out.println("#" + tc + " " + Math.round(result));
        }

    }
}
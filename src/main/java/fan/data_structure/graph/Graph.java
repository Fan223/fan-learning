package fan.data_structure.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图
 *
 * @author Fan
 * @since 2023/6/8 10:32
 */
public class Graph {

    /**
     * 用于深度优先搜素找到终止顶点后, 不再递归
     */
    boolean found = false;

    /**
     * 顶点的个数
     */
    private final int vertex;

    /**
     * 邻接表
     */
    private final LinkedList<Integer>[] adjacencyList;

    public Graph(int vertex) {
        this.vertex = vertex;
        adjacencyList = new LinkedList[vertex];

        for (int i = 0; i < vertex; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void queryAdjacency() {
        for (int i = 0; i < adjacencyList.length - 1; i++) {
            System.out.print(i + "-" + adjacencyList[i] + ", ");
        }
        System.out.println(adjacencyList.length - 1 + "-" + adjacencyList[adjacencyList.length - 1]);
    }

    /**
     * 无向图一条边存两次
     *
     * @param start 起始顶点
     * @param termination 终止顶点
     * @author Fan
     * @since 2023/6/8 10:38
     */
    public void addEdge(int start, int termination) {
        adjacencyList[start].add(termination);
        adjacencyList[termination].add(start);
    }

    /**
     * 广度优先搜索, 有点类似树的层次遍历, 一层一层向外搜索
     *
     * @param start       起始顶点
     * @param termination 终止顶点
     * @author Fan
     * @since 2023/6/8 10:50
     */
    public void bfs(int start, int termination) {
        if (start == termination) {
            return;
        }

        // 记录已经访问过的顶点
        boolean[] visited = new boolean[vertex];
        // 先将起始顶点记录为已访问
        visited[start] = true;

        // 存储已经被访问, 但相连的顶点还没有被访问的顶点
        Queue<Integer> queue = new LinkedList<>();
        // 这里的操作有点类似于树的层次遍历, 即先将起始顶点入队, 出队时将其相连顶点再入队, 然后依次入队和出队, 这样就一层层完成了遍历
        queue.add(start);

        // 记录搜索路径, 这个路径是反向存储的, 即存储的是指向前驱顶点的值
        int[] prev = new int[vertex];
        // 初始值先全设为 -1
        for (int i = 0; i < vertex; ++i) {
            prev[i] = -1;
        }

        while (!queue.isEmpty()) {
            // 出队
            int v = queue.poll();

            // 依次遍历它的相连顶点
            for (int i = 0; i < adjacencyList[v].size(); ++i) {
                int qv = adjacencyList[v].get(i);

                // 如果该顶点已经访问过, 则跳过
                if (!visited[qv]) {
                    // 记录搜索路径
                    prev[qv] = v;

                    // 如果该顶点是终点, 则表示已搜索到, 退出并打印搜索路径
                    if (qv == termination) {
                        print(prev, start, termination);
                        return;
                    }

                    // 记录访问过的顶点
                    visited[qv] = true;
                    // 入队
                    queue.add(qv);
                }
            }
        }
    }

    /**
     * 打印起始顶点到终止顶点的搜索路径, 因为存储搜索路径的数组是反向存储的, 即存储的是指向前驱顶点的值, 因此需要从终点开始递归到起始顶点, 然后打印
     *
     * @param prev        记录搜索路径的数组
     * @param start       起始顶点
     * @param termination 终止顶点
     * @author Fan
     * @since 2023/6/8 11:05
     */
    private void print(int[] prev, int start, int termination) {
        if (prev[termination] != -1 && termination != start) {
            print(prev, start, prev[termination]);
        }
        System.out.print(termination + " ");
    }

    /**
     * 深度优先搜索
     *
     * @param start       起始顶点
     * @param termination 终止顶点
     * @author Fan
     * @since 2023/6/8 16:19
     */
    public void dfs(int start, int termination) {
        found = false;

        // 这两个变量的作用与 BFS 中同
        boolean[] visited = new boolean[vertex];
        int[] prev = new int[vertex];
        for (int i = 0; i < vertex; ++i) {
            prev[i] = -1;
        }

        recurDfs(start, termination, visited, prev);
        print(prev, start, termination);
    }

    private void recurDfs(int start, int termination, boolean[] visited, int[] prev) {
        // 已经找到终止顶点, 不再递归
        if (found) {
            return;
        }

        // 将开始顶点设为 true
        visited[start] = true;
        // 开始顶点等于终止顶点, 则结束递归
        if (start == termination) {
            found = true;
            return;
        }

        // 遍历开始顶点的相连顶点
        for (int i = 0; i < adjacencyList[start].size(); ++i) {
            int qv = adjacencyList[start].get(i);

            // 如果该顶点已经访问过, 则跳过
            if (!visited[qv]) {
                prev[qv] = start;
                recurDfs(qv, termination, visited, prev);
            }
        }
    }
}

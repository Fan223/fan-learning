package fan.data_structure.graph;

/**
 * 无向图测试类
 *
 * @author Fan
 * @since 2023/6/8 11:14
 */
public class GraphTest {
    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(2, 5);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.queryAdjacency();

        System.out.print("广度优先搜索: ");
        graph.bfs(0, 7);
        System.out.println();

        System.out.print("深度优先搜索: ");
        graph.dfs(0, 7);
    }
}

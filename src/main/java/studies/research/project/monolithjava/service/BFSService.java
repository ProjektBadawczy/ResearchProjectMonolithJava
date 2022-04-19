package studies.research.project.monolithjava.service;

import org.springframework.stereotype.Service;
import studies.research.project.monolithjava.model.BFSResult;
import studies.research.project.monolithjava.model.Graph;

import java.util.LinkedList;

@Service
public class BFSService {

    public BFSResult bfs(Graph graph, int source, int t) {

        int numberOfVertices = graph.numberOfVertices();
        int[] parent = new int[numberOfVertices];
        boolean[] visited = new boolean[numberOfVertices];
        for(int i=0; i<numberOfVertices; ++i)
            visited[i]=false;

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source]=-1;
        // Standard BFS Loop
        while (queue.size()!=0)
        {
            int u = queue.poll();
            for (int v=0; v<numberOfVertices; v++)
            {
                if (!visited[v] && graph.adjacencyMatrix()[u][v] > 0)
                {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return new BFSResult(parent, visited[t]);
    }
}

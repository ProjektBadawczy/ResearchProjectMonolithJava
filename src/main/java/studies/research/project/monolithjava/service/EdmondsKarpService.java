package studies.research.project.monolithjava.service;

import org.springframework.stereotype.Service;
import studies.research.project.monolithjava.model.BFSResult;
import studies.research.project.monolithjava.model.Graph;

import static java.lang.Math.min;

@Service
public class EdmondsKarpService {

    private final BFSService bfsService;

    public EdmondsKarpService(BFSService bfsService) {
        this.bfsService = bfsService;
    }

    public int calculateMaxFlow(Graph graph, int source, int destination) {
        if (!areSourceAndGraphParametersValid(graph, source, destination)) {
            throw new IllegalArgumentException("Invalid source or destination parameter!\n Number of vertices: %s\n Source: %s\n Destination: %s\n".formatted(graph.numberOfVertices(), source, destination));
        }

        int u, v;
        Graph residualGraph = graph.clone();
        int max_flow = 0;
        BFSResult bfsResult = bfsService.bfs(residualGraph, source, destination);
        while (bfsResult.success()) {
            int path_flow = Integer.MAX_VALUE;
            for (v = destination; v != source; v = bfsResult.parents()[v]) {
                u = bfsResult.parents()[v];
                path_flow = min(path_flow, residualGraph.adjacencyMatrix()[u][v]);
            }

            for (v = destination; v != source; v = bfsResult.parents()[v]) {
                u = bfsResult.parents()[v];
                residualGraph.adjacencyMatrix()[u][v] -= path_flow;
                residualGraph.adjacencyMatrix()[v][u] += path_flow;
            }

            max_flow += path_flow;
            bfsResult = bfsService.bfs(residualGraph, source, destination);
        }

        return max_flow;
    }

    private boolean areSourceAndGraphParametersValid(Graph graph, int source, int destination) {
        return source >= 0 && source < graph.numberOfVertices() && destination >= 0 && destination < graph.numberOfVertices();
    }
}

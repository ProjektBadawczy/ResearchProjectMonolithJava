package studies.research.project.monolithjava.service;

import org.springframework.stereotype.Service;
import studies.research.project.monolithjava.model.DirectedGraph;
import studies.research.project.monolithjava.model.Graph;
import studies.research.project.monolithjava.repository.GraphRepository;

@Service
public class GraphService {

    private final GraphRepository graphRepository;

    public GraphService(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }

    public Graph getGraph(int id) {
        return graphRepository.getGraph(id);
    }

    public DirectedGraph getDirectedGraph(int id) {
        return graphRepository.getDirectedGraph(id);
    }
}

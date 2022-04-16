package studies.research.project.monolithjava.controller;


import io.vavr.control.Try;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import studies.research.project.monolithjava.model.Graph;
import studies.research.project.monolithjava.service.GraphService;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/graphs/{id}")
    public ResponseEntity<Graph> getGraph(@PathVariable("id") String id) {
        return Try.of(() -> Integer.parseInt(id))
                .map(graphService::getGraph)
                .map(graph -> new ResponseEntity<>(graph, OK))
                .getOrElseGet(e -> new ResponseEntity<>(NOT_FOUND));
    }
}

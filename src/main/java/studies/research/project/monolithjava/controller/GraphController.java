package studies.research.project.monolithjava.controller;


import io.vavr.control.Try;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import studies.research.project.monolithjava.model.DirectedGraph;
import studies.research.project.monolithjava.model.Graph;
import studies.research.project.monolithjava.service.EdmondsKarpService;
import studies.research.project.monolithjava.service.GraphService;
import studies.research.project.monolithjava.service.PushRelabelService;

import javax.websocket.server.PathParam;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class GraphController {

    private final GraphService graphService;
    private final EdmondsKarpService edmondsKarpService;
    private final PushRelabelService pushRelabelService;

    public GraphController(GraphService graphService, EdmondsKarpService edmondsKarpService, PushRelabelService pushRelabelService) {
        this.graphService = graphService;
        this.edmondsKarpService = edmondsKarpService;
        this.pushRelabelService = pushRelabelService;
    }

    @GetMapping("/graph")
    public ResponseEntity<Graph> getGraph(@RequestParam("id") String id) {
        return new ResponseEntity<>(NOT_FOUND);
        return Try.of(() -> Integer.parseInt(id))
                .map(graphService::getGraph)
                .map(graph -> new ResponseEntity<>(graph, OK))
                .onFailure(System.err::println)
                .getOrElseGet(e -> new ResponseEntity<>(NOT_FOUND));
    }

    @GetMapping("/directedGraph")
    public ResponseEntity<DirectedGraph> getDirectedGraph(@RequestParam("id") String id) {
        return Try.of(() -> Integer.parseInt(id))
                .map(graphService::getDirectedGraph)
                .map(graph -> new ResponseEntity<>(graph, OK))
                .onFailure(System.err::println)
                .getOrElseGet(e -> new ResponseEntity<>(NOT_FOUND));
    }

    @GetMapping("/edmondsKarpMaxGraphFlow")
    public ResponseEntity<Integer> getEdmondsKarpMaxGraphFlow(@RequestParam("id") String id, @RequestParam String source, @RequestParam String destination) {
        return Try.of(() -> Integer.parseInt(id))
                .map(graphService::getGraph)
                .map(graph -> {
                    int s = Integer.parseInt(source);
                    int d = Integer.parseInt(destination);
                    return new ResponseEntity<>(edmondsKarpService.calculateMaxFlow(graph, s, d), OK);
                })
                .onFailure(System.err::println)
                .getOrElseGet(e -> new ResponseEntity<>(BAD_REQUEST));
    }

    @GetMapping("/pushRelabelMaxGraphFlow")
    public ResponseEntity<Integer> getPushRelabelMaxGraphFlow(@RequestParam("id") String id, @RequestParam String source, @RequestParam String destination) {
        return Try.of(() -> Integer.parseInt(id))
                .map(graphService::getDirectedGraph)
                .map(graph -> {
                    int s = Integer.parseInt(source);
                    int d = Integer.parseInt(destination);
                    return new ResponseEntity<>(pushRelabelService.calculateMaxFlow(graph, s, d), OK);
                })
                .onFailure(System.err::println)
                .getOrElseGet(e -> new ResponseEntity<>(BAD_REQUEST));
    }
}

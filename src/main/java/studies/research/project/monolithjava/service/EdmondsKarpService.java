package studies.research.project.monolithjava.service;

import org.springframework.stereotype.Service;

@Service
public class EdmondsKarpService {

    private final BFSService bfsService;

    public EdmondsKarpService(BFSService bfsService) {
        this.bfsService = bfsService;
    }
}

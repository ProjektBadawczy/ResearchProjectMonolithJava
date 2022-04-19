package studies.research.project.monolithjava.model;


public record Graph(
        int id,
        int numberOfVertices,
        int[][] adjacencyMatrix
) {

    @Override
    public Graph clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        int [][] newAdjacencyMatrix = new int[this.adjacencyMatrix.length][];
        for(int i = 0; i < this.adjacencyMatrix.length; i++)
            newAdjacencyMatrix[i] = this.adjacencyMatrix[i].clone();
        return new Graph(this.id, this.numberOfVertices, newAdjacencyMatrix);
    }
}

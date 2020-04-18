package sample;

public class Logic {
    int width;
    int height;
    int[][] board;

    public Logic(int width, int height){
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public void makeBoard(){
        System.out.println("---");
        for (int y = 0; y < height; y++) {
            String line = "|";
            for (int x = 0; x < width; x++) {
                if (this.board[x][y] == 0){
                    line+= ".";
                }else{
                    line+="*";
                }
            }
            line +="|";
            System.out.println(line);
        }
        System.out.println("---\n");
    }
    public void Alive(int x, int y){
        this.board[x][y] = 1;
    }

    public void Dead(int x, int y){
        this.board[x][y] = 0;
    }

    public int aliveNeighbours(int x, int y){
        int count = 0;

        count += getState(x -1, y -1);
        count += getState(x, y -1);
        count += getState(x +1, y -1);

        count += getState(x - 1, y);
        count += getState(x + 1, y);

        count += getState(x - 1, y + 1);
        count += getState(x, y +1);
        count += getState(x +1, y +1);

        return count;
    }

    public int getState(int x, int y){
        if (x < 0 || x >= width){
        return 0;
    }
        if (y < 0 || y>= height){
            return 0;
        }
        return this.board[x][y];

}

public void runSim(){
        int[][] newBoard = new int[width][height];

    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            int aliveNeighbours = aliveNeighbours(x,y);

            if (getState(x,y) == 1){
                if (aliveNeighbours < 2){
                    newBoard[x][y] = 0;
                } else if (aliveNeighbours == 2 || aliveNeighbours == 3){
                    newBoard[x][y] = 1;
                }else if (aliveNeighbours > 3){
                    newBoard[x][y] = 0;
                }
            }
            else {
                if (aliveNeighbours == 3){
                    newBoard[x][y] = 1;
                }
            }
        }
    }
    this.board = newBoard;
}

    public static void main(String[] args) {
        Logic logic = new Logic(8,5);

        logic.Alive(2,2);
        logic.Alive(3,2);
        logic.Alive(4,2);

        logic.makeBoard();

        logic.runSim();

        logic.makeBoard();

        logic.runSim();

        logic.makeBoard();

        logic.runSim();

    }


}

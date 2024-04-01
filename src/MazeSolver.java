/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */
// Teddy Meeks
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        ArrayList<MazeCell> path = new ArrayList<MazeCell>();
        Stack<MazeCell> solution = new Stack<MazeCell>();
        solution.push(maze.getEndCell());
        while (solution.peek() != maze.getStartCell())
        {
            solution.push(solution.peek().getParent());
        }
        while (!solution.isEmpty())
        {
            path.add(solution.pop());
        }
        return path;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> stack = new Stack<MazeCell>();
        stack.push(maze.getStartCell());
        maze.getStartCell().setExplored(true);

        while (stack.peek() != maze.getEndCell())
        {
            System.out.println(stack.peek());

            MazeCell orig = stack.peek();
            int col = orig.getCol();
            int row = orig.getRow();
            stack.pop();

            // North
            if (maze.isValidCell(row - 1, col))
            {
                maze.getCell(row - 1, col).setExplored(true);
                maze.getCell(row - 1, col).setParent(orig);
                stack.push(maze.getCell(row - 1, col));
            }

            // East
            if (maze.isValidCell(row, col + 1))
            {
                maze.getCell(row, col + 1).setExplored(true);
                maze.getCell(row, col + 1).setParent(orig);
                stack.push(maze.getCell(row, col + 1));
            }

            // South
            if (maze.isValidCell(row + 1, col))
            {
                maze.getCell(row + 1, col).setExplored(true);
                maze.getCell(row + 1, col).setParent(orig);
                stack.push(maze.getCell(row + 1, col));
            }

            // West
            if (maze.isValidCell(row, col - 1))
            {
                maze.getCell(row, col - 1).setExplored(true);
                maze.getCell(row, col - 1).setParent(orig);
                stack.push(maze.getCell(row, col - 1));
            }


        }
        // Returns solution
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> queue = new LinkedList<MazeCell>();
        queue.add(maze.getStartCell());
        queue.peek().setExplored(true);

        while (queue.peek() != maze.getEndCell())
        {

            MazeCell temp = queue.peek();
            int col = temp.getCol();
            int row = temp.getRow();

            queue.remove();

            // North
            if (maze.isValidCell(row - 1, col))
            {
                maze.getCell(row - 1, col).setExplored(true);
                maze.getCell(row - 1, col).setParent(temp);
                queue.add(maze.getCell(row - 1, col));
            }

            // East
            if (maze.isValidCell(row, col + 1))
            {
                maze.getCell(row, col + 1).setExplored(true);
                maze.getCell(row, col + 1).setParent(temp);
                queue.add(maze.getCell(row, col + 1));
            }

            // South
            if (maze.isValidCell(row + 1, col))
            {
                maze.getCell(row + 1, col).setExplored(true);
                maze.getCell(row + 1, col).setParent(temp);
                queue.add(maze.getCell(row + 1, col));
            }

            // West
            if (maze.isValidCell(row, col - 1))
            {
                maze.getCell(row, col - 1).setExplored(true);
                maze.getCell(row, col - 1).setParent(temp);
                queue.add(maze.getCell(row, col - 1));
            }

        }
        // Returns solution
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}

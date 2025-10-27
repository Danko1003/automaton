import java.util.Arrays;

/**
 * Model a 1D elementary cellular automaton.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 */
public class Automaton
{
    // The number of cells.
    private final int numberOfCells;
    // The state of the cells.
    private int[] state;
    
    
    
    /**
     * Create a 1D automaton consisting of the given number of cells.
     * @param numberOfCells The number of cells in the automaton.
     */
    public Automaton(int numberOfCells)
    {
        this.numberOfCells = numberOfCells;
        state = new int[numberOfCells + 1];
        
        // Seed the automaton with a single 'on' cell in the middle.
        state[numberOfCells / 2] = 1;
    }
    
    /**
     * Print the current state of the automaton.
     */
    public void print() {
        for (int i = 0; i < numberOfCells; i++) {
            System.out.print(state[i] == 1 ? "*" : " ");
        }
        System.out.println();
    }
      
    
    /**
     * Update the automaton to its next state.
     */
    public void update() {
        int[] nextState = new int[state.length]; // still includes dummy at the end
        int left = 0;
        int center = state[0];
    
        for (int i = 0; i < numberOfCells; i++) { // only iterate over real cells
            int right = state[i + 1]; // no need to check bounds now
            nextState[i] = calculateNextState(left, center, right);
    
            left = center;
            center = right;
        }
    
        // Copy only the real cells back, keep the dummy 0 at the end
        for (int i = 0; i < numberOfCells; i++) {
            state[i] = nextState[i];
        }
        state[numberOfCells] = 0; // ensure the dummy remains 0
    }

    private int calculateNextState(int left, int center, int right) {
        return (center + right + center * right + left * center * right) % 2;
    }
    
    /**
     * Reset the automaton.
     */
    public void reset()
    {
        Arrays.fill(state, 0);
        
        int mid = numberOfCells / 2;
        state[mid - 1] = 1;
        state[mid] = 1;
        state[mid + 1] = 1;
    }
}

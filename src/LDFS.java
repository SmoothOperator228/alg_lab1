import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

public class LDFS {
    static int iterations;
    static int states = 1;
    static int limitReachedCounter;

    public static final int LIMIT = 8;

    static class Node{
        int[] state;
        Node parent;
        Stack<Node> successors = new Stack<Node>();
        boolean visited;

        public Node(int[] state) {
            this.state = state;
        }

        public boolean isGoal(){//checks if a node is the answer
            for (int i = 0; i < state.length; i++){//checking vertical and diagonal lines for collisions
                for (int j = 0; j < state.length; j++){
                    if(i == j){
                        continue;
                    }
                    if(state[i] == state[j] || state[i] + (j - i) == state[j] || state[i] - (j - i) == state[j]){
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static Node search(int[] state) throws InterruptedException {
        Node start = new Node(state);
        Node current = start;
        int depth = 0;
        while(!current.isGoal()){
            iterations++;
//            System.out.println(Arrays.toString(current.state));
            if(depth == LIMIT){
                current = current.parent;
                depth--;
                limitReachedCounter++;
                continue;
            }
            if(!current.visited){
                for (int i = 1; i < state.length; i++) {
                    int[] nextState = new int[8];
                    System.arraycopy(current.state, 0, nextState, 0, 8);

                    nextState[depth] += i;
                    if (nextState[depth] >= 8) {
                        nextState[depth] -= 8;
                    }
                    Node next = new Node(nextState);
                    next.parent = current;
                    current.successors.push(next);
                    states++;
                }
                current.visited = true;
            }
            if(!current.successors.empty()) {
                current = current.successors.pop();
                depth++;
            }else{
                current = current.parent;
                depth--;
            }
        }
        System.out.println("Iterations: " + iterations);
        System.out.println("States: " + states);
        System.out.println("Depth limit is reached " + limitReachedCounter + " times");
        return current;
    }



}

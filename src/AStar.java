import java.util.*;

public class AStar {
    public static final int LIMIT = 8;

    static class Node{
        int[] state;
        Node parent;
        ArrayList<Node> accessors = new ArrayList<>();
        boolean visited;
        int heuristic;
        int depth;

        public Node(int[] state) {
            this.state = state;
            heuristic = calcHeuristic();
        }

        private int calcHeuristic(){
            int counter = 0;
            for (int i = 0; i < state.length; i++){//checking vertical and diagonal lines for collisions
                for (int j = 0; j < state.length; j++){
                    if(i == j){
                        continue;
                    }
                    if(state[i] == state[j] || state[i] + (j - i) == state[j] || state[i] - (j - i) == state[j]){
                        counter++;
                    }
                }
            }
            return counter/2;
        }
        }

        public static Node search(int[] state){
        Node start = new Node(state);
        Node current = start;
        start.depth = 0;
        PriorityQueue<Node> open = new PriorityQueue<>((a, b) -> a.heuristic - b.heuristic);
        HashSet<Node> closed = new HashSet<Node>();
        int iterations = 0;
        int states = 0;
        while(current.heuristic!=0){
            iterations++;
//            System.out.println(Arrays.toString(current.state));
//            System.out.println(current.heuristic);
            for (int i = 1; i < state.length; i++) {
                    int[] nextState = new int[8];
                    System.arraycopy(current.state, 0, nextState, 0, 8);
                    if(current.depth >= 8){
                        current.depth -= 8;
                    }
                    nextState[current.depth] += i;
                    if (nextState[current.depth] >= 8) {
                        nextState[current.depth] -= 8;
                    }
                    Node next = new Node(nextState);
                    next.depth = current.depth + 1;
                    next.parent = current;
                    current.accessors.add(next);
                    closed.add(current);
                    states++;
            }
            open.addAll(current.accessors);
            Node next;
            while(true){
                next = open.poll();
                if(closed.contains(next)){
                    System.out.println("стоять!");
                    continue;
                }
                else{
                    break;
                }
            }
            current = next;

        }
            System.out.println("Iterations: " + iterations);
            System.out.println("States: " + states);
        return current;

    }
    private static Node findMinHeuristic (ArrayList<Node> list){
        Node needed = null;
        int min = Integer.MAX_VALUE;
        for (Node n : list){
            if (n.heuristic <= min){
                min = n.heuristic;
                needed = n;
            }
        }
        return needed;
    }

}

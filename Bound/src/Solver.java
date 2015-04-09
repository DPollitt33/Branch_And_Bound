import java.util.LinkedList;
public class Solver {
	private Node[] nodes;
	private Node root;
	private Node currentNode;
	private LinkedList<Edge> tour;
	private double bestSoFar;
	private String bestTourSoFar;
	private int nodesTraversed;
	
	public Solver(Node[] n){
		nodes = n;
		root = nodes[0];
		bestSoFar = Double.MAX_VALUE;
		bestTourSoFar = "";
		nodesTraversed = 0;
		//Exit edge
		root.giveEdge(new Edge(0, 0));
		tour = new LinkedList<Edge>();
		
		//Find starting bound
		root.setBound(calcBound());
		currentNode = root;
	}
	
	public void solve(){
		//Start timer
		long startTime = System.nanoTime();
		//OR statements allows repetition through final branch root
		while(root.hasNext()){
			Edge currEdge;
			//No more edges to try
			if(!currentNode.hasNext()){
				//If tour size is 1 less than number of edges then leaf
				if(tour.size() == nodes.length - 1){
					//Connect final city to root
					currentNode.setOut(currentNode.getEdge(root.getID()));
					root.setIn(root.getEdge(currentNode.getID()));
					
					//Final bound
					double result = calcBound();
					//Check against best so far
					if(result < bestSoFar){
						bestSoFar = result;
						bestTourSoFar = tour.toString().substring(1, tour.toString().length() - 1);
					}
					//Fix in outs
					backtrack(currentNode, root);
				}
				//Full backtrack
				//Never backtrack from the root
				if(!currentNode.equals(root)){
					Node prevNode = nodes[currentNode.getIn().getCity() - 1];
					backtrack(prevNode, currentNode);
					tour.removeLast();
					currentNode.reset();
					currentNode = prevNode;
				}
			}
			
			//Node still has edges to try
			if(currentNode.hasNext()){
				currEdge = currentNode.getNext();
				if(currEdge.isFeasible(currentNode, root, tour)){
					//Add to tour and set to out
					tour.add(currEdge);
					currentNode.setOut(currEdge);
					Node nextNode = nodes[currEdge.getCity() - 1];

					/* If next Out is the same length as the current edge
				   and is directed the opposite direction, then swap */
					if(nextNode.getOut().compare(currEdge) == 0 &&
							nextNode.getOut().getCity() == currentNode.getID()){
						nextNode.swap();
					}
					//INVARIANT: Next Out is not the target of change 
					/* If next In is not the same length as the current edge
				   and is shorter than next Out, swap and change In*/
					if(nextNode.getIn().compare(currEdge) != 0 &&
							nextNode.getIn().compare(nextNode.getOut()) == -1){
						nextNode.swap();
					}
					//Set In to edge from current node
					nextNode.setIn(nextNode.getEdge(currentNode.getID()));

					//Update current node
					currentNode = nextNode;
					currentNode.setBound(calcBound());
					nodesTraversed++;

					//If bound isn't promising, prune
					if(currentNode.getBound() >= bestSoFar){
						//Full backtrack
						Node prevNode = nodes[currentNode.getIn().getCity() - 1];
						backtrack(prevNode, currentNode);
						tour.removeLast();
						currentNode.reset();
						currentNode = prevNode;
					}
				}
			}
		}
				
		//End timer
		long endTime = System.nanoTime();
		
		// Output
		System.out.print("TOUR: ");
		System.out.println("1, " + bestTourSoFar);
		System.out.println("COST: " + bestSoFar);
		System.out.println("NODES TRAVERSED: " + nodesTraversed);
		System.out.println("TIME: " + (endTime - startTime) + "ns");
	}
	
	/**
	 * Backtrack resets the outbound Edge of the current Node and the inbound
	 * Edge of what was the next Node. It attempts to replace them with the Node's
	 * shortest Edge, but settles for the second shortest otherwise
	 * @param current The current Node
	 * @param next The "next" Node
	 */
	private void backtrack(Node current, Node next){
		//If the shortest edge is in use, set out to the second shortest
		if(current.getIn().compare(current.getShort()) == -2)
			current.setOut(current.getSecond());
		else//Set to shortest
			current.setOut(current.getShort());
		
		//Repeat for next node, setting In instead
		if(next.getOut().compare(next.getShort()) == -2)
			next.setIn(next.getSecond());
		else//Set to shortest
			next.setIn(next.getShort());
	}
	
	/**
	 * This calculates the bound of a given Node based on the state of all Nodes in the solver.
	 * This currently runs in O(n) time, but might further be updated to utilize the bound
	 * of the current Node and simply update the changes made for the next
	 * @return A double value representing the current bound of a Node
	 */
	private double calcBound(){
		double sum = 0.0;
		for(int i =0; i < nodes.length; i++){
			sum += nodes[i].getIn().getLength()
				 + nodes[i].getOut().getLength();
		}
		return sum / 2.0;
	}
}

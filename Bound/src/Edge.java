import java.util.LinkedList;


public class Edge {
	private double length;
	private int destination;
	
	public Edge(double l, int d) {
		length = l;
		destination = d;
	}
	
	//Accessor methods
	public double getLength(){
		return length;
	}
	public int getCity(){
		return destination;
	}
	
	/**
	 * Returns an int based on how this edge compares to another
	 * @param other Another Edge
	 * @return -1 if this is less, 1 if this is greater, 0 if they are
	 * 			equally long, and -2 if they are the same edge
	 */
	public int compare(Edge other){
		//This is greater
		if(other.getLength() < length)
			return 1;
		//Equal lengths but not the same edge
		if((other.getLength() == length) && other.getCity() != destination)
			return 0;
		//This is less
		if(other.getLength() > length)
			return -1;
		
		return -2; //Edges are equal
	}
	
	/**
	 * Returns this Edge as a String for use in tour output
	 * @return This Edge's destination
	 */
	@Override
	public String toString(){
		return "" + destination;
	}
	
	/**
	 * Decides whether or not an Edge is feasible, given the current state
	 * of the solver
	 * @param curr The current Node in the solver
	 * @param root The root Node of the solver
	 * @param tour The current state of the tour in solver
	 * @return A decision on whether this Node is feasible or not
	 */
	public boolean isFeasible(Node curr, Node root, LinkedList<Edge> tour){
		//Exit edge
		if(destination == 0)
			return false;
		//Can't link to self
		if(curr.getID() == destination)
			return false;
		//Don't link to root
		if(root.getID() == destination)
			return false;
		//Don't link to already used nodes	
		for(Edge e : tour)
			if(destination == e.getCity())
				return false;
		return true;
	}
}

import java.util.ArrayList;

/**
 * The Node class doubles as both a city on a coordinate plane and a state
 * of our solver's execution
 */
public class Node {
	private int cityID;
	private double xPos;
	private double yPos;
	private double bound;
	private Edge In;
	private Edge Out;
	private Edge shortest;
	private Edge secondShortest;
	private ArrayList<Edge> edges;
	private int index;
	private boolean isRoot = false;
	
	public Node(int id, double x, double y, int d){
		cityID = id;
		xPos = x;
		yPos = y;
		edges = new ArrayList<Edge>(d);
		index = 0;
		if(cityID == 1)
			isRoot = true;
	}
		
	//Accessor methods
	public int getID() {
		return cityID;
	}
	public double getX() {
		return xPos;
	}
	public double getY() {
		return yPos;
	}
	public double getBound() {
		return bound;
	}
	public Edge getIn() {
		return In;
	}
	public Edge getOut() {
		return Out;
	}
	public Edge getShort(){
		return shortest;
	}
	public Edge getSecond(){
		return secondShortest;
	}
	public Edge getNext(){
		Edge next = edges.get(index);
		index++;
		return next;
	}
	public Edge getEdge(int city){
		for(Edge e: edges)
			if(e.getCity() == city)
				return e;
		return null;
	}
	
	//Mutator methods
	public void setIn(Edge i){
		In = i;
	}
	public void setOut(Edge o) {
		Out = o;
	}
	public void setShort(Edge s){
		shortest = s;
	}
	public void setSecond(Edge s){
		secondShortest = s;
	}
	public void setBound(double b) {
		bound = b;
	}
	public void giveEdge(Edge e) {
		edges.add(e);
	}
	public void swap() {
		Edge temp = In;
		In = Out;
		Out = temp;
	}
	
	/**
	 * Decides whether this Node has another Edge to attempt branching to
	 * @return A decision on whether or not all Edges of this Node have been used
	 */
	public boolean hasNext(){
		if(isRoot && index == edges.size())
			return false;
		if(index < edges.size()){
			return true;
		}
		return false;
	}
	
	/**
	 * Nodes are equal if their city ID's are the same
	 * @param n Another Node for comparison
	 * @return A decision on whether or not these Nodes are equal
	 */
	public boolean equals(Node n){
		return cityID == n.getID();
	}
	
	/**
	 * Resets the index of this Node to zero, for use with backtracking
	 * This allows the Node to be chosen again and fully explored after a 
	 * backtrack
	 */
	public void reset(){
		index = 0;
	}
	
}

import java.util.Scanner;
import java.io.*;

/**
 * 
 */
public class Driver
{
    private static Scanner stdIn = new Scanner(System.in);

    public static void main(String args[]) throws IOException
    {
        //get the file name form the client
        System.out.print("Enter a TSP file: ");
        String fileName = stdIn.next(); 
        Scanner fileReader = new Scanner(new File("src/" + fileName + ".tsp"));
        
        boolean executing = true;
        String currentString = "";
        int dimension = 0;
        
        //searches for the name and dimension of the problem
        //until the node cord section is reached
        while(executing)
        {
            currentString = fileReader.next();
            if(currentString.trim().equals("DIMENSION:"))
            {
                dimension = Integer.parseInt(fileReader.next());
            }
            if(currentString.trim().equals("NODE_COORD_SECTION"))
            {
                executing = false;
            }//end if
            
        }//end while
        
        
        
        Node[] nodes = new Node[dimension];
        
        //populates the list of cities
        for(int i = 0; i < dimension; i ++) {
            int cityNum = Integer.parseInt(fileReader.next());
            double x = Double.parseDouble(fileReader.next());
            double y = Double.parseDouble(fileReader.next());
            nodes[i] = new Node(cityNum, x, y, dimension - 1);
        }	
        
        //Set two lowest edges of each node
        for(int i = 0; i < dimension; i++) {
        	
        	//Edge costs
        	Edge bestSoFar = (new Edge(Double.MAX_VALUE, - 1));
        	Edge secondBest = (new Edge(Double.MAX_VALUE, - 1));
        	
        	//Find two best cost, non-zero, edges
        	for(int j = 0; j < dimension; j++) {
        		//Only do if edge is not to itself
        		if(!nodes[i].equals(nodes[j])){
					double current = calculateDistance(nodes[i], nodes[j]);

					// Give to Edge list
					Edge currEdge = new Edge(current, j + 1);
					nodes[i].giveEdge(currEdge);

					if ((currEdge.compare(bestSoFar) == -1)
							&& currEdge.getLength() != 0.0) {
						secondBest = bestSoFar;
						bestSoFar = currEdge;
					} else if ((currEdge.compare(secondBest) == -1)
							&& currEdge.getLength() != 0.0) {
						secondBest = currEdge;
					}
        		}
        	}
        	
        	//Set
        	nodes[i].setIn(bestSoFar);
        	nodes[i].setOut(secondBest);
        	nodes[i].setShort(bestSoFar);
        	nodes[i].setSecond(secondBest);
        }
        
        Solver solver = new Solver(nodes);
        solver.solve();
        fileReader.close();
    }//end main()
    
    /**
     * calculates and returns the distance between two
     * cities.
     * 
     * *** note that both cites must have values for their
     * *** x and y coordinate fields
     * 
     * @param a:  city 1
     * @param b:  city 2
     * 
     * @return : the distance between them
     */
    private static double calculateDistance(Node a, Node b) throws IOException
    {
        double x = b.getX() - a.getX();
        double y = b.getY() - a.getY();
        
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        
    }//end distance()
}//end Driver class

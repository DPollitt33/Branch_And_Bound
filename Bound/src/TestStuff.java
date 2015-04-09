//import java.util.LinkedList;
//import java.util.Scanner;

public class TestStuff {
	public static void main(String[] args){
		Node[] nodes = new Node[5];
		
		
		// 1 --> Destination
		Node n1 = new Node(1, 0, 0, 4);
		Edge e11 =  new Edge(3,2);
		n1.giveEdge(e11);
		Edge e12 =  new Edge(8,3);
		n1.giveEdge(e12);
		Edge e13 =  new Edge(4,4);
		n1.giveEdge(e13);
		Edge e14 =  new Edge(2,5);
		n1.giveEdge(e14);
		
		n1.setIn(e14);
		n1.setOut(e11);
		n1.setShort(e14);
		n1.setSecond(e11);
		nodes[0] = n1;

		// 2 --> Destination
		Node n2 = new Node(2, 0, 0, 4);
		Edge e21 =  new Edge(3,1);
		n2.giveEdge(e21);
		Edge e22 =  new Edge(7,3);
		n2.giveEdge(e22);
		Edge e23 =  new Edge(9,4);
		n2.giveEdge(e23);
		Edge e24 =  new Edge(6,5);
		n2.giveEdge(e24);
		
		n2.setIn(e21);
		n2.setOut(e24);
		n2.setShort(e21);
		n2.setSecond(e24);
		nodes[1] = n2;
    	
		// 3 --> Destination
		Node n3 = new Node(3, 0, 0, 4);
		Edge e31 =  new Edge(8,1);
		n3.giveEdge(e31);
		Edge e32 =  new Edge(7,2);
		n3.giveEdge(e32);
		Edge e33 =  new Edge(6,4);
		n3.giveEdge(e33);
		Edge e34 =  new Edge(4,5);
		n3.giveEdge(e34);
		
		n3.setIn(e34);
		n3.setOut(e33);
		n3.setShort(e34);
		n3.setSecond(e33);
		nodes[2] = n3;
    	
		// 4 --> Destination
		Node n4 = new Node(4, 0, 0, 4);
		Edge e41 =  new Edge(4,1);
		n4.giveEdge(e41);
		Edge e42 =  new Edge(9,2);
		n4.giveEdge(e42);
		Edge e43 =  new Edge(6,3);
		n4.giveEdge(e43);
		Edge e44 =  new Edge(5,5);
		n4.giveEdge(e44);

		n4.setIn(e41);
		n4.setOut(e44);
		n4.setShort(e41);
		n4.setSecond(e44);
		nodes[3] = n4;

		// 5 --> Destination
		Node n5 = new Node(5, 0, 0, 4);
		Edge e51 =  new Edge(2,1);
		n5.giveEdge(e51);
		Edge e52 =  new Edge(6,2);
		n5.giveEdge(e52);
		Edge e53 =  new Edge(4,3);
		n5.giveEdge(e53);
		Edge e54 =  new Edge(5,4);
		n5.giveEdge(e54);
		
		n5.setIn(e51);
		n5.setOut(e53);
		n5.setShort(e51);
		n5.setSecond(e53);
		nodes[4] = n5;
		
		Solver s = new Solver(nodes);
		s.solve();
	}
}


/*
Node[] nodes = new Node[5];
for(int i = 0; i < nodes.length; i++)
{
	nodes[i] = new Node(i+1, 0, 0, 5);
}

// 1 --> Destination
	nodes[0].setIn(new Edge(2,5));
nodes[0].setOut(new Edge(3,2));
nodes[0].setShort(new Edge(2,5));
nodes[0].setSecond(new Edge(3,2));

nodes[0].giveEdge(nodes[0].getOut());
nodes[0].giveEdge(new Edge(8,3));
nodes[0].giveEdge(new Edge(4,4));
nodes[0].giveEdge(nodes[0].getIn());


// 2 --> Destination
nodes[1].giveEdge(new Edge(3,1));
nodes[1].giveEdge(new Edge(7,3));
nodes[1].giveEdge(new Edge(9,4));
nodes[1].giveEdge(new Edge(6,5));
	nodes[1].setIn(new Edge(3,1));
nodes[1].setOut(new Edge(6,5));
nodes[1].setShort(new Edge(3,1));
nodes[1].setSecond(new Edge(6,5));

// 3 --> Destination
nodes[2].giveEdge(new Edge(8,1));
nodes[2].giveEdge(new Edge(7,2));
nodes[2].giveEdge(new Edge(6,4));
nodes[2].giveEdge(new Edge(4,5));
	nodes[1].setIn(new Edge(4,5));
nodes[1].setOut(new Edge(6,4));
nodes[1].setShort(new Edge(4,5));
nodes[1].setSecond(new Edge(6,4));

// 4 --> Destination
nodes[3].giveEdge(new Edge(4,1));
nodes[3].giveEdge(new Edge(9,2));
nodes[3].giveEdge(new Edge(6,3));
nodes[3].giveEdge(new Edge(5,5));
	nodes[3].setIn(new Edge(4,1));
nodes[3].setOut(new Edge(5,5));
nodes[3].setShort(new Edge(4,1));
nodes[3].setSecond(new Edge(5,5));

// 5 --> Destination
nodes[4].giveEdge(new Edge(2,1));
nodes[4].giveEdge(new Edge(6,2));
nodes[4].giveEdge(new Edge(4,3));
nodes[4].giveEdge(new Edge(5,4));
	nodes[4].setIn(new Edge(2,1));
nodes[4].setOut(new Edge(4,3));
nodes[4].setShort(new Edge(2,1));
nodes[4].setSecond(new Edge(4,3));


Solver s = new Solver(nodes);
s.solve();
*/
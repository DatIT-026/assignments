class Node{	
   int data;
   Node preNode, nextNode, CurrentNode;
   Node() {
      preNode = null;
      nextNode = null; 
   }	   
   Node(int data) {
      this.data = data;
   }		
}
public class CircularLinked {
   Node head, tail;
   int size;
	   
   public void printData() {
      Node node = head;
      if(size<=0) {
         System.out.print("List is empty");
      } else {
         do {
            System.out.print(" " + node.data);
            node = node.nextNode;
         }
         while(node!=head);
      }
   }
   public void insertStart(int data) {
      Node node = new Node();
      node.data = data;
      node.nextNode = head;
      node.preNode = null;
      
      if(size==0) {
         head = node;
         tail = node;
         node.nextNode = head;
      } else {
         Node tempNode = head;
         node.nextNode = tempNode;
         head = node;
         tail.nextNode = node;
      }
      size++;   
   }
   public void insertEnd(int data) {
      if(size==0) {
         insertStart(data);
      } else {
         Node node = new Node();
         tail.nextNode =node;
         tail = node; 
         tail.nextNode = head;
         size++;   
      }
   }
	public static void main(String args[]) {
      CircularLinked dl = new CircularLinked();
      dl.insertStart(10);
      dl.insertStart(20);
      dl.insertStart(30);
      dl.insertStart(1);
      dl.insertStart(56);
      dl.insertStart(40);
      dl.printData();
   }			
}
// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============
class Node {
  Car info;
  Node next;
  Node() {
   }
  Node(Car x, Node p) {
    info=x;next=p;
   }
  Node(Car x) {
    this(x,null);
   }
 }


// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============
class Node {
  Canary info;
  Node next;
  Node() {
   }
  Node(Canary x, Node p) {
    info=x;next=p;
   }
  Node(Canary x) {
    this(x,null);
   }
 }


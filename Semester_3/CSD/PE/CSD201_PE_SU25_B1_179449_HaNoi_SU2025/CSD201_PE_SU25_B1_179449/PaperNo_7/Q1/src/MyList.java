/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {
  Node head,tail;
  MyList() {head=tail=null;}
  boolean isEmpty() {
    return(head==null);
   }
  void clear() {head=tail=null;}

  void fvisit(Node p, RandomAccessFile f) throws Exception {
    if(p != null) f.writeBytes(p.info + " ");
   }

  void ftraverse(RandomAccessFile f) throws Exception {
    Node p = head;
    while(p!=null) {
       fvisit(p,f); // You will use this statement to write information of the node p to the file
       p=p.next;
      }
    f.writeBytes("\r\n");
   }

  void loadData(int k) { //do not edit this function
    String [] a = Lib.readLineToStrArray("data.txt", k);
    int [] b = Lib.readLineToIntArray("data.txt", k+1);
    int [] c = Lib.readLineToIntArray("data.txt", k+2);
    int n = a.length;
    for(int i=0;i<n;i++) addLast(a[i],b[i],c[i]);
   }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
*/
  void addLast(String xPlace, int xSong, int xWing) {
    //You should write here appropriate statements to complete this function.
    
    Canary c = new Canary(xPlace, xSong, xWing);
    Node newNode = new Node(c);
    
    if (xPlace.charAt(0) != 'A') {
        if (isEmpty()) head = tail = newNode;
        else {
            tail.next = newNode;
            tail = newNode;
        }
    } else return;
   }

  //You do not need to edit this function. Your task is to complete the addLast function above only.
  void f1() throws Exception {
     clear();
     loadData(1);
     String fname = "f1.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     ftraverse(f);
     f.close();
    }  

//==================================================================
  void f2() throws Exception {
     clear();
     loadData(5);
     String fname = "f2.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     ftraverse(f);
     
     Canary x, y, z;
     x = new Canary("X",1,2);
     y = new Canary("Y",2,3);
     z = new Canary("Z",3,4);
     //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
     
     Node curr = head;
     Node putX = new Node(x);
     Node putY = new Node(y);
     Node putZ = new Node(z);
     
     if (isEmpty()) return;
     
     int pos = 0;
     
     while (curr != null) {
        if (pos == 1) {
            putX.next = curr.next;
            curr.next = putX;
        } else if (pos == 4) {
            putY.next = curr.next;
            curr.next = putY;
        } else if (pos == 5) {
            putZ.next = curr.next;
            curr.next = putZ;
        }
        
        pos++;
        curr = curr.next;
     }

    //------------------------------------------------------------------------------------
     ftraverse(f);
     f.close();
    }  

//==================================================================

  void f3() throws Exception {
    clear();
    loadData(9);
    String fname = "f3.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    ftraverse(f);
    //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
     
      Node curr = head;
      Node k = null;
      while (curr != null) {
          if (curr.info.place.equals("E")) {
              k = curr;
              break;
          }
          curr = curr.next;
      }
      
      curr = head;
      while (curr != null) {  
        if (curr.info.wing < k.info.wing) dele(curr);
        curr = curr.next;   
      }
     
    //------------------------------------------------------------------------------------
    ftraverse(f);
    f.close();
  }

  void dele(Node q) {
    Node f, p;
    f = null;
	  p = head;

    while (p != null) {
        if (p == q) break;
        f = p;
        p = p.next;
    }

    if (p == null) return;

    if (f == null) {
        head = head.next;
        if (head == null) tail = null;
        return;
    }
    
    f.next = p.next;
    if (f.next == null) tail = f;
   }
  
//==================================================================
  void f4() throws Exception {
    clear();
    loadData(13);
    String fname = "f4.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    ftraverse(f);
    //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
      
       Node curr = head;
       Node p, q;

       while (curr != null) {
          if (curr.info.place.equals('K')) {
              p = curr;
          } else if (curr.info.place.equals('E')) {
              q = curr;
          }

          curr = curr.next;
       }

       Node i, j;
       while (curr != null) {
          for (i = head; i < p && i != null; i = i.next) {
              for (j = i.next; j < p.next && j != null; j = j.next) {
                  if (i.info.wing > j.info.wing) {
                      Node temp = i.info;
                      i.info = j.info;
                      j.info = temp;
                  }
              }
          }    

          curr = curr.next;
       }
       
    //------------------------------------------------------------------------------------
    ftraverse(f);
    f.close();
   }

 }


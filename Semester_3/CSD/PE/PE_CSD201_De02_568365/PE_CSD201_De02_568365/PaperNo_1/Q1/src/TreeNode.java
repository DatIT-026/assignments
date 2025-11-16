/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class TreeNode {
    Order info;
    TreeNode left;
    TreeNode right;
    
   TreeNode(Order info, TreeNode left, TreeNode right){
        this.info = info;
        this.left = left;
        this.right = right;
    }
   
   TreeNode(Order o){
       this(o, null, null);
   }
}

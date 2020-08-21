package huffmantree;


import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anii BC
 *  @param <E>
 */
    public class BinaryTree <E>{
    
    private BinaryNode<E> root;
    
    private class BinaryNode<E>{
        E data;
        private BinaryNode<E> left;
        private BinaryNode<E> right;

        public BinaryNode(E data) {
            this.data = data;
            
        }
    
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public boolean add(E child, E parent){
        BinaryNode<E> nc = searchNode(child);
        if(nc!=null)
            return false;
        nc = new BinaryNode<>(child);
        if(parent == null && root==null){  
                 root=nc;
             return true;
        }
        BinaryNode<E> np= searchNode(parent);
        if(np!=null){
            
            if(np.left==null){
                np.left=nc;
                return true;  
            }
            else if (np.right == null){
                np.right=nc;
                return true;
            }
        }return false;
    }
        
    
     private BinaryNode<E> searchNode(E data){
          return searchNode(data,root);
     
     
     }
    private BinaryNode<E> searchNode(E data, BinaryNode<E> n){
          if(n==null)
              return n;
          else if(n.data.equals(data))
              return n;
          else{
              BinaryNode<E> nl = searchNode(data,n.left);
              if(nl!=null) return nl;
              return searchNode(data, n.right);
              
          }
     }
    
    
       public boolean remove(E child){
     if(child ==null || isEmpty())   
         return false;
     if(root.data.equals(child)){
        root= null;
        return true;
     } 
     BinaryNode<E> np = searchParent(child);
     if(np!=null){
         if(np.left!=null && np.left.data.equals(child))
             np.left=null;
         else
             np.right=null;
         return true;
     }
     return false;
    }
     
       public int size(){
           return size(root);
       }
       
       private int size(BinaryNode<E> n){
           if(n==null)
               return 0;
           return 1 + size(n.left)+ size(n.right);
       }
       
       public int height(){
           return height(root);
       }
       
       private int height(BinaryNode<E> n){
           if(n == null)
               return 0;
           return 1+ Math.max(height(n.left), height(n.right));
       }
       
       public boolean isFull(){
           return isFull(root);
       }
       private boolean isFull(BinaryNode <E> n){
           if(n==null)
               return true;
           else if((n.left==null && n.right!=null)|| (n.left!=null && n.right==null))
               return false;
           return height(n.left)== height(n.right) && isFull(n.left) && isFull(n.right);
           
       }   
       
    private BinaryNode<E> searchParent(E child){
        return searchParent(child, root);
    } 
     private BinaryNode<E> searchParent(E child, BinaryNode<E> n){
         if(n==null)
             return n;
         else if((n.left!= null && n.left.data.equals(child))|| (n.right!= null && n.right.data.equals(child)))
             return n;
         else{
              BinaryNode<E> nl = searchParent(child,n.left);
              if(nl!=null) return nl;
              return searchParent(child, n.right);
              
          }
   }
       
       
     public List<E> getValuesFromLevels(int rango1,int rango2){
        List<E> list=new LinkedList<>();
        if(rango1<1||rango1>rango2){
            return list;
        }
        return getValuesFromLevels(rango1, rango2, root, 1);
    }
    private List<E> getValuesFromLevels(int rango1,int rango2,BinaryNode<E> nodo, int nivel){
        List<E> list=new LinkedList<>();
        if(nodo==null) return list;
        else if(nivel>=rango1 && nivel<=rango2){
            list.add(nodo.data);
        }
        else if(nivel<rango2){
            list.addAll(getValuesFromLevels(rango1, rango2, nodo.left, nivel+1));
            list.addAll(getValuesFromLevels(rango1, rango2, nodo.right, nivel+1));
        }  
        return list;
   
    }
      
      public int maxLevel(int rango1){
        List<Integer> lista;
        if(rango1<1 || rango1>this.height()){
            return 0;
        }
        lista=maxLevel(rango1, (BinaryNode<Integer>)root, 1);
        return Collections.max(lista);
        
        
        
    }
    private List<Integer> maxLevel(int rango,BinaryNode<Integer> node, int nivel){
        List<Integer> list=new LinkedList<>();
        if(node==null) return list;
        else if(nivel==rango){
            list.add(node.data);
            
        }
        else if(nivel<rango){
            list.addAll(maxLevel(rango, node.left, nivel+1));
            list.addAll(maxLevel(rango, node.right, nivel+1));
        }  
        
        return list;
   
    }
       
     public static BinaryTree<HuffmanContent> buildHuffmanTree(HashMap<String,Integer> table){
        PriorityQueue<BinaryTree<HuffmanContent>> queue;
        queue = new PriorityQueue<>( (t1,t2)-> t1.root.data.getFrecuency()-t2.root.data.getFrecuency());
        HuffmanContent hc ;
     
       
        
        
        return queue.poll();
    }

       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
    
    
    
    
    
    
    
    
    
    
    
    
}

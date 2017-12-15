
// Adapted from code by Y. Daniel Liang
// Modified by C. Lee-Klawender

/*
Homework 4 Binary Search Tree Cards
Valeska Noelle Victoria
11/14/2017
Windows 10, Javac
A program which stores cards in a binary search tree in a sorted order,
either by their pips or by their suit, and provides all relevant BST functions, such as searching, inorder printing, and so on.
 */

interface DeepCloneable<T> {
    public T deepClone();
}

public abstract class BinaryTree<E extends DeepCloneable<E>> implements TreeInterface<E> {
	protected BinaryNode<E> root=null; // reference to the root
    protected int size=0; // number of nodes in the tree

    // default constructor
    public BinaryTree(){ }

    // deep copy method, called by the copy constructor, receiving the root node of another tree as a parameter
    protected BinaryNode<E> deepCopyTree(BinaryNode<E> node)    {

        if (node == null)
            return null;

        BinaryNode<E> newRoot = new BinaryNode<E>(node.getData().deepClone()); // a BinaryNode which represents the root node of the tree to be copied,
        // and is used to copy the remainder of the tree in a deep copy manner by setting its left and or right nodes to the appropriate
        // deep copied nodes, and so on until it reaches the end of the tree to be copied nodes.

        // YOU FINISH:
        //     to make a deep copy, you need make a new instance the Node parameter
        //     with a DEEP CLONE of the parameter's data!
        //     (YOU CAN'T USE BinaryNode's copy)
        //  THIS METHOD WILL TRAVERSE THIS TREE IN RECURSIVELY PREORDER FASHION
        //      so similar to the preorder method, you MUST check if the parameter
        //      is null first!

        //visitor.visit(node.getData());
        newRoot.setLeftChild(deepCopyTree(node.getLeftChild()));
        newRoot.setRightChild(deepCopyTree(node.getRightChild()));

        return newRoot;

    }
    // copy constructor
    public BinaryTree(BinaryTree<E> sourceTree)
    {
        // YOU WRITE (should do a DEEP COPY by calling deepCopyTree AND make sure this' size is updated)
    	root = deepCopyTree(sourceTree.root);
    	size = sourceTree.getSize();

    }

    /** Clears the whole tree */
    public void clear()
    {
    	// EXERCISE OR HOMEWORK
        root = null;
        size = 0;
    }

    @Override /** Preorder traversal from the root */
    public void preorder(Visitor<E> visitor) {
        preorder(root, visitor);
    }

    @Override /** Inorder traversal from the root*/
    public void inorder(Visitor<E> visitor) {
        // you finish (part of HW#4)
        inorder(root, visitor);
    }

    @Override /** Postorder traversal from the root */
    public void postorder(Visitor<E> visitor) {
         // you finish (part of HW#4)
        postorder(root, visitor);
    }

    @Override /** Get the number of nodes in the tree */
    public int getSize() {
    	return size;
    }


    @Override /** Return true if the tree is empty */
    public boolean isEmpty() {
    	return getSize() == 0;
    }

    /** Preorder traversal from a subtree */
    protected void preorder(BinaryNode<E> root, Visitor<E> visitor) {
    	if (root == null)
    		return;
    	visitor.visit(root.getData());
    	preorder(root.getLeftChild(), visitor);
    	preorder(root.getRightChild(), visitor);
    }

     /** Inorder traversal from a subtree */
    protected void inorder(BinaryNode<E> root, Visitor<E> visitor) {
         // you finish (part of HW#4)
        if (root == null)
            return;
        inorder(root.getLeftChild(), visitor);
        visitor.visit(root.getData());
        inorder(root.getRightChild(), visitor);

    }

     /** Posorder traversal from a subtree */
    protected void postorder(BinaryNode<E> root, Visitor<E> visitor) {
         // you finish (part of HW#4)
        if(root == null)
            return;

        postorder(root.getLeftChild(), visitor);
        postorder(root.getRightChild(), visitor);
        visitor.visit(root.getData());

    }
} // end abstract BinaryTree class

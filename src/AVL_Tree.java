import java.util.Comparator;

public class AVL_Tree<E extends DeepCloneable<E>> extends BST<E> {

    private boolean foundNode; // helper variable
    // DECLARE A PRIVATE COMPARATOR INSTANCE VARIABLE HERE!
    private Comparator<E> cmp; // a private comparator variable for comparing two objects according any provided member variable.

    private AVL_Node<E> ourRoot;

    public AVL_Tree(Comparator<E> cmp) {
        super(cmp);
        this.cmp = cmp;
        ourRoot = new AVL_Node<>(super.root);
    }

    public AVL_Tree(AVL_Tree<E> sourceTree) {
        super(sourceTree);
        this.cmp = sourceTree.cmp;
        ourRoot = new AVL_Node<>(super.root);
    }

    public AVL_Tree(E[] objects) {
        // An empty array to avoid having two trees sorted differently.
        super((E[]) new Object[]{});
        ourRoot = new AVL_Node<>(super.root);
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    // Find the left most node of a given AVL_Node
    AVL_Node<E> leftMostNode(AVL_Node<E> node)
    {
        AVL_Node<E> current = node;

        while (current.getLeftChild() != null)
            current = current.getLeftChild();

        return current;
    }

    // Retrieve the balance of a given AVL_Node
    int getBalance(AVL_Node<E> Node)
    {
        if (Node == null)
            return 0;
        return heightOf(Node.getLeftChild()) - heightOf(Node.getRightChild());
    }

    private AVL_Node<E> getRoot() {

        return ourRoot;

    }

    @Override
    public boolean insert(E e) {

        _insert(getRoot(), e);
        size++;
        return true;

    }

    AVL_Node<E> _insert(AVL_Node<E> node, E e) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new AVL_Node<E>(e));

        if (cmp.compare(e, node.getData()) < 0)
            node.setLeftChild(_insert(node.getLeftChild(), e));
        else if (cmp.compare(e, node.getData()) > 0)
            node.setRightChild(_insert(node.getRightChild(), e));
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.setNodeHeight(1 + Math.max(heightOf(node.getLeftChild()),
                heightOf(node.getRightChild())));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && cmp.compare(e, node.getLeftChild().getData()) < 0)
            return rotateWithLeftChild(node);

        // Right Right Case
        if (balance < -1 && cmp.compare(e, node.getRightChild().getData()) > 0)
            return rotateWithRightChild(node);

        // Left Right Case
        if (balance > 1 && cmp.compare(e, node.getLeftChild().getData()) > 0) {
            node.setLeftChild(rotateWithRightChild(node.getLeftChild()));
            return rotateWithLeftChild(node);
        }

        // Right Left Case
        if (balance < -1 && cmp.compare(e, node.getRightChild().getData()) < 0) {
            node.setRightChild(rotateWithLeftChild(node.getRightChild()));
            return rotateWithRightChild(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    @Override
    public boolean delete(E e) {

        foundNode = false;		// initialize boolean instance variable
        ourRoot = _delete(getRoot(), e); //call private method to do actual deletion

        if( foundNode )
        {
            size--;// Element deleted successfully
        }
        return foundNode;

    }

    public AVL_Node<E> _delete(AVL_Node<E> node, E e)
    {
        // Do normal deletion
        if (node == null)
            return node;

        // If the node with the data e to be deleted is less than
        // the node's data, then it lies in left subtree
        if (cmp.compare(e, node.getData()) < 0 )
            node.setLeftChild(_delete(node.getLeftChild(), e));

            // If the node with the data e to be deleted is greater than
            // the node's data, then it lies in right subtree
        else if (cmp.compare(e, node.getData()) > 0 )
            node.setRightChild(_delete(node.getRightChild(), e));

            // if e is same as root's e, then this is the node
            // to be deleted
        else
        {
            foundNode = true;

            // node with only one child or no child
            if ((node.getLeftChild() == null) || (node.getRightChild() == null))
            {
                AVL_Node<E> temp = null;
                if (temp == node.getLeftChild())
                    temp = node.getRightChild();
                else
                    temp = node.getLeftChild();

                // No child case
                if (temp == null)
                {
                    temp = node;
                    node = null;
                }
                else   // One child case
                    node = temp; // Copy the contents of
                // the non-empty child
            }
            else
            {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                AVL_Node temp = leftMostNode(node.getRightChild());

                // Copy the inorder successor's data to this node
                node.setData((E) temp.getData());

                // Delete the inorder successor
                node.setRightChild(_delete(node.getRightChild(), (E) temp.getData()));
            }
        }

        // If the tree had only one node then return
        if (node == null)
            return node;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        node.setNodeHeight(Math.max(heightOf(node.getLeftChild()), heightOf(node.getRightChild())) + 1);

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(node.getLeftChild()) >= 0)
            return rotateWithLeftChild(node);

        // Left Right Case
        if (balance > 1 && getBalance(node.getLeftChild()) < 0)
        {
            node.setLeftChild(rotateWithRightChild(node.getLeftChild()));
            return rotateWithLeftChild(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.getRightChild()) <= 0)
            return rotateWithRightChild(node);

        // Right Left Case
        if (balance < -1 && getBalance(node.getRightChild()) > 0)
        {
            node.setRightChild(rotateWithLeftChild(node.getRightChild()));
            return rotateWithRightChild(node);
        }

        return node;
    }
    
    //  THIS IS WHAT I CALLED Right Rotation at k2
    protected AVL_Node<E> rotateWithLeftChild(
            AVL_Node<E> k2 )
    {
        AVL_Node<E> k1 = k2.getLeftChild();
        k2.setLeftChild(k1.getRightChild());
        k1.setRightChild(k2);
        k2.setNodeHeight( Math.max( heightOf(k2.getLeftChild()),  heightOf(k2.getRightChild()) ) + 1 );
        k1.setNodeHeight( Math.max( heightOf(k1.getLeftChild()),  k2.getHeight() ) + 1 );
        return k1;
    }

    //  What I called Left Rotation at k2
    protected AVL_Node<E> rotateWithRightChild(
            AVL_Node<E> k2 )
    {
        AVL_Node<E> k1 = k2.getRightChild();
        k2.setRightChild(k1.getLeftChild());
        k1. setLeftChild(k2);
        k2.setNodeHeight( Math.max( heightOf(k2.getLeftChild()),  heightOf(k2.getRightChild()) ) + 1 );
        k1.setNodeHeight( Math.max( heightOf(k1.getRightChild()),  k2.getHeight() ) + 1 );
        return k1;
    }


    public int heightOf(AVL_Node<E> tmp) {
        return tmp.getNodeHeight();
    }

    // do a single LEFT rotation passing k3's left child, then return the result of calling rotateWithLeftChild passing the parameter
    protected AVL_Node<E> doubleWithLeftChild(
         AVL_Node<E> k3 ) 	//********YOU FINISH*************
    {

        rotateWithRightChild(k3.getLeftChild());
        return rotateWithLeftChild(k3);

    }

    // do a single RIGHT rotation passing k3's right child, then return the result of calling rotateWithRightChild passing the parameter
    protected AVL_Node<E> doubleWithRightChild(
     AVL_Node<E> k3 ) 	//********YOU FINISH*************

    {
        rotateWithLeftChild(k3.getRightChild());
        return rotateWithRightChild(k3);

    }


}

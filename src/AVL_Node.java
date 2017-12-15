public class AVL_Node<T> extends BinaryNode<T> {

    private int height = 0;


    public AVL_Node(){
        this(0, null, null, null);
    }

    public AVL_Node(T t) {

        this(0, t, null, null);

    }

    public AVL_Node(BinaryNode<T> nodeToCovert) {

        this.setData(nodeToCovert.getData());
        this.setLeftChild(nodeToCovert.getLeftChild());
        this.setRightChild(nodeToCovert.getRightChild());
        this.setHeight(nodeToCovert.getHeight());


    }

    public AVL_Node(int h, T dataPortion, BinaryNode<T> newLeftChild,
                    BinaryNode<T> newRightChild)
    {
        super(dataPortion, newLeftChild, newRightChild);
        height = h;
    } // end default constructor

    @Override
    public AVL_Node<T> getLeftChild() {
        return new AVL_Node<>(super.getLeftChild());
    }

    @Override
    public AVL_Node<T> getRightChild() {
        return new AVL_Node<>(super.getRightChild());
    }

    public int getNodeHeight() {
        height = getHeight();
        return height;
    }

    public void setHeight(int h) {
        height = h;
    }

}

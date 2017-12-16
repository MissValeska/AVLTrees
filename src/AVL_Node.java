public class AVL_Node<T> extends BinaryNode<T> {

    private int height = 0;


    public AVL_Node(){
        this(0, null, null, null);
    }

    public AVL_Node(T t) {

        this(0, t, null, null);

    }

    private AVL_Node<T> conversionHelper(BinaryNode<T> nodeToConvert) {

        if(nodeToConvert == null) {
            return null;
        }

        AVL_Node<T> tmp = new AVL_Node<>();

        tmp.setData(nodeToConvert.getData());
        tmp.setNodeHeight(nodeToConvert.getHeight());
        tmp.setRightChild(conversionHelper(nodeToConvert.getRightChild()));
        tmp.setLeftChild(conversionHelper(nodeToConvert.getLeftChild()));
        return tmp;

    }

    public AVL_Node(BinaryNode<T> nodeToConvert) {

        this.setData(nodeToConvert.getData());
        this.setNodeHeight(nodeToConvert.getHeight());
        this.setRightChild(conversionHelper(nodeToConvert.getRightChild()));
        this.setLeftChild(conversionHelper(nodeToConvert.getLeftChild()));

    }

    public AVL_Node(int h, T dataPortion, BinaryNode<T> newLeftChild,
                    BinaryNode<T> newRightChild)
    {
        super(dataPortion, newLeftChild, newRightChild);
        height = h;
    } // end default constructor

    @Override
    public AVL_Node<T> getLeftChild() {
        return (AVL_Node<T>) super.getLeftChild();
    }

    @Override
    public AVL_Node<T> getRightChild() {
        return (AVL_Node<T>) super.getRightChild();
    }

    public int getNodeHeight() {
        height = getHeight();
        return height;
    }

    public void setNodeHeight(int h) {
        height = h;
    }

}

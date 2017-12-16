import java.util.Comparator;

public class main implements DeepCloneable<main> {

    int data;

    public main() {
        data = 0;
    }
    public main(int d) {
        data = d;
    }


    public static void main(String[] arg) {

        AVL_Tree<main> tree = new AVL_Tree<main>(new Comparator<main>() {
            @Override
            public int compare(main o1, main o2) {
                return Integer.compare(o1.data, o2.data);
            }
        });

        tree.insert(new main(77));
        tree.insert(new main(30));
        tree.insert(new main(48));
        tree.insert(new main(38));
        tree.insert(new main(32));


        tree.preorder(new Visitor<main>() {
            @Override
            public void visit(main obj) {
                System.out.println(obj.data);
            }
        });

    }

    @Override
    public main deepClone() {
        return null;
    }
}

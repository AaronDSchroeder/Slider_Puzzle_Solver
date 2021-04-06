public class BinarySearchTree<E> {

    private TreeNode root;

    public boolean insert(E value){
        if(search(value)){
            return false;
        }
        if (root == null) {
            root = new TreeNode(value);
        }
        else {
            TreeNode parent = null;
            TreeNode node = root;

            while (node != null) {
                parent = node;
                if (node.compareTo(value) < 0) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }

            TreeNode newNode = new TreeNode(value);
            if(parent.compareTo(value) < 0) {
                parent.right = newNode;
            } else{
                parent.left = newNode;
            }
        }
        return true;
    }

    public boolean search(E value){
        boolean found = false;
        TreeNode node = root;
        while(!found && node != null){
            if(node.compareTo(value) == 0){
                found = true;
            }
            else if(node.compareTo(value) < 0){
                node = node.right;
            }
            else{
                node = node.left;
            }
        }

        return found;
    }

    public class TreeNode implements Comparable<E>{
        public E value;
        public TreeNode left;
        public TreeNode right;
        public int compareTo(E value){
            String item = value.toString();
                return ((this.value.toString()).compareTo(item));
        }

        public TreeNode(E value){
            this.value = value;
        }
    }
}
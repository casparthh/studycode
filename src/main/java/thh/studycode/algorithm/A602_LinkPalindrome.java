package thh.studycode.algorithm;

/**
 * 链表快慢指针问题
 * <p>
 * 判断节点是不是回文结构
 */
public class A602_LinkPalindrome {

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node addNext(int value) {
            Node n = new Node(value);
            this.next = n;
            return n;
        }
    }

    public boolean isPalindrome(Node root) {
        if (root == null) {
            return false;
        }
        if (root.next == null) {
            return true;
        }
        if (root.next.next == null) {
            return root.value == root.next.value;
        }

        Node midNode = findUpMid(root);
        Node rightRoot = reverse(midNode.next);
        Node rightNode = rightRoot;
        Node leftNode = root;

        boolean isPalindrome = true;
        while(leftNode != null && rightNode != null){
            if(leftNode.value != rightNode.value){
                isPalindrome = false;
                break;
            }
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }

        reverse(rightRoot);
        return isPalindrome;
    }

    public Node reverse(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node pNode = node;
        Node cNode = node.next;
        node.next = null;
        while (cNode != null) {
            Node nextNext = cNode.next;
            cNode.next = pNode;
            pNode = cNode;
            cNode = nextNext;
        }
        return pNode;
    }

    public Node findUpMid(Node root) {
        if (root == null || root.next == null) {
            return root;
        }
        Node slower = root;
        Node faster = root;
        while (faster.next != null && faster.next.next != null) {
            slower = slower.next;
            faster = faster.next.next;
        }
        return slower;
    }

    public static void main(String[] args) {
        Node root = (new Node(1));
        root.addNext(2)
                .addNext(3)
                .addNext(4)
                .addNext(5)
                .addNext(3)
                .addNext(2)
                .addNext(1);
        A602_LinkPalindrome link = new A602_LinkPalindrome();
        System.out.println(link.isPalindrome(root));
        System.out.println("----");
    }

}

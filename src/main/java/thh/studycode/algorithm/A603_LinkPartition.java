package thh.studycode.algorithm;

/**
 * 将单向链表，按某值划分成左边小、中间相等、右边大的形式
 * 方式一、
 *      把链表放入数组里，在数组上做parstition
 * 方式二、
 *      分成大、中、小三部分，再把各个部分之间串起来
 */
public class A603_LinkPartition {

    static class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node addNode(int value){
            Node n = new Node(value);
            this.next = n;
            return n;
        }
    }

    public Node partition(Node node,int num){
        if(node == null || node.next == null ){
            return node;
        }
        Node glHead = null;
        Node glTail = null;
        Node eqHead = null;
        Node eqTail = null;
        Node gtHead = null;
        Node gtTail = null;

        //分别找出小于部分的头至尾
        //分别找出等于部分的头至尾
        //分别找出大于部分的头至尾
        while(node != null){
            Node next = node.next;
            if(node.value< num){
                if(glHead == null){
                    glHead = node;
                    glTail = node;
                } else {
                    glTail.next = node;
                    glTail = node;
                }
                node.next = null;
            }
            if(node.value == num){
                if(eqHead == null){
                    eqHead = node;
                    eqTail = node;
                } else {
                    eqTail.next = node;
                    eqTail = node;
                }
                node.next = null;
            }
            if(node.value > num){
                if(gtHead == null){
                    gtHead = node;
                    gtTail = node;
                } else {
                    gtTail.next = node;
                    gtTail = node;
                }
                node.next = null;
            }
            node = next;
        }

        //重新连接
        if(glTail != null){
            if(eqHead != null){
                glTail.next= eqHead;
                eqTail.next = gtHead;
            } else {
                glTail.next = gtHead;
            }
        } else {
            if(eqHead != null){
                eqTail.next = gtHead;
            }
        }

        return glHead  != null ? glHead : (eqHead != null? eqHead : gtHead);
    }


    public static void main(String[] args) {
        A603_LinkPartition linkPartition = new A603_LinkPartition();
        Node root = new Node(1);
        Node node = root;
        node.addNode(2)
                .addNode(3)
                .addNode(4)
                .addNode(3)
                .addNode(6)
                .addNode(3)
                .addNode(4)
                .addNode(7)
                .addNode(0)
                .addNode(1);

        node = linkPartition.partition(root,7);
        while (node != null){
            System.out.print(node.value+"\t");
            node = node.next;
        }
        System.out.println("");
    }

}

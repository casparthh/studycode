package thh.studycode.algorithm;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1 和head2.
 * 实现一个函数，如查两个链表相交，请返回相交的第一个节点。如查不相交，返回null;
 * [要求]如果两个链表长度之和为N,时间复杂度请达到O(N),额外空间复杂度请达到O(1)
 */
public class A605_CrossLink {

    static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public Node findCrossNood(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cycleNode1 = isCycle(head1);
        Node cycleNode2 = isCycle(head2);
        if (cycleNode1 == null && cycleNode2 == null) {
            //都没有环的情况
            return noLoop(head1, head2);
        } else if (cycleNode1 != null && cycleNode2 != null) {
            //都有环的情况
            return loop(head1, head2, cycleNode1, cycleNode2);
        } else {
            //一个有环一个没有环的情况不可能相交。
            return null;
        }
    }

    /**
     * 两个链表都有环的情况
     * 情况1：两个链表的环的交点相同。把环的部分忽略，剩下的就是两个无环链表相交的情况了。
     * 情况2：两个链表的环的交点不同，但链表有相交。从第一个链表的环交点开始，一直走到中间遇到第二个链表的环交点。
     * 情况3：两个链表都有环，但没相交。从第一个链表的环交点开始，如果又回到了第一个链表的环交点，还没遇到第二个链表的环交点。
     *
     * @param head1
     * @param head2
     * @param cycleNode1
     * @param cycleNode2
     * @return
     */
    public Node loop(Node head1, Node head2, Node cycleNode1, Node cycleNode2) {
        if (cycleNode1 == cycleNode2) {
            Node n1 = head1;
            int h1Len = 1;
            while (n1 != cycleNode1) {
                h1Len++;
                n1 = n1.next;
            }

            Node n2 = head2;
            int h2Len = 1;
            while (n2 != cycleNode2) {
                h2Len++;
                n2 = n2.next;
            }

            n1 = head1;
            n2 = head2;
            if (h1Len > h2Len) {
                for (int i = 0; i < (h1Len - h2Len); i++) {
                    n1 = n1.next;
                }
            } else if (h2Len > h1Len) {
                for (int i = 0; i < (h2Len - h1Len); i++) {
                    n2 = n2.next;
                }
            }
            while (n1 != n2) {
                n1 = n1.next;
                n2 = n2.next;
            }
            return n1;
        } else {
            Node n1 = cycleNode1.next;
            while (n1 != cycleNode1) {
                if (n1 == cycleNode2) {
                    return cycleNode2;
                } else {
                    n1 = n1.next;
                }
            }
            return n1 != cycleNode1 ? cycleNode2 : null;
        }
    }

    /**
     * 没有形成环
     * 情况1：尾不同，所以没有相交
     * 情况2：尾相同，说明有相交；比较链表长度，长的链表先走大于的长度，然后两个链表一起走，直到相遇的点刚为相交点。
     * @param head1
     * @param head2
     * @return
     */
    public Node noLoop(Node head1, Node head2) {
        Node n1 = head1;
        int h1Len = 1;
        while (n1.next != null) {
            h1Len++;
            n1 = n1.next;
        }

        Node n2 = head2;
        int h2Len = 1;
        while (n2.next != null) {
            h2Len++;
            n2 = n2.next;
        }
        if (n1 != n2) {
            //尾不同，所以没有相交
            return null;
        } else {
            //尾相同，说明有相交；
            n1 = head1;
            n2 = head2;
            //比较链表长度，长的链表先走大于的长度，
            if (h1Len > h2Len) {
                for (int i = 0; i < (h1Len - h2Len); i++) {
                    n1 = n1.next;
                }
            } else if (h2Len > h1Len) {
                for (int i = 0; i < (h2Len - h1Len); i++) {
                    n2 = n2.next;
                }
            }
            //两个链表一起走，直到相遇的点刚为相交点。
            while (n1 != n2) {
                n1 = n1.next;
                n2 = n2.next;
            }
            return n1;
        }
    }


    public Node isCycle(Node head) {
        Node slower = head;
        Node faster = head;
        while (faster != null && faster.next != null) {
            slower = slower.next;
            faster = faster.next.next;
            if (slower == faster) {
                //快慢指针相遇了，肯定是有相交了
                return findCyclePointer(head, slower);
            }
        }
        return null;
    }

    public Node findCyclePointer(Node head, Node node) {
        Node slower = head;
        Node faster = node;
        while (slower != faster) {
            slower = slower.next;
            faster = faster.next;
        }
        return slower;
    }

    public static void main(String[] args) {
        A605_CrossLink crossLink = new A605_CrossLink();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);

        Node n14 = new Node(14);
        Node n15 = new Node(15);
        Node n16 = new Node(16);
        Node n17 = new Node(17);
        Node n18 = new Node(18);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n9.next = n4;

        n14.next = n18;
        n15.next = n16;
        n16.next = n17;
        n17.next = n18;
        n18.next = n7;

        Node node = crossLink.findCrossNood(n1, n14);
        if (node != null){
            System.out.println("cross node : "+node.value);
        } else {
            System.out.println("没有相交的点。。。。");
        }
    }

}

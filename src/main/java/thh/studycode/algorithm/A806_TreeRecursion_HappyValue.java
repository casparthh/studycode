package thh.studycode.algorithm;


import java.util.ArrayList;
import java.util.List;

/**
 * 派对的最大快乐值问题
 */
public class A806_TreeRecursion_HappyValue {

    //员工信息的定义
    public static class Employee {
        int happy;  //这名员工可以带来的快乐值
        List<Employee> subordinates; //这名员工有哪些直接下级

        public Employee(int happy) {
            this.happy = happy;
        }

        public Employee addEmploee(Employee emp){
            if(subordinates == null){
                subordinates = new ArrayList<>();
            }
            subordinates.add(emp);
            return this;
        }
    }


    public static class Info {
        int joinMax;
        int unjoinMax;

        public Info(int joinMax, int unjoinMax) {
            this.joinMax = joinMax;
            this.unjoinMax = unjoinMax;
        }
    }

    /**
     * 派对的最大快乐值
     * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则: .
     * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
     * 2.派对的整体快乐值是所有到场员工快乐值的累加
     * 3.你的目标是让派对的整体快乐值尽量大
     * 给定一棵多叉树的头节点boss,请返回派对的最大快乐值。
     *
     * @param employee
     * @return
     */
    public Info maxHappy(Employee employee) {
        if(employee == null){
            return new Info(0,0);
        }
        if (employee.subordinates == null || employee.subordinates.size() ==0){
            return new Info(employee.happy, 0);
        }

        //当前员工参加时的最大快乐值：
        int joinMax = employee.happy;
        //当前员工不参加时的最大快乐值：
        int unjoinMax = 0;

        for (Employee emp : employee.subordinates){
            Info subInfo = maxHappy(emp);
            joinMax+= subInfo.unjoinMax;
            //这里注意，当前员工不参加，他下级也可以参加，也可以不参加，所以要 += max
            unjoinMax += Math.max(subInfo.joinMax, subInfo.unjoinMax);
        }
        return new Info(joinMax, unjoinMax);
    }

    public static void main(String[] args) {
        A806_TreeRecursion_HappyValue happyValue = new A806_TreeRecursion_HappyValue();
        Employee employee = initNode();
        Info info = happyValue.maxHappy(employee);
        System.out.println("join max:" + info.joinMax);
        System.out.println("unjoin max:" + info.unjoinMax);
    }

    private static Employee initNode() {
        Employee boss = new Employee(10);
        Employee e1_1 = new Employee(10);
        Employee e1_2 = new Employee(10);
        Employee e1_3 = new Employee(10);

        Employee e2_1_1 = new Employee(10);
        Employee e2_1_2 = new Employee(10);
        Employee e2_1_3 = new Employee(10);

        Employee e2_2_1 = new Employee(10);
        Employee e2_2_2 = new Employee(10);
        Employee e2_2_3 = new Employee(10);

        Employee e2_3_1 = new Employee(10);
        Employee e2_3_2 = new Employee(100);
        Employee e2_3_3 = new Employee(100);

        boss.addEmploee(e1_1).addEmploee(e1_2).addEmploee(e1_3);
        e1_1.addEmploee(e2_1_1).addEmploee(e2_1_2).addEmploee(e2_1_3);
        e1_2.addEmploee(e2_2_1).addEmploee(e2_2_2).addEmploee(e2_2_3);
        e1_3.addEmploee(e2_3_1).addEmploee(e2_3_2).addEmploee(e2_3_3);
        return boss;
    }
}

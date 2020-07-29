package thh.studycode.algorithm;

import lombok.AllArgsConstructor;

import java.util.*;

/**
 * 贪心算法的解题套路实战
 * <p>
 * 输入:正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * 。K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明:每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出:你最后获得的最大钱数。
 * 。
 */
public class A1004_Greedy_CostProfit {

    @AllArgsConstructor
    public static class Project {
        int cost;
        int profit;
    }

    static Comparator<Project> comparator = new Comparator<Project>() {
        @Override
        public int compare(Project o1, Project o2) {
            return o2.profit - o1.profit;
        }
    };

    static Comparator<Project> comparator1 = new Comparator<Project>() {
        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost - o2.cost;
        }
    };

    /**
     * @param projectList
     * @return
     */
    public static int trade(List<Project> projectList, int balance, int k) {
        PriorityQueue<Project> queue = new PriorityQueue<Project>(comparator);
        projectList.forEach(item -> queue.add(item));

        PriorityQueue<Project> queue1 = new PriorityQueue<Project>(comparator1);

        for (int i = 0; i < k; i++) {
            while (queue.isEmpty() == false) {
                Project project = queue.poll();
                if (project.cost <= balance) {
                    balance += project.profit;
                    break;
                } else {
                    queue1.add(project);
                }
            }
            while (queue1.isEmpty() == false) {
                queue.add(queue1.poll());
            }
        }
        return balance;
    }

    /*
    如果用两个堆，一个小根堆按成本放，一个大根堆按利润放。
    初使将所有数据放入小根堆，然后将所有成本<=余额的移到大根堆（够成本做的项目），再从大根堆弹出一个项目做。
    盈利后，将利润与成本相加，然后再将小根堆中够成本做的项目再移到大根堆，，再从大根堆弹出一个项目做。周而复始。
    该解法不需要将大根堆倒到小根堆，解法更优
     */


    public static void main(String[] args) {
        List<Project> projectList = new ArrayList<Project>();
        for (int i = 0; i < 10; i++) {
            projectList.add(new Project(new Random().nextInt(5), new Random().nextInt(10)));
        }
        for (Project project : projectList) {
            System.out.println("project cost: " + project.cost + "\t project profit: " + project.profit);
        }
        int balance = trade(projectList, 4, 5);
        System.out.println(balance);

    }
}

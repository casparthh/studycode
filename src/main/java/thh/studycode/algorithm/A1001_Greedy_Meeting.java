package thh.studycode.algorithm;

import lombok.AllArgsConstructor;
import org.apache.commons.lang.time.DateUtils;

import java.util.*;

/**
 * 贪心算法的解题套路实战
 * <p>
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日和，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次
 */
public class A1001_Greedy_Meeting {
    static Comparator<Meeting> comparator = new Comparator<Meeting>() {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.endTime.compareTo(o2.endTime);
        }
    };


    @AllArgsConstructor
    static class Meeting {
        Date startTime;
        Date endTime;
    }


    public static int assign1(List<Meeting> meetingList) {
        Date day = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        int size = assign1(meetingList, day, 0);
        return size;
    }


    /**
     * @param meetingList 数据源
     * @param timeline    限制条件
     * @param size        汇总结果
     * @return
     */
    public static int assign1(List<Meeting> meetingList, Date timeline, int size) {
        int max = size;
        for (int i = 0; i < meetingList.size(); i++) {
            Meeting meeting = meetingList.get(i);
            if (meeting.startTime.getTime() >= timeline.getTime()) {
                int assign = assign1(meetingList, meeting.endTime, size + 1);
                max = Math.max(max, assign);
            }
        }
        return max;
    }


//    public static int assign(List<Meeting> meetingList) {
//        Date day = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
//        int size = assign(meetingList, day, 0);
//        return size;
//    }
//
//    public static int assign(List<Meeting> meetingList, Date timeline, int size) {
//        int max = size;
//        for (int i = 0; i < meetingList.size(); i++) {
//            Meeting meeting = meetingList.get(i);
//            if (meeting.startTime.getTime() >= timeline.getTime()) {
//                int assign = assign(copyList(meetingList, i), meeting.endTime, size + 1);
//                max = Math.max(max, assign);
//            }
//        }
//        return max;
//    }
//
//    public static List<Meeting> copyList(List<Meeting> resources, int except) {
//        List<Meeting> newList = new ArrayList<Meeting>();
//        newList.addAll(resources);
//        newList.remove(resources.get(except));
//        return newList;
//    }


    public static int assignByGreedyByQueue(List<Meeting> meetingList) {
        PriorityQueue<Meeting> queue = new PriorityQueue<Meeting>(comparator);
        meetingList.forEach(meeting -> queue.add(meeting));

        int times = 0;
        Date timeline = null;

        while (queue.isEmpty() == false) {
            Meeting meeting = queue.poll();
            if (timeline != null && meeting.startTime.getTime() >= timeline.getTime()) {
                times++;
                timeline = meeting.endTime;
            } else if (timeline == null) {
                times++;
                timeline = meeting.endTime;
            }
        }
        return times;
    }


    public static void main(String[] args) throws Exception {
        String[] format = new String[]{"yyyy-MM-dd HH:mm:ss"};
        Date d8 = DateUtils.parseDate("2020-07-15 08:00:00", format);
        Date d9 = DateUtils.parseDate("2020-07-15 09:00:00", format);
        Date d10 = DateUtils.parseDate("2020-07-15 10:00:00", format);
        Date d11 = DateUtils.parseDate("2020-07-15 11:00:00", format);
        Date d12 = DateUtils.parseDate("2020-07-15 12:00:00", format);
        Date d13 = DateUtils.parseDate("2020-07-15 13:00:00", format);
        Date d14 = DateUtils.parseDate("2020-07-15 14:00:00", format);
        Date d15 = DateUtils.parseDate("2020-07-15 15:00:00", format);
        Date d16 = DateUtils.parseDate("2020-07-15 16:00:00", format);
        Date d17 = DateUtils.parseDate("2020-07-15 17:00:00", format);
        Date d18 = DateUtils.parseDate("2020-07-15 18:00:00", format);
        Date d19 = DateUtils.parseDate("2020-07-15 19:00:00", format);
        Date d20 = DateUtils.parseDate("2020-07-15 20:00:00", format);
        Meeting m1 = new Meeting(d8, d10);
        Meeting m2 = new Meeting(d9, d10);
        Meeting m3 = new Meeting(d11, d15);
        Meeting m4 = new Meeting(d12, d15);
        Meeting m5 = new Meeting(d13, d15);
        Meeting m6 = new Meeting(d10, d12);
        Meeting m7 = new Meeting(d11, d12);
        Meeting m8 = new Meeting(d13, d14);
        Meeting m9 = new Meeting(d14, d16);
        Meeting m10 = new Meeting(d10, d11);
        List<Meeting> meetingList = new ArrayList<Meeting>();
        meetingList.add(m1);
        meetingList.add(m2);
        meetingList.add(m3);
        meetingList.add(m4);
        meetingList.add(m5);
        meetingList.add(m6);
        meetingList.add(m7);
        meetingList.add(m8);
        meetingList.add(m9);
        meetingList.add(m10);


//        int i = assign(meetingList);
        int i1 = assign1(meetingList);
        int qi = assignByGreedyByQueue(meetingList);
//        System.out.println(i);
        System.out.println(i1);
        System.out.println(qi);


        HashSet<String> items = permutation("abcd");
        PriorityQueue<String> queue = new PriorityQueue<String>();
        items.forEach(item -> queue.add(item));
        while (queue.isEmpty() == false) {
            System.out.println(queue.poll());
        }
    }

    public static HashSet<String> permutation(String str) {
        char[] chars = str.toCharArray();
        HashSet<String> items = new HashSet<String>();
        permutation(chars, items, new HashSet<Integer>(), "");
        return items;
    }

    /**
     * @param chars 源数据
     * @param items 数据结果集
     * @param used  限制条件
     * @param path  结果项
     */
    public static void permutation(char[] chars, Set<String> items, Set<Integer> used, String path) {
        if (chars.length == used.size()) {
            items.add(path);
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used.contains(i)) {
                continue;
            }
            used.add(i);
            permutation(chars, items, used, path + chars[i]);
            used.remove(i);
        }
    }

}

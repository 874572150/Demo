package com.os.test1_10;

import org.junit.Test;

/**
 * @Author: Oushuo
 * @Date: 2020/12/14 14:17
 * @Description: 两数相加
 */
public class Test02_AddTwoNumbers {
    @Test
    public void test() {
        ListNode listNode1 = new ListNode(9, new ListNode(9, new ListNode(9)));
        ListNode listNode2 = new ListNode(9, new ListNode(9, new ListNode(9)));

        ListNode listNode = new Test02_AddTwoNumbers().addTwoNumbers(listNode1, listNode2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = null;
        ListNode tempNode = null;
        int jinwei = 0;
        while (true) {
            if (l1 == null && l2 == null) {
                break;
            }
            int num1 = 0;
            int num2 = 0;
            if (l1 != null) {
                num1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                num2 = l2.val;
                l2 = l2.next;
            }
            int sum = num1 + num2 + jinwei;
            jinwei = sum / 10;

            if (listNode == null) {
                listNode = new ListNode(sum % 10);
                tempNode = listNode;
            } else {
                tempNode.next = new ListNode(sum % 10);
                tempNode = tempNode.next;
            }
        }
        if (jinwei == 1) {
            tempNode.next = new ListNode(jinwei);
        }
        return listNode;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}



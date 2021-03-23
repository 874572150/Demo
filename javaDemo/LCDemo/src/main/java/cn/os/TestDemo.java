package cn.os;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author oushuo
 * @date 2021/3/3
 * @description TODO
 */
public class TestDemo {

    abstract class Animals {
        public void crawl() {
            System.out.println("爬");
        }

        abstract public void eat();
        abstract public void speak();
    }

    class Cat extends Animals {
        @Override
        public void eat() {

        }

        @Override
        public void speak() {
     
        }
    }

    interface People {

        static void main(String[] args) {
            System.out.println(1+1);
        }

        public void speak();

    }
}

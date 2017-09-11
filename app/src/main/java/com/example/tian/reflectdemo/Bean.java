package com.example.tian.reflectdemo;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class Bean {
    private String name;
    private int id;

    public Bean(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Bean() {

    }

    public void hehe(String name, int id) {
//        name = "渣";
//        id = 13;
        System.out.println("我是" + name + "ID是" + id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

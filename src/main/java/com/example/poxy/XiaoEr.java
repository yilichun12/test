package com.example.poxy;

public class XiaoEr implements Person {

    private String sex = "女";
    private String name = "牛奶";

    @Override
    public Object findLove() {
        System.out.println("我叫：" + this.name);
        System.out.println("好看的");
        System.out.println("有钱的");
        System.out.println("身高180cm");
        System.out.println("体重70KG");
        return null;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

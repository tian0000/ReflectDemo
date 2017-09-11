package com.example.tian.reflectdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射的Demo
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        普通的对象,我们一般都会这样创建和表示：
        Bean bean = new Bean();
        Log.e("b1", bean.getName() + "----");
//        Class的源码中构造器是私有的, 只有JVM可以创建Class的对象，因此不可以像普通类一样new一个Class对象，虽然我们不能new一个Class对象,但是却可以通过已有的类得到一个Class对象，共有三种方式，如下：
        Class c1 = Bean.class;//这说明任何一个类都有一个隐含的静态成员变量class，这种方式是通过获取类的静态成员变量class得到的
        Log.e("b2", c1.getName() + "----");
        Class c2 = bean.getClass();//code1是Code的一个对象，这种方式是通过一个类的对象的getClass()方法获得的
        Log.e("b3", c2.getName() + "----");
        try {
            Class c3 = Class.forName("com.example.tian.reflectdemo");
            //这种方法是Class类调用forName方法，通过一个类的全量限定名获得
            Log.e("b4", c3.getName() + "----");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //  这里，c1、c2、c3都是Class的对象，他们是完全一样的，而且有个学名，叫做Bean的类类型（class type）。顾名思义，类类型就是类的类型，也就是描述一个类是什么，都有哪些东西，所以我们可以通过类类型知道一个类的属性和方法，并且可以调用一个类的属性和方法，这就是反射的基础。
//------------------------------------------------------------------
//        获取成员方法信息,两个参数分别是方法名和方法参数类的类类型列表。
//        方法一
        Method[] methods = c1.getDeclaredMethods();//获取class对象的所有声明方法
//        方法二
        Method[] methods1 = c1.getMethods();//获取class对象的所有public方法 包括父类的方法
//        方法三
        try {
            Method methods2 = c1.getMethod("hehe", String.class, int.class);
            //返回次Class对象对应类的、带指定形参列表的public方法
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
//        方法四
        try {
            Method methods3 = c1.getDeclaredMethod("hehe", String.class, int.class);//返回次Class对象对应类的、带指定形参列表的方法
            //返回次Class对象对应类的、带指定形参列表的方法
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
//        举个例子：
        initLizi();
//---------------------------------------------------------------------------
//        获取成员变量信息
//        想一想成员变量中都包括什么：成员变量类型+成员变量名 :类的成员变量也是一个对象，它是java.lang.reflect.Field的一个对象，所以我们通过java.lang.reflect.Field里面封装的方法来获取这些信息。
        Field[] fields = c1.getDeclaredFields();//获取class对象的所有属性
        Field[] fields1 = c1.getFields();//获取class对象的public属性
//        c1.getDeclaredField("name");//获取class指定属性
//        c1.getField("name");//获取class指定的public属性
        //        举个例子：
        initBianLian();


    }

    private void initBianLian() {
        try {
            Class c1 = Bean.class;
            Field name = c1.getDeclaredField("name");
            Object o = c1.newInstance();
            name.setAccessible(true);//设置是否允许访问，因为该变量是private的，所以要手动设置允许访问，如果msg是public的就不需要这行了。
            Object o1 = name.get(o);
            Log.e("tian", o1 + "--");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    获取成员方法例子
    private void initLizi() {
        try {
            Class c4 = Class.forName("com.example.tian.reflectdemo");//先生成class
            Object o = c4.newInstance();                 //newInstance可以初始化一个实例
            Method methods4 = c4.getMethod("hehe", String.class, int.class);//获取方法
            methods4.invoke(o, "tian", 18); //通过invoke调用该方法，参数第一个为实例对象，后面为具体参数值
        } catch (Exception e) {
            e.printStackTrace();
        }
//        获取所有方法的数组：
        try {
            Class c = Class.forName("com.example.tian.reflectdemo");
            Method[] methods5 = c.getDeclaredMethods(); // 得到该类所有的方法，不包括父类的
//            或者
            Method[] methods6 = c.getMethods();// 得到该类所有的public方法，包括父类的
//            然后循环这个数组就得到每个方法了：
            for (Method method : methods6) {
                System.out.println(method.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

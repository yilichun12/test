package com.example.licProxy;



import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class LProxy {

    private static final String ln = "\r\n";

    public static Object newProxyInstance(LClassLoader loader, Class<?>[] interfaces, LInvocationHandler h)throws IllegalArgumentException{

        try {
            //1.生产源代码
            String generate = generateSrc(interfaces[0]);

            //2。将生成的源代码输出磁盘，生成。java文件
            String filePath = LProxy.class.getResource("").getPath();
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write(generate);
            fileWriter.flush();
            fileWriter.close();

            //3。编译源代码，生成。class文件

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manager.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null,null,null,iterable);

            task.call();
            manager.close();
            //4。将class问爱你动态加载到jvm

            //5。返回代理后的代理对象
            Class proxyClass = loader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(LInvocationHandler.class);
            f.delete();
            return c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;
    }

    private static String generateSrc(Class<?> interfaces) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("package com.example.licProxy;" + ln);
        buffer.append("import com.example.poxy.Person;" + ln);
        buffer.append("import java.lang.reflect.InvocationHandler;" + ln);
        buffer.append("import java.lang.reflect.Method;" + ln);
        buffer.append("import java.lang.reflect.Proxy;" + ln);


        buffer.append("public class $Proxy0 extends LProxy implements " );
        //遍历多个实现类
        /*for(int i = 0; i < interfaces.length;i ++) {
            if (i == interfaces.length) {
                buffer.append(interfaces[i].getName());
            } else {
                buffer.append(interfaces[i].getName() + ",");
            }

        }*/
        buffer.append(interfaces.getName());
        buffer.append("{");

        buffer.append("LInvocationHandler h;" + ln);
        buffer.append("public $Proxy0 (LInvocationHandler h) {" + ln);
        buffer.append("this.h = h;" + ln);
        buffer.append("}" + ln);

        for (Method m : interfaces.getMethods()) {
            buffer.append("public " + m.getReturnType().getName() + " "+  m.getName() + "(){");
            buffer.append("try {");
            buffer.append("Method m = " + interfaces.getName() + ".class.getMethod(\"" + m.getName()+"\",new Class[]{});"  + ln);
            buffer.append("this.h.invoke(this,m,null);" + ln);
            buffer.append("} catch (Throwable e) { e.printStackTrace();}");
            if ( !m.getReturnType().getName().equals("void")) {
                buffer.append("return null;");
            }
            buffer.append("}" + ln);
        }
        buffer.append("}");


        return buffer.toString();
    }

}

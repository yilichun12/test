package com.example.licProxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LClassLoader extends  ClassLoader {

    private File baseDir;

    public LClassLoader() {
        String basePath = LClassLoader.class.getResource("").getPath();
        this.baseDir = new File(basePath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String className = LClassLoader.class.getPackage().getName() + "." + name;

        if (baseDir != null) {
            File classFile = new File(baseDir,name.replaceAll("\\.","/") + ".class");
            if (classFile.exists()) {
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try {

                    in  = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte [] buff = new byte[1024];
                    int len;
                    while((len = in.read(buff)) != -1){
                        out.write(buff,0,len);
                    }

                    return defineClass(className,out.toByteArray(),0,out.size());
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    classFile.delete();
                    if(null != in ){
                        try {
                            in.close();
                            if (null != out) {
                                out.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return null;
    }
}

package com.wuzz.demo.prototype;

import java.io.*;
import java.util.ArrayList;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 11:56
 * Description 描述:
 */
public class DeepClone implements Cloneable , Serializable {

    public String name;

    public ArrayList<String> list = new ArrayList();

    @Override
    protected Object clone() {
//        return super.clone();
        return deepClone();
    }

    public Object deepClone(){
        try{

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            DeepClone copy = (DeepClone)ois.readObject();

            return copy;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

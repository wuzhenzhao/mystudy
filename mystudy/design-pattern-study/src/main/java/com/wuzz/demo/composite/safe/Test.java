package com.wuzz.demo.composite.safe;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/24 19:33
 * @since 1.0
 **/
public class Test {
    public static void main(String[] args) {

        System.out.println("============安全组合模式===========");

        File qq = new File("QQ.exe");
        File wx = new File("微信.exe");

        Folder office = new Folder("办公软件",2);

        File word = new File("Word.exe");
        File ppt = new File("PowerPoint.exe");
        File excel = new File("Excel.exe");

        office.add(word);
        office.add(ppt);
        office.add(excel);

        Folder wps = new Folder("金山软件",3);
        wps.add(new File("WPS.exe"));
        office.add(wps);

        Folder root = new Folder("根目录",1);
        root.add(qq);
        root.add(wx);
        root.add(office);

        System.out.println("----------show()方法效果-----------");
        root.show();

        System.out.println("----------list()方法效果-----------");
        root.list();
    }
}

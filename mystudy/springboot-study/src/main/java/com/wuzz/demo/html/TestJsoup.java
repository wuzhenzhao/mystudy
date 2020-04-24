package com.wuzz.demo.html;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/4/21 14:35
 * @since 1.0
 **/
public class TestJsoup {

    private String[] cookie = {"QCCSESSID=od3rqlapee0av9spc7l9il7gc0; zg_did=%7B%22did%22%3A%20%2217197ba1d8278c-0bdeb307be984c-3a36520f-1fa400-17197ba1d837c3%22%7D; UM_distinctid=17197ba26f019b-0716e830d5f006-3a36520f-1fa400-17197ba26f2bb4; _uab_collina=158738849182936704903625; acw_tc=73dc082815873884919985674ed0fabc62e4b8cf1e86ac59708d40a039; hasShow=1; CNZZDATA1254842228=541320844-1587383924-https%253A%252F%252Fwww.baidu.com%252F%7C1587451249; Hm_lvt_78f134d5a9ac3f92524914d0247e70cb=1587451360,1587452887,1587452924,1587455061; zg_de1d1a35bfa24ce29bbf2c7eb17e6c4f=%7B%22sid%22%3A%201587455060531%2C%22updated%22%3A%201587455121005%2C%22info%22%3A%201587388489107%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22sp0.baidu.com%22%2C%22zs%22%3A%200%2C%22sc%22%3A%200%2C%22cuid%22%3A%20%2293ade1f5753b164ab986501938f11ce4%22%7D; Hm_lpvt_78f134d5a9ac3f92524914d0247e70cb=1587455121"};

    public Document getDocument(String url) {
        try {
            Connection connect = Jsoup.connect(url);
            //请求头设置，特别是cookie设置
            connect.header("Accept", "text/html, application/xhtml+xml, */*");
            connect.header("Content-Type", "application/x-www-form-urlencoded");
            connect.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))");
            connect.header("cookie", cookie[0]);
            //5000是设置连接超时时间，单位ms
            return connect.timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Document postDocument(String url) {
        try {
            Connection connect = Jsoup.connect(url);
            //请求头设置，特别是cookie设置
            connect.header("Accept", "text/html, application/xhtml+xml, */*");
            connect.header("Content-Type", "application/x-www-form-urlencoded");
            connect.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))");
            //5000是设置连接超时时间，单位ms
            return connect.timeout(5000).post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] readFileByBytes(String fileName) throws IOException {
        File file = new File(fileName);
        InputStream in = null;
        StringBuffer sb = new StringBuffer();


        System.out.println("以字节为单位读取文件内容，一次读多个字节：");
        // 一次读多个字节
        byte[] tempbytes = new byte[1024];
        int byteread = 0;
        in = new FileInputStream(file);

        // 读入多个字节到字节数组中，byteread为一次读入的字节数
        while ((byteread = in.read(tempbytes)) != -1) {
            //  System.out.write(tempbytes, 0, byteread);
            String str = new String(tempbytes, 0, byteread);
            sb.append(str.trim());
        }
        String s = sb.toString();
        return s.split("\r\n");

    }

    public static void createWB(List<EnterpriseInformation> list) throws IOException {
        //创建工作薄对象
//        HSSFWorkbook workbook=new HSSFWorkbook();//这里也可以设置sheet的Name
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表对象
//        HSSFSheet sheet = workbook.createSheet();
        XSSFSheet sheet = workbook.createSheet();
        //创建工作表的行
        XSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("企业法人代表");//第一行第一列为日期
        row1.createCell(1).setCellValue("经营状态");
        row1.createCell(2).setCellValue("成立日期");
        row1.createCell(3).setCellValue("注册资本");
        row1.createCell(4).setCellValue("实缴资本");
        row1.createCell(5).setCellValue("核准日期");
        row1.createCell(6).setCellValue("统一社会信用代码");
        row1.createCell(7).setCellValue("组织机构代码");
        row1.createCell(8).setCellValue("工商注册号");
        row1.createCell(9).setCellValue("纳税人识别号");
        row1.createCell(10).setCellValue("进出口企业代码");
        row1.createCell(11).setCellValue("所属行业");
        row1.createCell(12).setCellValue("企业类型");
        row1.createCell(13).setCellValue("营业期限");
        row1.createCell(14).setCellValue("登记机关");
        row1.createCell(15).setCellValue("人员规模");
        row1.createCell(16).setCellValue("参保人数");
        row1.createCell(17).setCellValue("所属地区");
        row1.createCell(18).setCellValue("企业地址");
        row1.createCell(19).setCellValue("经营范围");
        row1.createCell(20).setCellValue("企业名称");
        int row = 1;
        for (EnterpriseInformation ei : list) {
            XSSFRow sheetRow = sheet.createRow(row);
            sheetRow.createCell(0).setCellValue(ei.getFddbr());//第一行第一列为日期
            sheetRow.createCell(1).setCellValue(ei.getJyzt());
            sheetRow.createCell(2).setCellValue(ei.getClrq());
            sheetRow.createCell(3).setCellValue(ei.getZczb());
            sheetRow.createCell(4).setCellValue(ei.getSjzb());
            sheetRow.createCell(5).setCellValue(ei.getHzrq());
            sheetRow.createCell(6).setCellValue(ei.getTyshxydm());
            sheetRow.createCell(7).setCellValue(ei.getZzjgdm());
            sheetRow.createCell(8).setCellValue(ei.getGszch());
            sheetRow.createCell(9).setCellValue(ei.getNsrsbbs());
            sheetRow.createCell(10).setCellValue(ei.getJckqydm());
            sheetRow.createCell(11).setCellValue(ei.getSshy());
            sheetRow.createCell(12).setCellValue(ei.getQylx());
            sheetRow.createCell(13).setCellValue(ei.getYyqx());
            sheetRow.createCell(14).setCellValue(ei.getDjjg());
            sheetRow.createCell(15).setCellValue(ei.getRygm());
            sheetRow.createCell(16).setCellValue(ei.getCbrs());
            sheetRow.createCell(17).setCellValue(ei.getSsdq());
            sheetRow.createCell(18).setCellValue(ei.getQqdz());
            sheetRow.createCell(19).setCellValue(ei.getJyfw());
            sheetRow.createCell(20).setCellValue(ei.getQymc());
            row++;
        }


        workbook.setSheetName(0, "sheet的Name");//设置sheet的Name

        //文档输出
        FileOutputStream out = new FileOutputStream("D:/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xlsx");
        workbook.write(out);
    }


    public static void main(String[] args) throws InterruptedException {

        String[] strings = new String[0];
        try {
            strings = readFileByBytes("D:/qiyexinxi.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("读取完毕 " + strings.length);
        List<EnterpriseInformation> result = new ArrayList<>();
        TestJsoup t = new TestJsoup();
        for (int i = 0; i < strings.length; i++) {
            String s = strings[i];
            System.out.println("======" + s);
            Document doc = null;
            try {
                doc = t.getDocument("https://www.qcc.com/search?key=" + s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null == doc) {
                result.add(new EnterpriseInformation().setQymc(s));
                Thread.sleep(15000);
                continue;
            }
            if (null == doc.getElementById("countOld")) {
                result.add(new EnterpriseInformation().setQymc(s));
                Thread.sleep(15000);
                continue;
            };
            String count = doc.getElementById("countOld").getElementsByClass("text-danger").text();
            if (null == count) {
                result.add(new EnterpriseInformation().setQymc(s));
                Thread.sleep(15000);
                continue;
            };

            Element tbodyElement = doc.getElementById("search-result");
            if (null == tbodyElement) {
                result.add(new EnterpriseInformation().setQymc(s));
                Thread.sleep(20000);
                continue;
            }
            String attr = null;
            if (Integer.valueOf(count) > 1) {
                attr =tbodyElement.select("tr").get(0).select("a").get(0).attr("href");
            }else{
                attr = tbodyElement.select("a").get(0).attr("href");
            }
            if(null ==attr){
                result.add(new EnterpriseInformation().setQymc(s));
                Thread.sleep(20000);
                continue;
            }
            Document doc2 = null;
            try {
                doc2 = t.getDocument("https://www.qcc.com/" + attr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null == doc2 || null == doc2.getElementById("Cominfo") || null == doc2.getElementById("Cominfo").getElementsByClass("ntable"))
                {result.add(new EnterpriseInformation().setQymc(s));
                    Thread.sleep(15000);
                    continue;
                };
            Elements trs = doc2.getElementById("Cominfo").getElementsByClass("ntable").select("tr");
            if (trs == null) {
                result.add(new EnterpriseInformation().setQymc(s));
                Thread.sleep(15000);
                continue;
            };
            EnterpriseInformation ei = ei = new EnterpriseInformation().setQymc(s);
            try {
                 ei.setClrq(trs.get(0).select("td").get(5).text())
                        .setDjjg(trs.get(4).select("td").get(5).text())
                        .setFddbr(trs.get(0).select("h2").text())
                        .setGszch(trs.get(2).select("td").get(5).text())
                        .setHzrq(trs.get(1).select("td").get(5).text())
                        .setJckqydm(trs.get(3).select("td").get(3).text())
                        .setJyfw(trs.get(8).select("td").get(1).text())
                        .setJyzt(trs.get(0).select("td").get(3).text())
                        .setNsrsbbs(trs.get(3).select("td").get(1).text())
                        .setQqdz(trs.get(7).select("td").get(1).text().replace("查看地图", "").replace("附近企业", "").trim())
                        .setQylx(trs.get(4).select("td").get(1).text())
                        .setSjzb(trs.get(1).select("td").get(3).text())
                        .setSsdq(trs.get(5).select("td").get(5).text())
                        .setSshy(trs.get(3).select("td").get(5).text())
                        .setTyshxydm(trs.get(2).select("td").get(1).text())
                        .setYyqx(trs.get(4).select("td").get(3).text())
                        .setZczb(trs.get(1).select("td").get(1).text())
                        .setZzjgdm(trs.get(2).select("td").get(3).text())
                        .setRygm(trs.get(5).select("td").get(1).text())
                        .setCbrs(trs.get(5).select("td").get(3).text());
            }catch (Exception e){

            }
            result.add(ei);
            Thread.sleep(20000);
        }
        System.out.println(result.size());
        try {
            createWB(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

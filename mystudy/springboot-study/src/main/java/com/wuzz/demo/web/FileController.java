package com.wuzz.demo.web;

import com.wuzz.demo.core.Result;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/24 11:36
 * @since 1.0
 **/
@RestController
@RequestMapping("wuzz")
public class FileController {

    @RequestMapping(value = "/importFile.json", method = {RequestMethod.POST})
    public Result importFile(MultipartFile multipartFile) throws IOException {
        InputStream ins = multipartFile.getInputStream();
        File file = new File(multipartFile.getOriginalFilename());
        inputStreamToFile(ins, file);
        // 3.8版本的POI对excel的导出操作，一般只使用HSSFWorkbook以及 SXSSFWorkbook，
        // HSSFWorkbook用来处理较少的数据量， SXSSFWorkbook用来处理大数据量以及超大数据量的导出。
        // 值得注意的是SXSSFWorkbook只能写不能读。
        // POI之前的版本不支持大数据量处理，如果数据过多则经常报OOM错误，有时候调整JVM大小效果也不是太好。
        // 3.8版本的POI新出来了SXSSFWorkbook,可以支持大数据量的操作，只是 SXSSFWorkbook只支持.xlsx格式，不支持.xls格式。
//        SXSSFWorkbook wk = new SXSSFWorkbook();
        //dispose of temporary files backing this workbook on disk
        //处理磁盘上备份此工作簿的临时文件
//        wk.dispose();
        // 关闭
//        wk.close();
        //该类会出现内存溢出
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = null;

        //获取每个sheet
        sheet = workbook.getSheetAt(0);
        //getPhysicalNumberOfRows获取有记录的行数
        for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
            Row row = sheet.getRow(j);
            if (null != row) {
                //getLastCellNum获取最后一列
                for (int k = 0; k < row.getLastCellNum(); k++) {
                    if (null != row.getCell(k)) {
                        Cell cell = row.getCell(k);
                        cell.setCellType(CellType.STRING);
                        String d = cell.getStringCellValue();
                        System.out.print(d + "  ");
                    }
                }
                System.out.println("\t");
            }

        }
        System.out.println("读取sheet表完成");
        file.delete();
        return new Result(true, "000", "成功: ");
    }

    @RequestMapping(value = "/downLoad", method = RequestMethod.GET)
    public void downLoad(HttpServletResponse httpServletResponse) {
        //输出Excel文件
        OutputStream output = null;
        try {
            XSSFWorkbook wb = new XSSFWorkbook();//创建HSSFWorkbook对象
            XSSFSheet sheet = wb.createSheet("市场监管局数据");//建立sheet对象
            //在sheet里创建第1行
            XSSFRow row = sheet.createRow(0);
            //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
//            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
            //创建单元格并设置单元格内容
            row.createCell(0).setCellValue("查询日期");
            row.createCell(1).setCellValue("企业名称");
            row.createCell(2).setCellValue("注册号");
            row.createCell(3).setCellValue("统一社会信用代码");
            row.createCell(4).setCellValue("法定代表人");
            row.createCell(5).setCellValue("成立日期");
            row.createCell(6).setCellValue("登记机关");
            row.createCell(7).setCellValue("管辖单位");
            row.createCell(8).setCellValue("企业类型");
            row.createCell(9).setCellValue("注册资本");
            row.createCell(10).setCellValue("注册资本折美元");
            row.createCell(11).setCellValue("核准日期");
            row.createCell(12).setCellValue("行业");
            row.createCell(13).setCellValue("经营范围");
            row.createCell(14).setCellValue("经营截止日期");
            row.createCell(15).setCellValue("经营地址");
            row.createCell(16).setCellValue("吊销日期");
            row.createCell(17).setCellValue("注销日期");
            row.createCell(18).setCellValue("国别地区");
            row.createCell(19).setCellValue("投资总额");
            row.createCell(20).setCellValue("投资总额折美元");
            row.createCell(21).setCellValue("住所");
            row.createCell(22).setCellValue("实际出资额");
            row.createCell(23).setCellValue("实际出资额折美元");
            row.createCell(24).setCellValue("外方认缴出资额折美元");
            row.createCell(25).setCellValue("外方认缴出资额");
            row.createCell(26).setCellValue("电话");
            row.createCell(27).setCellValue("证件号码");
            row.createCell(28).setCellValue("档案号");
            row.createCell(29).setCellValue("企业额外属性");
            row.createCell(30).setCellValue("企业风险");
            output = httpServletResponse.getOutputStream();
            httpServletResponse.reset();
            //设置响应头，
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("application/octet-stream");
            httpServletResponse.setHeader("Content-Disposition", "attachment;fileName="
                    + URLEncoder.encode("市场监管局数据导入模板.xlsx", "UTF-8"));
//            IOUtils.copy(inputStream, outputStream);
//            response.flushBuffer();
            wb.write(output);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/sendUploadVoice", method = RequestMethod.GET)
    public void sendUploadVoice(HttpServletResponse res, HttpServletRequest req) {
        try {
            File file = new File("D://吴振照求职简历.doc"); //设置content-disposition响应头控制浏览器以下载的形式打开文件
            res.setCharacterEncoding("utf-8");
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("吴振照求职简历.doc", "UTF-8"));
            InputStream inputStream = new FileInputStream(file);//根据路径获取要下载的文件输入流
            OutputStream out = res.getOutputStream();
            byte[] b = new byte[1024];  //创建数据缓冲区
            int length;
            while ((length = inputStream.read(b)) > 0) {  //把文件流写到缓冲区里
                out.write(b, 0, length);
            }
            out.flush();
            out.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inputStreamToFile(InputStream ins, File file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != ins) {
                    ins.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}

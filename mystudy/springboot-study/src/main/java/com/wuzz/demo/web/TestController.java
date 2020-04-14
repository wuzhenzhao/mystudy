package com.wuzz.demo.web;

import com.wuzz.demo.core.Result;
import com.wuzz.demo.core.exception.BusinessException;
import com.wuzz.demo.core.exception.CommonErrorEnum;
import com.wuzz.demo.entity.EntityDemo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 14:29
 * Description 描述:
 */
@RestController
@RequestMapping("wuzz")
public class TestController {

    @RequestMapping(value = "/post.json", method = {RequestMethod.POST})
    public Result insert(EntityDemo entity) {

        entity.validate();
        if (entity.getId() == null)
            throw new BusinessException(CommonErrorEnum.PARAMER_ERROR);
//            throw new BusinessException(new ErrorObject("001", "error", "数据异常"));

        return new Result(true, "000", "成功: " + entity);
    }

    @RequestMapping(value = "/get.json", method = {RequestMethod.GET})
    public Result get(String name) {

        if (name == null)
            throw new BusinessException(CommonErrorEnum.PARAMER_ERROR);
        return new Result(true, "000", "成功: ");
    }

    @RequestMapping(value = "pus", method = {RequestMethod.GET})
    public Result get1(EntityDemo entity) {
        entity.validate();
        return new Result(true, "000", "成功1111111: ");
    }

    @RequestMapping(value = "/importFile.json", method = {RequestMethod.POST})
    public Result importFile(MultipartFile multipartFile) throws IOException {
        InputStream ins = multipartFile.getInputStream();
        File file = new File(multipartFile.getOriginalFilename());
        inputStreamToFile(ins, file);
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
                        cell.setCellType(Cell.CELL_TYPE_STRING);
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

    @RequestMapping(value = "/sendUploadVoice",method = RequestMethod.GET)
    public void sendUploadVoice(HttpServletResponse res, HttpServletRequest req) {
        try {
            File file=new File("D://吴振照求职简历.doc"); //设置content-disposition响应头控制浏览器以下载的形式打开文件
            res.setCharacterEncoding("utf-8");
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoder.encode("吴振照求职简历.doc", "UTF-8"));
            InputStream inputStream=new FileInputStream(file);//根据路径获取要下载的文件输入流
            OutputStream out = res.getOutputStream();
            byte[] b=new byte[1024];  //创建数据缓冲区
            int length;
            while((length=inputStream.read(b))>0){  //把文件流写到缓冲区里
                out.write(b,0,length);
            }
            out.flush();
            out.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

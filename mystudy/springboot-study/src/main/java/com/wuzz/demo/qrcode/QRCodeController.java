package com.wuzz.demo.qrcode;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/13 10:04
 * @since 1.0
 **/
@RestController
public class QRCodeController {

    @GetMapping("/qrcode")
    public void qrcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String content = "你是猪";
        long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        content += String.valueOf(l);
        if (StringUtils.isBlank(content)) {
            System.out.println("404");
            return;
        }
        //调用工具类，生成二维码
        RecodeUtil.creatRrCode(content, 180, 180, response);   //180为图片高度和宽度
    }

    @PostMapping("/qrcode/read")
    public void read(MultipartFile file) throws Exception {
        File toFile = null;
        InputStream ins = null;
        ins = file.getInputStream();
        toFile = new File(file.getOriginalFilename());
        inputStreamToFile(ins, toFile);
        ins.close();

        RecodeUtil.decode(toFile);
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

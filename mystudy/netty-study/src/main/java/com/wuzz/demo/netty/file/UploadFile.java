package com.wuzz.demo.netty.file;

import java.io.File;
import java.io.Serializable;

/**
 * User: Wuzhenzhao
 * Date: 2020/1/19
 * Time: 18:53
 * Description:
 * ClassPath:com.wuzz.demo.netty.file.FileUploadFile
 */
public class UploadFile implements Serializable {

    private static final long serialVersionUID = 1L;

    private File file;// 文件
    private String fileName;// 文件名
    private int starPos;// 开始位置
    private byte[] bytes;// 文件字节数组
    private int endPos;// 结尾位置

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getStarPos() {
        return starPos;
    }

    public void setStarPos(int starPos) {
        this.starPos = starPos;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}

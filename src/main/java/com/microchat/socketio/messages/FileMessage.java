package com.microchat.socketio.messages;

/**
 * 文件消息对象
 *
 * @author pengju.liao
 * @since 2019年03月27日
 */
public class FileMessage {

    /** 文件大小（字节） */
    private long fileLength;
    /** 文件名称 */
    private String fileName;
    /** 文件地址 */
    private String url;
    /** 文件加密密钥 */
    private String secret;

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}

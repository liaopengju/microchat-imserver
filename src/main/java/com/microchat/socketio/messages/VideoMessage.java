package com.microchat.socketio.messages;

/**
 * 视频文件对象
 *
 * @author pengju.liao
 * @since 2019年03月27日
 */
public class VideoMessage extends FileMessage {

    /** 视频播放长度（单位：秒） */
    private Long length;
    /** 缩略图地址 */
    private String thumb;
    /** 在上传视频缩略图后会返回字符串，只有含有secret才能够下载此视频缩略图 */
    private String thumbSecret;

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getThumbSecret() {
        return thumbSecret;
    }

    public void setThumbSecret(String thumbSecret) {
        this.thumbSecret = thumbSecret;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}

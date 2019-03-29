package com.microchat.socketio.messages;

/**
 * 地理位置消息(其他客户端通讯时消息格式--项目中不用)
 *
 * @author pengju.liao
 * @since 2019年03月27日
 */
public class LocationMessage extends Message {

    /** 地址信息 */
    private String address;
    /** 纬度 */
    private String lat;
    /** 经度 */
    private String lng;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}

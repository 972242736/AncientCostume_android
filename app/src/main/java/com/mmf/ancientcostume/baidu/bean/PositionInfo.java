package com.mmf.ancientcostume.baidu.bean;

import java.util.List;

/**
 * Created by MMF
 * date 2016/11/10
 * Description:
 */
public class PositionInfo {

    /**
     * companyName : 厦门科技有限公司
     * user_id : 1001
     * tags : 用户1
     * uid : 1853461983
     * province : 福建省
     * geotable_id : 157320
     * modify_time : 1478575715
     * district : 湖里区
     * create_time : 1478575437
     * city : 厦门市
     * location : [118.110429,24.524544]
     * address : 福建省厦门市湖里区兴湖路27号
     * title : 用户1
     * coord_type : 3
     * direction : 东
     * type : 0
     * distance : 60
     * weight : 0
     */

    private String companyName;
    private int user_id;
    private String tags;
    private int uid;
    private String province;
    private int geotable_id;
    private int modify_time;
    private String district;
    private int create_time;
    private String city;
    private String address;
    private String title;
    private int coord_type;
    private String direction;
    private int type;
    private int distance;
    private int weight;
    private List<Double> location;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getGeotable_id() {
        return geotable_id;
    }

    public void setGeotable_id(int geotable_id) {
        this.geotable_id = geotable_id;
    }

    public int getModify_time() {
        return modify_time;
    }

    public void setModify_time(int modify_time) {
        this.modify_time = modify_time;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoord_type() {
        return coord_type;
    }

    public void setCoord_type(int coord_type) {
        this.coord_type = coord_type;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }
}


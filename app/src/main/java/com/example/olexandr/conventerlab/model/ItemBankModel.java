package com.example.olexandr.conventerlab.model;

public class ItemBankModel {
    private String name;
    private String region;
    private String city;
    private String phone;
    private String address;
    private String link;

    public ItemBankModel(String _city, String _address, String _phone, String _name, String _region, String _link) {
        this.name = _name;
        this.region = _region;
        this.city = _city;
        this.phone = _phone;
        this.address = _address;
        this.link = _link;

    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {

        return link;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRegion() {
        return region;
    }
}

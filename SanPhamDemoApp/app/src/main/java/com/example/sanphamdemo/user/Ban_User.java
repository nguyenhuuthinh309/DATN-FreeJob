package com.example.sanphamdemo.user;

public class Ban_User {
    private Integer id;
    private String ten;
    private String giovao;
    private Integer gia;
    private Integer trangthai;
    private String ngaythang;

    public Ban_User(Integer id, String ten, String giovao, Integer gia, Integer trangthai, String ngaythang) {
        this.id = id;
        this.ten = ten;
        this.giovao = giovao;
        this.gia = gia;
        this.trangthai = trangthai;
        this.ngaythang = ngaythang;
    }

    public Ban_User(Integer id, String ten, Integer trangthai) {
        this.id = id;
        this.ten = ten;
        this.trangthai = trangthai;
    }

    public Ban_User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGiovao() {
        return giovao;
    }

    public void setGiovao(String giovao) {
        this.giovao = giovao;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public String getNgaythang() {
        return ngaythang;
    }

    public void setNgaythang(String ngaythang) {
        this.ngaythang = ngaythang;
    }
}

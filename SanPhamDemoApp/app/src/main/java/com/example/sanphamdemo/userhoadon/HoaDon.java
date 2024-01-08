package com.example.sanphamdemo.userhoadon;

public class HoaDon {
    int idHoaDon,idUngVien,idCongTy;
    String ngay,noidung,kihan,trangthai,thanhtien;

    public HoaDon(int idHoaDon, int idUngVien, int idCongTy, String ngay, String noidung, String kihan, String trangthai, String thanhtien) {
        this.idHoaDon = idHoaDon;
        this.idUngVien = idUngVien;
        this.idCongTy = idCongTy;
        this.ngay = ngay;
        this.noidung = noidung;
        this.kihan = kihan;
        this.trangthai = trangthai;
        this.thanhtien = thanhtien;
    }

    public HoaDon(int idUngVien, int idCongTy, String ngay, String noidung, String kihan, String trangthai,String thanhtien) {
        this.idUngVien = idUngVien;
        this.idCongTy = idCongTy;
        this.ngay = ngay;
        this.noidung = noidung;
        this.kihan = kihan;
        this.trangthai = trangthai;
        this.thanhtien = thanhtien;
    }

    public HoaDon() {
    }

    public String getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(String thanhtien) {
        this.thanhtien = thanhtien;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdUngVien() {
        return idUngVien;
    }

    public void setIdUngVien(int idUngVien) {
        this.idUngVien = idUngVien;
    }

    public int getIdCongTy() {
        return idCongTy;
    }

    public void setIdCongTy(int idCongTy) {
        this.idCongTy = idCongTy;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getKihan() {
        return kihan;
    }

    public void setKihan(String kihan) {
        this.kihan = kihan;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}

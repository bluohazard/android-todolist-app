package com.bluohazard.todolistapplication.Model;

public class ToDoList {
    int id;
    String namaKegiatan, keterangan, waktu;

    // constructor (empty)
    public ToDoList() {
    }

    public ToDoList(int id, String namaKegiatan, String keterangan, String waktu) {
        this.id = id;
        this.namaKegiatan = namaKegiatan;
        this.keterangan = keterangan;
        this.waktu = waktu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}

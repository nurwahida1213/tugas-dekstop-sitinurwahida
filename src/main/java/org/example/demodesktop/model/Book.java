package org.example.demodesktop.model;

import java.time.LocalDateTime;

public class Book {
    private int id;
    private String nama;
    private String instansi;
    private String judulBuku;
    private LocalDateTime tanggalPeminjaman;
    private LocalDateTime tanggalPengembalian;

    // Getter dan Setter untuk setiap field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public LocalDateTime getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public void setTanggalPeminjaman(LocalDateTime tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public LocalDateTime getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(LocalDateTime tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", instansi='" + instansi + '\'' +
                ", judulBuku='" + judulBuku + '\'' +
                ", tanggalPeminjaman=" + tanggalPeminjaman +
                ", tanggalPengembalian=" + tanggalPengembalian +
                '}';
    }
}

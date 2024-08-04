package org.example.demodesktop.repository;

import org.example.demodesktop.config.DatabaseConnection;
import org.example.demodesktop.model.Book;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookRepository {
    private static final Logger log = Logger.getLogger(BookRepository.class.getName());

    public void save(Book book) {
        String sql = "INSERT INTO daftar_pinjam_buku (nama_peminjam, instansi_peminjam, judul_buku, tanggal_peminjaman, tanggal_pengembalian) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getDbConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getNama());
            statement.setString(2, book.getInstansi());
            statement.setString(3, book.getJudulBuku());
            statement.setTimestamp(4, Timestamp.valueOf(book.getTanggalPeminjaman()));
            statement.setTimestamp(5, Timestamp.valueOf(book.getTanggalPengembalian()));
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error saving book: {0}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Book findById(int id) {
        String sql = "SELECT id, nama, instansi, judul_buku, tanggal_peminjaman, tanggal_pengembalian FROM daftar_pinjam_buku WHERE id = ?";
        Book book = null;
        try (Connection connection = DatabaseConnection.getDbConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    book = mapResultSetToBook(rs);
                }
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error finding book by ID: {0}", e.getMessage());
        }
        return book;
    }

    public List<Book> findAll() {
        String sql = "SELECT id, nama, instansi, judul_buku, tanggal_peminjaman, tanggal_pengembalian FROM daftar_pinjam_buku";
        List<Book> books = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getDbConnection();
             Statement statement = Objects.requireNonNull(connection).createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error finding all books: {0}", e.getMessage());
            throw new RuntimeException(e);
        }
        return books;
    }

    public boolean update(Book book) {
        String sql = "UPDATE daftar_pinjam_buku SET nama = ?, instansi = ?, judul_buku = ?, tanggal_peminjaman = ?, tanggal_pengembalian = ? WHERE id = ?";
        boolean updated = false;
        try (Connection connection = DatabaseConnection.getDbConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(sql)) {
            statement.setString(1, book.getNama());
            statement.setString(2, book.getInstansi());
            statement.setString(3, book.getJudulBuku());
            statement.setTimestamp(4, Timestamp.valueOf(book.getTanggalPeminjaman()));
            statement.setTimestamp(5, Timestamp.valueOf(book.getTanggalPengembalian()));
            statement.setInt(6, book.getId());
            updated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error updating book: {0}", e.getMessage());
        }
        return updated;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM daftar_pinjam_buku WHERE id = ?";
        boolean deleted = false;
        try (Connection connection = DatabaseConnection.getDbConnection();
             PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(sql)) {
            statement.setInt(1, id);
            deleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error deleting book: {0}", e.getMessage());
        }
        return deleted;
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setNama(rs.getString("nama"));
        book.setInstansi(rs.getString("instansi"));
        book.setJudulBuku(rs.getString("judul_buku"));
        book.setTanggalPeminjaman(rs.getTimestamp("tanggal_peminjaman").toLocalDateTime());
        book.setTanggalPengembalian(rs.getTimestamp("tanggal_pengembalian").toLocalDateTime());
        return book;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;
import beans.LibroBeans;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ander
 */
public class LibroDatos {
    // 1. INSERTAR LIBRO
    public boolean insertar(LibroBeans libro) {
        String sql = "INSERT INTO libro (titulo, año_publicacion, Id_Autor, Id_Categoria) VALUES (?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setInt(2, libro.getAñoPublicacion());
            ps.setInt(3, libro.getIdAutor());
            ps.setInt(4, libro.getIdCategoria());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        }
    }

    // 2. LISTAR LIBROS (Para la JTable)
    public List<LibroBeans> listarLibros() {
        List<LibroBeans> lista = new ArrayList<>();
        String sql = "SELECT * FROM libro";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                LibroBeans l = new LibroBeans();
                l.setIdLibro(rs.getInt("Id_libro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAñoPublicacion(rs.getInt("año_publicacion"));
                l.setIdAutor(rs.getInt("Id_Autor"));
                l.setIdCategoria(rs.getInt("Id_Categoria"));
                lista.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(con);
        }
        
        return lista;
    }

    // 3. ACTUALIZAR LIBRO
    public boolean actualizar(LibroBeans libro) {
        String sql = "UPDATE libro SET titulo = ?, año_publicacion = ?, Id_Autor = ?, Id_Categoria = ? WHERE Id_libro = ?";
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setInt(2, libro.getAñoPublicacion());
            ps.setInt(3, libro.getIdAutor());
            ps.setInt(4, libro.getIdCategoria());
            ps.setInt(5, libro.getIdLibro());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        }
    }

    // 4. ELIMINAR LIBRO
    public boolean eliminar(int idLibro) {
        String sql = "DELETE FROM libro WHERE Id_libro = ?";
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idLibro);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.close(ps);
            Conexion.close(con);
        }
    }
}

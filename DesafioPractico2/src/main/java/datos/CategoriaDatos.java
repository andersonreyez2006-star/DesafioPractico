/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;
import beans.CategoriaBeans;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ander
 */
public class CategoriaDatos {
    public List<CategoriaBeans> listarCategorias() {
        List<CategoriaBeans> lista = new ArrayList<>();
        String sql = "SELECT Id_categoria, nombre_categoria FROM categoria";
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                CategoriaBeans cat = new CategoriaBeans();
                cat.setIdcategorias(rs.getInt("Id_categoria"));
                cat.setNombreCategoria(rs.getString("nombre_categoria"));
                lista.add(cat);
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;
import beans.AutorBeans;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ander
 */
public class AutorDatos {
    
    public List<AutorBeans> listarAutores() {
        List<AutorBeans> lista = new ArrayList<>();
        String sql = "SELECT Id_autor, nombre, nacionalidad FROM autor";
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                AutorBeans autor = new AutorBeans();
                // Ajusta el nombre de la columna si en tu BD se llama diferente (ej: IdAutor)
                autor.setIdAutor(rs.getInt("Id_autor")); 
                autor.setNombre(rs.getString("nombre"));
                autor.setNacionalidad(rs.getString("nacionalidad"));
                lista.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Usamos tus métodos seguros de cierre
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(con);
        }
        
        return lista;
    }
}

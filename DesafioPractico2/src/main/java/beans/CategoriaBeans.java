/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author ander
 */
public class CategoriaBeans {
    
    private int idcategorias;
    private String nombreCategoria;
    
    public CategoriaBeans(){}
    
    public CategoriaBeans(int idcategorias, String nombreCategoria){
    
        this.idcategorias = idcategorias;
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * @return the idcategorias
     */
    public int getIdcategorias() {
        return idcategorias;
    }

    /**
     * @param idcategorias the idcategorias to set
     */
    public void setIdcategorias(int idcategorias) {
        this.idcategorias = idcategorias;
    }

    /**
     * @return the nombreCategoria
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * @param nombreCategoria the nombreCategoria to set
     */
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
    @Override
    public String toString(){
        return this.nombreCategoria;
    }
}

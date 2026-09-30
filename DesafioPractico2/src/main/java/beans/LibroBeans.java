/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author ander
 */
public class LibroBeans {
    private int idLibro;
    private String titulo;
    private int añoPublicacion;
    private int idAutor;
    private int idCategoria;

    public LibroBeans() {}

    public LibroBeans(int idLibro, String titulo, int añoPublicacion, int idAutor, int idCategoria) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.añoPublicacion = añoPublicacion;
        this.idAutor = idAutor;
        this.idCategoria = idCategoria;
    }

    public int getIdLibro() { return idLibro; }
    public void setIdLibro(int idLibro) { this.idLibro = idLibro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getAñoPublicacion() { return añoPublicacion; }
    public void setAñoPublicacion(int añoPublicacion) { this.añoPublicacion = añoPublicacion; }

    public int getIdAutor() { return idAutor; }
    public void setIdAutor(int idAutor) { this.idAutor = idAutor; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }
}

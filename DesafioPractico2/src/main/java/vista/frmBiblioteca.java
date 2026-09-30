/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

/**
 *
 * @author ander
 */
import beans.*;
import datos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmBiblioteca extends JFrame {
    
    private JTextField txtId, txtTitulo, txtAnio;
    private JComboBox<AutorBeans> cmbAutor;
    private JComboBox<CategoriaBeans> cmbCategoria;
    private JTable tblLibros;
    private DefaultTableModel modeloTabla;
    private JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar;

    private LibroDatos lDatos = new LibroDatos();
    private AutorDatos aDatos = new AutorDatos();
    private CategoriaDatos cDatos = new CategoriaDatos();

    public frmBiblioteca() {
        setTitle("Sistema de Gestión de Biblioteca");
        setSize(720, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // --- ETIQUETAS Y CAMPOS DE TEXTO ---
        JLabel lblTituloForm = new JLabel("REGISTRO DE LIBROS");
        lblTituloForm.setBounds(30, 20, 250, 25);
        lblTituloForm.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        add(lblTituloForm);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(30, 65, 120, 25);
        add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(150, 65, 200, 25);
        add(txtTitulo);

        JLabel lblAnio = new JLabel("Año Publicación:");
        lblAnio.setBounds(30, 105, 120, 25);
        add(lblAnio);

        txtAnio = new JTextField();
        txtAnio.setBounds(150, 105, 200, 25);
        add(txtAnio);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(30, 145, 120, 25);
        add(lblAutor);

        cmbAutor = new JComboBox<>();
        cmbAutor.setBounds(150, 145, 200, 25);
        add(cmbAutor);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(30, 185, 120, 25);
        add(lblCategoria);

        cmbCategoria = new JComboBox<>();
        cmbCategoria.setBounds(150, 185, 200, 25);
        add(cmbCategoria);

        // Campo ID oculto para saber qué registro editar o eliminar
        txtId = new JTextField();
        txtId.setVisible(false);
        add(txtId);

        // --- BOTONES ---
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(400, 65, 110, 30);
        add(btnGuardar);

        btnEditar = new JButton("Actualizar");
        btnEditar.setBounds(400, 105, 110, 30);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(400, 145, 110, 30);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(400, 185, 110, 30);
        add(btnLimpiar);

        // --- TABLA ---
        tblLibros = new JTable();
        JScrollPane scroll = new JScrollPane(tblLibros);
        scroll.setBounds(30, 235, 640, 220);
        add(scroll);

        // --- CARGAR DATOS INICIALES ---
        cargarAutores();
        cargarCategorias();
        listarTabla();

        // --- EVENTOS DE LOS BOTONES ---
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarLibro();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarLibro();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        // --- SELECCIONAR FILA DE LA TABLA AL HACER CLIC ---
        tblLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tblLibros.getSelectedRow();
                if (fila >= 0) {
                    txtId.setText(tblLibros.getValueAt(fila, 0).toString());
                    txtTitulo.setText(tblLibros.getValueAt(fila, 1).toString());
                    txtAnio.setText(tblLibros.getValueAt(fila, 2).toString());
                }
            }
        });
    }

    // Método para llenar el JComboBox de Autores
    private void cargarAutores() {
        List<AutorBeans> lista = aDatos.listarAutores();
        DefaultComboBoxModel<AutorBeans> model = new DefaultComboBoxModel<>();
        for (AutorBeans a : lista) {
            model.addElement(a);
        }
        cmbAutor.setModel(model);
    }

    // Método para llenar el JComboBox de Categorías
    private void cargarCategorias() {
        List<CategoriaBeans> lista = cDatos.listarCategorias();
        DefaultComboBoxModel<CategoriaBeans> model = new DefaultComboBoxModel<>();
        for (CategoriaBeans c : lista) {
            model.addElement(c);
        }
        cmbCategoria.setModel(model);
    }

    // Método para listar los libros en el JTable
    private void listarTabla() {
        List<LibroBeans> lista = lDatos.listarLibros();
        String[] columnas = {"ID", "Título", "Año", "ID Autor", "ID Categoría"};
        Object[][] datos = new Object[lista.size()][5];

        for (int i = 0; i < lista.size(); i++) {
            datos[i][0] = lista.get(i).getIdLibro();
            datos[i][1] = lista.get(i).getTitulo();
            datos[i][2] = lista.get(i).getAñoPublicacion();
            datos[i][3] = lista.get(i).getIdAutor();
            datos[i][4] = lista.get(i).getIdCategoria();
        }

        modeloTabla = new DefaultTableModel(datos, columnas);
        tblLibros.setModel(modeloTabla);
    }

    // Lógica del botón Guardar
    private void guardarLibro() {
        try {
            String titulo = txtTitulo.getText();
            int anio = Integer.parseInt(txtAnio.getText());
            AutorBeans autor = (AutorBeans) cmbAutor.getSelectedItem();
            CategoriaBeans cat = (CategoriaBeans) cmbCategoria.getSelectedItem();

            LibroBeans lib = new LibroBeans();
            lib.setTitulo(titulo);
            lib.setAñoPublicacion(anio);
            lib.setIdAutor(autor.getIdAutor());
            lib.setIdCategoria(cat.getIdcategorias());

            if (lDatos.insertar(lib)) {
                JOptionPane.showMessageDialog(this, "¡Libro guardado con éxito!");
                listarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el libro.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un año válido (número entero).");
        }
    }

    // Lógica del botón Actualizar
    private void actualizarLibro() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro de la tabla para actualizar.");
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText());
            String titulo = txtTitulo.getText();
            int anio = Integer.parseInt(txtAnio.getText());
            AutorBeans autor = (AutorBeans) cmbAutor.getSelectedItem();
            CategoriaBeans cat = (CategoriaBeans) cmbCategoria.getSelectedItem();

            LibroBeans lib = new LibroBeans();
            lib.setIdLibro(id);
            lib.setTitulo(titulo);
            lib.setAñoPublicacion(anio);
            lib.setIdAutor(autor.getIdAutor());
            lib.setIdCategoria(cat.getIdcategorias());

            if (lDatos.actualizar(lib)) {
                JOptionPane.showMessageDialog(this, "¡Libro actualizado con éxito!");
                listarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el libro.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // Lógica del botón Eliminar
    private void eliminarLibro() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro de la tabla para eliminar.");
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este libro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (lDatos.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.");
                listarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el libro.");
            }
        }
    }

    // Limpiar los campos del formulario
    private void limpiarCampos() {
        txtId.setText("");
        txtTitulo.setText("");
        txtAnio.setText("");
        if (cmbAutor.getItemCount() > 0) cmbAutor.setSelectedIndex(0);
        if (cmbCategoria.getItemCount() > 0) cmbCategoria.setSelectedIndex(0);
    }

    // Método main para ejecutar directamente la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frmBiblioteca().setVisible(true);
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

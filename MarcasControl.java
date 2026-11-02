package polizas.Marcas;

import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JOptionPane;

public class MarcasControl {
    private MarcasVista mv;
    private MarcasModelo mm;
    private List<Marca> lista;

    public MarcasControl(MarcasVista marcasVista) {
        this.mv = marcasVista;
        this.mm = new MarcasModelo();
        marcasEventos();
    }

    public void marcasEventos() {
        mv.btn1.addActionListener(e -> {
            lista = mm.read();
            mv.actualizarVista(lista);
        });

      mv.btn_a.addActionListener(e -> {
            Object marca = new Marca(0, mv.txt1.getText());
            mm.create(marca);
            JOptionPane.showMessageDialog(mv, "Marca agregada con éxito"); 
            mv.actualizarVista(mm.read());
        });

        mv.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println("Registro: " + mv.marcasModelo.getValueAt(mv.tabla.getSelectedRow(), 0));
                mv.txt1.setText((String) mv.marcasModelo.getValueAt(mv.tabla.getSelectedRow(), 1));
            }
        });

        mv.tabla.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                int fila = mv.tabla.getSelectedRow();
                if (fila >= 0) {
                    System.out.println("Registro Fila: " + fila);
                }
            }
        });

        // el de actuazlizr evento
        mv.btn_u.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow();
            if (fila >= 0) {
                try {
                    Object valorId = mv.marcasModelo.getValueAt(fila, 0);
                    int id = Integer.parseInt(valorId.toString());
                    
                    Object marcaActualizada = new Marca(id, mv.txt1.getText());
                    int resultado = mm.update(marcaActualizada);
                    
                    if (resultado > 0) {
                        JOptionPane.showMessageDialog(mv, "Marca actualizada con éxito");
                    } else {
                        JOptionPane.showMessageDialog(mv, "No se pudo actualizar la marca en la base de datos.");
                    }
                    
                    mv.actualizarVista(mm.read());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mv, "Error al actualizar: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(mv, "Selecciona una fila para actualizar");
            }
        });

        // Evento para Eliminar
        mv.btn_d.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow();
            if (fila >= 0) {
                try {
                    Object valorId = mv.marcasModelo.getValueAt(fila, 0);
                    int id = Integer.parseInt(valorId.toString());
                    
                    Object marcaAEliminar = new Marca(id, "");
                    int resultado = mm.delete(marcaAEliminar);
                    
                    if (resultado > 0) {
                        JOptionPane.showMessageDialog(mv, "Marca eliminada con éxito");
                    } else {
                        JOptionPane.showMessageDialog(mv, "No se pudo eliminar la marca en la base de datos.");
                    }
                    
                    mv.actualizarVista(mm.read());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mv, "Error al eliminar: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(mv, "Selecciona una fila para eliminar");
            }
        });
    }
}
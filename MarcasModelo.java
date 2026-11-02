package polizas.Marcas;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import polizas.General.Conexion;
import polizas.General.DAO;

public class MarcasModelo extends Conexion implements DAO {
    
    public MarcasModelo() {
        super();
    }

    @Override
    public List read() {
        List<Marca> lista = new ArrayList<>();
        try {
            this.conectar();
           
            if (cnx != null) {
                ps = cnx.prepareStatement("SELECT * FROM marcas");
                rs = ps.executeQuery();
                lista.clear();
                while (rs.next()) {
                    lista.add(new Marca(rs.getInt("id"), rs.getString("nombre")));
                }
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException ex) {
            System.out.println("Error en lectura: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            if (cnx != null) {
                Marca marca = (Marca) obj;
                ps = cnx.prepareStatement("INSERT INTO marcas (nombre) VALUES (?)");
                ps.setString(1, marca.getNombre());
                return ps.executeUpdate();
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "La conexión a la BD es nula (cnx == null)");
            }
        } catch (SQLException ex) {
   
            javax.swing.JOptionPane.showMessageDialog(null, "Error SQL al crear: " + ex.getMessage());
            System.out.println("Error al crear: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return 0;
    }
    @Override
    public int update(Object obj) {
        try {
            this.conectar();
            if (cnx != null) {
                Marca marca = (Marca) obj;
                ps = cnx.prepareStatement("UPDATE marcas SET nombre = ? WHERE id = ?");
                ps.setString(1, marca.getNombre());
                ps.setInt(2, marca.getId());
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        try {
            this.conectar();
            if (cnx != null) {
                Marca marca = (Marca) obj;
                ps = cnx.prepareStatement("DELETE FROM marcas WHERE id = ?");
                ps.setInt(1, marca.getId());
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return 0;
    }
}
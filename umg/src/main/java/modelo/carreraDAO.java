/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.carreras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author miais
 */
public class carreraDAO {
    
    private static final String SQL_SELECT = "SELECT carcodigo, carnombre, carestatus FROM carreras";
    private static final String SQL_INSERT = "INSERT INTO carreras (carnombre) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE carreras SET carnombre=? WHERE carcodigo = ?";
    private static final String SQL_DELETE = "DELETE FROM carreras WHERE carcodigo=?";
    private static final String SQL_QUERY = "SELECT carcodigo, carnombre FROM carreras WHERE carcodigo = ?";
    
    public List<carreras> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        carreras carrera = null;
        List<carreras> Carreras = new ArrayList<carreras>();

        try {
            conn = conexión.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int carcodigo = rs.getInt("carcodigo");
                String carnombre = rs.getString("carnombre");
                               
                carrera = new carreras();
                carrera.setCarcodigo(carcodigo);
                carrera.setCarnombre(carnombre);
                                
                Carreras.add(carrera);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            conexión.close(rs);
            conexión.close(stmt);
            conexión.close(conn);
        }

        return Carreras;
    }   

    public int insert(carreras carrera) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = conexión.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, carrera.getCarnombre());
            
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            conexión.close(stmt);
            conexión.close(conn);
        }

        return rows;
    }

    public int update(carreras carrera) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = conexión.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, carrera.getCarnombre());
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            conexión.close(stmt);
            conexión.close(conn);
        }

        return rows;
    }

    public int delete(carreras carrera) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = conexión.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, carrera.getCarcodigo());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            conexión.close(stmt);
            conexión.close(conn);
        }

        return rows;
    }


    public carreras query(carreras carrera) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<carreras> Carrera = new ArrayList<carreras>();
        int rows = 0;

        try {
            conn = conexión.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, carrera.getCarcodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigocarrera = rs.getInt("carcodigo");
                String nombrecarrera = rs.getString("carnombre");
                                
                carrera = new carreras();
                carrera.setCarcodigo(codigocarrera);
                carrera.setCarnombre(nombrecarrera);
                
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            conexión.close(rs);
            conexión.close(stmt);
            conexión.close(conn);
        }

        
        return carrera;
    }

}

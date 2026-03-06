/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.jornadas;
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
public class jornadasDAO {
    
    private static final String SQL_SELECT = "SELECT jorcodigo, jornombre FROM jornadas";
    private static final String SQL_INSERT = "INSERT INTO jornadas (jornombre) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE jornadas SET jornombre=? WHERE jorcodigo = ?";
    private static final String SQL_DELETE = "DELETE FROM jornadas WHERE jorcodigo=?";
    private static final String SQL_QUERY = "SELECT jorcodigo, jornombre FROM jornadas WHERE jorcodigo = ?";
    
    public List<jornadas> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        jornadas jornada = null;
        List<jornadas> Jornada = new ArrayList<jornadas>();

        try {
            conn = conexión.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Jorcodigo = rs.getInt("jorcodigo");
                String Jornombre = rs.getString("jornombre");
                               
                jornada = new jornadas();
                jornada.setJorcodigo(Jorcodigo);
                jornada.setJornombre(Jornombre);
                                
                Jornada.add(jornada);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            conexión.close(rs);
            conexión.close(stmt);
            conexión.close(conn);
        }

        return Jornada;
    }
    
    public int insert(jornadas jornada) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = conexión.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, jornada.getJornombre());
            
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
    
    public int update(jornadas jornada) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = conexión.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, jornada.getJornombre());
            
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
    
    public int delete(jornadas jornada) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = conexión.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, jornada.getJorcodigo());
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
    
    public jornadas query(jornadas jornada) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<jornadas> Jornada = new ArrayList<jornadas>();
        int rows = 0;

        try {
            conn = conexión.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, jornada.getJorcodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigocarrera = rs.getInt("carcodigo");
                String nombrecarrera = rs.getString("carnombre");
                                
                jornada = new jornadas();
                jornada.setJorcodigo(codigocarrera);
                jornada.setJornombre(nombrecarrera);
                
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            conexión.close(rs);
            conexión.close(stmt);
            conexión.close(conn);
        }

        
        return jornada;
    }
}

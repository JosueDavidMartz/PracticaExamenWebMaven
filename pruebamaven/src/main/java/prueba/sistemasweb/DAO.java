package prueba.sistemasweb;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

// Data Access Object
public class DAO {
    // en el dao se establece la conexion a la BD
    private static Conexion c = new Conexion();

    // este metodo regresa un conjunto de usuarios que cumpla un criterio
    public static List<Gato> dameGatos() {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Gato> resultado = new ArrayList<>();

        conn = c.getConnection();

        try {
            String sql = "SELECT nombre from gatitos";
            stm = (Statement) conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Gato u = new Gato(rs.getInt("id"), rs.getString("nombre"));
                resultado.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            rs = null;
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return resultado;
    }

    public static String crearGato(Gato u) {
        PreparedStatement stm = null;
        Connection conn = null;
        String msj = "";

        conn = c.getConnection();
        try {
            String sql = "INSERT INTO gato (nombre) values (?)";
            stm = (PreparedStatement) conn.prepareStatement(sql);
            stm.setString(1, u.getNombre());
         
            if (stm.executeUpdate() > 0)
                msj = "gato agregado";
            else
                msj = "gato no agregado";

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }
}

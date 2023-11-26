package dao;

import connections.ConnectionFactory;
import entities.VitaminaD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VitaminaDDao {
    private Connection con;

    public VitaminaDDao() {
        this.con = ConnectionFactory.getConnection();
    }

    public void adiciona(VitaminaD vitaminaD) {
        String sql = "insert into Vitamina_D (id, ng_mL) values (?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, vitaminaD.getId());
            stmt.setString(2, vitaminaD.getNgml());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Integer id) {
        String sql = "delete from Vitamina_D where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(VitaminaD vitaminaD, Integer id) {
        String sql = "update Vitamina_D set ng_mL = ? where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, vitaminaD.getNgml());
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<VitaminaD> lista() {
        String sql = "select * from Vitamina_D";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<VitaminaD> vitaminaDs = new ArrayList<>();
            while (rs.next()) {
                VitaminaD vitaminaD = new VitaminaD();
                vitaminaD.setId(rs.getInt("id"));
                vitaminaD.setNgml(rs.getString("ng_mL"));
                vitaminaDs.add(vitaminaD);
            }
            rs.close();
            stmt.close();
            con.close();
            return vitaminaDs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public VitaminaD buscaPorId(Integer id) {
        String sql = "select * from Vitamina_D where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            VitaminaD vitaminaD = new VitaminaD();
            while (rs.next()) {
                vitaminaD.setId(rs.getInt("id"));
                vitaminaD.setNgml(rs.getString("ng_mL"));
            }
            stmt.close();
            rs.close();
            con.close();
            return vitaminaD;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

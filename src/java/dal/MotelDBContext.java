/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Motel;

/**
 *
 * @author FPTSHOP
 */
public class MotelDBContext extends DBContext {
    public ArrayList<Motel> getMotel()
    {
         ArrayList<Motel> motels = new ArrayList<>();
        try {
            String sql = "SELECT mid,mname,mfloor FROM Motel";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Motel m = new Motel();
                m.setId(rs.getInt("mid"));
                m.setName(rs.getString("mname"));
                 m.setFloor(rs.getInt("mfloor"));
                motels.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MotelDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return motels;
    }
}

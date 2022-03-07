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
import model.Customer;
import model.Motel;
import model.Parent;

/**
 *
 * @author FPTSHOP
 */
public class ParentDBContext extends DBContext {
    public ArrayList<Parent> getParent(int mid) {
        ArrayList<Parent> parents = new ArrayList<>();
        try {
            String sql =  "SELECT c.cid,c.firstName,c.lastName ,c.gender,c.dob,c.address,c.telephone,c.email,p.pid ,p.phonenumber,p.paddress,p.pemail,p.job,p.pname ,m.mfloor,m.mid,m.mname FROM\n" +
                        "Customer c INNER JOIN Parent p  ON c.cid = p.cid\n" +
                        "INNER JOIN Motel m on  m.mid=c.mid";
            if (mid > -1) {
                sql += " WHERE m.mid = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (mid > -1) {
                stm.setInt(1, mid);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Parent p = new Parent();
                Customer c = new Customer();
                Motel m = new Motel();
                c.setId(rs.getInt("cid"));
                c.setFirstName(rs.getString("firstName"));
                c.setLastName(rs.getString("lastName"));
                c.setGender(rs.getBoolean("gender"));
                c.setDob(rs.getDate("dob"));
                c.setAddress(rs.getString("address"));
                c.setEmail(rs.getString("email"));
                c.setTelephone(rs.getInt("telephone"));
                m.setId(rs.getInt("mid"));
                m.setName(rs.getString("mname"));
                m.setFloor(rs.getInt("mfloor"));
                p.setId(rs.getInt("pid"));
                p.setPhonenumber(rs.getInt("phonenumber"));
                p.setAddress(rs.getString("paddress"));
                p.setEmail(rs.getString("pemail"));
                p.setJob(rs.getString("job"));
                p.setPname(rs.getString("pname"));
                p.setM(m);
                p.setC(c);
                parents.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parents;
    }

}

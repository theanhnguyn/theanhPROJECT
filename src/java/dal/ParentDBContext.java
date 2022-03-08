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

    public ArrayList<Parent> getParents(int mid) {
        ArrayList<Parent> parents = new ArrayList<>();
        try {
            String sql = "SELECT c.cid,c.firstName,c.lastName ,c.gender,c.dob,c.address,c.telephone,c.email,p.pid ,p.phonenumber,p.paddress,p.pemail,p.job,p.pname ,m.mfloor,m.mid,m.mname FROM\n"
                    + "Customer c INNER JOIN Parent p  ON c.cid = p.cid\n"
                    + "INNER JOIN Motel m on  m.mid=c.mid";
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

    public Parent getParent(int id) {
        ArrayList<Parent> parents = new ArrayList<>();
        try {
            String sql = "SELECT c.cid,c.firstName,c.lastName ,c.gender,c.dob,c.address,c.telephone,c.email,p.pid ,p.phonenumber,p.paddress,p.pemail,p.job,p.pname ,m.mfloor,m.mid,m.mname FROM\n"
                    + "                    Customer c INNER JOIN Parent p  ON c.cid = p.cid\n"
                    + "                    INNER JOIN Motel m on  m.mid=c.mid\n"
                    + " WHERE p.pid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
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
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertParent(Parent p) {
        {
            String sql = "INSERT INTO [Parent]\n"
                    + "           ([pid]\n"
                    + "           ,[cid]\n"
                    + "           ,[phonenumber]\n"
                    + "           ,[paddress]\n"
                    + "           ,[pemail]\n"
                    + "           ,[job]\n"
                    + "           ,[pname])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = null;
            try {
                stm = connection.prepareStatement(sql);
                stm.setInt(1, p.getId());
                stm.setInt(2, p.getC().getId());
                stm.setInt(3, p.getPhonenumber());
                stm.setString(4, p.getAddress());
                stm.setString(5, p.getEmail());
                stm.setString(6, p.getJob());
                stm.setString(7, p.getPname());
                stm.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (stm != null) {
                    try {
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public void updateParent(Parent p) {
        {
            String sql = "UPDATE [Parent]\n"
                    + "   SET  \n"
                    + "      [cid] = ?\n"
                    + "      ,[phonenumber] = ?\n"
                    + "      ,[paddress] = ?\n"
                    + "      ,[pemail] = ?\n"
                    + "      ,[job] = ?\n"
                    + "      ,[pname] =?\n"
                    + " WHERE [pid]=?\n"
                    + "\n"
                    + "\n"
                    + "";

            PreparedStatement stm = null;
            try {
                stm = connection.prepareStatement(sql);

                stm.setInt(1, p.getC().getId());
                stm.setInt(2, p.getPhonenumber());
                stm.setString(3, p.getAddress());
                stm.setString(4, p.getEmail());
                stm.setString(5, p.getJob());
                stm.setString(6, p.getPname());
                stm.setInt(7, p.getId());
                stm.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (stm != null) {
                    try {
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }

    public void deleteParent(int id) {
         String sql = "DELETE   [Parent]\n" +
             "      WHERE pid =?\n" ;

        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(stm!=null)
            {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null)
            {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ParentDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}

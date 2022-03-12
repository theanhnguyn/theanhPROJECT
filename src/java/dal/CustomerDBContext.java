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

/**
 *
 * @author FPTSHOP
 */
public class CustomerDBContext extends DBContext {

    public ArrayList<Customer> getCustomers(int mid) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            String sql = "SELECT c.cid,c.firstName,c.lastName ,c.gender,c.dob,c.address,c.telephone,c.email,m.mname,m.mid,m.mfloor\n"
                    + "                    FROM Customer c INNER JOIN Motel m \n"
                    + "                    ON c.mid = m.mid\n";
            if (mid > -1) {
                sql += " WHERE m.mid = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (mid > -1) {
                stm.setInt(1, mid);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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
                c.setMotel(m);
                customers.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    public Customer getCustomer(int id) {

        try {
            String sql = "SELECT c.cid,c.firstName,c.lastName ,c.gender,c.dob,c.address,c.telephone,c.email,m.mname,m.mid,m.mfloor\n"
                    + "                    FROM Customer c INNER JOIN Motel m \n"
                    + "                    ON c.mid = m.mid\n"
                    + "                      WHERE c.cid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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
                c.setMotel(m);
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertCustomer(Customer c) {
        String sql = "INSERT INTO [Customer]\n"
                + "           ([cid]\n"
                + "           ,[firstName]\n"
                + "           ,[lastName]\n"
                + "           ,[gender]\n"
                + "           ,[dob]\n"
                + "           ,[email]\n"
                + "           ,[address]\n"
                + "           ,[telephone]\n"
                + "           ,[mid])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, c.getId());
            stm.setString(2, c.getFirstName());
            stm.setString(3, c.getLastName());
            stm.setBoolean(4, c.isGender());
            stm.setDate(5, c.getDob());
            stm.setString(6, c.getEmail());
            stm.setString(7, c.getAddress());
            stm.setInt(8, c.getTelephone());
            stm.setInt(9, c.getMotel().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateCustomer(Customer c) {
        {
            String sql = "UPDATE [Customer]\n"
                    + "   SET [firstName] = ?\n"
                    + "      ,[lastName] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[dob] = ?\n"
                    + "      ,[email] = ?\n"
                    + "      ,[address] =?\n"
                    + "      ,[telephone] = ?\n"
                    + "      ,[mid] = ?\n"
                    + " WHERE [cid]=?\n"
                    + "\n"
                    + "\n"
                    + "";

            PreparedStatement stm = null;
            try {
                stm = connection.prepareStatement(sql);

                stm.setString(1, c.getFirstName());
                stm.setString(2, c.getLastName());
                stm.setBoolean(3, c.isGender());
                stm.setDate(4, c.getDob());
                stm.setString(5, c.getEmail());
                stm.setString(6, c.getAddress());
                stm.setInt(7, c.getTelephone());
                stm.setInt(8, c.getMotel().getId());
                stm.setInt(9, c.getId());
                stm.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (stm != null) {
                    try {
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }

    public void deleteCustomer(int id) {
        String sql = "DELETE Customer"
                + " WHERE [cid] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public ArrayList<Customer> getCustomerById(int mid) {
        ArrayList<Customer> customer = new ArrayList<>();
        try {
            String comment = "";
            String sql = "SELECT c.cid,c.firstName,c.lastName ,c.gender,c.dob,c.address,c.telephone,c.email,m.mname,m.mid,m.mfloor\n"
                    + "                                       FROM Customer c INNER JOIN Motel m \n"
                    + "                                       ON c.mid = m.mid \n"
                    + comment
                    + "                                       order by c.cid desc";
            if (mid > -1) {
                comment = "WHERE m.mid = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (mid > -1) {
                stm.setInt(1, mid);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

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
                c.setMotel(m);
                customer.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public ArrayList<Customer> getCustomerByDob(int mid) {
         ArrayList<Customer> customer = new ArrayList<>();
        try {
            String comment = "";
            String sql = "SELECT c.cid,c.firstName,c.lastName ,c.gender,c.dob,c.address,c.telephone,c.email,m.mname,m.mid,m.mfloor\n"
                    + "                                       FROM Customer c INNER JOIN Motel m \n"
                    + "                                       ON c.mid = m.mid \n"
                    + comment
                    + "                                       order by c.dob asc";
            if (mid > -1) {
                comment = "WHERE m.mid = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (mid > -1) {
                stm.setInt(1, mid);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

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
                c.setMotel(m);
                customer.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }
}

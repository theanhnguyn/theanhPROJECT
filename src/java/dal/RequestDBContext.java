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
import model.Request;

/**
 *
 * @author FPTSHOP
 */
public class RequestDBContext extends DBContext {
     public ArrayList<Request> getRequests()
    {
        ArrayList<Request> requests = new ArrayList<>();
        try {
            String sql = "SELECT rid,rname FROM Request";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Request r = new Request();
                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                requests.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requests;
    }
}

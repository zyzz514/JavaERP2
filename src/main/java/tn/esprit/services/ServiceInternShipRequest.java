
package tn.esprit.services;


import tn.esprit.interfaces.IServiceInternShipRequest;
import tn.esprit.models.Internshiprequest;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceInternShipRequest implements IServiceInternShipRequest<Internshiprequest> {

    private Connection cnx ;

    public ServiceInternShipRequest(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    @Override
    public void add(Internshiprequest i) {
        //1-req sql INSERT
        //2-executer req
        String qry ="INSERT INTO `internshiprequest` (`intern_id`, `cinfile`, `cvfile`, `reclettrefile`,`internship_id` ) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm =cnx.prepareStatement(qry);

            pstm.setInt(1,i.getIntern_id());
            pstm.setString(2,i.getCinfile());
            pstm.setString(3,i.getCvfile());
            pstm.setString(4,i.getReclettrefile());
            pstm.setInt(5,i.getInternship_id());



            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Internshiprequest> getAll() {
        ArrayList<Internshiprequest> internshiprequests = new ArrayList<>();
        String qry = "SELECT * FROM `internshiprequest`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                Internshiprequest i = new Internshiprequest();
                i.setId(rs.getInt("id"));
                i.setIntern_id(rs.getInt("intern_id"));
                i.setCinfile(rs.getString("cinfile"));
                i.setCvfile(rs.getString("cvfile"));
                i.setReclettrefile(rs.getString("reclettrefile"));
                i.setInternship_id(rs.getInt("internship_id"));

                internshiprequests.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return internshiprequests;
    }

    @Override
    public void update(Internshiprequest internShip) {
        String query = "UPDATE `internshiprequest` SET `intern_id` = ?,`cinfile` = ?, `cvfile` = ?, `reclettrefile` = ?,`internship_id` = ? WHERE `id` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(query);
            pstm.setInt(1, internShip.getIntern_id());
            pstm.setString(2, internShip.getCinfile());
            pstm.setString(3, internShip.getCvfile());
            pstm.setString(4, internShip.getReclettrefile());
            pstm.setInt(5, internShip.getInternship_id());
            pstm.setInt(6, internShip.getId());
            pstm.executeUpdate();
            System.out.println("Internship request updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating internship request: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM `internshiprequest` WHERE `id` = ?";
        try {
            PreparedStatement pstm = cnx.prepareStatement(query);
            pstm.setInt(1, id);
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Internship request deleted successfully!");
                return true;
            } else {
                System.out.println("Internship request does not exist or could not be deleted.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting internship request: " + e.getMessage());
            return false;
        }
    }
}

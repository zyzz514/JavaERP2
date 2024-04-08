
package tn.esprit.services;

import tn.esprit.interfaces.IServiceIntern;
import tn.esprit.models.Intern;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceIntern implements IServiceIntern<Intern> {

    private Connection cnx ;

    public ServiceIntern(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    @Override
    public boolean add(Intern i) {
        //1-req sql INSERT
        //2-executer req
        String qry ="INSERT INTO `intern` ( `user_id`,`cin_passport`, `studylevel`, `speciality`, `sector`, `procontact`, `latitude`, `longitude`, `profileimage`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm =cnx.prepareStatement(qry);
            pstm.setInt(1, i.getUser_id());
            pstm.setString(2, i.getCin_passport());
            pstm.setString(3, i.getStudylevel());
            pstm.setString(4, i.getSpeciality());
            pstm.setString(5, i.getSector());
            pstm.setString(6, i.getProcontact());
            pstm.setString(7, i.getLatitude());
            pstm.setString(8, i.getLongitude());
            pstm.setString(9, i.getProfileimage());

            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public ArrayList<Intern> getAll() {
        //1-req SELECT
        //2-recuperation de la base de donné remplissage dans Array
        //3-retour du tableau done
        ArrayList<Intern> interns = new ArrayList<>();
        String qry ="SELECT * FROM `intern`";
        try {
            Statement stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()){
                Intern i = new Intern();
                i.setId(rs.getInt("id"));
                i.setUser_id(rs.getInt("user_id")); // Assuming this is the correct column name for user_id
                i.setCin_passport(rs.getString("cin_passport"));
                i.setStudylevel(rs.getString("studylevel"));
                i.setSpeciality(rs.getString("speciality"));
                i.setSector(rs.getString("sector"));
                i.setProcontact(rs.getString("procontact"));
                i.setLatitude(rs.getString("latitude"));
                i.setLongitude(rs.getString("longitude"));
                i.setProfileimage(rs.getString("profileimage"));

                interns.add(i);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return interns;
    }

    @Override
    public void update(Intern intern) {
        String query = "UPDATE intern SET user_id =? ,cin_passport = ?, studylevel = ?, speciality = ?, sector = ?, procontact = ?, latitude = ?, longitude = ?, profileimage = ? WHERE id = ?";

        try {
            PreparedStatement pstm = cnx.prepareStatement(query);

            // Setting values
            pstm.setInt(1, intern.getUser_id());
            pstm.setString(2, intern.getCin_passport());
            pstm.setString(3, intern.getStudylevel());
            pstm.setString(4, intern.getSpeciality());
            pstm.setString(5, intern.getSector());
            pstm.setString(6, intern.getProcontact());
            pstm.setString(7, intern.getLatitude());
            pstm.setString(8, intern.getLongitude());
            pstm.setString(9, intern.getProfileimage());
            pstm.setInt(10, intern.getId());

            // Execute update
            pstm.executeUpdate();

            System.out.println("Intern updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating intern: " + e.getMessage());
        }
    }


    public boolean delete(int id) {
            // Requête SQL pour supprimer un stage
            String query = "DELETE FROM intern WHERE id = ?";

            try {
                PreparedStatement pstm = cnx.prepareStatement(query);

                // Paramètre pour la requête préparée
                pstm.setInt(1, id);

                // Exécution de la suppression
                int rowsAffected = pstm.executeUpdate();

                // Vérification si une ligne a été affectée (stage supprimé)
                if (rowsAffected > 0) {
                    System.out.println("Stagiaire supprimé avec succès !");
                    return true;
                } else {
                    System.out.println("Le stagiaire n'existe pas ou n'a pas pu être supprimé.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la suppression du stage : " + e.getMessage());
                return false;
            }
    }
}

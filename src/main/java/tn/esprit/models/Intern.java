package tn.esprit.models;

import javafx.beans.property.*;

public class Intern {
    private int id;
    private final IntegerProperty user_id = new SimpleIntegerProperty();
    private String cin_passport;
    private String studylevel;
    private String speciality;
    private String sector;
    private String procontact;
    private String latitude;
    private String longitude;
    private String profileimage;


public Intern(){

}
    public Intern(int id, int userId, String cinPassport, String studylevel, String speciality,
                  String sector, String procontact, String latitude, String longitude, String profileimage) {
        this.id = id;
        this.user_id.set(userId);
        this.cin_passport = cinPassport;
        this.studylevel = studylevel;
        this.speciality = speciality;
        this.sector = sector;
        this.procontact = procontact;
        this.latitude = latitude;
        this.longitude = longitude;
        this.profileimage = profileimage;
    }

    public Intern(int userId, String cinPassport, String studylevel, String speciality,
                  String sector, String procontact, String latitude, String longitude, String profileimage) {
        this.user_id.set(userId);
        this.cin_passport = cinPassport;
        this.studylevel = studylevel;
        this.speciality = speciality;
        this.sector = sector;
        this.procontact = procontact;
        this.latitude = latitude;
        this.longitude = longitude;
        this.profileimage = profileimage;
    }

    public int getUser_id() {
        return user_id.get();
    }

    public IntegerProperty user_idProperty() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id.set(user_id);
    }

    public String getCin_passport() {
        return cin_passport;
    }

    public void setCin_passport(String cin_passport) {
        this.cin_passport = cin_passport;
    }

    public String getStudylevel() {
        return studylevel;
    }

    public void setStudylevel(String studylevel) {
        this.studylevel = studylevel;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getProcontact() {
        return procontact;
    }

    public void setProcontact(String procontact) {
        this.procontact = procontact;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    public IntegerProperty userIdProperty() {
        return user_id;
    }

    public StringProperty cinPassportProperty() {
        return new SimpleStringProperty(cin_passport);
    }

    public StringProperty studylevelProperty() {
        return new SimpleStringProperty(studylevel);
    }

    public StringProperty specialityProperty() {
        return new SimpleStringProperty(speciality);
    }

    public StringProperty sectorProperty() {
        return new SimpleStringProperty(sector);
    }

    public StringProperty procontactProperty() {
        return new SimpleStringProperty(procontact);
    }

    public StringProperty latitudeProperty() {
        return new SimpleStringProperty(latitude);
    }

    public StringProperty longitudeProperty() {
        return new SimpleStringProperty(longitude);
    }

    public StringProperty profileimageProperty() {
        return new SimpleStringProperty(profileimage);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return user_id.get();
    }

    public void setUserId(int userId) {
        this.user_id.set(userId);
    }

    public String getCinPassport() {
        return cin_passport;
    }

    public void setCinPassport(String cinPassport) {
        this.cin_passport = cinPassport;
    }

    // Add similar getter and setter methods for other fields...
    @Override
    public String toString() {
        return "intern{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", cin_passport='" + cin_passport + '\'' +
                ", studylevel='" + studylevel + '\'' +
                ", speciality='" + speciality + '\'' +
                ", sector='" + sector + '\'' +
                ", procontact='" + procontact + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", profileimage='" + profileimage + '\'' +
                '}';
    }
}


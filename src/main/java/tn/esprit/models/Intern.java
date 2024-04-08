package tn.esprit.models;

public class Intern {
    private int id ,user_id;
    private String cin_passport,studylevel,speciality, sector;
    private String procontact,latitude,longitude,profileimage;
    public Intern(int id, int user_id, String cin_passport, String studylevel, String speciality,
                  String sector, String procontact, String latitude, String longitude, String profileimage) {

        this.id = id;
        this.user_id = user_id;
        this.cin_passport = cin_passport;
        this.studylevel = studylevel;
        this.speciality = speciality;
        this.sector = sector;
        this.procontact = procontact;
        this.latitude = latitude;
        this.longitude = longitude;
        this.profileimage = profileimage;
    }

    public Intern( int user_id, String cin_passport, String studylevel, String speciality,
                  String sector, String procontact, String latitude, String longitude, String profileimage) {


        if (isValidStudyLevel(studylevel)) {
            this.studylevel = studylevel;
        } else {
            throw new IllegalArgumentException("Invalid study level: " + studylevel);
        }
        this.user_id = user_id;
        this.cin_passport = cin_passport;
        this.speciality = speciality;
        this.sector = sector;
        this.procontact = procontact;
        this.latitude = latitude;
        this.longitude = longitude;
        this.profileimage = profileimage;
    }
    private boolean isValidStudyLevel(String studylevel) {

        return studylevel != null && (studylevel.equals("Bachelor") || studylevel.equals("Master") || studylevel.equals("PhD"));
    }

    public Intern() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCin_passport() {
        return cin_passport;
    }

    public void setCin_passport(String cin_passport) {
        if (validateCinPassport(cin_passport)) {
            this.cin_passport = cin_passport;
        } else {
            throw new IllegalArgumentException("Invalid value for cin_passport: " + cin_passport);
        }
    }

    private boolean validateCinPassport(String cin_passport) {
        boolean isValid = true;

        // Validate each field individually
        if (cin_passport == null || cin_passport.isEmpty()) {
            System.out.println("CIN/passport is required.");
            isValid = false;
        }
        return isValid;
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

package tn.esprit.models;

public class Internshiprequest {
    private int id,intern_id,internship_id;
    private String cinfile,cvfile,reclettrefile;

    public Internshiprequest(int id, int intern_id, int internship_id, String cinfile, String cvfile, String reclettrefile) {
        this.id = id;
        this.intern_id = intern_id;
        this.internship_id = internship_id;
        if (isValidCinFile(cinfile)) {
            this.cinfile = cinfile;
        } else {
            throw new IllegalArgumentException("cinfile must be not null: " + cinfile);
        }

        this.cvfile = cvfile;
        this.reclettrefile = reclettrefile;
    }
    private boolean isValidCinFile(String cinfile){
        if (cinfile==null || cinfile.isEmpty()){
            return false;
        }
        return true;
    }
    public Internshiprequest( int intern_id, int internship_id, String cinfile, String cvfile, String reclettrefile) {
        this.intern_id = intern_id;
        this.internship_id = internship_id;
        this.cinfile = cinfile;
        this.cvfile = cvfile;
        this.reclettrefile = reclettrefile;
    }

    public Internshiprequest(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntern_id() {
        return intern_id;
    }

    public void setIntern_id(int intern_id) {
        this.intern_id = intern_id;
    }

    public int getInternship_id() {
        return internship_id;
    }

    public void setInternship_id(int internship_id) {
        this.internship_id = internship_id;
    }

    public String getCinfile() {
        return cinfile;
    }

    public void setCinfile(String cinfile) {
        this.cinfile = cinfile;
    }

    public String getCvfile() {
        return cvfile;
    }

    public void setCvfile(String cvfile) {
        this.cvfile = cvfile;
    }

    public String getReclettrefile() {
        return reclettrefile;
    }

    public void setReclettrefile(String reclettrefile) {
        this.reclettrefile = reclettrefile;
    }

    @Override
    public String toString() {
        return "internshiprequest{" +
                "id=" + id +
                ", intern_id=" + intern_id +
                ", internship_id=" + internship_id +
                ", cinfile='" + cinfile + '\'' +
                ", cvfile='" + cvfile + '\'' +
                ", reclettrefile='" + reclettrefile + '\'' +
                '}';
    }
}

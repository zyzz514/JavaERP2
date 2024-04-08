package tn.esprit;

import tn.esprit.models.Intern;
import tn.esprit.models.Internshiprequest;
import tn.esprit.services.ServiceIntern;
import tn.esprit.services.ServiceInternShipRequest;


public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");


        //  ServiceIntern serve=new ServiceIntern();
        //Intern i2 =new Intern(1,"3333","Master","informatique","agriculture","pro1@pro.com","1.778","7.658","image");
        //serve.add(i2);

        //Internshiprequest i3=new Internshiprequest(6,1,"cin06","cv06","rec06");
       // Internshiprequest i4=new Internshiprequest(3,8,1,"test","test","rectest");
        ServiceInternShipRequest servI=new ServiceInternShipRequest();
       // servI.add(i3);
       // servI.update(i4);
       System.out.println( servI.getAll());

    }
}
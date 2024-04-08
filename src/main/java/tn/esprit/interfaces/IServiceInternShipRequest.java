package tn.esprit.interfaces;

import java.util.ArrayList;

public interface IServiceInternShipRequest<T> {

    void add (T t );
    ArrayList<T> getAll();

    void update(T t );
    boolean delete (int t);


}
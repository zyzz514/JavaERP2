package tn.esprit.interfaces;

import java.util.ArrayList;

public interface IServiceIntern<T> {

    boolean add (T t );
    ArrayList<T> getAll();

    void update(T t );
    boolean delete (int t);
//findby..

    //getby ...

}

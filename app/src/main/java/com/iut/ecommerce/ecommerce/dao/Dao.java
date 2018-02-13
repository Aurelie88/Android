package com.iut.ecommerce.ecommerce.dao;

/**
 * Created by Damien on 25/01/2018.
 */

public interface Dao<C> {

    void findAll();
    void create(C objet);
    void update(C objet);
    void delete(C objet);

    void traiteFindAll(String result);
}

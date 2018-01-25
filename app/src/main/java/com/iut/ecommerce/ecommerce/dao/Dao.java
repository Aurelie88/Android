package com.iut.ecommerce.ecommerce.dao;

/**
 * Created by Damien on 25/01/2018.
 */

public interface Dao<C> {

    public void findAll();
    public void create();
    public void update();
    public void delete();

    public void traiteFindAll(String result);
}

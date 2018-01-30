package com.iut.ecommerce.ecommerce.dao;

import com.iut.ecommerce.ecommerce.modele.Categorie;

/**
 * Created by Damien on 25/01/2018.
 */

public interface Dao<C> {

    public void findAll();
    public void create(C objet);
    public void update(C objet);
    public void delete(C objet);

    public void traiteFindAll(String result);
}

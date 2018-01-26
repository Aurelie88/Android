package com.iut.ecommerce.ecommerce.dao;

import java.util.ArrayList;

/**
 * Created by Damien on 25/01/2018.
 */

public interface ActiviteEnAttenteAvecResultat extends ActiviteEnAttente {

    public void notifyRetourRequete(String resultat);
    public void notifyRetourRequeteFindAll(ArrayList liste);

}

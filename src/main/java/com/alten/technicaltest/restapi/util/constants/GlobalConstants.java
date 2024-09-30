package com.alten.technicaltest.restapi.util.constants;

public final class GlobalConstants {

    private GlobalConstants() throws InstantiationException {
        throw new InstantiationException("Instances of this type are forbidden");
    }
    /**
     * PATH API
     **/
    public static final String BASE_PATH = "/alten/technical-test/v1/products-management/";
    /**
     * OpenApi
     **/
    public static final String INFO_API_TITLE = "ALTEN TEST TECHNIQUE RESTFUL APIs : GESTION DES PRODUITS";
    public static final String INFO_API_DESCRIPTION_PRODUITS = "RESTful API pour la gestion des produits.";
    public static final String PRODUITS_APIS_TAG = "Produits APIs";


    /**
     * API ERROR CODES
     **/
    private static final String BASE_URI = "/problem";

}

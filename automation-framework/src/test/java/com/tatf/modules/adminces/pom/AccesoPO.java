package com.tatf.modules.adminces.pom;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.util.ConfigReader;

public class AccesoPO {

    private final IBrowser browser;

    private static final ConfigReader CONFIG =
            new ConfigReader("config.properties");

    private static final String URL_ADMINCES =
            CONFIG.asString("adminces.url");

    private static final String CLAVE_ACCESO =
            CONFIG.asString("adminces.access_key");

    private static final String CAMPO_CLAVE =
            "input[type='password']";

    private static final String BOTON_INGRESAR =
            "button[type='submit']";


    public AccesoPO(IBrowser browser) {
        this.browser = browser;
    }


    public void abrir() {
        browser.interaction()
                .navigateTo(URL_ADMINCES);
    }


    public void ingresarClave() {
        browser.find()
                .css(CAMPO_CLAVE)
                .clear()
                .write(CLAVE_ACCESO);
    }


    public void clickIngresar() {
        browser.find()
                .css(BOTON_INGRESAR)
                .click();
    }


    public InicioPO acceder() {

        abrir();
        ingresarClave();
        clickIngresar();

        InicioPO inicioPO =
                new InicioPO(browser);

        inicioPO.esperarCarga();

        return inicioPO;
    }
}
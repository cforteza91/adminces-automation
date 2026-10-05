package com.tatf.modules.adminces.pom;

import com.tatf.core.browser.IBrowser;

public class InicioPO {

    private final IBrowser browser;

    private static final String LINK_REGISTRARSE =
            "Registrarse";

    private static final String LINK_INICIAR_SESION =
            "Iniciar sesión";

    private static final String TARJETA_REINICIAR_CONTRASENA =
            "//a[@href='/adminces/forgot-password' " +
                    "and .//div[normalize-space()='Reiniciar contraseña']]";


    public InicioPO(IBrowser browser) {
        this.browser = browser;
    }


    public void esperarCarga() {
        browser.wait(LINK_REGISTRARSE).link();
    }


    public RegistroAdministradorPO irARegistroAdministrador() {

        browser.find()
                .link(LINK_REGISTRARSE)
                .click();

        RegistroAdministradorPO registroPO =
                new RegistroAdministradorPO(browser);

        registroPO.esperarCarga();

        return registroPO;
    }


    public LoginPO irAInicioSesion() {

        browser.find()
                .link(LINK_INICIAR_SESION)
                .click();

        LoginPO loginPO =
                new LoginPO(browser);

        loginPO.esperarCarga();

        return loginPO;
    }


    public ReiniciarContrasenaPO irAReiniciarContrasena() {

        browser.find()
                .xpath(TARJETA_REINICIAR_CONTRASENA)
                .click();

        ReiniciarContrasenaPO reiniciarPO =
                new ReiniciarContrasenaPO(browser);

        reiniciarPO.esperarCarga();

        return reiniciarPO;
    }
}
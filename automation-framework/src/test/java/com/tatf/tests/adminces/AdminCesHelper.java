package com.tatf.tests.adminces;

import com.tatf.core.browser.IBrowser;

public final class AdminCesHelper {

    private static final String URL_ADMINCES =
            "http://cestore.ces.com.uy/adminces/";

    private static final String CLAVE_ACCESO =
            "3)ea60e0be3ba12c6ecd%7297868%5c4";


    private AdminCesHelper() {
        // Evita instanciar la clase
    }


    // ==================================================
    // ACCESO GENERAL A ADMINCES
    // ==================================================

    public static void accederAdminCes(IBrowser browser) {

        browser.interaction().navigateTo(URL_ADMINCES);

        browser.find()
                .css("input[type='password']")
                .clear()
                .write(CLAVE_ACCESO);

        browser.find()
                .css("button[type='submit']")
                .click();

        browser.wait("Registrarse").link();
    }


    // ==================================================
    // CERRAR SWEETALERT CON BOTÓN OK
    // ==================================================

    public static void cerrarMensajeOk(IBrowser browser) {

        browser.wait("//button[normalize-space()='OK']").xpath();

        browser.find()
                .xpath("//button[normalize-space()='OK']")
                .click();
    }


    // ==================================================
    // INICIAR SESIÓN COMO ADMINISTRADOR
    // ==================================================

    public static void iniciarSesion(
            IBrowser browser,
            String email,
            String password
    ) {

        browser.wait("Iniciar sesión").link();

        browser.find()
                .link("Iniciar sesión")
                .click();

        browser.wait("inputEmail").name();

        browser.find()
                .name("inputEmail")
                .write(email);

        browser.find()
                .name("inputPassword")
                .write(password);

        browser.find()
                .xpath("//button[contains(text(),'Iniciar Sesión')]")
                .click();
    }


    // ==================================================
    // CREAR ADMINISTRADOR AUXILIAR
    // Usar solamente cuando crear Administrador sea
    // preparación para otro escenario.
    // ==================================================

    public static void crearAdministradorAuxiliar(
            IBrowser browser,
            String nombre,
            String apellido,
            String email,
            String password,
            String pais
    ) {

        browser.wait("Registrarse").link();

        browser.find()
                .link("Registrarse")
                .click();

        browser.wait("inputFirstName").name();

        browser.find()
                .name("inputFirstName")
                .write(nombre);

        browser.find()
                .name("inputLastName")
                .write(apellido);

        browser.find()
                .name("inputEmail")
                .write(email);

        browser.find()
                .name("inputPassword")
                .write(password);

        browser.find()
                .name("inputRepeatPassword")
                .write(password);

        browser.find()
                .name("inputCountry")
                .write(pais);

        browser.find()
                .id("btnRegister")
                .click();
    }
}
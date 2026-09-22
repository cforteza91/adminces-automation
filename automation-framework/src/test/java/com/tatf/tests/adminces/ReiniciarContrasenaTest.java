package com.tatf.tests.adminces;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ReiniciarContrasenaTest {

    private static IBrowser browser;
    private static IVerify verify;

    @BeforeAll
    static void beforeAll() {
        browser = BrowserFactory.getBrowser(true);
        verify = IVerify.create();
    }

    @AfterAll
    static void afterAll() {
        BrowserFactory.quitBrowser();
    }

    @Test
    void reiniciarContrasena() {

        // ==================================================
        // DATOS DE PRUEBA
        // ==================================================

        String email =
                "reset.test."
                        + System.currentTimeMillis()
                        + "@gmail.com";

        String passwordInicial = "Clave123!";
        String passwordNueva = "NuevaClave456!";


        // ==================================================
        // 1. ACCEDER A ADMINCES
        // ==================================================

        AdminCesHelper.accederAdminCes(browser);


        // ==================================================
        // 2. CREAR ADMINISTRADOR AUXILIAR
        // ==================================================

        AdminCesHelper.crearAdministradorAuxiliar(
                browser,
                "Usuario",
                "Reset",
                email,
                passwordInicial,
                "Uruguay"
        );


        // ==================================================
        // 3. VALIDAR CREACIÓN DEL ADMINISTRADOR
        // ==================================================

        browser.wait("swal2-html-container").id();

        String mensajeCreacion = browser.find()
                .id("swal2-html-container")
                .getText();

        verify.verify(
                "Usuario creado.",
                mensajeCreacion,
                "No se pudo crear el Administrador necesario para la prueba."
        );

        AdminCesHelper.cerrarMensajeOk(browser);


        // ==================================================
        // 4. IR A REINICIAR CONTRASEÑA
        // ==================================================

        browser.find()
                .xpath(
                        "//a[@href='/adminces/forgot-password' " +
                                "and .//div[normalize-space()='Reiniciar contraseña']]"
                )
                .click();


        // ==================================================
        // 5. ESPERAR FORMULARIO
        // ==================================================

        browser.wait(
                "//input[@type='email' and @placeholder='Email']"
        ).xpath();


        // ==================================================
        // 6. COMPLETAR REINICIO DE CONTRASEÑA
        // ==================================================

        browser.find()
                .xpath("//input[@type='email' and @placeholder='Email']")
                .write(email);

        browser.find()
                .name("inputPassword")
                .write(passwordNueva);

        browser.find()
                .name("inputRepeatPassword")
                .write(passwordNueva);

        browser.find()
                .id("btnReset")
                .click();


        // ==================================================
        // 7. VALIDAR RESPUESTA DEL SISTEMA
        // ==================================================

        browser.wait("swal2-html-container").id();

        /*
         * No fijamos acá un texto concreto porque el objetivo
         * principal del escenario es comprobar posteriormente
         * que la nueva contraseña permite iniciar sesión.
         */

        AdminCesHelper.cerrarMensajeOk(browser);


        // ==================================================
        // 8. INICIAR SESIÓN CON LA NUEVA CONTRASEÑA
        // ==================================================

        AdminCesHelper.iniciarSesion(
                browser,
                email,
                passwordNueva
        );


        // ==================================================
        // 9. CERRAR MENSAJE DEL LOGIN
        // ==================================================

        AdminCesHelper.cerrarMensajeOk(browser);


        // ==================================================
        // 10. VALIDAR QUE LA NUEVA CONTRASEÑA FUNCIONA
        // ==================================================

        browser.wait("Crear usuario").link();

        String textoCrearUsuario = browser.find()
                .link("Crear usuario")
                .getText();

        verify.verify(
                "Crear usuario",
                textoCrearUsuario,
                "No fue posible iniciar sesión utilizando la nueva contraseña."
        );
    }
}
package com.tatf.tests.adminces;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CrearAdministradorTest {

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
    void crearCuentaAdministrador() {

        // ==================================================
        // DATOS DE PRUEBA
        // ==================================================

        String nombre = "Juan";
        String apellido = "Perez";

        String email =
                "juan.perez."
                        + System.currentTimeMillis()
                        + "@gmail.com";

        String password = "Clave123!";
        String pais = "Uruguay";


        // ==================================================
        // 1. ACCEDER A ADMINCES
        // ==================================================

        AdminCesHelper.accederAdminCes(browser);


        // ==================================================
        // 2. IR A REGISTRARSE
        // ==================================================

        browser.find()
                .link("Registrarse")
                .click();

        browser.wait("inputFirstName").name();


        // ==================================================
        // 3. COMPLETAR REGISTRO
        // ==================================================

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


        // ==================================================
        // 4. VALIDAR CREACIÓN
        // ==================================================

        browser.wait("swal2-html-container").id();

        String mensaje = browser.find()
                .id("swal2-html-container")
                .getText();

        verify.verify(
                "Usuario creado.",
                mensaje,
                "No se mostró el mensaje esperado de creación."
        );


        // ==================================================
        // 5. CERRAR MENSAJE
        // ==================================================

        AdminCesHelper.cerrarMensajeOk(browser);


        // ==================================================
        // 6. INICIAR SESIÓN CON EL ADMINISTRADOR CREADO
        // ==================================================

        AdminCesHelper.iniciarSesion(
                browser,
                email,
                password
        );


        // ==================================================
        // 7. CERRAR MENSAJE DEL LOGIN
        // ==================================================

        AdminCesHelper.cerrarMensajeOk(browser);


        // ==================================================
        // 8. VALIDAR ACCESO
        // ==================================================

        browser.wait("Crear usuario").link();

        String textoCrearUsuario = browser.find()
                .link("Crear usuario")
                .getText();

        verify.verify(
                "Crear usuario",
                textoCrearUsuario,
                "No se pudo iniciar sesión con el Administrador creado."
        );
    }
}
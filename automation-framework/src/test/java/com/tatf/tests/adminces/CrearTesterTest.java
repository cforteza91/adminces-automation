package com.tatf.tests.adminces;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CrearTesterTest {

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
    void crearCuentaTester() {

        // ==================================================
        // DATOS DE PRUEBA
        // ==================================================

        String emailAdministrador =
                "admin.tester."
                        + System.currentTimeMillis()
                        + "@gmail.com";

        String passwordAdministrador = "Clave123!";

        String nombreTester = "Carlos";
        String apellidoTester = "Prueba";

        String emailTester =
                "tester."
                        + System.currentTimeMillis()
                        + "@gmail.com";

        String passwordTester = "Tester123!";
        String paisTester = "Uruguay";


        // ==================================================
        // 1. ACCEDER A ADMINCES
        // ==================================================

        AdminCesHelper.accederAdminCes(browser);


        // ==================================================
        // 2. CREAR ADMINISTRADOR AUXILIAR
        // ==================================================

        AdminCesHelper.crearAdministradorAuxiliar(
                browser,
                "Admin",
                "Tester",
                emailAdministrador,
                passwordAdministrador,
                "Uruguay"
        );


        // ==================================================
        // 3. VALIDAR CREACIÓN DEL ADMINISTRADOR
        // ==================================================

        browser.wait("swal2-html-container").id();

        String mensajeCreacionAdministrador = browser.find()
                .id("swal2-html-container")
                .getText();

        verify.verify(
                "Usuario creado.",
                mensajeCreacionAdministrador,
                "No se pudo crear el Administrador necesario para la prueba."
        );

        AdminCesHelper.cerrarMensajeOk(browser);


        // ==================================================
        // 4. INICIAR SESIÓN COMO ADMINISTRADOR
        // ==================================================

        AdminCesHelper.iniciarSesion(
                browser,
                emailAdministrador,
                passwordAdministrador
        );


        // ==================================================
        // 5. CERRAR MENSAJE POSTERIOR AL LOGIN
        // ==================================================

        AdminCesHelper.cerrarMensajeOk(browser);


        // ==================================================
        // 6. VALIDAR INGRESO DEL ADMINISTRADOR
        // ==================================================

        browser.wait("Crear usuario").link();

        String textoCrearUsuario = browser.find()
                .link("Crear usuario")
                .getText();

        verify.verify(
                "Crear usuario",
                textoCrearUsuario,
                "No se pudo iniciar sesión con el Administrador."
        );


        // ==================================================
        // 7. IR A CREAR USUARIO TESTER
        // ==================================================

        browser.find()
                .link("Crear usuario")
                .click();

        browser.wait("inputFirstName").name();


        // ==================================================
        // 8. COMPLETAR DATOS DEL TESTER
        // ==================================================

        browser.find()
                .name("inputFirstName")
                .write(nombreTester);

        browser.find()
                .name("inputLastName")
                .write(apellidoTester);

        browser.wait(
                "//input[@type='email' and @placeholder='Email']"
        ).xpath();

        browser.find()
                .xpath("//input[@type='email' and @placeholder='Email']")
                .write(emailTester);

        browser.find()
                .name("inputCountry")
                .selectValue(paisTester);

        browser.find()
                .name("inputPassword")
                .write(passwordTester);


        // ==================================================
        // 9. SELECCIONAR PERFIL TESTER SENIOR
        // ==================================================

        browser.find()
                .id("testerSenior")
                .click();


        // ==================================================
        // 10. CREAR CUENTA TESTER
        // ==================================================

        browser.find()
                .id("btnRegister")
                .click();


        // ==================================================
        // 11. VALIDAR MENSAJE DE CREACIÓN
        // ==================================================

        browser.wait("swal2-html-container").id();

        String mensajeCreacionTester = browser.find()
                .id("swal2-html-container")
                .getText();

        verify.verify(
                "Usuario creado.",
                mensajeCreacionTester,
                "No se mostró el mensaje esperado al crear la cuenta Tester."
        );


        // ==================================================
        // 12. CERRAR MENSAJE
        // ==================================================

        AdminCesHelper.cerrarMensajeOk(browser);


        // ==================================================
        // 13. IR A VER USUARIOS
        // ==================================================

        browser.wait("Ver usuarios").link();

        browser.find()
                .link("Ver usuarios")
                .click();


        // ==================================================
        // 14. BUSCAR EL TESTER CREADO
        // ==================================================

        String xpathEmailTester =
                "//td[normalize-space()='" + emailTester + "']";

        browser.wait(xpathEmailTester).xpath();

        String emailObtenido = browser.find()
                .xpath(xpathEmailTester)
                .getText();


        // ==================================================
        // 15. VALIDAR QUE EL TESTER EXISTE
        // ==================================================

        verify.verify(
                emailTester,
                emailObtenido,
                "El Tester creado no aparece en el listado de usuarios."
        );
    }
}
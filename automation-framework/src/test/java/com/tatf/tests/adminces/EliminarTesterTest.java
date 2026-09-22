package com.tatf.tests.adminces;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EliminarTesterTest {

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
    void eliminarCuentaTester() {

        // ==================================================
        // DATOS DE PRUEBA
        // ==================================================

        String emailAdministrador =
                "admin.eliminar."
                        + System.currentTimeMillis()
                        + "@gmail.com";

        String passwordAdministrador = "Clave123!";

        String nombreTester = "Tester";
        String apellidoTester = "Eliminar";

        String emailTester =
                "tester.eliminar."
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
                "Eliminar",
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

        AdminCesHelper.cerrarMensajeOk(browser);


        // ==================================================
        // 5. VALIDAR INGRESO
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
        // 6. IR A CREAR USUARIO TESTER
        // ==================================================

        browser.find()
                .link("Crear usuario")
                .click();

        browser.wait("inputFirstName").name();


        // ==================================================
        // 7. COMPLETAR DATOS DEL TESTER
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

        browser.find()
                .id("testerSenior")
                .click();


        // ==================================================
        // 8. CREAR CUENTA TESTER
        // ==================================================

        browser.find()
                .id("btnRegister")
                .click();


        // ==================================================
        // 9. VALIDAR CREACIÓN DEL TESTER
        // ==================================================

        browser.wait("swal2-html-container").id();

        String mensajeCreacionTester = browser.find()
                .id("swal2-html-container")
                .getText();

        verify.verify(
                "Usuario creado.",
                mensajeCreacionTester,
                "No se pudo crear el Tester necesario para la prueba."
        );

        AdminCesHelper.cerrarMensajeOk(browser);


        // ==================================================
        // 10. IR A VER USUARIOS
        // ==================================================

        browser.wait("Ver usuarios").link();

        browser.find()
                .link("Ver usuarios")
                .click();


        // ==================================================
        // 11. LOCALIZAR LA FILA DEL TESTER
        // ==================================================

        String xpathFilaTester =
                "//tr[.//td[normalize-space()='" + emailTester + "']]";

        browser.wait(xpathFilaTester).xpath();


        // ==================================================
        // 12. VALIDAR QUE EXISTE ANTES DE ELIMINAR
        // ==================================================

        String emailObtenido = browser.find()
                .xpath(
                        xpathFilaTester
                                + "//td[normalize-space()='"
                                + emailTester
                                + "']"
                )
                .getText();

        verify.verify(
                emailTester,
                emailObtenido,
                "El Tester que se desea eliminar no aparece en el listado."
        );


        // ==================================================
        // 13. PRESIONAR PAPELERA DEL TESTER
        // ==================================================

        browser.find()
                .xpath(xpathFilaTester + "//button")
                .click();


        // ==================================================
        // 14. VALIDAR MENSAJE DE CONFIRMACIÓN
        // ==================================================

        browser.wait("swal2-html-container").id();

        String mensajeEliminar = browser.find()
                .id("swal2-html-container")
                .getText();

        String mensajeEsperado =
                "¿Eliminar usuario: " + emailTester + "?";

        verify.verify(
                mensajeEsperado,
                mensajeEliminar,
                "El mensaje de confirmación no corresponde al Tester seleccionado."
        );


        // ==================================================
        // 15. CONFIRMAR ELIMINACIÓN
        // ==================================================

        browser.find()
                .xpath("//button[normalize-space()='Sí']")
                .click();


        // ==================================================
        // 16. VALIDAR MENSAJE DE ELIMINACIÓN
        // ==================================================

        browser.wait("swal2-html-container").id();

        String mensajeEliminacion = browser.find()
                .id("swal2-html-container")
                .getText();

        verify.verify(
                "Usuario eliminado.",
                mensajeEliminacion,
                "No se mostró el mensaje esperado de eliminación."
        );

        AdminCesHelper.cerrarMensajeOk(browser);


        // ==================================================
        // 17. VALIDAR QUE EL TESTER YA NO EXISTE
        // ==================================================

        boolean testerSigueExistiendo =
                !browser.find()
                        .xpathList(xpathFilaTester)
                        .isEmpty();

        verify.verifyFalse(
                testerSigueExistiendo,
                "El Tester continúa apareciendo en el listado luego de eliminarlo."
        );
    }
}
package com.tatf.modules.adminces.test;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import com.tatf.modules.adminces.data.AdminCesData;
import com.tatf.modules.adminces.task.CrearAdministradorTask;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CrearAdministradorTest {

    private IBrowser browser;
    private IVerify verify;


    @BeforeEach
    void beforeEach() {
        browser = BrowserFactory.getBrowser(true);
        verify = IVerify.create();
    }


    @AfterEach
    void afterEach() {
        BrowserFactory.quitBrowser();
    }


    @ParameterizedTest(
            name = "Crear Administrador -> Nombre: {0}, Apellido: {1}, País: {2}"
    )
    @CsvFileSource(
            resources = "/datos_administradores.csv",
            numLinesToSkip = 1
    )
    void crearCuentaAdministrador(
            String nombre,
            String apellido,
            String pais,
            String password
    ) {

        AdminCesData data =
                AdminCesData.paraAdministrador(
                        nombre,
                        apellido,
                        pais,
                        password
                );

        CrearAdministradorTask task =
                new CrearAdministradorTask(
                        browser,
                        verify,
                        data
                );

        task.crearAdministrador();
    }
}
package com.tatf.modules.adminces.test;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import com.tatf.modules.adminces.data.AdminCesData;
import com.tatf.modules.adminces.task.ReiniciarContrasenaTask;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ReiniciarContrasenaTest {

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
            name = "Reiniciar contraseña -> Nombre: {0}, Apellido: {1}, País: {2}"
    )
    @CsvFileSource(
            resources = "/datos_reinicio_contrasena.csv",
            numLinesToSkip = 1
    )
    void reiniciarContrasena(
            String nombre,
            String apellido,
            String pais,
            String password,
            String passwordNueva
    ) {

        AdminCesData data =
                AdminCesData.paraReinicioContrasena(
                        nombre,
                        apellido,
                        pais,
                        password,
                        passwordNueva
                );

        ReiniciarContrasenaTask task =
                new ReiniciarContrasenaTask(
                        browser,
                        verify,
                        data
                );

        task.reiniciarContrasena();
    }
}
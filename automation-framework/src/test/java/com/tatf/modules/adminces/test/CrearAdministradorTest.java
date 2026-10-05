package com.tatf.modules.adminces.test;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import com.tatf.modules.adminces.data.AdminCesData;
import com.tatf.modules.adminces.task.AdminCesTask;

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

        AdminCesData data =
                new AdminCesData();

        AdminCesTask task =
                new AdminCesTask(
                        browser,
                        verify,
                        data
                );

        task.crearAdministrador();
    }
}
package com.tatf.modules.adminces.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import com.tatf.modules.adminces.data.AdminCesData;
import com.tatf.modules.adminces.pom.AccesoPO;
import com.tatf.modules.adminces.pom.AlertaPO;
import com.tatf.modules.adminces.pom.InicioAdministradorPO;
import com.tatf.modules.adminces.pom.InicioPO;
import com.tatf.modules.adminces.pom.LoginPO;
import com.tatf.modules.adminces.pom.RegistroAdministradorPO;

public class CrearAdministradorTask {

    private final IBrowser browser;
    private final IVerify verify;
    private final AdminCesData data;

    private final AccesoPO accesoPO;
    private final AlertaPO alertaPO;


    public CrearAdministradorTask(
            IBrowser browser,
            IVerify verify,
            AdminCesData data
    ) {
        this.browser = browser;
        this.verify = verify;
        this.data = data;

        this.accesoPO = new AccesoPO(browser);
        this.alertaPO = new AlertaPO(browser);
    }


    public void crearAdministrador() {

        InicioPO inicioPO =
                accesoPO.acceder();

        RegistroAdministradorPO registroPO =
                inicioPO.irARegistroAdministrador();

        registroPO.registrarAdministrador(
                data.getNombreAdministrador(),
                data.getApellidoAdministrador(),
                data.getEmailAdministrador(),
                data.getPasswordAdministrador(),
                data.getPaisAdministrador()
        );


        String mensajeCreacion =
                alertaPO.obtenerMensaje();

        verify.verify(
                data.getMensajeUsuarioCreado(),
                mensajeCreacion,
                "No se mostró el mensaje esperado al crear el Administrador."
        );

        alertaPO.cerrar();


        LoginPO loginPO =
                inicioPO.irAInicioSesion();

        loginPO.iniciarSesion(
                data.getEmailAdministrador(),
                data.getPasswordAdministrador()
        );

        alertaPO.cerrar();


        InicioAdministradorPO inicioAdministradorPO =
                new InicioAdministradorPO(browser);

        String textoCrearUsuario =
                inicioAdministradorPO.obtenerTextoCrearUsuario();

        verify.verify(
                data.getTextoCrearUsuario(),
                textoCrearUsuario,
                "No se pudo iniciar sesión con el Administrador creado."
        );
    }
}
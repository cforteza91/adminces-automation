package com.tatf.modules.adminces.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import com.tatf.modules.adminces.data.AdminCesData;
import com.tatf.modules.adminces.pom.AccesoPO;
import com.tatf.modules.adminces.pom.AlertaPO;
import com.tatf.modules.adminces.pom.CrearTesterPO;
import com.tatf.modules.adminces.pom.InicioAdministradorPO;
import com.tatf.modules.adminces.pom.InicioPO;
import com.tatf.modules.adminces.pom.LoginPO;
import com.tatf.modules.adminces.pom.RegistroAdministradorPO;
import com.tatf.modules.adminces.pom.UsuariosPO;

public class CrearTesterTask {

    private final IBrowser browser;
    private final IVerify verify;
    private final AdminCesData data;

    private final AccesoPO accesoPO;
    private final AlertaPO alertaPO;


    public CrearTesterTask(
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


    public void crearTester() {

        InicioAdministradorPO inicioAdministradorPO =
                prepararAdministrador();


        CrearTesterPO crearTesterPO =
                inicioAdministradorPO.irACrearUsuario();

        crearTesterPO.crearTesterSenior(
                data.getNombreTester(),
                data.getApellidoTester(),
                data.getEmailTester(),
                data.getPaisTester(),
                data.getPasswordTester()
        );


        String mensajeTester =
                alertaPO.obtenerMensaje();

        verify.verify(
                data.getMensajeUsuarioCreado(),
                mensajeTester,
                "No se mostró el mensaje esperado al crear el Tester."
        );

        alertaPO.cerrar();


        UsuariosPO usuariosPO =
                inicioAdministradorPO.irAVerUsuarios();

        String emailObtenido =
                usuariosPO.obtenerEmailUsuario(
                        data.getEmailTester()
                );

        verify.verify(
                data.getEmailTester(),
                emailObtenido,
                "El Tester creado no aparece en el listado de usuarios."
        );
    }


    private InicioAdministradorPO prepararAdministrador() {

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
                "No se pudo crear el Administrador necesario para la prueba."
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
                "No se pudo iniciar sesión con el Administrador."
        );

        return inicioAdministradorPO;
    }
}
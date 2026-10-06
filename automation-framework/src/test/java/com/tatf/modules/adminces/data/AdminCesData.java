package com.tatf.modules.adminces.data;

public class AdminCesData {

    // ==================================================
    // DATOS ADMINISTRADOR
    // ==================================================

    private final String nombreAdministrador;
    private final String apellidoAdministrador;
    private final String emailAdministrador;
    private final String passwordAdministrador;
    private final String paisAdministrador;


    // ==================================================
    // DATOS PARA REINICIAR CONTRASEÑA
    // ==================================================

    private final String passwordNueva;


    // ==================================================
    // DATOS TESTER
    // ==================================================

    private final String nombreTester;
    private final String apellidoTester;
    private final String emailTester;
    private final String passwordTester;
    private final String paisTester;


    // ==================================================
    // RESULTADOS ESPERADOS
    // ==================================================

    private final String mensajeUsuarioCreado =
            "Usuario creado.";

    private final String mensajeUsuarioEliminado =
            "Usuario eliminado.";

    private final String textoCrearUsuario =
            "Crear usuario";


    // ==================================================
    // CONSTRUCTOR POR DEFECTO
    // ==================================================

    public AdminCesData() {

        this(
                "Juan",
                "Perez",
                "Uruguay",
                "Clave123!",
                "NuevaClave456!",
                "Carlos",
                "Prueba",
                "Uruguay",
                "Tester123!"
        );
    }


    // ==================================================
    // CONSTRUCTOR PARA TESTER PARAMETRIZADO
    // ==================================================

    public AdminCesData(
            String nombreTester,
            String apellidoTester,
            String paisTester,
            String passwordTester
    ) {

        this(
                "Juan",
                "Perez",
                "Uruguay",
                "Clave123!",
                "NuevaClave456!",
                nombreTester,
                apellidoTester,
                paisTester,
                passwordTester
        );
    }


    // ==================================================
    // MÉTODO FÁBRICA PARA ADMINISTRADOR PARAMETRIZADO
    // ==================================================

    public static AdminCesData paraAdministrador(
            String nombreAdministrador,
            String apellidoAdministrador,
            String paisAdministrador,
            String passwordAdministrador
    ) {

        return new AdminCesData(
                nombreAdministrador,
                apellidoAdministrador,
                paisAdministrador,
                passwordAdministrador,
                "NuevaClave456!",
                "Carlos",
                "Prueba",
                "Uruguay",
                "Tester123!"
        );
    }


    // ==================================================
    // MÉTODO FÁBRICA PARA REINICIO DE CONTRASEÑA
    // ==================================================

    public static AdminCesData paraReinicioContrasena(
            String nombreAdministrador,
            String apellidoAdministrador,
            String paisAdministrador,
            String passwordAdministrador,
            String passwordNueva
    ) {

        return new AdminCesData(
                nombreAdministrador,
                apellidoAdministrador,
                paisAdministrador,
                passwordAdministrador,
                passwordNueva,
                "Carlos",
                "Prueba",
                "Uruguay",
                "Tester123!"
        );
    }


    // ==================================================
    // CONSTRUCTOR INTERNO
    // ==================================================

    private AdminCesData(
            String nombreAdministrador,
            String apellidoAdministrador,
            String paisAdministrador,
            String passwordAdministrador,
            String passwordNueva,
            String nombreTester,
            String apellidoTester,
            String paisTester,
            String passwordTester
    ) {

        this.nombreAdministrador = nombreAdministrador;
        this.apellidoAdministrador = apellidoAdministrador;
        this.paisAdministrador = paisAdministrador;
        this.passwordAdministrador = passwordAdministrador;
        this.passwordNueva = passwordNueva;

        this.emailAdministrador =
                "admin."
                        + nombreAdministrador.toLowerCase()
                        + "."
                        + System.currentTimeMillis()
                        + "@gmail.com";


        this.nombreTester = nombreTester;
        this.apellidoTester = apellidoTester;
        this.paisTester = paisTester;
        this.passwordTester = passwordTester;

        this.emailTester =
                "tester."
                        + nombreTester.toLowerCase()
                        + "."
                        + System.currentTimeMillis()
                        + "@gmail.com";
    }

    // ==================================================
    // GETTERS - ADMINISTRADOR
    // ==================================================

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public String getApellidoAdministrador() {
        return apellidoAdministrador;
    }

    public String getEmailAdministrador() {
        return emailAdministrador;
    }

    public String getPasswordAdministrador() {
        return passwordAdministrador;
    }

    public String getPaisAdministrador() {
        return paisAdministrador;
    }


    // ==================================================
    // GETTERS - REINICIO DE CONTRASEÑA
    // ==================================================

    public String getPasswordNueva() {
        return passwordNueva;
    }


    // ==================================================
    // GETTERS - TESTER
    // ==================================================

    public String getNombreTester() {
        return nombreTester;
    }

    public String getApellidoTester() {
        return apellidoTester;
    }

    public String getEmailTester() {
        return emailTester;
    }

    public String getPasswordTester() {
        return passwordTester;
    }

    public String getPaisTester() {
        return paisTester;
    }


    // ==================================================
    // GETTERS - RESULTADOS ESPERADOS
    // ==================================================

    public String getMensajeUsuarioCreado() {
        return mensajeUsuarioCreado;
    }

    public String getMensajeUsuarioEliminado() {
        return mensajeUsuarioEliminado;
    }

    public String getTextoCrearUsuario() {
        return textoCrearUsuario;
    }

    public String getMensajeConfirmacionEliminarTester() {
        return "¿Eliminar usuario: " + emailTester + "?";
    }
}
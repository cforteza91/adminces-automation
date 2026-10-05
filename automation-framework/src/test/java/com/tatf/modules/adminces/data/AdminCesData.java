package com.tatf.modules.adminces.data;

public class AdminCesData {

    // ==================================================
    // DATOS GENERALES DE ACCESO
    // ==================================================

    private final String claveAcceso =
            "3)ea60e0be3ba12c6ecd%7297868%5c4";


    // ==================================================
    // DATOS ADMINISTRADOR
    // ==================================================

    private final String nombreAdministrador = "Juan";
    private final String apellidoAdministrador = "Perez";

    private final String emailAdministrador =
            "admin."
                    + System.currentTimeMillis()
                    + "@gmail.com";

    private final String passwordAdministrador = "Clave123!";
    private final String paisAdministrador = "Uruguay";


    // ==================================================
    // DATOS PARA REINICIAR CONTRASEÑA
    // ==================================================

    private final String passwordNueva = "NuevaClave456!";


    // ==================================================
    // DATOS TESTER
    // ==================================================

    private final String nombreTester = "Carlos";
    private final String apellidoTester = "Prueba";

    private final String emailTester =
            "tester."
                    + System.currentTimeMillis()
                    + "@gmail.com";

    private final String passwordTester = "Tester123!";
    private final String paisTester = "Uruguay";


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
    // GETTERS - ACCESO
    // ==================================================

    public String getClaveAcceso() {
        return claveAcceso;
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
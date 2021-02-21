package vass.assessment.server.security.utils;

public class Constantes {
	
	public static Integer ESTADO_OK = 1;
	public static Integer ESTADO_ERROR = 0;
	public static String MSG_LISTAR_ERROR = "Ocurrio un error interno al intentar listar la informacion.";	
	public static String MSG_ELIMINAR_NOEXISTS = "El registro que intenta eliminar no existe o ya ha sido eliminado";
	public static String MSG_ELIMINAR_ERROR = "Ocurrio un error interno al intentar eliminar el registro";
	
	//Reclamos
	public static String MSG_REGISTRAR_USUARIO_OK = "El usuario se ha registrado correctamente.";
	public static String MSG_REGISTRAR_USUARIO_ALREADYEXISTS = "El nombre de usuario ingresado ya existe, porfavor coloque uno diferente.";
	public static String MSG_REGISTRAR_USUARIO_ERROR = "Ocurrio un error interno al intentar registar el usuario";
	
	public static String MSG_USERNAME_NOT_FOUND = "Error en el login, el usuario ingresado no existe";

}

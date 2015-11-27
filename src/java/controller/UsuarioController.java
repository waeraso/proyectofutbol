package controller;

import entidades.Usuario;
import beans.UsuarioFacade;
import beans.util.JsfUtil;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import seguridad.Encrypt;
import beans.AbstractController.PersistAction;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;

@ManagedBean(name = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {
    private int id_usuario;
    private String usuario;
    private String contrasenia;
    private String rol;
    private Usuario selected;
    static int cont=0;
    static Boolean banCont = false;
    static Date fechaActual = new Date();
    static Calendar calendarActual = Calendar.getInstance();
    public HttpSession miSession;
    private List<Usuario> items = null;
    
    @EJB
    private UsuarioFacade ejbFacade;
    
     public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public String getUsuario() {
        return usuario;
    }
 
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
 
    public String getContrasenia() {
        return contrasenia;
    }
 
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public String getRol() {
        return rol;
    }
 
    public void setRol(String rol) {
        this.rol = rol;
    } 

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }  
    
    public String login() {
        String pag = "index";
        cont++;

        if (cont < 3) {

            List<Usuario> usuarios = getFacade().findAll();
            contrasenia = Encrypt.sha512(contrasenia);

            boolean ban = busquedaUsuario(2);

            if (ban == true) {
                cont = 0;
                banCont = false;
                JsfUtil.addSuccessMessage("Bienvienido: " + usuario);
                pag = "inicio";
            } else {
                JsfUtil.addErrorMessage("Nombre de usuario o Contraseña incorrectos");
                pag = "index";
            }
        } else {
            if (banCont == false) {
                fechaActual = new Date();
                calendarActual.setTime(fechaActual); // Configuramos la fecha que se recibe	
                calendarActual.add(Calendar.SECOND, 30);  // numero de horas a añadir, o restar en caso de horas<0                    
                JsfUtil.addErrorMessage("Numero máximo de intentos permitidos, vuelva a intentarlo despues de 30 segundos");
                banCont = true;
            } else {
                Date fecha = new Date();
                Calendar calend = Calendar.getInstance();
                calend.setTime(fecha);

                if (calend.after(calendarActual)) {
                    cont = 0;
                    banCont = false;
                    JsfUtil.addErrorMessage("Sesion Reactivada");
                    pag = "inicio";
                } else {
                    JsfUtil.addErrorMessage("Numero máximo de intentos permitidos, vuelva a intentarlo despues de 30 segundos");
                }
            }
        }
        selected = null;
        usuario = null;
        return pag;        
    }
    
    public boolean busquedaUsuario(int opcion){
        
        //verifico si el usuario existe o no
        List<Usuario> usuarios = getFacade().findAll();
        boolean banExiste = false;
        
        if (opcion == 1) {
            for (int i = 0; i < usuarios.size() && !banExiste; i++) {
                if (usuario.equals(usuarios.get(i).getUsername())) {
                    banExiste = true;
                }
            }
        }
        else if(opcion == 2){
            for (int i = 0; i < usuarios.size() && !banExiste; i++) {
                if (usuario.equals(usuarios.get(i).getUsername()) && contrasenia.equals(usuarios.get(i).getPassword())) {
                    miSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    miSession.setAttribute("usuario", usuario);
                    banExiste = true;
                }
            }            
        }
        else {
            for (int i = 0; i < usuarios.size() && !banExiste; i++) {
                if (contrasenia.equals(usuarios.get(i).getPassword()) && selected.getUsername().equals(usuarios.get(i).getUsername())) {
                    banExiste = true;
                }

            }
        }
        return banExiste;
    }
    
    //registrar un usuario desde formulario de registro
    public void RegistrarUsuario(){
        
        //existe == true ; el usuario ya se encuentra registrado
        boolean existe = busquedaUsuario(1);
        
        if (existe == false) {
            //validar contraseña alfanumerica, minusculas, mayusculas:
            boolean minus = false, mayus = false, num = false, especial = false;
            
            for (int i = 0; i < contrasenia.length(); i++) {
                char c = contrasenia.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    minus = true;
                } else if (c >= 'A' && c <= 'Z') {
                    mayus = true;
                } else if (c >= '0' && c <= '9') {
                    num = true;
                } else if ((int) c > 32 && (int) c <= 47) {
                    especial = true;
                } else if ((int) c >= 58 && (int) c <= 64) {
                    especial = true;
                } else if ((int) c >= 91 && (int) c <= 96) {
                    especial = true;
                } else if ((int) c >= 123 && (int) c <= 126) {
                    especial = true;
                } else if ((int) c == 168 || (int) c == 173) {
                    especial = true;
                }
            }
            if (minus == true && mayus == true && num == true && especial == true) {

                selected = new Usuario();
                selected.setUsername(usuario);
                selected.setPassword(Encrypt.sha512(contrasenia));
                selected.setRol(rol);
                
                persist(PersistAction.CREATE, "Usuario Registrado, inicie sesion");
                if (!JsfUtil.isValidationFailed()) {
                    items = null;    // Invalidate list of items to trigger re-query.
                }
            } else {
                JsfUtil.addErrorMessage("La contraseña debe tener letras mayusculas, minusculas, numeros y caracteres especiales");
            }
        }
        else {
            JsfUtil.addErrorMessage("El usuario ya existe, por favor ingresa otro nombre");
        }
        
        selected=null;
        usuario = null;        
    } 
    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     * Initialize the concrete Usuario controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario Entity
        super(Usuario.class);
    }

}

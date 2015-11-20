package beans;

import entidades.EquipoJugador;
import controller.EquipoJugadorFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "equipoJugadorController")
@ViewScoped
public class EquipoJugadorController extends AbstractController<EquipoJugador> {

    @EJB
    private EquipoJugadorFacade ejbFacade;
    @ManagedProperty(value = "#{equipoController}")
    private EquipoController idequipoController;
    @ManagedProperty(value = "#{jugadorController}")
    private JugadorController idjugadorController;

    /* Setter method for managed property idequipoController */
    public void setIdequipoController(EquipoController idequipoController) {
        this.idequipoController = idequipoController;
    }

    /* Setter method for managed property idjugadorController */
    public void setIdjugadorController(JugadorController idjugadorController) {
        this.idjugadorController = idjugadorController;
    }

    /**
     * Initialize the concrete EquipoJugador controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EquipoJugadorController() {
        // Inform the Abstract parent controller of the concrete EquipoJugador Entity
        super(EquipoJugador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idequipoController.setSelected(null);
        idjugadorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Equipo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdequipo(ActionEvent event) {
        if (this.getSelected() != null && idequipoController.getSelected() == null) {
            idequipoController.setSelected(this.getSelected().getIdequipo());
        }
    }

    /**
     * Sets the "selected" attribute of the Jugador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdjugador(ActionEvent event) {
        if (this.getSelected() != null && idjugadorController.getSelected() == null) {
            idjugadorController.setSelected(this.getSelected().getIdjugador());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Gol entities that are
     * retrieved from EquipoJugador?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Gol page
     */
    public String navigateGolCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Gol_items", this.getSelected().getGolCollection());
        }
        return "/gol/index";
    }

    /**
     * Sets the "items" attribute with a collection of Sancion entities that are
     * retrieved from EquipoJugador?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Sancion page
     */
    public String navigateSancionCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Sancion_items", this.getSelected().getSancionCollection());
        }
        return "/sancion/index";
    }

}

package beans;

import entidades.Sancion;
import controller.SancionFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "sancionController")
@ViewScoped
public class SancionController extends AbstractController<Sancion> {

    @EJB
    private SancionFacade ejbFacade;
    @ManagedProperty(value = "#{equipoJugadorController}")
    private EquipoJugadorController idEquipoJugadoController;
    @ManagedProperty(value = "#{partidoController}")
    private PartidoController idpartidoController;
    @ManagedProperty(value = "#{tipoSancionController}")
    private TipoSancionController tipoSancionController;

    /* Setter method for managed property idEquipoJugadoController */
    public void setIdEquipoJugadoController(EquipoJugadorController idEquipoJugadoController) {
        this.idEquipoJugadoController = idEquipoJugadoController;
    }

    /* Setter method for managed property idpartidoController */
    public void setIdpartidoController(PartidoController idpartidoController) {
        this.idpartidoController = idpartidoController;
    }

    /* Setter method for managed property tipoSancionController */
    public void setTipoSancionController(TipoSancionController tipoSancionController) {
        this.tipoSancionController = tipoSancionController;
    }

    /**
     * Initialize the concrete Sancion controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public SancionController() {
        // Inform the Abstract parent controller of the concrete Sancion Entity
        super(Sancion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idEquipoJugadoController.setSelected(null);
        idpartidoController.setSelected(null);
        tipoSancionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the EquipoJugador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEquipoJugado(ActionEvent event) {
        if (this.getSelected() != null && idEquipoJugadoController.getSelected() == null) {
            idEquipoJugadoController.setSelected(this.getSelected().getIdEquipoJugado());
        }
    }

    /**
     * Sets the "selected" attribute of the Partido controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdpartido(ActionEvent event) {
        if (this.getSelected() != null && idpartidoController.getSelected() == null) {
            idpartidoController.setSelected(this.getSelected().getIdpartido());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoSancion controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoSancion(ActionEvent event) {
        if (this.getSelected() != null && tipoSancionController.getSelected() == null) {
            tipoSancionController.setSelected(this.getSelected().getTipoSancion());
        }
    }
}

package beans;

import entidades.Gol;
import controller.GolFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "golController")
@ViewScoped
public class GolController extends AbstractController<Gol> {

    @EJB
    private GolFacade ejbFacade;
    @ManagedProperty(value = "#{equipoJugadorController}")
    private EquipoJugadorController equipoJugadorController;
    @ManagedProperty(value = "#{partidoController}")
    private PartidoController idpartidoController;

    /* Setter method for managed property equipoJugadorController */
    public void setEquipoJugadorController(EquipoJugadorController equipoJugadorController) {
        this.equipoJugadorController = equipoJugadorController;
    }

    /* Setter method for managed property idpartidoController */
    public void setIdpartidoController(PartidoController idpartidoController) {
        this.idpartidoController = idpartidoController;
    }

    /**
     * Initialize the concrete Gol controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public GolController() {
        // Inform the Abstract parent controller of the concrete Gol Entity
        super(Gol.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        equipoJugadorController.setSelected(null);
        idpartidoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the EquipoJugador controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEquipoJugador(ActionEvent event) {
        if (this.getSelected() != null && equipoJugadorController.getSelected() == null) {
            equipoJugadorController.setSelected(this.getSelected().getEquipoJugador());
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
}

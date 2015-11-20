package beans;

import entidades.Equipo;
import controller.EquipoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "equipoController")
@ViewScoped
public class EquipoController extends AbstractController<Equipo> {

    @EJB
    private EquipoFacade ejbFacade;
    @ManagedProperty(value = "#{grupoController}")
    private GrupoController idgrupoController;

    /* Setter method for managed property idgrupoController */
    public void setIdgrupoController(GrupoController idgrupoController) {
        this.idgrupoController = idgrupoController;
    }

    /**
     * Initialize the concrete Equipo controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EquipoController() {
        // Inform the Abstract parent controller of the concrete Equipo Entity
        super(Equipo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idgrupoController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of EquipoJugador entities
     * that are retrieved from Equipo?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EquipoJugador page
     */
    public String navigateEquipoJugadorCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EquipoJugador_items", this.getSelected().getEquipoJugadorCollection());
        }
        return "/equipoJugador/index";
    }

    /**
     * Sets the "selected" attribute of the Grupo controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdgrupo(ActionEvent event) {
        if (this.getSelected() != null && idgrupoController.getSelected() == null) {
            idgrupoController.setSelected(this.getSelected().getIdgrupo());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Partido entities that are
     * retrieved from Equipo?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Partido page
     */
    public String navigatePartidoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Partido_items", this.getSelected().getPartidoCollection());
        }
        return "/partido/index";
    }

    /**
     * Sets the "items" attribute with a collection of Partido entities that are
     * retrieved from Equipo?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Partido page
     */
    public String navigatePartidoCollection1() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Partido_items", this.getSelected().getPartidoCollection1());
        }
        return "/partido/index";
    }

}

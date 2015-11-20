package beans;

import entidades.Grupo;
import controller.GrupoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "grupoController")
@ViewScoped
public class GrupoController extends AbstractController<Grupo> {

    @EJB
    private GrupoFacade ejbFacade;
    @ManagedProperty(value = "#{campeonatoController}")
    private CampeonatoController idcampeonatoController;

    /* Setter method for managed property idcampeonatoController */
    public void setIdcampeonatoController(CampeonatoController idcampeonatoController) {
        this.idcampeonatoController = idcampeonatoController;
    }

    /**
     * Initialize the concrete Grupo controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public GrupoController() {
        // Inform the Abstract parent controller of the concrete Grupo Entity
        super(Grupo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idcampeonatoController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Equipo entities that are
     * retrieved from Grupo?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Equipo page
     */
    public String navigateEquipoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Equipo_items", this.getSelected().getEquipoCollection());
        }
        return "/equipo/index";
    }

    /**
     * Sets the "selected" attribute of the Campeonato controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdcampeonato(ActionEvent event) {
        if (this.getSelected() != null && idcampeonatoController.getSelected() == null) {
            idcampeonatoController.setSelected(this.getSelected().getIdcampeonato());
        }
    }
}

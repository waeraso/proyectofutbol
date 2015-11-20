package beans;

import entidades.Campeonato;
import controller.CampeonatoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "campeonatoController")
@ViewScoped
public class CampeonatoController extends AbstractController<Campeonato> {

    @EJB
    private CampeonatoFacade ejbFacade;
    @ManagedProperty(value = "#{tipoCampeonatoController}")
    private TipoCampeonatoController tipoCampeonatoController;

    /* Setter method for managed property tipoCampeonatoController */
    public void setTipoCampeonatoController(TipoCampeonatoController tipoCampeonatoController) {
        this.tipoCampeonatoController = tipoCampeonatoController;
    }

    /**
     * Initialize the concrete Campeonato controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CampeonatoController() {
        // Inform the Abstract parent controller of the concrete Campeonato Entity
        super(Campeonato.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        tipoCampeonatoController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Grupo entities that are
     * retrieved from Campeonato?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Grupo page
     */
    public String navigateGrupoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Grupo_items", this.getSelected().getGrupoCollection());
        }
        return "/grupo/index";
    }

    /**
     * Sets the "selected" attribute of the TipoCampeonato controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTipoCampeonato(ActionEvent event) {
        if (this.getSelected() != null && tipoCampeonatoController.getSelected() == null) {
            tipoCampeonatoController.setSelected(this.getSelected().getTipoCampeonato());
        }
    }
}

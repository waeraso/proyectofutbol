package beans;

import entidades.Cancha;
import controller.CanchaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;

@ManagedBean(name = "canchaController")
@ViewScoped
public class CanchaController extends AbstractController<Cancha> {

    @EJB
    private CanchaFacade ejbFacade;

    /**
     * Initialize the concrete Cancha controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public CanchaController() {
        // Inform the Abstract parent controller of the concrete Cancha Entity
        super(Cancha.class);
    }

    /**
     * Sets the "items" attribute with a collection of Partido entities that are
     * retrieved from Cancha?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Partido page
     */
    public String navigatePartidoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Partido_items", this.getSelected().getPartidoCollection());
        }
        return "/partido/index";
    }

}

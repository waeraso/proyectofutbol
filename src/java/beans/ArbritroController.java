package beans;

import entidades.Arbritro;
import controller.ArbritroFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;

@ManagedBean(name = "arbritroController")
@ViewScoped
public class ArbritroController extends AbstractController<Arbritro> {

    @EJB
    private ArbritroFacade ejbFacade;

    /**
     * Initialize the concrete Arbritro controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public ArbritroController() {
        // Inform the Abstract parent controller of the concrete Arbritro Entity
        super(Arbritro.class);
    }

    /**
     * Sets the "items" attribute with a collection of Partido entities that are
     * retrieved from Arbritro?cap_first and returns the navigation outcome.
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
     * retrieved from Arbritro?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Partido page
     */
    public String navigatePartidoCollection1() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Partido_items", this.getSelected().getPartidoCollection1());
        }
        return "/partido/index";
    }

    /**
     * Sets the "items" attribute with a collection of Partido entities that are
     * retrieved from Arbritro?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Partido page
     */
    public String navigatePartidoCollection2() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Partido_items", this.getSelected().getPartidoCollection2());
        }
        return "/partido/index";
    }

    /**
     * Sets the "items" attribute with a collection of Partido entities that are
     * retrieved from Arbritro?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Partido page
     */
    public String navigatePartidoCollection3() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Partido_items", this.getSelected().getPartidoCollection3());
        }
        return "/partido/index";
    }

}

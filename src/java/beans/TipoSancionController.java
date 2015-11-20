package beans;

import entidades.TipoSancion;
import controller.TipoSancionFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;

@ManagedBean(name = "tipoSancionController")
@ViewScoped
public class TipoSancionController extends AbstractController<TipoSancion> {

    @EJB
    private TipoSancionFacade ejbFacade;

    /**
     * Initialize the concrete TipoSancion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoSancionController() {
        // Inform the Abstract parent controller of the concrete TipoSancion Entity
        super(TipoSancion.class);
    }

    /**
     * Sets the "items" attribute with a collection of Sancion entities that are
     * retrieved from TipoSancion?cap_first and returns the navigation outcome.
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

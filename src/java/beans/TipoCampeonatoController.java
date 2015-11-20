package beans;

import entidades.TipoCampeonato;
import controller.TipoCampeonatoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;

@ManagedBean(name = "tipoCampeonatoController")
@ViewScoped
public class TipoCampeonatoController extends AbstractController<TipoCampeonato> {

    @EJB
    private TipoCampeonatoFacade ejbFacade;

    /**
     * Initialize the concrete TipoCampeonato controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public TipoCampeonatoController() {
        // Inform the Abstract parent controller of the concrete TipoCampeonato Entity
        super(TipoCampeonato.class);
    }

    /**
     * Sets the "items" attribute with a collection of Campeonato entities that
     * are retrieved from TipoCampeonato?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Campeonato page
     */
    public String navigateCampeonatoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Campeonato_items", this.getSelected().getCampeonatoCollection());
        }
        return "/campeonato/index";
    }

}

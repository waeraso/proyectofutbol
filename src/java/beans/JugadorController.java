package beans;

import entidades.Jugador;
import controller.JugadorFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;

@ManagedBean(name = "jugadorController")
@ViewScoped
public class JugadorController extends AbstractController<Jugador> {

    @EJB
    private JugadorFacade ejbFacade;

    /**
     * Initialize the concrete Jugador controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public JugadorController() {
        // Inform the Abstract parent controller of the concrete Jugador Entity
        super(Jugador.class);
    }

    /**
     * Sets the "items" attribute with a collection of EquipoJugador entities
     * that are retrieved from Jugador?cap_first and returns the navigation
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

}

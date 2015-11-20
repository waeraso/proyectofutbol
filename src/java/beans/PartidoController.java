package beans;

import entidades.Partido;
import controller.PartidoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "partidoController")
@ViewScoped
public class PartidoController extends AbstractController<Partido> {

    @EJB
    private PartidoFacade ejbFacade;
    @ManagedProperty(value = "#{arbritroController}")
    private ArbritroController arbritroCentralController;
    @ManagedProperty(value = "#{arbritroController}")
    private ArbritroController idarbritro1Controller;
    @ManagedProperty(value = "#{arbritroController}")
    private ArbritroController idarbritro2Controller;
    @ManagedProperty(value = "#{arbritroController}")
    private ArbritroController idarbritro3Controller;
    @ManagedProperty(value = "#{canchaController}")
    private CanchaController idcanchaController;
    @ManagedProperty(value = "#{equipoController}")
    private EquipoController equipoVisitanteController;
    @ManagedProperty(value = "#{equipoController}")
    private EquipoController equipoLocalController;

    /* Setter method for managed property arbritroCentralController */
    public void setArbritroCentralController(ArbritroController arbritroCentralController) {
        this.arbritroCentralController = arbritroCentralController;
    }

    /* Setter method for managed property idarbritro1Controller */
    public void setIdarbritro1Controller(ArbritroController idarbritro1Controller) {
        this.idarbritro1Controller = idarbritro1Controller;
    }

    /* Setter method for managed property idarbritro2Controller */
    public void setIdarbritro2Controller(ArbritroController idarbritro2Controller) {
        this.idarbritro2Controller = idarbritro2Controller;
    }

    /* Setter method for managed property idarbritro3Controller */
    public void setIdarbritro3Controller(ArbritroController idarbritro3Controller) {
        this.idarbritro3Controller = idarbritro3Controller;
    }

    /* Setter method for managed property idcanchaController */
    public void setIdcanchaController(CanchaController idcanchaController) {
        this.idcanchaController = idcanchaController;
    }

    /* Setter method for managed property equipoVisitanteController */
    public void setEquipoVisitanteController(EquipoController equipoVisitanteController) {
        this.equipoVisitanteController = equipoVisitanteController;
    }

    /* Setter method for managed property equipoLocalController */
    public void setEquipoLocalController(EquipoController equipoLocalController) {
        this.equipoLocalController = equipoLocalController;
    }

    /**
     * Initialize the concrete Partido controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public PartidoController() {
        // Inform the Abstract parent controller of the concrete Partido Entity
        super(Partido.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        arbritroCentralController.setSelected(null);
        idarbritro1Controller.setSelected(null);
        idarbritro2Controller.setSelected(null);
        idarbritro3Controller.setSelected(null);
        idcanchaController.setSelected(null);
        equipoVisitanteController.setSelected(null);
        equipoLocalController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Gol entities that are
     * retrieved from Partido?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Gol page
     */
    public String navigateGolCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Gol_items", this.getSelected().getGolCollection());
        }
        return "/gol/index";
    }

    /**
     * Sets the "items" attribute with a collection of Sancion entities that are
     * retrieved from Partido?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Sancion page
     */
    public String navigateSancionCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Sancion_items", this.getSelected().getSancionCollection());
        }
        return "/sancion/index";
    }

    /**
     * Sets the "selected" attribute of the Arbritro controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareArbritroCentral(ActionEvent event) {
        if (this.getSelected() != null && arbritroCentralController.getSelected() == null) {
            arbritroCentralController.setSelected(this.getSelected().getArbritroCentral());
        }
    }

    /**
     * Sets the "selected" attribute of the Arbritro controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdarbritro1(ActionEvent event) {
        if (this.getSelected() != null && idarbritro1Controller.getSelected() == null) {
            idarbritro1Controller.setSelected(this.getSelected().getIdarbritro1());
        }
    }

    /**
     * Sets the "selected" attribute of the Arbritro controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdarbritro2(ActionEvent event) {
        if (this.getSelected() != null && idarbritro2Controller.getSelected() == null) {
            idarbritro2Controller.setSelected(this.getSelected().getIdarbritro2());
        }
    }

    /**
     * Sets the "selected" attribute of the Arbritro controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdarbritro3(ActionEvent event) {
        if (this.getSelected() != null && idarbritro3Controller.getSelected() == null) {
            idarbritro3Controller.setSelected(this.getSelected().getIdarbritro3());
        }
    }

    /**
     * Sets the "selected" attribute of the Cancha controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdcancha(ActionEvent event) {
        if (this.getSelected() != null && idcanchaController.getSelected() == null) {
            idcanchaController.setSelected(this.getSelected().getIdcancha());
        }
    }

    /**
     * Sets the "selected" attribute of the Equipo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEquipoVisitante(ActionEvent event) {
        if (this.getSelected() != null && equipoVisitanteController.getSelected() == null) {
            equipoVisitanteController.setSelected(this.getSelected().getEquipoVisitante());
        }
    }

    /**
     * Sets the "selected" attribute of the Equipo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEquipoLocal(ActionEvent event) {
        if (this.getSelected() != null && equipoLocalController.getSelected() == null) {
            equipoLocalController.setSelected(this.getSelected().getEquipoLocal());
        }
    }
}

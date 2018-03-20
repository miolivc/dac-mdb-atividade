
package edu.ifpb.dac.controlador;

import edu.ifpb.dac.entidade.Produto;
import edu.ifpb.dac.service.Vitrine;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named
@ApplicationScoped
public class VitrineController {
    
    @EJB
    private Vitrine vitrine;
    private Produto produto = new Produto();

    public VitrineController() {
        this.produto = vitrine.escolherProduto();
    }
    
    public void atualizarVitrine(ActionEvent event) {
        this.produto = vitrine.escolherProduto();
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
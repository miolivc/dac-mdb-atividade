
package edu.ifpb.dac.controlador;

import edu.ifpb.dac.entidade.Produto;
import edu.ifpb.dac.service.Vitrine;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class VitrineController {
    
    @Inject
    private Vitrine vitrine;
    private Produto produto;
    
    public Produto show() {
        if (vitrine.destaque() != null) {
            produto = vitrine.destaque();
        }
        return produto;
    }
    
}

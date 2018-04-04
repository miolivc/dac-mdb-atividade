
package edu.ifpb.dac.service;

import edu.ifpb.dac.entidade.Produto;
import edu.ifpb.dac.infra.ProdutoDao;
import java.util.List;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class Vitrine {
    
    @Inject
    private ProdutoDao repo; 
    
    public Produto destaque() {
        List<Produto> produtos = repo.listar();
        if (produtos != null && ! produtos.isEmpty()) {
        	int random = (int) (Math.random() * produtos.size());    
        	return produtos.get(random);    	
        }
        return null;
    }
    
}

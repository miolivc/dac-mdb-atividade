package edu.ifpb.dac.infra;

import edu.ifpb.dac.entidade.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProdutoDao {
    
    @PersistenceContext
    private EntityManager manager;
    
    public void adicionar(Produto produto) {
        manager.persist(produto);
    }

    public Produto editar(Produto produto) {
        return manager.merge(produto);
    }
    
    public void remover(Produto produto) {
        manager.remove(produto);
    }
    
    public List<Produto> listar() {
        return manager.createQuery("FROM Produto p").getResultList();
    }
}

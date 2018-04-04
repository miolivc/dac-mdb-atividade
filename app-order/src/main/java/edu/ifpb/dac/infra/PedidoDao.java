package edu.ifpb.dac.infra;

import edu.ifpb.dac.entidade.Pedido;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PedidoDao {
    
    @PersistenceContext
    private EntityManager manager;
    
    public void adicionar(Pedido pedido) {
        manager.persist(pedido);
    }

    public Pedido editar(Pedido pedido) {
        return manager.merge(pedido);
    }
    
    public void remover(Pedido pedido) {
        manager.remove(pedido);
    }
    
    public List<Pedido> listar() {
        return manager.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }
}

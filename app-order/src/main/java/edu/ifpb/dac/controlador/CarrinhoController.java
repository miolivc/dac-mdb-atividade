
package edu.ifpb.dac.controlador;

import edu.ifpb.dac.entidade.Cliente;
import edu.ifpb.dac.entidade.ItemPedido;
import edu.ifpb.dac.entidade.Produto;
import edu.ifpb.dac.service.Carrinho;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alexalins
 * @author miolivc
 */
@Named
@SessionScoped
public class CarrinhoController implements Serializable {
    
    @Inject
    private Carrinho carrinho;
    private Cliente cliente = new Cliente();
    
    public String adicionarItem(Produto produto) {
        carrinho.adicionarProduto(produto);
        return null;
    }
    
    public String removerItem(Produto produto) {
        carrinho.removerProduto(produto);
        return null;
    }
    
    public List<ItemPedido> itensCarrinho() {
        return carrinho.verItensCarrinho();
    }
    
    public BigDecimal valorCompra() {
        return carrinho.valorCarrinho();
    }
    
    public String finalizarCompra() {
        carrinho.adicionarCliente(cliente);
        carrinho.finalizarCarrinho();
        return null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
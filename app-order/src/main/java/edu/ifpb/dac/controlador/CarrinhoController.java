package edu.ifpb.dac.controlador;

import edu.ifpb.dac.entidade.Cliente;
import edu.ifpb.dac.entidade.ItemPedido;
import edu.ifpb.dac.entidade.Produto;
import edu.ifpb.dac.service.Carrinho;
import edu.ifpb.dac.service.Clientes;
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
    @Inject
    private Clientes clientes;
    private String comprador = null;

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
        Cliente cliente = clientes.buscar(comprador);
        carrinho.adicionarCliente(cliente);
        carrinho.finalizarCarrinho();
        return null;
    }
    
    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    private void reset() {
//        HttpSession session = (HttpSession) 
//                FacesContext.getCurrentInstance()
//                            .getExternalContext()
//                            .getSession(false);
//        session.invalidate();
    }
    
    
}

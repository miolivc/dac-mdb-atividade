/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.controlador;

import edu.ifpb.dac.entidade.Cliente;
import edu.ifpb.dac.entidade.Produto;
import edu.ifpb.dac.infra.PedidoDao;
import edu.ifpb.dac.service.Carrinho;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alexalins
 * @author miolivc
 */
@Named
@RequestScoped
public class CarrinhoController {
    
    @Inject
    private Carrinho carrinho;
    @Inject
    private PedidoDao pedidos;
    private Cliente cliente = new Cliente();
    
    public String adicionar(Produto produto) {
        carrinho.adicionarProduto(produto);
        return null;
    }
    
    public String finalizarCompra() {
        carrinho.adicionarCliente(cliente);
        pedidos.adicionar(carrinho.verCarrinho());
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
package edu.ifpb.dac.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ricardo Job
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ItemPedido> produtos = new ArrayList<>();

    @OneToOne
    private Cliente cliente;

    public Pedido() {
    }

    public BigDecimal valorPedido() {
        return produtos.parallelStream()
                .map(ItemPedido::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal valorTotal = new BigDecimal(0);
//        produtos.forEach((produto) -> {
//            valorTotal.add(produto.getPreco());
//        });
//        Long collect = produtos.stream()
//                .flatMap((Produto t) -> Stream.of(t.getPreco()))
//                .collect(Collectors.reducing();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void add(Produto produto) {
        List<ItemPedido> collect = produtos.parallelStream()
                .filter((p) -> (p.getProduto().getId() == produto.getId()))
                .collect(Collectors.toList());

        if (collect == null || collect.isEmpty()) {
            produtos.add(new ItemPedido(produto));
            return;
        }
        produtos.parallelStream().forEach((p) -> {
            if (p.getProduto().getId() == produto.getId()) {
                p.setQuantidade(p.getQuantidade().add(BigDecimal.ONE));
            }
        });

    }

    public void remove(Produto produto) {
        List<ItemPedido> collect = produtos.parallelStream()
                .filter((p) -> (p.getProduto().equals(produto)))
                .collect(Collectors.toList());

        if (! collect.isEmpty()) {
            ItemPedido remover = null;

            for (ItemPedido p : produtos) {
                if (p.getProduto().getId() == produto.getId()) {
                    if (p.getQuantidade().compareTo(BigDecimal.ONE) == 1) {
                        p.setQuantidade(p.getQuantidade().subtract(BigDecimal.ONE));
                    } else {
                        remover = p;
                    }
                    break;
                }
            }

            if (remover != null) {
                produtos.remove(remover);
            }
        }
//        this.produtos.remove(produto);
    }

    public List<ItemPedido> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ItemPedido> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void
            setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "\nPedido:\n" + "id=" + id + "\nprodutos=" + produtos
                + "\nValor total:" + this.valorPedido() + "\ncliente=" + cliente + '}';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.dto.PedidoReciclagemDto;
import br.com.granderio.appreciclagem.model.Negociacao;
import br.com.granderio.appreciclagem.model.PedidoReciclagem;
import br.com.granderio.appreciclagem.model.Reciclador;
import br.com.granderio.appreciclagem.util.UtilError;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

public class DAONegociacao extends DAO<Negociacao> {

	public DAONegociacao(Negociacao neg) {
		super(neg);
	}

	public Negociacao buscarNegociacao(long id) {
		List<Negociacao> lista = new ArrayList();
		try {
			DAO.getS().getTransaction().begin();
			Criteria criteria = DAO.getS().createCriteria(Negociacao.class);
			criteria.add(Restrictions.eq("idNegociacao", id));
			lista = criteria.list();
			DAO.getS().getTransaction().commit();
		} catch (HibernateException ex) {
			String mensagem = UtilError.getMensagemErro(ex);
			System.err.println("Erro ao buscar registros (lista): " + mensagem);
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }
		

		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	public List<Negociacao> buscarNegociacaoPorIdGeradorReciclador(PedidoReciclagemDto pedido, Reciclador rec) {
		List<Negociacao> retorno = null;
		DAOPedidoReciclagem daoPedido = new DAOPedidoReciclagem(new PedidoReciclagem());
		PedidoReciclagem p = daoPedido.findPedidoReciclagem(pedido.getIdPedido());
		Query query = null;
		try {
			DAO.getS().getTransaction().begin();
			query = DAO.getS().getNamedQuery("Negociacao.listarPorPedidoRecicladorGerador")
					.setLong("id1", p.getIdPedidoReciclagem()).setLong("id2", p.getGerador().getIdPessoaJuridica())
					.setLong("id3", rec.getIdPessoaJuridica());
			retorno = query.list();
			DAO.getS().getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			DAO.getS().getTransaction().rollback();
		} finally {
        	DAO.fecharSession();
        }

		return retorno;
	}

}

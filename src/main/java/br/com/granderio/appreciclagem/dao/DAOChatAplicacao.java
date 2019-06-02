/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.dao;

import br.com.granderio.appreciclagem.model.Chat;
import br.com.granderio.appreciclagem.model.ChatAplicacao;
import br.com.granderio.appreciclagem.util.UtilError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;


public class DAOChatAplicacao extends DAO<ChatAplicacao> {
     
    public DAOChatAplicacao(ChatAplicacao chat){
        super(chat);
    }
    
    public ChatAplicacao buscarChatAplicacao(long id){
      List<ChatAplicacao> lista = new ArrayList();
        try {
        	DAO.getS().getTransaction().begin();
            Criteria criteria = DAO.getS().createCriteria(ChatAplicacao.class);
            criteria.add(Restrictions.eq("idChatAplicacao", id));
            lista = criteria.list();
            DAO.getS().getTransaction().commit();
        } catch (HibernateException ex) {
            String mensagem = UtilError.getMensagemErro(ex);
            System.err.println("Erro ao buscar registros (lista): " + mensagem);
            DAO.getS().getTransaction().rollback();
        }finally {
        	DAO.fecharSession();
        }
        
        if(lista.size() >0){
            return lista.get(0);
        }
        
        return null;
    }
    
    public List<ChatAplicacao> buscarMensagensChat(Chat chat){
        List<ChatAplicacao> lista = null;
        
         try{
        	 DAO.getS().getTransaction().begin();
            Criteria criteria = DAO.getS().createCriteria(ChatAplicacao.class);
            criteria.add(Restrictions.eq("chat", chat));
            criteria.addOrder(Order.desc("idChatAplicacao"));
            lista = criteria.list();  
            DAO.getS().getTransaction().commit();   
        }catch(HibernateException e){
             System.out.println("Error: " + e.getMessage());
             DAO.getS().getTransaction().rollback();
        }
         
         return lista;
    }
    
        public List<ChatAplicacao> buscarLazyModelMensagens(int first, int max, String sortField, SortOrder sortOrder, Map<String, Object> filters){   
        List<ChatAplicacao> lista = null;
        long idChat = (long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idChat");
        Query query = null;
         try{
        	 DAO.getS().getTransaction().begin();
            query = DAO.getS().getNamedQuery("ChatAplica.buscarTodos").setLong("idChat", idChat);
            query.setFirstResult(first);
            query.setMaxResults(max);
            lista = query.list();
            DAO.getS().getTransaction().commit();
        }catch(HibernateException e){
             System.out.println("Error: " + e.getMessage());
             DAO.getS().getTransaction().rollback();
        }
         return lista;
    }
        
             public int getChatAplicacaoTotalCount() {
      List<ChatAplicacao> lista = null;
      Query query = null;
      long idChat = (long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idChat");
         try{
        	 DAO.getS().getTransaction().begin();
            query = DAO.getS().getNamedQuery("ChatAplica.buscarTodos").setLong("idChat", idChat);
            lista = query.list();
            DAO.getS().getTransaction().commit();         
        }catch(HibernateException e){
             System.out.println("Error: " + e.getMessage());
             DAO.getS().getTransaction().rollback();
        }
         
         if(lista != null)
        	 return lista.size();
         
         return 0;
  }
}

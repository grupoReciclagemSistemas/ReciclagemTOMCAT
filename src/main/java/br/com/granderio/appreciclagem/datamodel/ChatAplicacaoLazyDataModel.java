/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.datamodel;

import br.com.granderio.appreciclagem.dao.DAO;
import br.com.granderio.appreciclagem.dao.DAOChatAplicacao;
import br.com.granderio.appreciclagem.model.ChatAplicacao;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @programador Feito por Rafael Nunes - rafaelnunes.inf@gmail.com
 */
public class ChatAplicacaoLazyDataModel extends LazyDataModel<ChatAplicacao> {
    
    public ChatAplicacaoLazyDataModel(){
        super();
    }
     
    @Override
    public List<ChatAplicacao> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
      DAOChatAplicacao dao = new DAOChatAplicacao(new ChatAplicacao());
      List<ChatAplicacao> lista = dao.buscarLazyModelMensagens(first, pageSize, sortField, sortOrder, filters);
      setRowCount(dao.getChatAplicacaoTotalCount());
      setPageSize(pageSize);
      return lista;
    }
     
    @Override
    public Object getRowKey(ChatAplicacao chat) {
        return chat.getIdChatAplicacao();
    }

}

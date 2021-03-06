/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
*
* Rafael Nunes - Version 1.0 - Desenvolvedor Java
*/
@Entity
public class Legislacao implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLegislacao;
    
    
    private String codigo;
    
    //Descrição Completa
    private String descricao;
    
    //Descrição Resumo para ser mostrado na tela 
    private String resumoDescricao;
    
    @OneToMany(mappedBy="legislacao", fetch = FetchType.LAZY)
    private List<MaterialLegislacao> listaMatLegi;
    
    public Legislacao(){
        idLegislacao = -1;
        listaMatLegi = new ArrayList();
    }
    
    public void addMatLegi(MaterialLegislacao matLegi){
        if(!(listaMatLegi.contains(matLegi))){
            listaMatLegi.add(matLegi);
            return;
        }
        System.out.println("Ja esta na lista de MatLegi");
    }

    /**
     * @return the idLegislacao
     */
    public long getIdLegislacao() {
        return idLegislacao;
    }

    /**
     * @param idLegislacao the idLegislacao to set
     */
    public void setIdLegislacao(long idLegislacao) {
        this.idLegislacao = idLegislacao;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the listaMatLegi
     */
    public List<MaterialLegislacao> getListaMatLegi() {
        return listaMatLegi;
    }

    /**
     * @param listaMatLegi the listaMatLegi to set
     */
    public void setListaMatLegi(List<MaterialLegislacao> listaMatLegi) {
        this.listaMatLegi = listaMatLegi;
    }

    /**
     * @return the resumoDescricao
     */
    public String getResumoDescricao() {
        return resumoDescricao;
    }

    /**
     * @param resumoDescricao the resumoDescricao to set
     */
    public void setResumoDescricao(String resumoDescricao) {
        this.resumoDescricao = resumoDescricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.idLegislacao ^ (this.idLegislacao >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Legislacao other = (Legislacao) obj;
        if (this.idLegislacao != other.idLegislacao) {
            return false;
        }
        return true;
    }
    
    

}

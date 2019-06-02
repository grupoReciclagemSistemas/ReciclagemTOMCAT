/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granderio.appreciclagem.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

/**
 *
 * @author Rafael
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PessoaJuridica implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idPessoaJuridica;
    
    @Column(unique=true, nullable=false)
    private String email;
    
    @Column(nullable = false)
    private String razaoSocial;
    
    @Column(nullable = false)
    private String senha;
    
    @Column(unique=true, nullable=false)
    private String cnpj;
    
    @Column(nullable = false)
    private String telefone;
    
    @OneToOne(mappedBy="pessoa", cascade = CascadeType.ALL)
    private Endereco endereco;
    
    @Column(nullable = true)
    private String telefoneEmpresa;

    public PessoaJuridica(){
        idPessoaJuridica = -1;
        endereco = new Endereco();
    }

    /**
     * @return the idPessoaJuridica
     */
    public long getIdPessoaJuridica() {
        return idPessoaJuridica;
    }

    /**
     * @param idPessoaJuridica the idPessoaJuridica to set
     */
    public void setIdPessoaJuridica(long idPessoaJuridica) {
        this.idPessoaJuridica = idPessoaJuridica;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the razaoSocial
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @param razaoSocial the razaoSocial to set
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = criptografarSenha(senha);
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

   
    public String retornaLatLng(){
        return getEndereco().getLat() + ", " + getEndereco().getLng();
    }

    /**
     * @return the telefoneEmpresa
     */
    public String getTelefoneEmpresa() {
        return telefoneEmpresa;
    }

    /**
     * @param telefoneEmpresa the telefoneEmpresa to set
     */
    public void setTelefoneEmpresa(String telefoneEmpresa) {
        this.telefoneEmpresa = telefoneEmpresa;
    }
    
    // Método de Criptografia para as Senhas dos Clientes.
    private String criptografarSenha(String senha) {
        MessageDigest mDigest;
        try {
            mDigest = MessageDigest.getInstance("MD5");
            byte[] valorMD5 = mDigest.digest(senha.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (byte b : valorMD5) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    
}

//Grupo:

//Felipe Luvizotto De Castro RA:22129
//Vitor Silveira De Lucena RA:22154
//Mickeias Gomes Araujo RA:22144

public class User {
    public String nome;

    public String email;
    public String telefone;
    public String cep;
    public String estado;
    public String cidade;
    public String bairro;
    public String rua;
    public String numero;
    public String senha;

    public User() {

    }

    public User(String nome, String email, String telefone, String cep, String numero, String rua, String bairro, String cidade, String estado, String senha) {
        setNome(nome);
        setEmail(email);
        setTelefone(telefone);
        setCep(cep);
        setNumero(numero);
        setRua(rua);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setSenha(senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && nome.length() < 100) {
            this.nome = nome;
        } else {
            System.out.println("Nome invalido");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.length() < 100 && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Email invalido");
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone != null && telefone.length() == 13) {
            this.telefone = telefone;
        } else {
            System.out.println("Telefone invalido");
        }
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if (cep != null && cep.length() == 8) {
            this.cep = cep;
        } else {
            System.out.println("Cep invalido");
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado != null && estado.length() < 100) {
            this.estado = estado;
        } else {
            System.out.println("Estado invalido");
        }
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if (cidade != null && cidade.length() < 100) {
            this.cidade = cidade;
        } else {
            System.out.println("Cidade invalida");
        }
    }


    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if (bairro != null && bairro.length() < 100) {
            this.bairro = bairro;
        } else {
            System.out.println("Bairro invalido");
        }
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        if (rua != null && rua.length() < 100) {
            this.rua = rua;
        } else {
            System.out.println("Rua invalida");
        }
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (numero != null && numero.length() < 100) {
            this.numero = numero;
        } else {
            System.out.println("Numero invalido");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null && senha.length() < 100) {
            this.senha = senha;
        } else {
            System.out.println("Senha invalida");
        }
    }

    public String toJson() {
        return "{" +
                "\"nome\":\"" + nome + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"telefone\":\"" + telefone + "\"," +
                "\"cep\":\"" + cep + "\"," +
                "\"numero\":\"" + numero + "\"," +
                "\"rua\":\"" + rua + "\"," +
                "\"bairro\":\"" + bairro + "\"," +
                "\"cidade\":\"" + cidade + "\"," +
                "\"estado\":\"" + estado + "\"," +
                "\"senha\":\"" + senha + "\"" +
                "}";
    }
}
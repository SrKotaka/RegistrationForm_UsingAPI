class User
{
    
    #nome
    #email
    #telefone
    #cep
    #numero
    #rua
    #bairro
    #cidade
    #estado
    #senha

    constructor (nome, email, telefone,cep,numero,rua,bairro,cidade,estado,senha)
    {
        this.nome=nome;
        this.email=email;
        this.telefone=telefone;
        this.cep=cep;
        this.numero=numero;
        this.rua=rua;
        this.bairro=bairro;
        this.cidade=cidade;
        this.estado=estado;
        this.senha=senha;
    }
    

    get nome ()
    {
        return this.#nome
    }

    get email ()
    {
       return this.#email
    }

    get telefone ()
    {
        return this.#telefone
    }

    get cep ()
    {
        return this.#cep
    }

    get numero ()
    {
        return this.#numero
    }

    get rua ()
    {
        return this.#rua
    }

    get bairro ()
    {
        return this.#bairro
    }

    get cidade ()
    {
        return this.#cidade
    }

    get estado ()
    {
        return this.#estado
    }

    get senha ()
    {
        return this.#senha
    }


    set nome (nome)
    {
        if (nome===undefined || typeof nome !== 'string' || nome==="")
            throw ('Nome inválido');

        this.#nome = nome;
    }
    set email (email)
    {
        if (email===undefined || typeof email !== 'string' || email==="")
            throw ('email inválido');

        this.#email =email;
    }
    set telefone (telefone)
    {
        if (telefone===undefined || typeof telefone !== 'string' || telefone==="")
            throw ('telefone inválido');

        this.#telefone = telefone;
    }
    set cep (cep)
    {

        if (cep===undefined || typeof cep !== 'string' || cep==="")
            throw ('cep inválido');

        this.#cep = cep;
    }
    set numero (numero)
    {
        if (numero===undefined || typeof numero !== 'string' || numero==="")
            throw ('numero inválido');

        this.#numero = numero;
    }

    set rua (rua)
    {
        if (rua===undefined || typeof rua !== 'string' || rua==="")
            throw ('rua inválido');

        this.#rua = rua;
    }

    set bairro (bairro)
    {
        if (bairro===undefined || typeof bairro !== 'string' || bairro==="")
            throw ('bairro inválido');

        this.#bairro = bairro;
    }

    set cidade (cidade)
    {
        if (cidade===undefined || typeof cidade !== 'string' || cidade==="")
            throw ('cidade inválido');

        this.#cidade = cidade;
    }

    set estado (estado)
    {
        if (estado===undefined || typeof estado !== 'string' || estado==="")
            throw ('estado inválido');

        this.#estado = estado;
    }

    set senha (senha)
    {
        if (senha===undefined || typeof senha !== 'string' || senha==="")
            throw ('senha inválido');

        this.#senha = senha;
    }
}

function novo (nome, email,telefone,cep,numero,rua,bairro,cidade,estado,senha)
{
        
    return new User (nome, email,telefone,cep,numero,rua,bairro,cidade,estado,senha);
   
}

module.exports = {novo}

const bd = require ('./bd');

async function inclua (user)
{
    const conexao = await bd.getConexao ();
    if (conexao==null) return null;

    try
    {
        const sql     = 'INSERT INTO users (nome, email, telefone,cep,numero,rua,bairro,cidade,estado,senha) VALUES (?,?,?,?,?,?,?,?,?,?)';
        const dados   = [user.nome,user.email,user.telefone,user.cep,user.numero,user.rua,user.bairro,user.cidade,user.estado,user.senha];
        await conexao.query (sql, dados);
        return true;
    }
    catch (excecao)
    {
        return false;
    }
}

async function atualize (user)
{
    const conexao = await bd.getConexao ();
    if (conexao==null) return null;

    try
    {
        const sql   = 'UPDATE users SET nome=?,telefone=?,cep=?,numero=?,rua=?,bairro=?,cidade=?,estado=?,senha=? WHERE email=?';
        const dados = [user.nome,user.telefone,user.cep,user.numero,user.rua,user.bairro,user.cidade,user.estado,user.senha,user.email];
        await conexao.query (sql,dados);
        return true;
    }
    catch (excecao)
    {
        return false;
    }
}
    
async function remova (email)
{
    const conexao = await bd.getConexao ();
    if (conexao==null) return null;

    try
    {
        const sql   = 'DELETE FROM users WHERE email=?';
        const dados = [email];
        await conexao.query (sql,dados);
        return true;
    }
    catch (excecao)
    {
        return false;
    }
}

async function recupereUm (email)
{
    const conexao = await bd.getConexao();
    if (conexao==null) return null;

    try
    {
        const  sql     = 'SELECT * FROM users WHERE email=?';
        const  dados   = [email];
        const [linhas] = await conexao.execute(sql,dados);
        return linhas;
    }
    catch (excecao)
    {
        return false;
    }
}

async function recupereTodos ()
{
    const conexao = await bd.getConexao();
    if (conexao==null) return null;

    try
    {
        const  sql     = 'SELECT * FROM users';
        const [linhas] = await conexao.query(sql);
        return linhas;
    }
    catch (excecao)
    {
        return false;
    }
}

module.exports = {inclua, atualize, remova, recupereUm, recupereTodos}




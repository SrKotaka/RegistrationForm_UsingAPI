const mysql    = require("mysql2/promise");
const bdConfig = require('./bdConfig.js');

async function getConexao ()
{
    if (global.conexao && global.conexao.state !== 'disconnected')
        return global.conexao;

    try
    {
        const conexao = await mysql.createConnection (bdConfig);
        global.conexao = conexao;
        return conexao;
    }
    catch (erro)
    {
        return null;
    }
}

async function estrutureSe ()
{
    const conexao = await getConexao ();
    if (conexao==undefined) return null;

    const sql = 'CREATE TABLE IF NOT EXISTS users (nome VARCHAR(100) NOT NULL, email VARCHAR(100), telefone VARCHAR(100) NOT NULL ,cep VARCHAR(100) NOT NULL,numero VARCHAR(100) NOT NULL,rua VARCHAR(100) NOT NULL,bairro VARCHAR(100) NOT NULL,cidade VARCHAR(100) NOT NULL,estado VARCHAR(100) NOT NULL,senha VARCHAR(100) NOT NULL,PRIMARY KEY (email))';
    
    try
    {
        await conexao.query (sql);
        return true;
    }
    catch (erro)
    {
        return false;
    }
}

module.exports = {getConexao, estrutureSe}
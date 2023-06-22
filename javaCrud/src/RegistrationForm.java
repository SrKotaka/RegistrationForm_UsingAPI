//Grupo:

//Felipe Luvizotto De Castro RA:22129
//Vitor Silveira De Lucena RA:22154
//Mickeias Gomes Araujo RA:22144

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegistrationForm extends JFrame {
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfCep;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;
    private JTextField tfNumero;
    private JTextField tfBairro;
    private JTextField tfCidade;
    private JTextField tfEstado;
    private JButton btnCep;
    private JButton btnExcluir;
    private JButton btnConsulta;
    private JButton btnAtualiza;
    private JTextField tfRua;
    private JButton btnLimpar;

    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm();
        myForm.setVisible(true);
    }

    public RegistrationForm() {
        setTitle("Criar Nova Conta");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(700, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.NORMAL);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                achaCep();
            }
        });
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        btnAtualiza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualiza();
            }
        });
        btnConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultar();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });

    }

    private void achaCep() {
        try {
            String cep = tfCep.getText();
            if (cep.length() == 8) {
                try {
                    Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", cep);
                    tfRua.setText(logradouro.getLogradouro());
                    tfBairro.setText(logradouro.getBairro());
                    tfEstado.setText(logradouro.getEstado());
                    tfCidade.setText(logradouro.getCidade());
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(this, "Cep não existe", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cep esta com o formato incorreto", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }
    }

    private void registerUser() {
        String nome = tfName.getText();
        String email = tfEmail.getText();
        String telefone = tfPhone.getText();
        String cep = tfCep.getText();
        String numero = tfNumero.getText();
        String bairro = tfBairro.getText();
        String rua = tfRua.getText();
        String cidade = tfCidade.getText();
        String estado = tfEstado.getText();
        String senha = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());
        if (senha.equals(confirmPassword)) {
            if (!nome.equals("") && !email.equals("") && !telefone.equals("") && !cep.equals("") && !numero.equals("") && !bairro.equals("") && !rua.equals("") && !cidade.equals("") && !estado.equals("") && !senha.equals("")) {
                if (telefone.length() == 13) {
                    try {
                        boolean emailExiste = checkIfEmailExiste(email);
                        if (emailExiste) {
                            JOptionPane.showMessageDialog(this, "Email ja existe", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
                            return;
                        } else {
                            User user = new User(nome, email, telefone, cep, numero, rua, bairro, cidade, estado, senha);
                            ClienteWS.postObjeto(user.toJson(), "http://localhost:3000/users");
                            JOptionPane.showMessageDialog(this, "Usuario criado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            limpar();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Telefone esta com o formato incorreto", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "As senhas não são iguais", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
        }
        limpar();
    }

    private void excluir() {
        String nome = tfName.getText();
        String email = tfEmail.getText();
        String telefone = tfPhone.getText();
        String cep = tfCep.getText();
        String numero = tfNumero.getText();
        String bairro = tfBairro.getText();
        String rua = tfRua.getText();
        String cidade = tfCidade.getText();
        String estado = tfEstado.getText();
        String senha = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());
        if (senha.equals(confirmPassword)) {
            if (nome.equals("") && !email.equals("") && telefone.equals("") && cep.equals("") && numero.equals("") && bairro.equals("") && rua.equals("") && cidade.equals("") && estado.equals("") && !senha.equals("") && !confirmPassword.equals("")) {
                boolean emailExiste = checkIfEmailExiste(email);
                if (!emailExiste) {
                    JOptionPane.showMessageDialog(this, "Email não existe", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    User user = (User) ClienteWS.getObjeto(User.class, "http://localhost:3000/users", email);
                    try {
                        if (user.senha.equals(senha)) {
                            ClienteWS.deleteObjeto("http://localhost:3000/users", email);
                            JOptionPane.showMessageDialog(this, "Usuario excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            limpar();
                        } else {
                            ClienteWS.deleteObjeto("http://localhost:3000/users", email);
                            JOptionPane.showMessageDialog(this, "Usuario excluido com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            limpar();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Para excluir uma conta é necessario somente os campos email e senha deixe os outros limpos porfavor", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "As senhas não são iguais", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void consultar() {
        String nome = tfName.getText();
        String email = tfEmail.getText();
        String telefone = tfPhone.getText();
        String cep = tfCep.getText();
        String numero = tfNumero.getText();
        String bairro = tfBairro.getText();
        String rua = tfRua.getText();
        String cidade = tfCidade.getText();
        String estado = tfEstado.getText();
        String senha = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());
        if (nome.equals("") && !email.equals("") && telefone.equals("") && cep.equals("") && numero.equals("") && bairro.equals("") && rua.equals("") && cidade.equals("") && estado.equals("") && senha.equals("") && confirmPassword.equals("")) {
            try {
                boolean emailExiste = checkIfEmailExiste(email);
                if (!emailExiste) {
                    JOptionPane.showMessageDialog(this, "Email não existe", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                User user = (User) ClienteWS.getObjeto(User.class, "http://localhost:3000/users", email);
                tfName.setText(user.nome);
                tfPhone.setText(user.telefone);
                tfCep.setText(user.cep);
                tfNumero.setText(user.numero);
                tfBairro.setText(user.bairro);
                tfRua.setText(user.rua);
                tfCidade.setText(user.cidade);
                tfEstado.setText(user.estado);
                JOptionPane.showMessageDialog(this, "Usuario consultado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Para consultar um usuario e nescesario somente o email", "Tente Novamente", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void atualiza() {
        String nome = tfName.getText();
        String email = tfEmail.getText();
        String telefone = tfPhone.getText();
        String cep = tfCep.getText();
        String numero = tfNumero.getText();
        String bairro = tfBairro.getText();
        String rua = tfRua.getText();
        String cidade = tfCidade.getText();
        String estado = tfEstado.getText();
        String senha = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());
        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || cep.isEmpty() || numero.isEmpty() || bairro.isEmpty() || rua.isEmpty() || cidade.isEmpty() || estado.isEmpty() || senha.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
        } else {
            if (senha.equals(confirmPassword)) {
                if (telefone.length() == 13) {
                    try {
                        User user = new User(nome, email, telefone, cep, numero, rua, bairro, cidade, estado, senha);
                        ClienteWS.putObjeto(user, "http://localhost:3000/users", email);
                        JOptionPane.showMessageDialog(this, "Usuario atualizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        limpar();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Telefone esta com o formato incorreto", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "As senhas não são iguais", "Tente Novamente", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpar() {
        tfName.setText("");
        tfEmail.setText("");
        tfPhone.setText("");
        tfCep.setText("");
        tfNumero.setText("");
        tfBairro.setText("");
        tfRua.setText("");
        tfCidade.setText("");
        tfEstado.setText("");
        pfPassword.setText("");
        pfConfirmPassword.setText("");
    }

    private boolean checkIfEmailExiste(String email) {
        List<User> users = ClienteWS.getAllUsers("http://localhost:3000/users");
        for (User user : users) {
            if (user.email.equals(email)) {
                return true;
            }
        }
        return false;
    }

}
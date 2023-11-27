package app;

import dao.PacienteDao;
import dao.SenhaDao;
import dao.ValoresReferenciaDao;
import dao.VitaminaDDao;
import entities.Paciente;
import entities.Senha;
import entities.ValoresReferencia;
import entities.VitaminaD;
import utils.CryptoUtil;
import utils.HashingUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println();

        System.out.println("""
                Você é um paciente que fez um exame de Vitamina D.
                Seu exame ficou pronto e você quer ver o resultado.
                Para isso, você precisa primeiro se cadastrar no sistema.""");
        System.out.println();

        // prompt de cadastro de usuario
        System.out.println("Cadastre o seu usuario.");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.println();

        // cadastra o usuario no banco de dados
        Paciente usuario = new Paciente(nome, cpf, login, senha);
        PacienteDao pacienteDao = new PacienteDao();
        pacienteDao.adiciona(usuario);
        System.out.println("Conta criada com sucesso!");
        System.out.println();
        System.out.println("As informações abaixo não são do interesse do usuário comum,\nmas você não é um " +
                "usuário comum, não é mesmo?");
        System.out.println();

        // gera uma chave secreta para o usuario
        String userSecretKey = CryptoUtil.generateKey();
        System.out.println("userSecretKey: " + userSecretKey);

        // cria um objeto senha com a chave secreta do usuario criptografada com sua senha
        String chaveUserCrypto = CryptoUtil.encrypt(userSecretKey, senha);
        Senha senhaObj = new Senha(1, chaveUserCrypto);
        System.out.println("Chave secreta criptografada: " + senhaObj.getKey());

        // cadastra a chave secreta no banco de dados
        SenhaDao senhaDaoCadastra = new SenhaDao();
        senhaDaoCadastra.cadastrar(senhaObj);


        // resultado mockado do exame - essa parte aqui só o pessoal do laboratório pode fazer
        int resultadoDoExame = 70;
        System.out.println("Resultado do exame: " + resultadoDoExame);

        // criptografa o resultado do exame com a chave secreta do usuario gerada anteriormente
        String resultadoCriptografado = CryptoUtil.encrypt(Integer.toString(resultadoDoExame), userSecretKey);
        System.out.println("Resultado do exame criptografado: " + resultadoCriptografado);

        // cria um objeto vitaminaD com o resultado do exame criptografado
        VitaminaD vitaminaD = new VitaminaD(1, resultadoCriptografado);
        VitaminaDDao vitaminaDDaoInsere = new VitaminaDDao();
        vitaminaDDaoInsere.adiciona(vitaminaD);
        System.out.println();


        System.out.println("Agora você pode logar no sistema para ver o resultado do seu exame. Você precisa\n" +
                "informar o seu login e senha.");
        boolean logado = false;
        while (!logado) {
            System.out.print("Login: ");
            login = sc.nextLine();
            System.out.print("Senha: ");
            senha = sc.nextLine();
            logado = logarPaciente(login, senha);
            if (!logado) {
                System.out.println("Login ou senha incorretos. Tente novamente.");
            }
        }

        System.out.println();
        System.out.println("Usuario logado!");
        System.out.println();
        System.out.println("""
                Você clicou no botão para ver o resultado do seu exame.
                O sistema vai buscar o seu resultado no banco de dados e vai
                descriptografar o resultado usando a sua senha que você acabou de informar
                (mais informações que um usuário comum jamais veria).""");

        // busca a chave secreta do usuario no banco de dados e descriptografa com a senha informada
        SenhaDao senhaDaoBusca = new SenhaDao();
        Senha chaveCriptografada = senhaDaoBusca.getSenha();
        String chaveDescriptografada = CryptoUtil.decrypt(chaveCriptografada.getKey(), senha);
        System.out.println("Chave secreta descriptografada: " + chaveDescriptografada);
        System.out.println();


        // busca o resultado do exame no banco de dados - o id é 1 porque só tem um resultado no banco de dados
        VitaminaDDao vitaminaDDaoBusca = new VitaminaDDao();
        VitaminaD resultado = vitaminaDDaoBusca.buscaPorId(1);
        String resultadoDescriptografado = CryptoUtil.decrypt(resultado.getNgml(), chaveDescriptografada);
        System.out.println("O resultado do seu exame é: " + resultadoDescriptografado + " ng/mL");
        System.out.println("Valores de referência:");
        List<ValoresReferencia> valoresReferencia = new ValoresReferenciaDao().listar();

        for (ValoresReferencia valor : valoresReferencia) {
            System.out.println(valor.getDescricao() + ": " + valor.getLimiteInferior() + " - " + valor.getLimiteSuperior() + " " + valor.getUnidade());
        }
        System.out.println();
        System.out.println("""
                Você chegou ao final do programa! Lembre-se que o código é apenas uma demonstração e está configurado
                para funcionar apenas uma vez. Se você quiser executar novamente testando outros logins e senhas, você
                precisa apagar o banco de dados e utilizar o script para criá-lo novamente.""");


        sc.close();
    }

    public static boolean logarPaciente(String login, String senha) {
        PacienteDao pacienteDao = new PacienteDao();
        Paciente paciente = pacienteDao.buscaPorLogin(login);
        if (paciente != null) {
            String senhaHash = HashingUtil.hashSha128(senha);
            return senhaHash.equals(paciente.getSenha());
        }
        return false;
    }
}

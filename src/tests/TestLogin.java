package tests;

import app.Main;

import java.util.Scanner;

import static app.Main.logarPaciente;

public class TestLogin {
    public static void main(String[] args) {
        boolean logado = false;
        Scanner sc = new Scanner(System.in);
        while (!logado) {


            System.out.print("Login: ");
            String login = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            logado = logarPaciente(login, senha);
            if (!logado) {
                System.out.println("Login ou senha incorretos. Tente novamente.");
            }


        }

        sc.close();
    }
}

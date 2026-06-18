package br.com.implantacao.sgp_autenticacao.controllers;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/email/{destinatario}")
    public String enviarEmailHtml(@PathVariable String destinatario) {

        try {

            MimeMessage message =
                    mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            // destinatário
            helper.setTo(destinatario);

            // assunto
            helper.setSubject("Redefinir Senha");

            // conteúdo html
            String html = ""
            	    + "<div style='background-color: #f4f5f7; padding: 40px 20px; font-family: Segoe UI, Tahoma, Geneva, Verdana, sans-serif; min-height: 400px;'>\n"
            	    + "\n"
            	    + "<div style='max-width: 500px; margin: 0 auto; background-color: #ffffff; border-radius: 12px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05); overflow: hidden; border: 1px solid #e1e4e8;'>\n"
            	    + "\n"
            	    + "<div style='background-color: #0066cc; height: 6px; width: 100%;'></div>\n"
            	    + "\n"
            	    + "<div style='padding: 32px 40px;'>\n"
            	    + "\n"
            	    + "<div style='text-align: center; margin-bottom: 24px;'>\n"
            	    + "<span style='font-size: 22px; font-weight: 700; color: #1a1a1a; letter-spacing: -0.5px;'>SG<span style='color: #0066cc;'>P</span></span>\n"
            	    + "</div>\n"
            	    + "<h2 style='color: #1a1a1a; font-size: 20px; font-weight: 600; margin-top: 0; margin-bottom: 12px; text-align: center;'>\n"
            	    + "Recuperação de Senha\n"
            	    + "</h2>\n"
            	    + "<p style='color: #515b6f; font-size: 15px; line-height: 1.6; margin-top: 0; margin-bottom: 24px; text-align: center;'>\n"
            	    + "Olá! Recebemos uma solicitação para redefinir a senha da sua conta. Clique no botão abaixo para escolher uma nova credencial.\n"
            	    + "</p>\n"
            	    + "\n"
            	    + "<div style='text-align: center; margin-bottom: 24px;'>\n"
            	    + "\n"
            	    + "<a href='http://http://192.168.10.84/:8010/redefinirSenha.html?email=" + destinatario + "' target='_blank' style='display: inline-block; background-color: #0066cc; color: #ffffff; font-size: 15px; font-weight: 600; text-decoration: none; padding: 14px 32px; border-radius: 8px; box-shadow: 0 2px 6px rgba(0, 102, 204, 0.2);'>\n"
            	    + "Redefinir Minha Senha\n"
            	    + "</a>\n"
            	    + "</div>\n"
            	    + "\n"
            	    + "<div style='background-color: #f8f9fa; border-left: 4px solid #f0b429; padding: 12px 16px; border-radius: 0 8px 8px 0; margin-bottom: 24px;'>\n"
            	    + "<p style='margin: 0; font-size: 13px; color: #627d98; line-height: 1.5;'>\n"
            	    + "<strong>Importante:</strong> Se você não solicitou essa alteração, pode ignorar este e-mail com segurança. Sua senha atual continuará funcionando.\n"
            	    + "</p>\n"
            	    + "</div>\n"
            	    + "<hr style='border: 0; border-top: 1px solid #e1e4e8; margin-bottom: 20px;'>\n"
            	    + "\n"
            	    + "<p style='color: #7b8794; font-size: 12px; line-height: 1.5; margin: 0; text-align: center;'>\n"
            	    + "Se o botão não funcionar, copie e cole o link abaixo no seu navegador:<br>\n"
            	    + "<a href='http://http://192.168.10.84/:8010/redefinirSenha.htmlemail=" + destinatario + "' target='_blank' style='color: #0066cc; text-decoration: none; word-break: break-all;'>CLIQUE AQUI</a>\n"
            	    + "</p>\n"    
            	    + "</div>\n"
            	    + "</div>\n"
            	    + "<div style='text-align: center; margin-top: 24px;'>\n"
            	    + "<p style='color: #9aa5b1; font-size: 12px; margin: 0;'>\n"
            	    + "© 2026 Sua Empresa. Todos os direitos reservados.\n"
            	    + "</p>\n"
            	    + "</div>\n"
            	    + "</div>";

            helper.setText(html, true);

            // envia email
            mailSender.send(message);

            return "Email enviado com sucesso!";

        } catch (Exception e) {

            e.printStackTrace();

            return "Erro ao enviar email: " + e.getMessage();
        }
    }
}
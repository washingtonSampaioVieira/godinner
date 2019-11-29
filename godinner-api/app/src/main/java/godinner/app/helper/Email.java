package godinner.app.helper;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import godinner.app.model.Consumidor;


//ENVIAR EMAIL COM CODIGO DE CONFIRMAÇÃO PARA USUARIOS
public class Email {

	
	public static boolean enviar(Consumidor c) { 	
		HtmlEmail email = new HtmlEmail();
		email.setSmtpPort(465);//PORTA DE SAIDA DO EMAIL
		email.setHostName("smtp.googlemail.com");//HOST DE ENVIO(NO CASO O GMAIL
		try {
			email.setAuthenticator(new DefaultAuthenticator("brace.everything@gmail.com", "duh2019s2@"));//AUTENTICAÇÃO DO REMETENTE DO EMAIL
			email.setSSLOnConnect(true);
			email.setFrom("godinnerfood@gmail.com", "Godinner!");//REMETENTE
			email.addTo(c.getEmail());
			email.setSubject("Seu pedido saiu para entrega!");//ASSUNTO

			//MENSAGEM DE DE EMAIL EM HTML
			String msgHTML = "\"<head> \\r\\n\" + \r\n" + 
					"					\"    <link href=\\\"https://fonts.googleapis.com/css?family=Lexend+Deca&display=swap\\\" rel=\\\"stylesheet\\\"> \\r\\n\" + \r\n" + 
					"					\"    <link href=\\\"https://fonts.googleapis.com/css?family=Manjari&display=swap\\\" rel=\\\"stylesheet\\\"> \\r\\n\" + \r\n" + 
					"					\"    <link href=\\\"https://fonts.googleapis.com/css?family=Space+Mono:700&display=swap\\\" rel=\\\"stylesheet\\\"> \\r\\n\" + \r\n" + 
					"					\"</head>\\r\\n\" + \r\n" + 
					"					\"<center>\\r\\n\" + \r\n" + 
					"					\"    <table class=\\\"conteudo\\\" style=\\\"padding:20px;margin:0px; background-image: linear-gradient(to bottom right, rgb(224, 103, 59), rgb(247, 128, 85));box-sizing: border-box; border-radius: 10px;\\\">\\r\\n\" + \r\n" + 
					"					\"        <tr height=\\\"30\\\">\\r\\n\" + \r\n" + 
					"					\"            <td width=\\\"100%\\\" colspan=\\\"3\\\"><h2 class=\\\"titulo-email\\\" style=\\\"padding:0px;margin:0px;  color: #eee;font-family: 'Lexend Deca', sans-serif;\\\">Olá \\\" +c.getNome() + \\\"</h2></td>\\r\\n\" + \r\n" + 
					"					\"        </tr>\\r\\n\" + \r\n" + 
					"					\"        <tr height=\\\"100\\\">\\r\\n\" + \r\n" + 
					"					\"            <td width=\\\"100%\\\">\\r\\n\" + \r\n" + 
					"					\"                <p class=\\\"texto-email\\\" style=\\\"padding:0px;margin:0px;  font-size: 18px;font-family: 'Manjari', sans-serif;margin-top: 5px;\\\">Seu pedido saiu para a entrega!</p>\\r\\n\" + \r\n" + 
					"					\"            </td>\\r\\n\" + \r\n" + 
					"					\"        </tr>\\r\\n\" + \r\n" + 
					"					\"    </table>\\r\\n\" + \r\n" + 
					"					\"</center>\";";
			email.setHtmlMsg(msgHTML);//DEFININDO QUAL A MENSAGEM DO EMAIL
			email.send();//ENVIANDO EMAIL
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
	}
}


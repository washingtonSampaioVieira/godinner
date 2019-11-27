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
			String msgHTML = 
					"<head> "+
					"    <link href=\"https://fonts.googleapis.com/css?family=Lexend+Deca&display=swap\" rel=\"stylesheet\"> "+
					"    <link href=\"https://fonts.googleapis.com/css?family=Manjari&display=swap\" rel=\"stylesheet\"> "+
					"    <link href=\"https://fonts.googleapis.com/css?family=Space+Mono:700&display=swap\" rel=\"stylesheet\"> "+
					"</head>"+
					"<table class=\"conteudo\" style=\"padding:20px;margin:0px; background-image: linear-gradient(to bottom right, #d7f1f5, #d2f1e4);box-sizing: border-box;\">"+
					"    <tr height=\"30\">"+
					"        <td width=\"100%\" colspan=\"3\"><h2 class=\"titulo-email\" style=\"padding:0px;margin:0px;  color: #157581;font-family: 'Lexend Deca', sans-serif;\">Olá "+c.getNome()+"</h2></td>"+
					"    </tr>"+
					"    <tr height=\"100\">"+
					"        <td width=\"100%\" colspan=\"2\">"+
					"            <p class=\"texto-email\" style=\"padding:0px;margin:0px;  font-size: 18px;font-family: 'Manjari', sans-serif;margin-top: 5px;\">Seu pedido saiu para a entrega!</p>"+
					"        </td>"+
					"        <td width=\"100%\"></td>"+
					"    </tr>"+
					"        <td width=\"33%\"></td>"+
					"    </tr>"+
					"</table>";
			email.setHtmlMsg(msgHTML);//DEFININDO QUAL A MENSAGEM DO EMAIL
			email.send();//ENVIANDO EMAIL
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
	}
}


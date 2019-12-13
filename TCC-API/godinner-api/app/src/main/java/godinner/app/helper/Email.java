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
			email.setAuthenticator(new DefaultAuthenticator("godinner.tk@gmail.com", "TcC-G$odinner"));//AUTENTICAÇÃO DO REMETENTE DO EMAIL
			email.setSSLOnConnect(true);
			email.setFrom("godinner.tk@gmail.com", "Godinner!");//REMETENTE
			email.addTo(c.getEmail());
			email.setSubject("Seu pedido saiu para entrega!");//ASSUNTO

			//MENSAGEM DE DE EMAIL EM HTML
			String msgHTML = "<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <meta name='viewport' content='width=device-width, initial-scale=1.0'>    <meta http-equiv='X-UA-Compatible' content='ie=edge'>    <title>Document</title></head><body>    <head>      <link href=https://fonts.googleapis.com/css?family=Lexend+Deca&display=swap rel=stylesheet>       <link href=https://fonts.googleapis.com/css?family=Manjari&display=swap rel=stylesheet>       <link href=https://fonts.googleapis.com/css?family=Space+Mono:700&display=swap rel=stylesheet>   </head>  <center>      <table cellpadding='10' class=conteudo style='alt:20px;margin:0px; background-image: linear-gradient(to right top, #ee5e2a, #ee6331, #ee6838, #ee6d3e, #ee7245); box-sizing: border-box; border-radius: 10px;'>          <tr height=30>              <td width=100% colspan=3><h2 class=titulo-email style='padding:0px;margin:0px;  color: #fff;font-family: sans-serif;'>Olá " + c.getNome() + "</h2></td>          </tr>          <tr height=100>              <td width=100%>                  <p class=texto-email style='padding:0px;margin:0px;  font-size: 18px;font-family: Manjari, sans-serif;margin-top: 5px; color: #fff;'>Seu pedido saiu para a entrega!</p>              </td>          </tr>      </table>  </center></body></html>";	
			email.setHtmlMsg(msgHTML);//DEFININDO QUAL A MENSAGEM DO EMAIL
			email.send();//ENVIANDO EMAIL
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
	}
}


import React from 'react';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

//CONSTANTES DOS TIPOS DE NOTIFICAÇÃO  
export const SUCESSO = "success";
export const INFO = "info";
export const AVISO = "warning";
export const ERRO = "error";
export const PADRAO = "default";

//CONSTANTES DE MENSAGENS GENÉRICAS
export const CAMPO_VAZIO = "Preencha todos os campos";
export const ERRO_CONEXAO = "Erro de conexão";
export const USUARIO_INVALIDO = "Email ou senha incorretos";
export const NOME_MINIMO = "O nome do restaurante deve conter no mínimo 3 caracteres";
export const ERRO_CNPJ = "Esse CNPJ já está cadastrado ou é inválido";
export const SENHA_MINIMA = "A senha deve conter no mínimo 6 caracteres";
export const ERRO_EMAIL = "Este e-mail já está cadastrado ou é inválido";
export const ERRO_SENHA = "A Senha está incorreta";
export const ERRO_REQUISICAO = "Verifique se todos os campos foram preenchidos corretamente";

//FUNÇÃO DE NOTIFICAÇÃO
export const Notificacao = (tipo, mensagem) => {

    const parametros = {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
    }

    const msgBraca = (<font className="text-light">{mensagem} </font>)
    const msgPreta = (<font className="text-dark">{mensagem} </font>)


    switch (tipo) {
        case 'info':
            toast.info(msgBraca, {
                parametros
            });
            break;
        case 'success':
            toast.success(msgBraca, {
                parametros
            });
            break;
        case 'warning':
            toast.warn(msgPreta, {
                parametros
            });
            break;
        case 'error':
            toast.error(msgBraca, {
                parametros
            });
            break;
        case 'default':
            toast(msgPreta, {
                parametros
            });
            break;
        default:

    }

}
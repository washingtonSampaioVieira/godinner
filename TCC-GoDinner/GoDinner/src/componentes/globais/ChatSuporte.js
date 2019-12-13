
import { MessageList } from 'react-chat-elements';
import "../../index.css"
import 'react-chat-elements/dist/main.css';
import iconChat  from '../../recursos/icons/ico-chat.png';
import React, {Component} from 'react' ;
import io from 'socket.io-client';
import $ from "jquery" 
import {confirmAlert} from 'react-confirm-alert';
import './css/react-confirm-alert.css'; // Import css

const socket = io('http://godinner.tk:3000', {secure:false});

const initialState ={
                        dataSource:[]
                    }
class ChatSuporte extends Component{

    constructor(){
        super();
        this.state = { ...initialState }
    }

    componentDidMount(){
        socket.on('suporte', data => {
            this.verificarDesponibilidade(data.id);
            $("#top_chat").html(`Usuário: ${data.nome}`);
        });

        $('#campo_da_mensagem').keypress(function (e) {
            if (e.which == 13 || e.keyCode == 13) {
                let texto = $("#campo_da_mensagem").val().trim();
                if(texto != ""){
                    $("#campo_da_mensagem").val("");
                    socket.emit('message', {username: "GoDinner", message:texto, remetente:"F" });
                }
                return false;
            }
        });
    }
    
    verificarDesponibilidade(id){
        const option={
            title: 'Suporte',
            message: 'Deseja atender o pedido de suporte?',
            buttons: [
            {
                label: 'Sim, agora!',
                onClick: () => this.entrarNoChamado(id)
            },
            {
                label: 'Não, estou ocupado(a)',
                // onClick: () => entrarNoChamado(id)
            }
            ]
        }
        confirmAlert(option);
    }

    entrarNoChamado(id){
        $('#top_chat').attr({scrollTop: $('#top_chat').attr('scrollHeight')});

        const mCaixaChat = document.getElementById("caixa_chat");
        let idFuncionario = localStorage.getItem("id");
        let username = localStorage.getItem("nome");
        let token = localStorage.getItem("token");
        socket.emit('join',{ room: id, idFuncionario: idFuncionario,token: token,  username: username, remetente: "F"});

        socket.off('message');
        socket.on('message', data => {
            let dataSourceNew = this.state.dataSource
            let lado = data.remetente === "F" ? "right" : "left" ;

            dataSourceNew.push(
                {
                    position: lado,
                    type: 'text',
                    text: data.message,
                    date: new Date()
                }
            );
            
            this.setState({dataSource: dataSourceNew});
            $("#caixa_chat").animate({
                scrollTop: $("#caixa_chat")[0].scrollHeight
            }, 200);

            $("#btn_chat").removeClass("desativado_btn_chat");
            
        });
    }

    enviarMensagem(){
        let texto = $("#campo_da_mensagem").val().trim();
        if(texto != ""){
            $("#campo_da_mensagem").val("");
            socket.emit('message', {username: "GoDinner", message:texto, remetente:"F" });
        }
    }

    abaixarLevantarChat(){
        if($("#caixa_chat").hasClass("caixa_chat")){
            $("#caixa_chat").removeClass("caixa_chat")
            $("#caixa_chat").addClass("caixa_baixa")
            $("#todape_chat").addClass("display_none")

            $("#top_chat").removeClass("top_chat")
            $("#top_chat").addClass("top_chat_baixo")
        }else{
            $("#top_chat").addClass("top_chat")
            $("#top_chat").removeClass("top_chat_baixo")

            $("#todape_chat").removeClass("display_none")
            $("#caixa_chat").removeClass("caixa_baixa")
            $("#caixa_chat").addClass("caixa_chat")
        }
    }

    render() {
        return(
            <div className="caixa_chat" id="caixa_chat" >
                <div className="top_chat p-2 justify-content-center text-light d-flex flex-rows" id="top_chat" onClick={() => this.abaixarLevantarChat()}>
                    <img className=" ml-2" src={iconChat} alt="chat" style={{maxWidth: "35px"}}/>
                    <font className="ml-2">Chat</font>
                </div>
                <MessageList
                    className='message-list'
                    lockable={true}
                    toBottomHeight={'70%'}
                    dataSource={this.state.dataSource} />

                    <div className="div_rodape_chat d-flex flex-rows" id="todape_chat">
                        <input text="text" class="input_envia_msg form-control w-75" id="campo_da_mensagem"/>
                        <button className="btn btn_enviar desativado_btn_chat text-light" id="btn_chat" onClick={() => this.enviarMensagem()}>
                            Enviar
                        </button>
                    </div>
            </div> 

        );
    }
}

export default ChatSuporte;
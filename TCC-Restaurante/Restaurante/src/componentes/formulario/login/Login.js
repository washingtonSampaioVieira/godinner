import React, { Component } from 'react';
import { Label } from '../../globais/label/Label';
import LogoGoDinner from '../../../recursos/imgs/img-login.png';
import { InputCadastro } from '../../globais/input/Input';
import { BotaoLink } from '../../globais/botao/Botao';
import $ from 'jquery';
import { DOMINIO, TOKEN } from '../../../link_config';
import { withRouter } from 'react-router-dom';
import PropTypes from "prop-types";
import { ERRO, Notificacao, CAMPO_VAZIO, USUARIO_INVALIDO, ERRO_REQUISICAO } from '../../../funcoes/Alerta';
import './style.css';

//ARMAZENA OS ESTADOS INICIAIS
const initialState = {
    restaurante: {
        email: '',
        senha: '',
    },
}

export const TOKEN_KEY = "token";


class FormularioLogin extends Component {

    //STATE ESTÁ RECEBENDO OS ESTADOS INICIAIS
    state = { ...initialState }

    //PROPRIEDADES DO WITH ROUTER
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };

    async enviaFormulario() {

        const restaurante = { ...this.state.restaurante }

        const url = `${DOMINIO}/login/restaurante`;

        const email = this.state.restaurante.email;

        const senha = this.state.restaurante.senha;

        $.ajax({
            url: url,
            type: 'post',
            data: JSON.stringify({ "email": email, "password": senha }),
            dataType: 'json',
            contentType: "application/json",
            success: function (resposta) {

                if (resposta.error) {

                    Notificacao(ERRO, USUARIO_INVALIDO);
                } else {

                    localStorage.setItem(TOKEN_KEY, resposta.token);

                    this.props.history.push("/restaurante");

                }


            }.bind(this),
            error: function (data) {

                Notificacao(ERRO, ERRO_REQUISICAO);

            }
        });
    }   

    //ENVIA OS DADOS DO FORMULÁRIO PARA O SESSION STORAGE
    validaCampos(e) {
        e.preventDefault();

        const bordasCampoVazio = 'border border-danger';

        //VERIFICA SE OS CAMPOS ESTÃO PRENCHIDOS
        if (!$('#email').val()) {
            $('#email').addClass(bordasCampoVazio);
        }

        if (!$('#senha').val()) {
            $('#senha').addClass(bordasCampoVazio);
        }

        if (!$('#email').val() || !$('#senha').val()) {
            Notificacao(ERRO, CAMPO_VAZIO);
        } else {
            this.enviaFormulario(e);
        }

    }

    //ATUALIZA AS INPUTS COM OS ESTADOS 
    atualizaCampo(e) {
        const restaurante = { ...this.state.restaurante }
        restaurante[e.target.name] = e.target.value;

        this.setState({ restaurante });

        const bordasCampoVazio = 'border border-danger';


        //REMOVE A BORDA VERMELHA DOS CAMPOS PREENCHIDOS
        if (!$('#email').val() === '') {
            $('#email').removeClass(bordasCampoVazio);
        }

        if (!$('#senha').val() === '') {
            $('#senha').removeClass(bordasCampoVazio);
        }

    }

    /* FORMULÁRIO DO LOGIN */
    render() {
        return (
            <form className="form-group bg-white container col-10 col-sm-8 col-md-6 col-lg-4 pt-4 pb-5 rounded border" style={{ maxWidth: 450 + 'px', maxHeight: 580 + 'px' }}>
                <div className="row justify-content-center mb-5">
                    <img src={LogoGoDinner} className="img-fluid icone-img" style={{ maxWidth: 300 + 'px' }} />
                </div>
                <div className="row mb-4">
                    <div className="mx-auto text-dark h3">Bem-vindo(a) GoDinner</div>
                </div>
                <div className="row mb-4 pl-5 pr-5 ">

                    <Label className="h6 text-secondary" texto="Email" />
                    <InputCadastro className="form-control p-1" type="email" id="email" name="email" maxWidth={100}
                        value={this.state.restaurante.email} onChange={e => this.atualizaCampo(e)} />

                </div>

                <div className="row mb-5 pl-5 pr-5">

                    <Label className="h6 text-secondary" texto="Senha" />
                    <InputCadastro className="form-control p-1" type="password" id="senha" name="senha"
                        value={this.state.restaurante.senha} onChange={e => this.atualizaCampo(e)}/>

                </div>
                <div className="row mt-5 pl-5 pr-5">
                    <button type="submit" onClick={e => this.validaCampos(e)} to="/cadastro" className="btn form-control p-1 btn-login">Entrar</button>
                </div>
                <div className="row mt-3 pl-5 pr-5">
                    <BotaoLink to="/cadastro" className="btn form-control p-1" texto="Cadastrar" />
                </div>
            </form>
        )
    }

}

export default withRouter(FormularioLogin);
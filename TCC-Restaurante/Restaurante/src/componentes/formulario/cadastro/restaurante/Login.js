import React, { Component } from 'react';
import { InputCadastro } from '../../../globais/input/Input';
import { Label } from '../../../globais/label/Label';
import { BotaoLink } from '../../../globais/botao/Botao';
import Corpo from '../../../corpo/Corpo';
import { DOMINIO } from '../../../../link_config';
import $ from 'jquery';
import { withRouter } from 'react-router-dom';
import PropTypes from "prop-types";
import { ERRO, Notificacao, CAMPO_VAZIO, SENHA_MINIMA, ERRO_SENHA, ERRO_EMAIL } from '../../../../funcoes/Alerta';

/*PROPRIEDADES DO CABEÇALHO*/
const propriedadesCabecalho = {
    to: '/cadastro/endereco',
    width: 'w-75'
}

//ARMAZENA OS ESTADOS INICIAIS
const initialState = {
    restaurante: {
        email: '',
        senha: '',
        confirmarSenha: '',
    },


    classErro: '',
    textoErro: ''
}

class FormularioLogin extends Component {

    //STATE ESTÁ RECEBENDO OS ESTADOS INICIAIS
    state = { ...initialState }

    //PROPRIEDADES DO WITH ROUTER
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };

    enviaFormulario() {

        const restaurante = { ...this.state.restaurante }

        var dados = sessionStorage.getItem('dados');

        const json = JSON.parse(dados);

        var novoDado = { ...json, ...restaurante };

        //RETIRA O CONFIRMAR SENHA DO ARRAY
        delete novoDado["confirmarSenha"];

        sessionStorage.setItem('dados', JSON.stringify(novoDado));

        const jsonRestaurante = sessionStorage.getItem('dados');


        const url = `${DOMINIO}/restaurante`;

        $.ajax({
            url: url,
            dataType: 'json',
            type: 'POST',
            data: jsonRestaurante,
            contentType: 'application/json',
            success: function (resposta) {

                sessionStorage.setItem('dados', JSON.stringify(resposta))
                this.props.history.push("/cadastro/bem-vindo");

            }.bind(this),
            error: function (data) {
                console.log('Erro:', data);

            }
        });
    }

    validaSenha(e) {

        if ($('#senha').val() !== '' && $('#confirmarSenha').val()
            && $('#senha').val().length >= 6 && $('#senha').val() === $('#confirmarSenha').val()
        ) {
            this.enviaFormulario(e)
        } else {

            Notificacao(ERRO, ERRO_SENHA);
        }
    }

    //ENVIA OS DADOS DO FORMULÁRIO PARA O SESSION STORAGE
    validaCampos(e) {
        e.preventDefault();

        const restaurante = this.state.restaurante;
        const email = restaurante.email;

        const bordasCampoVazio = 'border border-danger';

        //VERIFICA SE OS CAMPOS ESTÃO PRENCHIDOS
        if (!$('#email').val()) {
            $('#email').addClass(bordasCampoVazio);
        }

        if (!$('#senha').val()) {
            $('#senha').addClass(bordasCampoVazio);
        }

        if (!$('#confirmarSenha').val()) {
            $('#confirmarSenha').addClass(bordasCampoVazio);
        }

        if (!$('#email').val() || !$('#senha').val() || !$('#confirmarSenha').val()) {
            Notificacao(ERRO, CAMPO_VAZIO);
        }

        //VERIFICA SE A SENHA FOI DIGITADA CORRETAMENTE
        if ($('#senha').val() !== $('#confirmarSenha').val()) {
            Notificacao(ERRO, ERRO_SENHA);
        } else if ($('#senha').val().length < 6) {
            Notificacao(ERRO, SENHA_MINIMA);
        }

        //REALIZA AS REQUISIÇÕES NA API DE VALIDAÇÃO
        const URL_EMAIL = `${DOMINIO}/restaurante/valida/email/${email}`;

        $.ajax({
            url: URL_EMAIL,
            dataType: "text",
            type: 'GET',
            success: function (data) {
                if (data == "true") {

                    this.validaSenha(e)
                } else {
                    Notificacao(ERRO, ERRO_EMAIL)
                }
            }.bind(this),
            error: function (data) {

                console.log('Erro:', data);

            }
        });

    }

    //ATUALIZA AS INPUTS COM OS ESTADOS 
    atualizaCampo(e) {
        const restaurante = { ...this.state.restaurante }
        restaurante[e.target.name] = e.target.value
        this.setState({
            restaurante,
            textoErro: initialState.textoErro,
            classErro: initialState.classErro
        })

        const bordasCampoVazio = 'border border-danger';

        //REMOVE A BORDA VERMELHA DOS CAMPOS PREENCHIDOS
        if (!$('#email').val() === '') {
            $('#email').removeClass(bordasCampoVazio);
        }

        if (!$('#senha').val() === '') {
            $('#senha').removeClass(bordasCampoVazio);
        }

        if (!$('#confirmarSenha').val() === '') {
            $('#confirmarSenha').removeClass(bordasCampoVazio);
        }

    }

    /* FORMULÁRIO DO ENDEREÇO */
    renderForm() {
        return (
            <form className="form-group mt-5">
                <span className={this.state.classErro}>{this.state.textoErro}</span>
                <Label className="h2 mb-1" texto="Login do restaurante" />
                <div className="row mt-4 mb-4">
                    <InputCadastro className="col col-sm p-1 col-md col-lg p-1 ml-3 mr-3" id="email" name="email"
                        type="email" placeholder="Email" value={this.state.restaurante.email} onChange={e => this.atualizaCampo(e)} />
                </div>
                <div className="row mb-5">
                    <InputCadastro className="col col-sm col-md col-lg p-1 ml-3 mr-3" id="senha" name="senha" type="password"
                        placeholder="Senha" value={this.state.restaurante.senha} onChange={e => this.atualizaCampo(e)} />
                    <InputCadastro className="col col-sm col-md col-lg p-1 mr-3" id="confirmarSenha" name="confirmarSenha" type="password"
                        placeholder="Confirmar Senha" value={this.state.restaurante.confirmarSenha} onChange={e => this.atualizaCampo(e)} />
                </div>
                <div className="row justify-content-end">
                    <BotaoLink onClick={e => this.validaCampos(e)} className="col-4 col-sm-4 col-md-4 col-lg-4 btn-orange mr-3" texto="Próximo" />
                </div>

            </form>
        )
    }

    /*CORPO DA PÁGINA, COM  FORMULÁRIO DENTRO */
    render() {
        return (
            <Corpo {...propriedadesCabecalho}>{/*CABECALHO RECEBE AS PROPRIEDADES DA CONST*/}
                {this.renderForm()}

            </Corpo>
        )
    }
}

export default withRouter(FormularioLogin);
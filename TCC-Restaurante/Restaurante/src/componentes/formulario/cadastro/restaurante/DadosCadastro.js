import React, { Component } from 'react';
import PropTypes from "prop-types";
import { withRouter } from 'react-router-dom';

import { InputCadastro } from '../../../globais/input/Input';
import { Label } from '../../../globais/label/Label';
import { BotaoLink } from '../../../globais/botao/Botao';
import Corpo from '../../../corpo/Corpo';
import $ from 'jquery';
import { DOMINIO } from '../../../../link_config';
import { ERRO, Notificacao, CAMPO_VAZIO, NOME_MINIMO, ERRO_CNPJ } from '../../../../funcoes/Alerta';

/*PROPRIEDADES DO CABEÇALHO*/
const propriedadesCabecalho = {
    to: '/',
    width: 'w-25'
}

//ARMAZENA OS ESTADOS INICIAIS
const initialState = {
    restaurante: {
        razaoSocial: '',
        cnpj: '',
        telefone: '',
    },

    classErro: '',
    textoErro: ''
}

class FormularioDados extends Component {

    //STATE ESTÁ RECEBENDO OS ESTADOS INICIAIS
    state = { ...initialState }

    //PROPRIEDADES DO WITH ROUTER
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };

    //REDIRECIONA O COMPONENTE VALIDADO
    campoValidado() {
        const restaurante = { ...this.state.restaurante }
        sessionStorage.setItem('dados', JSON.stringify(restaurante));

        const dados = sessionStorage.getItem('dados')

        if (dados !== null) {
            this.props.history.push("/cadastro/endereco");
        }

    }

    //ENVIA OS DADOS DO FORMULÁRIO PARA O SESSION STORAGE
    validaCampos() {      

        const bordasCampoVazio = 'border border-danger';

        //VERIFICA SE OS CAMPOS ESTÃO PRENCHIDOS
        if (!$('#cnpj').val()) {
            $('#cnpj').addClass(bordasCampoVazio);
        }

        if (!$('#razaoSocial').val()) {
            $('#razaoSocial').addClass(bordasCampoVazio);
        }

        if (!$('#telefone').val()) {
            $('#telefone').addClass(bordasCampoVazio);
        }

        if (!$('#cnpj').val() || !$('#razaoSocial').val() || !$('#telefone').val()) {
            Notificacao(ERRO, CAMPO_VAZIO);
        } else if ($('#razaoSocial').val().length < 3) {
            Notificacao(ERRO, NOME_MINIMO);
        }else if($('#cnpj').val() !== '' && $('#razaoSocial').val() !== '' && $('#telefone').val() !== ''){
            this.validaCNPJ();
        }
        

    }

    validaCNPJ(e){

        const restaurante = this.state.restaurante;
        sessionStorage.setItem('dados', JSON.stringify(restaurante));
        
        const cnpj = restaurante.cnpj.replace("/", "@");

        //REALIZA AS REQUISIÇÕES NA API DE VALIDAÇÃO
        const URL_CNPJ = `${DOMINIO}/restaurante/valida/cnpj/${cnpj}`;

        $.ajax({
            url: URL_CNPJ,
            dataType: "json",
            type: 'GET',
            success: function (data) {
                if (data) {
                    this.campoValidado();

                } else {
                    Notificacao(ERRO, ERRO_CNPJ);
                }

            }.bind(this),
            error: function (data) {

            

            }
        });

    }

    //ATUALIZA AS INPUTS COM OS ESTADOS 
    atualizaCampo(e) {
        const restaurante = { ...this.state.restaurante }
        restaurante[e.target.name] = e.target.value;

        const bordasCampoVazio = 'border border-danger';

        //MÁCARAS DOS CAMPOS
        $("#cnpj").mask("99.999.999/9999-99");
        $('#telefone').mask('00 0000-0000');

        //REMOVE A BORDA VERMELHA DOS CAMPOS PREENCHIDOS
        if (!$('#cnpj').val() === '') {
            $('#cnpj').removeClass('border border-danger');
        }

        if (!$('#razaoSocial').val() === '') {
            $('#razaoSocial').removeClass(bordasCampoVazio);
        }

        if (!$('#telefone').val() === '') {
            $('#telefone').removeClass(bordasCampoVazio);
        }

        this.setState({
            restaurante,
            textoErro: initialState.textoErro,
            classErro: initialState.classErro
        })

    }

    /* FORMULÁRIO DO ENDEREÇO */
    renderForm() {

        return (

            /* FORMULÁRIO DO ENDEREÇO */
            <form className="form-group mt-5" >
                <span className={this.state.classErro}>{this.state.textoErro}</span>
                <Label className="h2 mb-4" texto="Dados do Restaurante" />
                <div className="row mb-4">
                    <InputCadastro className="col col-sm col-md col-lg p-1 ml-3 mr-3" id="razaoSocial" name="razaoSocial" type="text"
                        placeholder="Nome" value={this.state.restaurante.razaoSocial} onChange={e => this.atualizaCampo(e)} />
                </div>
                <div className="row mb-5">
                    <InputCadastro className="col col-sm col-md col-lg p-1 ml-3 mr-3" id="cnpj" name="cnpj" type="text"
                        placeholder="CNPJ" value={this.state.restaurante.cnpj} onChange={e => this.atualizaCampo(e)} />
                    <InputCadastro className="col col-sm col-md col-lg p-1 mr-3" id="telefone" name="telefone" type="text"
                        placeholder="Telefone" value={this.state.restaurante.telefone} only-these-char="0123456789" onChange={e => this.atualizaCampo(e)} />
                </div>
                {/*LINHA DO  BOTÃO COM A ROTA PARA O PRÓXIMA PÁGINA  */}
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

export default withRouter(FormularioDados);
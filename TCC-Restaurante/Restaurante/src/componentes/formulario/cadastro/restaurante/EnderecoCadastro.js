import React, { Component } from 'react';
import { InputCadastro } from '../../../globais/input/Input';
import { Label } from '../../../globais/label/Label';
import { BotaoLink } from '../../../globais/botao/Botao';
import $ from 'jquery';
import { withRouter } from 'react-router-dom';
import PropTypes from "prop-types";
import Corpo from '../../../corpo/Corpo';
import { DOMINIO } from '../../../../link_config';
import { ERRO, Notificacao, CAMPO_VAZIO } from '../../../../funcoes/Alerta';

/*PROPRIEDADES DO CABEÇALHO*/
const propriedadesCabecalho = {
    to: '/cadastro',
    width: 'w-50'
}

class FormularioEndereco extends Component {

    constructor() {
        super();
        this.state = {
            restaurante: {
                cep: '',
                logradouro: '',
                bairro: '',
                complemento: '',
                numero: '',
                referencia: '',
                cidade: ''
            },
            cidade: [],

            estado: [],

            classErro: '',
            textoErro: ''
        }
    }

    //PROPRIEDADES DO WITH ROUTER
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };

    // state = { ...initialState }

    //ENVIA OS DADOS DO FORMULÁRIO PARA O SESSION STORAGE
    enviaFormulario(e) {

        var dados = sessionStorage.getItem('dados');

        const json = JSON.parse(dados);

        const idCidade = document.getElementById("sql_cidade").value

        const enderecoRestaurante = this.state.restaurante;
        enderecoRestaurante.cidade = { "id": idCidade };
        this.setState({ restaurante: enderecoRestaurante });

        var novoDado = { ...json, 'endereco': enderecoRestaurante };

        sessionStorage.setItem('dados', JSON.stringify(novoDado));

        this.props.history.push("/cadastro/login");

    }

    //ENVIA OS DADOS DO FORMULÁRIO PARA O SESSION STORAGE
    validaCampos(e) {
        e.preventDefault();

        const bordasCampoVazio = 'border border-danger';

        //VERIFICA SE OS CAMPOS ESTÃO PRENCHIDOS
        if (!$('#cep').val()) {
            $('#cep').addClass(bordasCampoVazio);
        }

        if (!$('#logradouro').val()) {
            $('#logradouro').addClass(bordasCampoVazio);
        }

        if (!$('#bairro').val()) {
            $('#bairro').addClass(bordasCampoVazio);
        }

        if (!$('#sql_estado').val()) {
            $('#sql_estado').addClass(bordasCampoVazio);
        }

        if (!$('#sql_cidade').val()) {
            $('#sql_cidade').addClass(bordasCampoVazio);
        }

        if (!$('#numero').val()) {
            $('#numero').addClass(bordasCampoVazio);
        }

        if (!$('#cep').val() || !$('#logradouro').val() || !$('#bairro').val() ||
            !$('#sql_cidade').val() || !$('#sql_cidade').val() || !$('#numero').val()) {

            Notificacao(ERRO, CAMPO_VAZIO);
        } else {
            this.enviaFormulario(e)
        }
    }

    //REQUISIÇÃO QUE BUSCA CIDADES DAQUELE ESTADO
    buscarCidadesporEstado(id = "") {
        let url = `${DOMINIO}/cidade`;
        if (id !== "") {
            url += "/" + id;
        }
        $.ajax({
            url: url,
            dataType: 'json',
            type: 'GET',
            success: function (resposta) {
                // this.state.cidade = "";

                this.setState({ cidade: resposta })

            }.bind(this),
            error: function (data) {
                console.log('Erro:', data);
            }
        });
    }

    //REQUISIÇÃO QUE BUSCA TODOS OS ESTADOS
    buscarEstados() {
        $.ajax({
            url: `${DOMINIO}/estado`,
            dataType: 'json',
            type: 'GET',
            success: function (resposta) {
                this.setState({ estado: resposta })
            }.bind(this),
            error: function (data) {
                console.log('Erro:', data);
            }
        });
    }

    componentWillMount() {
        this.buscarEstados();
        this.buscarCidadesporEstado(11);
    }


    //ATUALIZA AS INPUTS COM OS ESTADOS 
    atualizaCampo(e) {
        const restaurante = { ...this.state.restaurante }
        restaurante[e.target.name] = e.target.value
        this.setState({ restaurante })

        //Máscaras do campos
        $("#cep").mask("99999-999");

        let tamanhoCep = this.state.restaurante.cep;

        if(tamanhoCep.length === 8){
            this.atualizaCamposViaCep();
        }

        const bordasCampoVazio = 'border border-danger';

        //REMOVE A BORDA VERMELHA DOS CAMPOS PREENCHIDOS
        if (!$('#cep').val() === '') {
            $('#cep').removeClass(bordasCampoVazio);
        }

        if (!$('#logradouro').val() === '') {
            $('#logradouro').removeClass(bordasCampoVazio);
        }

        if (!$('#bairro').val() === '') {
            $('#bairro').removeClass(bordasCampoVazio);
        }

        if (!$('#sql_estado').val() === '') {
            $('#sql_estado').removeClass(bordasCampoVazio);
        }

        if (!$('#sql_cidade').val() === '') {
            $('#sql_cidade').removeClass(bordasCampoVazio);
        }

        if (!$('#numero').val() === '') {
            $('#numero').removeClass(bordasCampoVazio);
        }

    }

    //PEGA A CIDADE E O ID DELA
    atualizaCidade() {
        let id = document.getElementById("sql_estado").value
        this.buscarCidadesporEstado(id);
    }

    //REQUISIÇÃO QUE PEGA O CEP E PREENCHE OS CAMPOS
    atualizaCamposViaCep(e) {
        let cep = document.getElementById("cep").value;

        cep = cep.replace(/[^0-9]/g, "");

        if (cep.length > 4) {
            $.ajax({
                url: `${DOMINIO}/endereco/cep/${cep}`,
                dataType: 'json',
                success: function (resposta) {
                    const restaurante = { ...this.state.restaurante }

                    $("#logradouro").val(resposta.logradouro);
                    $("#bairro").val(resposta.bairro);


                    //ATRIBUI VALOR AOS ESTADOS
                    this.state.restaurante.logradouro = resposta.logradouro;
                    this.state.restaurante.bairro = resposta.bairro;


                    this.atualizaCidade();
                }.bind(this),
                error: function (data) {
                    console.log('Erro:', data);
                }
            });
        }

    }



    /* FORMULÁRIO DO ENDEREÇO */
    renderForm() {
        return (

            <form className="form-group mt-5">
                <Label className="h2 mb-1" texto="Endereço do restaurante" />
                <div className="row mt-4 mb-4">
                    <InputCadastro className="col col-sm p-1 col-md col-lg p-1 ml-3 mr-3" id="cep" name="cep" type="text"
                        placeholder="CEP" value={this.state.restaurante.cep}  onChange={e => this.atualizaCampo(e)} />
                    <InputCadastro className="col col-sm-5 col-md-5 col-lg-5 p-1 mr-3" id="logradouro" name="logradouro" type="text"
                        placeholder="Logradouro" value={this.state.restaurante.longradouro} onChange={e => this.atualizaCampo(e)} />
                    <InputCadastro className="col col-sm col-md col-lg p-1 mr-3" id="bairro" name="bairro" type="text"
                        placeholder="Bairro" onChange={e => this.atualizaCampo(e)} />
                </div>
                <div className="row mb-4" >

                    <select name="estado" id="sql_estado" onChange={e => this.atualizaCidade(e)} className="col col-sm col-md col-lg p-1 ml-3 mr-3 border-input">
                        <option value=''>Estado</option>
                        {this.state.estado.map(item => (
                            <option key={item.id} id={"estado-" + item.id} value={item.id}>
                                {item.estado}
                            </option>
                        ))}
                    </select>
                    <select name="cidade" id="sql_cidade" className="col col-sm col-md col-lg p-1 mr-3 border-input">
                        <option value=''>Cidade</option>
                        {this.state.cidade.map(item => (
                            <option key={item.id} id={"cidade-" + item.id} value={item.id}>
                                {item.cidade}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="row mb-4">
                    <InputCadastro className="col col-sm-9 col-md-9 col-lg-9 p-1 ml-3 mr-3 " id="complemento" name="complemento" type="text"
                        placeholder="Complemento" value={this.state.restaurante.complemento} onChange={e => this.atualizaCampo(e)} />
                    <InputCadastro className="col col-sm col-md col-lg p-1 mr-3" id="numero" name="numero" type="text"
                        placeholder="N°" value={this.state.restaurante.n} onChange={e => this.atualizaCampo(e)} />
                </div>
                <div className="row mb-1">
                    <Label className="col col-sm col-md col-lg p-1 ml-3 mr-3" texto="Referência" />
                </div>
                <div className="row mb-5">
                    <textarea className="col col-sm col-md col-lg p-2 ml-3 mr-3 border-input" id="referencia" maxLength="150" name="referencia" type="text" value={this.state.restaurante.referencia} onChange={e => this.atualizaCampo(e)}></textarea>
                </div>
                {/*LINHA DO  BOTÃO COM A ROTA PARA O PRÓXIMA PÁGINA  */}
                <div className="row justify-content-end">
                    <BotaoLink onClick={e => this.validaCampos(e)} className="col-3 col-sm-3 col-md-3 col-lg-3 btn-orange mr-3" texto="Próximo" />
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

export default withRouter(FormularioEndereco);


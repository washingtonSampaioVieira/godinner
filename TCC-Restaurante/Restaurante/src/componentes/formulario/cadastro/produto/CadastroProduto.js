import React, { Component } from 'react';
import $ from 'jquery';
import { withRouter, Link } from 'react-router-dom';
import PropTypes from "prop-types";

import { FaTrashAlt } from "react-icons/fa";

import { CadastroImagem } from './CadastroImagem';
import { DOMINIO } from '../../../../link_config';
import { SessaoCategoria } from './SessaoCategoria';
import { CorpoCemVh } from '../../../corpo/styled';

//ARMAZENA OS ESTADOS INICIAIS
const initialState = {
    produto: {
        nome: '',
        preco: '',
        desconto: '',
        descricao: '',
        vendidos: 0
    },

    isChecked: true || false,

    restaurante: {
        id: ''
    },

}

class CadastroProduto extends Component {

    //ARMAZENA OS ESTADOS INICIAIS
    state = { ...initialState }

    //PROPRIEDADES DO WITH ROUTER
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };

    apagarIdProduto() {
        sessionStorage.removeItem("id_produto");
    }

    excluirProduto() {

        const { id } = this.props.match.params;

        const url = `${DOMINIO}/produto/${id}`;

        const token = localStorage.getItem("token");

        $.ajax({
            url: url,
            type: 'DELETE',
            headers: { 'token': token },
            success: (result) => {

                this.props.history.push("/restaurante/visualizar-produto");

            }, error: (result) => {
                console.log(result);
            }
        });
    }

    verificaStatus() {

        const { id } = this.props.match.params;
        const token = localStorage.getItem('token');

        const url = `${DOMINIO}/produto/${id}`;

        if (id !== null && id !== undefined) {



            $.ajax({
                url: url,
                type: 'get',
                headers: { 'token': token },
                success: function (resposta) {

                    this.setState({ produto: resposta })

                    switch (resposta.status) {
                        case "0":


                            break;
                        case "1":



                            $('#btn-status').attr("checked", "checked");

                            break;
                    }
                    $("#btn-lixeira").removeClass("d-none");
                    $("#btn-switch").removeClass("d-none");

                }.bind(this),
                error: function (data) {
                    console.log('Erro:', data);

                }
            });


        } else {

        }
    }

    componentDidMount() {

        const { id } = this.props.match.params;

        if (id !== null || id !== '') {
            this.verificaStatus();
        }

    }

    enviaFormulario(e) {
        e.preventDefault();

        const produto = { ...this.state.produto };

        const { id } = this.props.match.params;

        let url;

        let novoproduto;

        let method;

        const token = localStorage.getItem('token');

        if (id !== null && id !== undefined) {
            method = 'put';

            novoproduto = { ...produto };
            url = `${DOMINIO}/produto`;

        } else {
            method = 'post';

            this.state.restaurante.id = localStorage.getItem("id");

            novoproduto = { ...produto, 'restaurante': this.state.restaurante };
            url = `${DOMINIO}/produto`;
        }

        $.ajax({

            url: url,
            contentType: "application/json",
            dataType: 'json',
            headers: { 'token': token },
            type: method,
            data: JSON.stringify(novoproduto),


            success: function (resposta) {

                this.props.history.push(`/restaurante/cadastro-produto/${resposta.id}`);

                $("#btn-lixeira").removeClass("d-none");
                $("#btn-switch").removeClass("d-none");
                $('#btn-status').attr("checked", "checked");



            }.bind(this),
            error: function () {

            }
        });
    }

    //ATIVA E DESATIVA O PRODUTO
    desativarProduto() {

        const { id } = this.props.match.params;

        const url = `${DOMINIO}/produto/status/${id}`;

        const token = localStorage.getItem("token");

        $.ajax({
            url: url,
            type: 'PUT',
            headers: { 'token': token },
            success: function (data) {



            }
        });

    }



    //ATUALIZA AS INPUTS COM OS ESTADOS 
    atualizaCampo(e) {
        const produto = { ...this.state.produto }
        produto[e.target.name] = e.target.value;

        //MÁSCARA E VALIDAÇÃO
        $('#desconto').mask('999');
        $('#preco').mask('999');

        this.setState({ isChecked: !this.state.isChecked });

        this.setState({
            produto
        });

    }



    render() {
        const { nome, preco, desconto, descricao } = this.state.produto;
        const { isChecked } = this.state.isChecked;
        const { id } = this.props.match.params;
        return (
            <CorpoCemVh className="container mx-auto">
                <div className="row mt-5 mb-5 mr-5 justify-content-center ">
                    <h1>Cadastro de Produtos</h1>
                    <label className="switch ml-5 mt-2 d-none" id="btn-switch">
                        <input id="btn-status" type="checkbox" value={isChecked} onChange={e => this.atualizaCampo} onClick={e => this.desativarProduto(e)} />
                        <div className="slider"></div>
                    </label>
                    <Link style={{ color: 'inherit' }} onClick={id => this.excluirProduto()}>
                        <FaTrashAlt id="btn-lixeira" className="ml-5 mt-3 d-none" size={25} />
                    </Link>

                </div>

                <form className="container p-0 mx-auto" onSubmit={e => this.enviaFormulario(e)} style={{ maxWidth: 75 + '%' }}>

                    <h4>1º Passo</h4>
                    <hr />

                    <div className="table mx-auto w-75">

                        <div className="row p-2 mb-3">
                            <div className="col-12">
                                <label className="h5">Nome do Produto</label>
                                <input className="form-control" type="text" name="nome" id="nome" value={nome} onChange={e => this.atualizaCampo(e)} required />
                            </div>
                        </div>

                        <div className="row p-2 mb-3">
                            <div className="col-12 col-md-3 mb-3">
                                <label className="h5">Preço</label>
                                <input className="form-control" type="text" name="preco" id="preco" value={preco} onChange={e => this.atualizaCampo(e)} required />
                            </div>
                            <div className="col-9 col-md-3">
                                <label className="h5">Promoção</label>
                                <input className="form-control" maxLength="3" type="text" name="desconto" id="desconto" value={desconto} onChange={e => this.atualizaCampo(e)} required />
                            </div>
                            <div className="col-3">
                                <h5 className="simbolo-porcentagem">%</h5>
                            </div>
                        </div>

                        <div className="row p-2 mb-3">
                            <div className="col-12">
                                <div className="form-group">
                                    <label for="exampleFormControlTextarea1" className="h5">Descrição do Produto</label>
                                    <textarea className="form-control" id="exampleFormControlTextarea1" rows="4" id="descricao" name="descricao" value={descricao} onChange={e => this.atualizaCampo(e)} required ></textarea>
                                </div>
                            </div>
                        </div>

                        <div className="row p-2 mb-3">
                            <div className="col-12">
                                <button type="submit" className="btn btn-outline-success btn-sm col-lg-5" id="prox-campo" >
                                    <span className="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    Próximo Passo
                                </button>
                            </div>
                        </div>

                        {/* Segundo Passo */}
                    </div>
                    <div className="table mt-5">

                        <CadastroImagem className="disabilita-elemento" idProduto={id} />

                    </div>
                    <div className="table mt-5">
                        <div className="col col-sm col-md col-lg">
                            <SessaoCategoria className="disabilita-elemento" id="categoria-produto" idProduto={id} />
                        </div>
                    </div>
                    <div className="row mx-auto w-100 mt-2 justify-content-end">
                        <div className="col-4 col-sm-4 col-md-3 col-lg-2">
                            <Link class="btn btn-success" to="/restaurante/visualizar-produto">Finalizar</Link>
                        </div>
                    </div>
                </form>
            </CorpoCemVh>
        )
    }
}

export default withRouter(CadastroProduto);
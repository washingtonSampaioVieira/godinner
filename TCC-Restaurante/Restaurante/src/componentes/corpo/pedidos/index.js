import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import './style.css'
import { FaSyncAlt } from "react-icons/fa";
import { DOMINIO, CORES_STATUS } from '../../../link_config';
import $ from 'jquery';
import { IoMdClose } from "react-icons/io";
import ModalProduto from '../../modals/Modalproduto';
import { CorpoCemVh } from '../styled';

export class SeusPedidos extends Component {

    constructor(props) {
        super();

        this.state = { pedidos: [] }


    }

    componentDidMount() {

        this.visualizarPedidos();

        $('.menu-pedido').click(function () {
            $('.menu-pedido').removeClass('text-dark border-bottom border-secondary').addClass("text-secondary");
            $(this).addClass('text-dark border-bottom border-secondary');
        });
    }

    visualizarPedidos(e) {

        let id = localStorage.getItem("id");
        let token = localStorage.getItem("token");

        const url = `${DOMINIO}/pedidos/producoes/${id}`;

        $.ajax({
            url: url,
            method: 'get',
            headers: { "token": token },
            dataType: 'json',
            contentType: 'application/json',
            success: function (resposta) {



                this.pedidosProducao(resposta);

            }.bind(this),
            error: function (data) {


            }
        });
    }

    pedidosProducao(resposta) {

        this.setState({ pedidos: [] });

        let id = localStorage.getItem("id");
        let token = localStorage.getItem("token");

        const url = `${DOMINIO}/pedidos/abertos/${id}`;

        $.ajax({
            url: url,
            method: 'get',
            headers: { "token": token },
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {

                let dados = resposta.concat(data);

                this.setState({ pedidos: dados });


            }.bind(this),
            error: function (data) {


            }
        });
    }

    pedidosEntregue(e) {

        this.setState({ pedidos: [] });

        let id = localStorage.getItem("id");
        let token = localStorage.getItem("token");

        const url = `${DOMINIO}/pedidos/entregas/${id}`;

        $.ajax({
            url: url,
            method: 'get',
            headers: { "token": token },
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {

                this.setState({ pedidos: data });


            }.bind(this),
            error: function (data) {

            }
        });
    }

    entregues(){
        this.setState({ pedidos: [] });
    }

    render() {
        return (
            <CorpoCemVh className="container pb-5">

                <div className="ml-2 border mx-auto text-center rounded mensagem-slogan mb-5">Rapidez  + Qualidade =  + Dinheiro  + Clientes </div>
                <div className="row mb-5 mx-auto select-data">
                    <div className="col">
                        <button type="button" class="btn btn-warning font-weight-bold" onClick={e => this.visualizarPedidos(e)}>
                            <FaSyncAlt className="mr-2" />Atualizar
                        </button>
                    </div>
                </div>
                <div className="row border-bottom mx-auto pl-3 pb-2" style={{ cursor: 'pointer' }}>
                    <span className="col-6 col-sm-6 col-md-6 col-lg-3 text-center text-sencodary menu-pedido" onClick={e => this.visualizarPedidos(e)} >Em produção</span>
                    <span className="col-6 col-sm-6 col-md-6 col-lg-3 text-center text-secondary menu-pedido" onClick={e => this.pedidosEntregue(e)}>Sendo entregue</span>
                    <span className="col-6 col-sm-6 col-md-6 col-lg-2 text-center text-secondary menu-pedido" onClick={e => this.entregues(e)}>Entregues</span>
                </div>

                {this.state.pedidos.map(item => (
                    <DadosPedido item={item || ""} />
                ))}
            </CorpoCemVh>
        );
    }
}

export class DadosPedido extends Component {
    constructor(props) {
        super(props);

        this.state = { item: props.item, isOpen: false }

    }

    //ABRE A MODAL
    openModal() {

        this.setState({ isOpen: true });
    }

    //FECHA A MODAL
    closeModal() {

        this.setState({ isOpen: false });
    }

    horaPedidoFormatada(hora) {

        let horaFormatada = hora.substring(11, 16);

        return horaFormatada;
    }

    previsaoPedido(hora) {

        let horaInicio = new Date(hora);

        horaInicio.setMinutes(horaInicio.getMinutes() + 30);

        let previsao = `${horaInicio.getHours()}:${horaInicio.getMinutes()}`

        return previsao;
    }

    atualizaStatusPedido(idPedido) {

        let token = localStorage.getItem("token");

        const url = `${DOMINIO}/pedidos/entregue/${idPedido}`;

        $.ajax({
            url: url,
            method: 'PUT',
            headers: { "token": token },
            success: function (data) {

                this.closeModal(data);


            }.bind(this),
            error: function (data) {

            }
        });
    }

    render() {
        const item = this.state.item;
        return (
            <div>
                <ModalProduto show={this.state.isOpen}>
                    <div className="modalPedidos pt-3">
                        <div className="d-flex flex-row-reverse bd-highlight mt-2 mb-2">
                            <IoMdClose className="close" onClick={e => this.closeModal(e)} />
                        </div>
                        <div className="row justify-content-center">
                            <h3>Pedido realizado por: {item.consumidor.nome}</h3>
                            <h6>Endereço para entrega: {item.consumidor.endereco.longradouro}, {item.consumidor.endereco.numero} - {item.consumidor.endereco.bairro}, {item.consumidor.endereco.cidade.cidade} - {item.consumidor.endereco.cidade.uf}, {item.consumidor.endereco.cep}</h6>
                        </div>
                        <hr />
                        <div className="row justify-content-center">
                            <p className="col">Pedido feito às: {this.horaPedidoFormatada(this.state.item.dataDoPedido)}</p>
                            <p className="col text-info">Valor de entrega:  R${item.valorEntrega}</p>
                        </div>
                        <div className="row justify-content-center">
                            <p className="col ">Previsão de entrega: {this.previsaoPedido(item.dataDoPedido)}</p>
                            <p className="col text-success">Valor total R${item.valorTotal}</p>
                        </div>
                        <div className="row justify-content-center">


                        </div>
                        <hr />
                        {item.produtos.map(item => (
                            <>
                                <div className="row justify-content-center">
                                    <h4 className="col">{item.quantidade}x {item.produto.nome}</h4>
                                </div>
                                <div className="row justify-content-center">
                                    <p className="col">Valor Unitário: R$ 10,00</p>
                                    <p className="col">Valor Total: R$ 10,00</p>
                                    <div className="col">Desconto: R${item.produto.desconto}</div>
                                </div>

                                <hr />
                            </>
                        ))}
                        <div className="row pl-3">
                            <label>Observação:</label>
                            <textarea className="w3-input" readOnly="readonly">
                                {item.descricao}
                            </textarea>
                        </div>
                        <hr />
                        <div className="row justify-content-start pl-3 mt-4">
                            <label className="mr-2 text-secondary">Status:</label>
                            <label className="text-secondary">
                                {item.statusPedido.descricao}
                            </label>
                        </div>
                        <div className="row justify-content-end pr-2 mb-3 ">
                            <Link to="/restaurante/pedidos" style={{ display: item.statusPedido.id === 2 ? 'block' : 'none' }} id="btn-status-pedido" className="btn btn-primary" onClick={e => this.atualizaStatusPedido(item.id)}>Pronto para Entrega</Link>
                            <Link to="/restaurante/pedidos" style={{ display: item.statusPedido.id === 2 && item.statusPedido.id !== 1 ? 'none' : 'block' }} id="btn-status-pedido-entregue" className="btn btn-primary">Finalizar</Link>
                        </div>
                    </div>
                </ModalProduto>
                <div className="card mx-auto mt-5 max" id={item.id} onClick={e => this.openModal(e)} >
                    <div className="text-right pr-3 pt-2 mb-2 mt-2">
                        <span className={`${CORES_STATUS[item.statusPedido.id]} rounded-circle mr-2 circle`}></span>
                        <font className="">{item.statusPedido.descricao}</font>
                    </div>

                    <div className=" text-right pr-3">

                        <font className="text-success mr-3">Pedido: {this.horaPedidoFormatada(item.dataDoPedido)} </font>
                        <font className="text-info">Previsão: {this.previsaoPedido(item.dataDoPedido)}</font>

                    </div>

                    <div className="row mt-3">
                    </div>
                    {item.produtos.map(item => (
                        <ul className="list-group list-group-flush">
                            <li className="list-group-item">
                                <span className="font-weight-bold">{item.quantidade}x</span> {item.produto.nome}
                            </li>
                        </ul>
                    ))}

                    <div className="p-4 text-muted">

                        <blockquote className="text-muted">Pedido realizado por: {item.consumidor.nome}</blockquote>
                        <blockquote className="text-success">Valor total: {item.valorTotal}</blockquote>
                        <blockquote className="text-muted h6">Obs: {item.descricao}</blockquote>

                    </div>
                </div >

            </div>

        )
    }
}


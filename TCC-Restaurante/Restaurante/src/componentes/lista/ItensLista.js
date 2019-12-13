
import React, { Component } from 'react';
import { FaPencilAlt } from 'react-icons/fa';
import { IoMdClose } from "react-icons/io";
import ModalProduto from '../modals/Modalproduto';
import { FOTOLANCHEPADRAO, DOMINIO_IMG } from "../../link_config"
import { Image } from 'react-bootstrap';
import { Link } from 'react-router-dom';

class ItensLista extends Component {

    constructor(props) {
        super();

        this.state = { item: props.item }

    }

    // status modal

    toggleModal = () => {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return (
            <div className="item-list-p list-group-item-action mb-3 w-100" onClick={this.toggleModal}>

                <ModalProduto show={this.state.isOpen} onClose={this.toggleModal}>
                    <div className="modalStyle pt-3" >
                        <div className="d-flex flex-row-reverse bd-highlight mt-2 mb-2">
                            <IoMdClose className="close" />
                        </div>
                        <div className="modal-header h-50 pt-0 ">
                            <figure className="figure w-100 h-100">
                                <img className="mx-auto rounded w-100 h-100"
                                    src={this.state.item.foto.length === 0 ? FOTOLANCHEPADRAO : DOMINIO_IMG + this.state.item.foto[0].foto}
                                    alt={this.state.item.foto.length === 0 ? "foto produto" : this.state.item.foto[0].legenda} />
                            </figure>

                        </div>
                        <div className="modal-body ">
                            <div className="modal-title">
                                <h3>{this.state.item.nome}</h3>
                            </div>
                            <div className="" style={{ overflow: 'auto', height: 150 }}>
                                <p className="text-break">
                                    {this.state.item.descricao}
                                </p>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <p className="text-success blockquote">
                                R$ {this.state.item.preco}
                            </p>
                        </div>
                    </div>
                </ModalProduto>
                <div className="row m-1 ">
                    <div className="col-6 col-lg-3 p-2 ">
                        <div className=" mx-auto" >
                            <figure className="figure p-0 m-0">
                                <Image
                                    className="rounded-circle w-100"
                                    src={this.state.item.foto.length === 0 ? FOTOLANCHEPADRAO : DOMINIO_IMG + this.state.item.foto[0].foto}
                                    alt={this.state.item.foto.length === 0 ? "Foto Produto" : this.state.item.foto[0].legenda}
                                    style={{ maxWidth: '150px !important', height: '150px' }} />

                            </figure>
                        </div>
                    </div>
                    <div className="col-6 col-lg-9">
                        <div className="row">
                            <div className="col-12 col-md-10 col-lg-11">
                                <p className="h4 p-2 m-0">{this.state.item.nome}</p>
                            </div>
                            <div className="col-12 col-md-2 col-lg-1 mt-2">
                                <Link to={`/restaurante/cadastro-produto/${this.state.item.id}`} className="text-muted">
                                    <FaPencilAlt className="pointer" size={30} />
                                </Link>
                            </div>
                        </div>

                        <div className="row ">
                            <div className="col p-1 ">
                                <p className="text-muted text-break ml-3" >
                                    {this.state.item.descricao}
                                </p>
                            </div>
                        </div>
                        <div className="row " >
                            <div className="col text-right mt-4">
                                <small className="text-success blockquote">R$ {this.state.item.preco}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ItensLista;
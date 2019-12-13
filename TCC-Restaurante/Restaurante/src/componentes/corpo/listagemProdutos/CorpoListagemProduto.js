import React, { Component } from 'react';
import '../../../recursos/css/style.css';
import $ from 'jquery';
import { FaSearch } from 'react-icons/fa';
import { DOMINIO } from "../../../link_config"
import ItensLista from './../../lista/ItensLista';
import { InputGroup, FormControl, ListGroup } from 'react-bootstrap';
import { CorpoCemVh } from '../styled';

class CorpoListagemProduto extends Component {

    constructor() {
        super();
        this.state = {
            itens: []
        }

    }

    componentDidUpdate() {
        $('span').click(function () {
            $('span').removeClass('text-dark border-bottom border-secondary').addClass("text-secondary");
            $(this).addClass('text-dark border-bottom border-secondary');
        });
    }

    visualizarProduto(e) {

        this.setState({ itens: [] });

        let id = localStorage.getItem("id");
        let token = localStorage.getItem("token");

        let url;


        switch (e) {
            case "ativo":
                url = `${DOMINIO}/produto/exposicoes/${id}`;

                break;
            case "desativo":
                url = `${DOMINIO}/produto/desativados/${id}`;

                break;
            default:
                url = `${DOMINIO}/produto/exposicoes/${id}`;
        }

        $.ajax({
            url: url,
            method: 'get',
            headers: { "token": token },
            dataType: 'json',
            contentType: 'application/json',
            success: function (resposta) {

                this.setState({ itens: resposta });

            }.bind(this),
            error: function (data) {


            }
        });
    }

    componentDidMount() {

        this.visualizarProduto();
    }

    maxWidth = {"maxWidth": "1600px" }

    render() {

        return (
            <CorpoCemVh className="mx-auto" style={this.maxWidth}>
                <InputGroup className="col-8 col-lg-5 item-list-p p-1 mx-auto mt-5 mb-5">
                    <FormControl
                        className="border-0  shadow-none"
                        type="text"
                        placeholder="Buscar"
                        aria-label="Search"
                        aria-describedby="btnGroupAddon"
                    />
                    <InputGroup.Prepend className="border-0">
                        <InputGroup.Text className="border-0 bg-transparent" id="btnGroupAddon"><FaSearch /></InputGroup.Text>
                    </InputGroup.Prepend>
                </InputGroup>
                <div className="row border-bottom mx-auto mb-4 pl-3 w-75 pb-2" style={{ maxWidth: '80%', cursor: 'pointer' }}>
                    <span className="col-6 col-sm-6 col-md-6 col-lg-3 text-center text-dark border-bottom border-secondary" onClick={e => this.visualizarProduto(e = "ativo")}>Em exposição</span>
                    <span className="col-6 col-sm-6 col-md-6 col-lg-2 text-secondary text-center" onClick={e => this.visualizarProduto(e = "desativo")} > Arquivados</span>
                </div>
                <ListGroup className="p-1 w-75 mx-auto mb-5 ">
                    {this.state.itens.map(item => (
                        <ItensLista item={item || ""} />
                    ))}
                </ListGroup>
            </CorpoCemVh>
        )
    }

}
export default CorpoListagemProduto;
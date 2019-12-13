import React, { Fragment, Component } from 'react';
import ItensListaRestaurante from '../componentes/lista/ItensListaRestautantes';
import  {FormControl, FormGroup } from 'react-bootstrap';
import {DOMINIO, TOKEN} from '../link_config';
import $ from 'jquery';

export class PaginaListaRestaurante extends Component {

    constructor() {
        super();
        this.state = {
            itens: [],
            foto: '',
            arrecadacao:''


        }

    }
    visualizarRestaurante(e){
        let token = localStorage.getItem('token');

        this.setState({ itens: [] });

        let url;

        switch (e) {
            case "ativo":
                url = `${DOMINIO}/restaurante/ativo`;
               
                break;
            case "desativo":
                url = `${DOMINIO}/restaurante/desativo`;

                break;
            default:
                url = `${DOMINIO}/restaurante`;
        }

        $.ajax({
            url: url,
            type: 'GET',
            headers: { "token": token },
            // dataType: 'json',
            contentType: 'application/json',
            
            success: function (resposta) {

                this.setState({ itens: resposta, foto: resposta.foto });
            }.bind(this),
            error: function (data) {

                console.log(data)
            }
        });
    }

    componentDidMount() {
       
        this.visualizarRestaurante();
    }

       

    render() {
        return (
            <Fragment>
                <div className=" container">
                    <h1 className="mb-3 text-center mb-5 mt-5">Restaurantes Cadastrados</h1>

                    <div className="row mt-5" style={{cursor: 'pointer' }}>
                        <span className="col-1 " onClick={e => this.visualizarRestaurante(e = "ativo")}>Ativados</span>
                        <span className="col-1 ml-5" onClick={e => this.visualizarRestaurante(e = "desativo")}> Destivados</span>
                    </div>
                    <hr/>
                    {this.state.itens.map(item => (
                        <div className="card mb-5 mt-5">
                            <ItensListaRestaurante foto={item.foto} item={item || ""} ar={item.id}/>
                        </div>
                    ))}
                </div>
            </Fragment>
        
        )
    }

}
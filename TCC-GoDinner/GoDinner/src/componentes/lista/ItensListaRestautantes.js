import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {DOMINIO_IMG, DOMINIO, TOKEN} from '../../link_config';
import $ from 'jquery';



class ItensListaRestaurante extends Component{
 
    constructor(props){
        super();

        this.state = {
            item: props.item
            
        }

    }

    

    render() {
        return (            
            <div className="row">
                <div className="col-11 col-md-3" >
                    <img src={ `${DOMINIO_IMG}${this.state.item.foto}`} alt="" style={{width: 100 + '%' , height: 150 + 'px'}} className="m-3"/>
                </div>
                <div className="col-12 col-md-9">
                    <div className="row ">
                        <div className="col-md-9 mt-3 ml-2 col-8 ">
                            <h2>{this.state.item.razaoSocial}</h2>
                        </div>
                        <div className="col-md-2 mt-3 col-4 ">
                            <img src="img/icone/verde.png" className="rounded" alt=""/>
                        </div>
                    </div>
                    <div className="row  mt-1 mt-md-5 mb-3">
                        <div className="col-md-5 ml-2">Email: {this.state.item.email}</div>
                        <div className="col-md-4 ml-2">Telefone: {this.state.item.telefone}</div>
                        <div className="col-md-2  ">
                            <Link to={`/administracao-godinner/lista-restaurante-detalhe/${this.state.item.id}`}>
                                <span className="btn btn-outline-success ">Detalhes</span>
                            </Link>
                        </div>        
                    </div>
                </div>
            </div>
            
        )
    }
}

export default ItensListaRestaurante;
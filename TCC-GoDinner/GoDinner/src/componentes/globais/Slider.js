import React,{Component} from 'react';
import './css/carousel.css'
import Fritas from './../../recursos/icons/iconsSlider/1.png';
import Macarrao from './../../recursos/icons/iconsSlider/2.jpg';
import $ from 'jquery';
import {DOMINIO, DOMINIO_IMG} from '../../link_config';

export class SliderBox extends Component{
    constructor(props) {
        super();
        this.state = {

            categoria: {
               nome: '',
               foto:'',
               descricao:''
            },

            categorias: []

        }
    }

    componentDidMount() {
        const url = `${DOMINIO}/categoria`;

       
        $.ajax({
            url: url,
            type: 'GET',
            success: function (resposta) {
                console.log(this.setState({ categorias: resposta }));

            }.bind(this),
            error: function (resposta) {
                console.log(resposta)
            }
        });
    }


    render(){
          
        return(
            <div className="container mt-5">
                <h4 className="font-tamanho-23">Para todos os tipos de gostos</h4>
                <div id="carouselExampleControls" className="carousel slide" data-ride="carousel">
                
                <div className="box">
                    <div className="dummy">
                        {this.state.categorias.map(item => (
                            <div className="categoria-comida" key={item.nome}>
                                <img className="tamanho-imagem rounded-circle"  alt={item.nome} src={ `${DOMINIO_IMG}${item.foto}`} 
                                />
                                <p className="text-center mt-2">{item.nome}</p>
                            </div>
                                    
                        ))} 
                        
                        
                        
                        </div>
                    </div>

                   
                </div>
                <hr/>
            </div>
        )
    }
}
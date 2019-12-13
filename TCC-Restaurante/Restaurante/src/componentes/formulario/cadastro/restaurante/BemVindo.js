import React, { Component } from 'react';
import PropTypes from "prop-types";
import { withRouter } from 'react-router-dom';

import { Label } from '../../../globais/label/Label';
import { BotaoLink } from '../../../globais/botao/Botao';
import Corpo from '../../../corpo/Corpo';
import ImgPizza from '../../../../recursos/imgs/pizza.jpg';
import { FaHome } from 'react-icons/fa';
import { DOMINIO } from '../../../../link_config';
import $ from 'jquery';
import './style.css';

/*PROPRIEDADES DO CABEÇALHO*/
const propriedadesCabecalho = {
    to: '/cadastro/bem-vindo',
    width: 'w-100',
}

//ARMAZENA OS ESTADOS INICIAIS
const initialState = {

    restaurante: {
        foto: '',
        id: ''
    },

    imgSrc: `${ImgPizza}`,

    texto: 'teste'

}


class FormularioBemVindo extends Component {

    //STATE ESTÁ RECEBENDO OS ESTADOS INICIAIS
    state = { ...initialState }

    
    //PROPRIEDADES DO WITH ROUTER
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };

    componentWillMount() {
        var dados = sessionStorage.getItem('dados');

        const json = JSON.parse(dados)

        this.state.texto = json.razaoSocial;

    }


    enviaFormulario() {

        const restaurante = { ...this.state.restaurante }

        const foto = restaurante.foto;

        var dados = sessionStorage.getItem('dados');

        const json = JSON.parse(dados)

        this.state.restaurante.id = json.id;

        const url = `${DOMINIO}/foto/restaurante`;

        //FAZ O UPLOAD DA FOTO
        var formData = new FormData();
        var files = $("#foto")[0].files[0];
        formData.append('foto', files);
        formData.append('id', this.state.restaurante.id);

        if (foto !== '') {
            $.ajax({
                url: url,
                type: 'post',
                data: formData,
                contentType: false,
                processData: false,
                success: function (resposta) {

                    //Limpa os storages
                    localStorage.clear();
                    sessionStorage.clear();

                    this.props.history.push("/");

                }.bind(this),
                error: function (data) {
                    console.log('Erro:', data);

                }
            });
        } else {

        }

    }

    //ATUALIZA AS INPUTS COM OS ESTADOS 
    atualizaCampo(e) {
        const restaurante = { ...this.state.restaurante }
        restaurante[e.target.name] = e.target.value
        this.setState({ restaurante })

        //PREVIEW FOTO
        var file = this.refs.file.files[0];
        var reader = new FileReader();
        var url = reader.readAsDataURL(file);

        reader.onloadend = function (e) {
            this.setState({
                imgSrc: [reader.result],
            })
        }.bind(this);

    }



    /* FORMULÁRIO DO ENDEREÇO */
    renderForm() {
        return (
            <form className="form-group mt-4" method="POST" enctype="multipart/form-data" action="#">
                <Label className="h1 mb-3" texto="Bem-vindo à GoDinner" />
                <div className="row mb-2  justify-content-center">
                    <img className=" img-login rounded-circle img-responsive" id="foto-restaurante" alt="Imagem Empresa" src={this.state.imgSrc} />
                </div>
                <div className=" row justify-content-center mb-3">
                    <div className="input-file">
                        <span className="font-weight-bold text-light text-upload">Anexar Imagem</span>
                        <input ref="file" type="file" accept=".jpg, .png, .svg, .jpeg" className="upload" multiple="true" id="foto" name="foto" value={this.state.restaurante.foto} onChange={e => this.atualizaCampo(e)} />
                    </div>
                </div>
                <div className="row mb-5">
                    <Label className="col col-sm col-md col-lg h2 text-center" id="nome-restaurante" name="nome-restaurante" texto={this.state.texto} />
                </div>
                <div className="row mt-3 justify-content-center">
                    <BotaoLink to="/" className="col-5 col-sm-4 col-md-5 col-lg-5 btn-orange ml-3 mr-3 " onClick={e => this.enviaFormulario(e)} texto="Finalizar" />
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

export default withRouter(FormularioBemVindo);
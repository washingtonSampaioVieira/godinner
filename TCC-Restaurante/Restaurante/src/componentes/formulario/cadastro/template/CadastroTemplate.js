import React, { Component, Fragment } from 'react';
import { Link } from 'react-router-dom';
import { DOMINIO, TOKEN, DOMINIO_IMG, TEMPLATE_IMG_1, TEMPLATE_IMG_2, TEMPLATE_IMG_3 } from '../../../../link_config';
import { ToastContainer } from 'react-toastify';
import { Notificacao, ERRO, CAMPO_VAZIO, } from '../../../../funcoes/Alerta/index';
import $ from 'jquery';

export class CadastroTemplate extends Component {

    constructor(props) {
        super();
        this.state = {

            template: {
                foto: `${TEMPLATE_IMG_1}`,
                sobre: '',
                slogan: '',

                restaurante: {
                    id: localStorage.getItem("id")
                }

            }
        }
    }

    atualizaCampo(e) {
        const template = { ...this.state.template };
        template[e.target.name] = e.target.value

        this.setState({
            template

        })

    }


    validaCampos(e) {
        e.preventDefault();

        if (!$('#descricao-principal').val() || !$('#sobre-nos').val()) {
            Notificacao(ERRO, CAMPO_VAZIO);

        } else {
            this.cadastroTemplate(e);

        }

    }

    cadastroTemplate(e) {

        const template = { ...this.state.template };
        const url = `${DOMINIO}/templates`;

        $.ajax({
            url: url,
            type: 'POST',
            headers: { 'token': TOKEN },
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify(template),

            success: function (resposta) {

                console.log(resposta);
                this.props.history.push("/restaurante");

            }.bind(this),
            error: function (data) {
                console.log(data);

            }
        });
    }




    render() {
        return (
            <Fragment>
                <form>
                    <div class=" container">
                        <h1 class="mb-3 text-center mb-5">Sua página</h1>
                        <h5>Conte sobre sua empresa</h5>
                        <hr />
                        <div class="row mt-4">
                            <div class="col-md-6 col-12">
                                <label class="h6">Descrição Principal</label><br />
                                <textarea class="form-control" style={{ height: 150 + 'px' }} id="descricao-principal" value={this.state.template.slogan} name="slogan" onChange={e => this.atualizaCampo(e)}></textarea>
                            </div>
                            <div class="col-md-6 col-12">
                                <label class="h6">Sobre nós</label><br />
                                <textarea class="form-control" style={{ height: 150 + 'px' }} id="sobre-nos" name="sobre" value={this.state.template.sobre} onChange={e => this.atualizaCampo(e)}></textarea>
                            </div>
                        </div>

                        <h5 class="mt-5">Plano de fundo</h5>
                        <hr />
                        <div class="row mt-4 mb-5">
                            <div class="col-12 col-md-4">
                                <div class="card mb-3">
                                    <img class="card-img-top" style={{ height: 220 + 'px' }} src={`${DOMINIO_IMG + TEMPLATE_IMG_1}`} alt="imagem Template 01" />
                                </div>
                                <input class="mr-3" type="radio" name="foto" defaultChecked style={{ boxShadow: 'none' }}
                                    checked={this.state.foto}
                                    value={`${TEMPLATE_IMG_1}`}
                                    onChange={e => this.atualizaCampo(e)} />Imagem 01
                            </div>
                            <div class="col-12 col-md-4">
                                <div class="card mb-3">
                                    <img class="card-img-top" style={{ height: 220 + 'px' }} src={`${DOMINIO_IMG + TEMPLATE_IMG_2}`} alt="imagem Template 02" />
                                </div>
                                <input class="mr-3" type="radio" name="foto" style={{ boxShadow: 'none' }}
                                    checked={this.state.foto}
                                    value={`${TEMPLATE_IMG_2}`}
                                    onChange={e => this.atualizaCampo(e)} />Imagem 02
                            </div>
                            <div class="col-12 col-md-4">
                                <div class="card mb-3">
                                    <img class="card-img-top" style={{ height: 220 + 'px' }} src={`${DOMINIO_IMG + TEMPLATE_IMG_3}`} alt="imagem Template 03" />
                                </div>

                                <input class="mr-3" type="radio" style={{ boxShadow: 'none' }} name="foto"
                                    checked={this.state.foto}
                                    value={`${TEMPLATE_IMG_3}`}
                                    onChange={e => this.atualizaCampo(e)} />Imagem 03
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-2 ml-auto mb-5">
                                <div className="col col-sm col-md   col-lg">
                                    <Link class="btn btn-success" onClick={e => this.validaCampos(e)}>Finalizar</Link>
                                </div>
                            </div>
                        </div>

                        <ToastContainer />
                    </div>
                </form>
            </Fragment>
        )
    }
}
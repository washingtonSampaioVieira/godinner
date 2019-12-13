import React, { Component } from 'react';
import $ from 'jquery';
import { DOMINIO, DOMINIO_IMG } from '../../../../link_config';
import '../../../../recursos/js/AddImagem';
import ImgProduto from '../../../../recursos/imgs/imagem-produto.png';
import './style.css';

const initialState = {

    Imagem: {
        foto: '',
        index: '',
        legenda: '',
        idproduto: '',
    },

    imagens: [],


    imgSrc: `${ImgProduto}`,

    img1: `${ImgProduto}`

}

export class CadastroImagem extends Component {

    state = { ...initialState }

    componentDidUpdate() {
        const idProduto = this.props.idProduto;

        if (idProduto !== null) {
            $("#cadastro-imagem").removeClass("disabilita-elemento");
        }
    }

    componentWillMount() {
        this.visualizarImgSalva();
    }

    apagaFoto(id) {

        const url = `${DOMINIO}/fotoproduto/${id}`;
        const token = localStorage.getItem("token");

        $.ajax({
            url: url,
            type: 'DELETE',
            headers: { 'token': token },
            success: function (resposta) {

                this.visualizarImgSalva();

            }.bind(this),
            error: function (data) {



            }
        });


    }


    visualizarImgSalva() {
        const idProduto = this.props.idProduto;

        const url = `${DOMINIO}/fotoproduto/${idProduto}`;

        const token = localStorage.getItem("token");

        $.ajax({
            url: url,
            type: 'GET',
            headers: { 'token': token },
            success: function (resposta) {



                if (resposta.length >= 3) {
                    $("#addInput").prop('disabled', true);
                } else {
                    $("#addInput").prop('disabled', false);
                }



                this.setState({ imagens: resposta })

            }.bind(this),
            error: function (data) {
                console.log('Erro:', data);

            }
        });

    }



    atualizaCampo(e) {
        const Imagem = { ...this.state.Imagem }
        Imagem[e.target.name] = e.target.value
        this.setState({ Imagem })

        //PREVIEW FOTO
        var file = this.refs.file.files[0];
        var reader = new FileReader();


        //Verifica se a imagem foi alterada (Tratamento de erro)
        if (file !== '' && file !== null && file !== undefined) {
            reader.onloadend = function (e) {

                this.setState({
                    imgSrc: [reader.result],
                })
            }.bind(this);

            reader.readAsDataURL(file)
        }


    }


    enviaImagem(e) {

        const idProduto = this.props.idProduto;

        const url = `${DOMINIO}/foto/produto`;

        let token = localStorage.getItem('token')

        //FAZ O UPLOAD DA FOTO
        var formData = new FormData();
        var files = $("#foto")[0].files[0];
        formData.append('foto', files);
        formData.append('legenda', this.state.Imagem.legenda);
        formData.append('index', 1);
        formData.append('id', idProduto);


        if (token !== '') {
            $.ajax({
                url: url,
                type: 'post',
                data: formData,
                headers: { 'token': token },
                contentType: false,
                processData: false,
                success: function (resposta) {

                    this.setState({ ...initialState })



                    this.visualizarImgSalva()

                }.bind(this),
                error: function (data) {
                    console.log('Erro:', data);

                }
            });
        }

    }


    render() {
        return (
            <div className={` ${this.props.className}`} id="cadastro-imagem">

                <h4 >2º Passo</h4>
                <hr />

                <div className="row mx-auto w-100">
                    <div className="card card-maior mx-auto">
                        <img src={this.state.imgSrc} className="card-img-top tamanho-imagem-produto border-bottom" style={{ maxWidth: 230 + 'px' }} alt="..." />
                        <div className="card-body pb-0">
                            <div className="input-group input-group-sm mx-auto mb-2">
                                <input type="text" className="form-control" aria-label="" id="legenda" name="legenda" placeholder="Escreva uma legenda.." value={this.state.Imagem.legenda} onChange={e => this.atualizaCampo(e)} />
                            </div>

                            <div className="input-file-produto btn-success rounded mx-auto">
                                <span className="anexar-imagem text-light font-weight-bold">Anexar Imagem</span>
                                <input ref="file" accept=".jpg, .png, .svg, .jpeg" type="file" className="upload-image" multiple="true" id="foto" name="foto" value={this.state.Imagem.foto} onChange={e => this.atualizaCampo(e)} />
                            </div>
                            <div className="row">
                                <div className="col-md-12 col-12" >
                                    <input type="button" className="btn btn-outline-success btn-sm col-12 mb-2 mx-auto" id="addInput" onClick={e => this.enviaImagem(e)} value="Salvar"
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="row w-75 mx-auto mt-4">
                    {this.state.imagens.map(item => (
                        <div className="col-10 col-sm-9 col-md-8  col-lg-4 mb-3 mx-auto" id="campo">
                            <div className="card card-menor ">
                                <span className="col-1 align-self-end cor-cinza" onClick={e => this.apagaFoto(item.id)}>x</span>
                                <img key={item.id} name="img1" className="card-img-top tamanho-imagem-produto border-top" alt="..." title={item.legenda} src={DOMINIO_IMG + item.foto} />
                            </div>

                        </div>
                    ))}
                </div>
            </div>
        )
    }
}
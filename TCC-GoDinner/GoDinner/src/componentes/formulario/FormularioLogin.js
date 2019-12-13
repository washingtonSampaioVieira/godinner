import React,{Component, Fragment} from 'react';
import Logo from '../../recursos/icons/logo.png';
import { withRouter } from 'react-router-dom';
import {DOMINIO} from '../../link_config';
import $ from 'jquery';
import PropTypes from "prop-types";
import { ToastContainer} from 'react-toastify';
import { ERRO, Notificacao,CAMPO_VAZIO, INFORMACAO_INVALIDA} from '../../Alert/Alert';

const initialState ={
    funcionario: {
        email: '',
        password: ''
    }
}

export const TOKEN_KEY = "token";


class FormularioLogin extends Component{

    state = { ...initialState }

    //PROPRIEDADES DO WITH ROUTER
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };


    validaCampos(e) {
        e.preventDefault();

        const bordasCampoVazio = 'border border-danger';

        //VERIFICA SE OS CAMPOS ESTÃO PRENCHIDOS
        if (!$('#email').val()) {
            $('#email').addClass(bordasCampoVazio);
            Notificacao(ERRO, CAMPO_VAZIO);
        }

        if (!$('#password').val()) {
            $('#password').addClass(bordasCampoVazio);
            Notificacao(ERRO, CAMPO_VAZIO);
        }else{
            this.enviaFormulario(e);
        } 

    }


    enviaFormulario() {
        
        // const funcionario = { ...this.state.funcionario }

        const url = `${DOMINIO}/login/funcionarios`;

        const email = this.state.funcionario.email;

        const password = this.state.funcionario.password;

        $.ajax({
            url: url,
            type: 'post',
            data: JSON.stringify({ "email": email, "password": password }),
            dataType: 'json',
            contentType: "application/json",

            success: function (resposta) {
                
                if(!resposta === false){

                    localStorage.setItem(TOKEN_KEY, resposta.token);
                    this.props.history.push("/administracao-godinner");
                }else{
                    Notificacao(ERRO, INFORMACAO_INVALIDA)
                }

            }.bind(this),

            error: function (data) {
                console.log(data);

            }
        });
    }
    
    
    atualizaCampo(e) {
        const funcionario = { ...this.state.funcionario }
        funcionario[e.target.name] = e.target.value
        this.setState({
           funcionario
        })
    }
    

    render(){
        return(
            <Fragment>
                <form>
                    <div className="row mt-5">
                        <div className="col-1  col-md-2 col-lg-3"></div>
                        {/* <!-- Formulário para Login --> */}
                    <div className="col-10  col-md-8 col-lg-6">
                        {/* <!--Div de logo e boas vindas--> */}
                        <div className="row">
                            <img src={Logo} className=" col-12 icone-imagem" alt="Logo GoDinner"/>
                        </div>
                        <div className="row" id="testee">
                            <h3 className="mx-auto">Sistema GoDinner</h3>
                        </div>
                        {/* <!--Div de Cadastro--> */}
                        <div className="row ">
                            <div className="col-12 mt-5 ">
                                <label className="h5">Email</label><br/>
                                <input className="form-control  mb-2 mr-sm-2" type="email" id="email" name="email" value={this.state.funcionario.email}  placeholder="Digite o seu Email ..." onChange={e => this.atualizaCampo(e)}/>
                            </div>
                        </div>
                        <div className="row ">
                            <div className="col-12 ">
                                <label className="h5">Senha</label><br/>
                                <input className="form-control   mr-sm-12" type="password" id="password" name="password"  value={this.state.funcionario.password} placeholder="Digite a sua senha .."  onChange={e => this.atualizaCampo(e)}/>
                            </div>
                        </div>
                        {/* <!--botões para cadastrar e entrar--> */}
                        <div className="row mt-5 mb-5 ml-1 mr-1">
                            <span onClick={e => this.validaCampos(e)} className="btn btn-orange"  style={{ minWidth: 100 + '%', cursor: 'pointer' }}>Entrar</span>
                        </div>  
                        {/* <div className="row mt-3 mb-5 ml-1 mr-1">
                            <a href="#" className="btn btn-orange " style={{ minWidth: 100 + '%', cursor: 'pointer' }}>Cadastrar</a>
                        </div> */}
                        <hr/>
                    </div>
                    <div className="col-1 col-md-2 col-lg-3"></div>
                    </div>
                </form>
            <ToastContainer/>
            </Fragment>
           
           
        )

    }
}  export default withRouter(FormularioLogin);
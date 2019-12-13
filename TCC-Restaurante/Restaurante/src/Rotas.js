import React, { Component, Fragment } from 'react';
import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';

import FormularioDados from './componentes/formulario/cadastro/restaurante/DadosCadastro';
import FormularioEndereco from './componentes/formulario/cadastro/restaurante/EnderecoCadastro';
import FormularioLogin from './componentes/formulario/cadastro/restaurante/Login';
import FormularioBemVindo from './componentes/formulario/cadastro/restaurante/BemVindo'

import { PaginaLogin } from './paginas/login/PaginaLogin';
import { Rodape } from './componentes/rodape/cadastro/rodape';
import CorpoListagemProdutos from "./componentes/corpo/listagemProdutos/CorpoListagemProduto";
import CadastroProduto from "./componentes/formulario/cadastro/produto/CadastroProduto";
import CabecalhoPaginaRestaurante from './componentes/cabecalho/restaurante/Cabecalho';
import CorpoIndex from './componentes/corpo/index/Corpo';
import TemplateRestaurante from './componentes/corpo/template/TemplateRestaurante';
import { CadastroTemplate } from './componentes/formulario/cadastro/template/CadastroTemplate';
import { SeusPedidos } from './componentes/corpo/pedidos';

import { Pagamento } from './componentes/corpo/pagamento'
import ErrorNotFound from './componentes/error';

export const estaAutenticado = () => localStorage.getItem("token") != null || sessionStorage.getItem("dados") != null;

const PrivateRoute = ({ component: Component, ...rest }) => (
    <Route
        {...rest}
        render={props =>
            estaAutenticado() ? (
                <Component {...props} />
            ) : (
                    <Redirect to={{ pathname: "/", state: { from: props.location } }} />
                )
        }
    />
);

export class RotaPaginas extends Component {

    render() {
        return (

            <BrowserRouter>
                <Switch>

                    <Route path="/" exact component={PaginaLogin} />

                    <Route
                        path="/cadastro" render={({ match: { url } }) => (
                            <Fragment>
                                <Route path={`${url}/`} exact component={FormularioDados} />
                                <PrivateRoute path={`${url}/endereco`} component={FormularioEndereco} exact />
                                <PrivateRoute path={`${url}/login`} component={FormularioLogin} />
                                <PrivateRoute path={`${url}/bem-vindo`} component={FormularioBemVindo} />
                                <Rodape />
                            </Fragment>

                        )}
                    />

                    <Route path="/restaurante" render={({ match: { url } }) => (
                        <Fragment>
                            <CabecalhoPaginaRestaurante />
                            <PrivateRoute path={`${url}/`} component={CorpoIndex} exact />
                            <PrivateRoute path={`${url}/pedidos`} component={SeusPedidos} exact />
                            <PrivateRoute path={`${url}/cadastro-produto/:id?`} component={CadastroProduto} />
                            <PrivateRoute path={`${url}/visualizar-produto`} component={CorpoListagemProdutos} />
                            <PrivateRoute path={`${url}/cadastro-template`} component={CadastroTemplate} />
                            <PrivateRoute path={`${url}/pagamento`} component={Pagamento} />
                            <Rodape />
                        </Fragment>

                    )}
                    />

                    <Route path="/corpo" render={({ match: { url } }) => (

                        <Route path={`${url}/TemplateRestaurante`} component={TemplateRestaurante} />

                    )} />

                    <Route path='*' component={ErrorNotFound} />

                </Switch>
            </ BrowserRouter>

        )
    }
}
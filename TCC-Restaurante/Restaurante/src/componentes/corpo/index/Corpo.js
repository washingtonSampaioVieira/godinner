import React, { Component } from 'react';
import {
    DivOpecoes, DivOpecoesTitulo, CabecalhoGraficos,
    CorpoGraficos, IconeOpcoes
} from './styled';
import { FiTrendingUp } from "react-icons/fi";
import Talher from '../../../recursos/icons/talher.png';
import Pedido from '../../../recursos/icons/pedido.png';
import Chat from '../../../recursos/icons/chat.png';
import $ from 'jquery';
import { Link } from 'react-router-dom';
import { CorpoCemVh } from '../styled';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { getRestaurante } from '../../cabecalho/restaurante/actions';
import Chart from "react-apexcharts";

//COMPONENTE DO CORPO DA PÁGINA DE LOGIN
class CorpoIndex extends Component {

    constructor(props) {
        super(props);

        this.state = {
            options: {
                chart: {
                    id: "basic-bar"
                },
                xaxis: {
                    categories: ["Janeiro", "Fevereiro",
                                 "Março", "Abril", 
                                "Maio", "Junho", 
                                "Julho", "Agosto",
                                "Setembro", "Outubro",
                                "Novembro", "Dezembro"]
                }
            },
            series: [
                {
                    name: "series-1",
                    data: [0, 0, 0, 10, 25, 30, 48, 0]
                }
            ]
        };
    }

    componentWillMount() {
        this.props.getRestaurante();

    };

    componentWillUpdate() {
        $("#pedidos-div").click(function () {
            $("#pedidos").addClass("border-bottom-laranja");
        });
        $("#cadastrar-div").click(function () {
            $("#cadastrar").addClass("border-bottom-laranja");
        });
    }

    render() {
        const { razaoSocial } = this.props.restaurante;
        return (
            <CorpoCemVh className="mx-auto">
                <div className="row text-center mt-3">
                    <h1 className="mx-auto nome-restaurante">{razaoSocial}</h1>
                </div>
                <div className="row text-center mt-2 border-bottom">
                    <h3 className="mx-auto">Painel Administrativo {razaoSocial} </h3>

                </div>
                <div className="row mt-5 justify-content-center">
                    <Link to="/restaurante/pedidos" id="pedidos-div" className="col col-sm col-md col-lg-4 h4 nav-link text-dark">
                        < DivOpecoes theme={{ cor: 'marrom' }}>
                            <IconeOpcoes src={Pedido} />
                        </DivOpecoes>
                        <DivOpecoesTitulo className="pt-1" theme={{ cor: 'marrom' }}>
                            Pedido
                </DivOpecoesTitulo>
                    </Link>
                    <Link to="/restaurante/cadastro-produto" id="cadastrar-div" className="col col-sm col-md col-lg-4 h4 nav-link text-dark">
                        < DivOpecoes theme={{ cor: 'laranja' }}>
                            <IconeOpcoes src={Talher} />
                        </ DivOpecoes>
                        <DivOpecoesTitulo className="pt-1" theme={{ cor: 'laranja' }}>
                            Cadastrar
                </DivOpecoesTitulo>
                    </Link>
                </div>
                <div className="row mt-5">
                    <div className="col col-sm col-md col-lg h4">
                        <CabecalhoGraficos className="border border-secondary pt-1 mx-auto">
                            <FiTrendingUp className="ml-3 mr-4" />
                            Gráfico de vendas do mês
                        </CabecalhoGraficos>
                        <CorpoGraficos className="border border-secondary mx-auto">
                            <div className="app">
                                <div className="row">
                                    <div className="mixed-chart mx-auto mt-3">
                                        <Chart
                                            options={this.state.options}
                                            series={this.state.series}
                                            type="bar"
                                            width="600"
                                        />
                                    </div>
                                </div>
                            </div>
                        </CorpoGraficos>
                    </div>
                </div>

            </CorpoCemVh>
        )
    }

};

const mapStateToProps = state => ({ restaurante: state.restaurante.restaurante });
const mapDispatchToProps = dispatch => bindActionCreators({ getRestaurante }, dispatch);
export default connect(mapStateToProps, mapDispatchToProps)(CorpoIndex);
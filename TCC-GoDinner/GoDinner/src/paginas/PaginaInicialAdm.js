import React,{Component} from 'react';
import { Card} from 'react-bootstrap';
import { Line } from 'react-chartjs-2';

import {DOMINIO, TOKEN} from '../link_config';
import $ from 'jquery';



const meses = ['JAN','FEV','MAR', 'ABR', 'MAI', 'JUN', 'JUL', 'AGO', 'SET', 'OUT', 'NOV', 'DEZ'];

export class PaginaInicialAdm extends Component{

    constructor(props){
        super(props);
        this.state = {
          chartData:{
              labels: meses,
              datasets:[{
                  label: 'Restaurantes cadastrados',
                  data:[],
                  backgroundColor:[
                      'rgba(242, 107, 58, 0.6)'
                  ]
              }]
          },
          

          chartData2:{
            labels: meses,
            datasets:[{
                label: 'Arrecadação p/ mês em reais',
                data:[],
                backgroundColor:[
                    'rgba(234, 177, 19,0.6 )'
                ]
            }]
            },

            arrecadacao:{
                total: ''
            },

            restaurante:{
                total: ''
            },

            consumidor:{
                total: ''
            },
            
            devedor:{
                total: ''
            }
           

        }
        this.buscarRestaurantesCadastrados();
        this.buscarArrecadacaoDoMes();
      }
      
      static defaultProps = {
        displayTitle:true,
        displayLegend: true,
        legendPosition:'bottom',
        location:'City'
      }
    buscarRestaurantesCadastrados(){
        let url = DOMINIO + "/funcionarios/arrecadacaomes"
        let token = localStorage.getItem("token")
        $.ajax({
            url: url,
            type: 'get',
            dataType: 'json',
            headers: {"token":token},
            contentType: "application/json",
            success: (resposta)=> {
                let chartDataState = this.state.chartData2;
                
                chartDataState.datasets[0].data[0] = (resposta.janeiro == null?0:resposta.janeiro)
                chartDataState.datasets[0].data[1] = (resposta.fevereiro == null?0:resposta.fevereiro)
                chartDataState.datasets[0].data[2] = (resposta.marco == null?0:resposta.marco)

                chartDataState.datasets[0].data[3] = (resposta.abril == null?0:resposta.abril)
                
                chartDataState.datasets[0].data[4] = (resposta.maio == null?0:resposta.maio)
                chartDataState.datasets[0].data[5] = (resposta.junho == null?0:resposta.junho)
                chartDataState.datasets[0].data[6] = (resposta.julho == null?0:resposta.julho)
                chartDataState.datasets[0].data[7] = (resposta.agosto == null?0:resposta.agosto)
                chartDataState.datasets[0].data[8] = (resposta.setembro == null?0:resposta.setembro)
                chartDataState.datasets[0].data[9] = (resposta.outubro == null?0:resposta.outubro)
                chartDataState.datasets[0].data[10] = (resposta.novembro == null?0:resposta.novembro)
                chartDataState.datasets[0].data[11] = (resposta.dezembro == null?0:resposta.dezembro)
                
                // chartDataState.datasets[0].data 
                this.setState({chartData2: chartDataState});
            },

            error: function (data) {
                console.log(data);

            }
        });
    }
    buscarArrecadacaoDoMes(){
        let url = DOMINIO + "/funcionarios/restaurantescadastrado"
        let token = localStorage.getItem("token")
        $.ajax({
            url: url,
            type: 'get',
            dataType: 'json',
            headers: {"token":token},
            contentType: "application/json",
            success: (resposta)=> {
                let chartDataState = this.state.chartData;
                
                chartDataState.datasets[0].data[0] = (resposta.janeiro == null?0:resposta.janeiro)
                chartDataState.datasets[0].data[1] = (resposta.fevereiro == null?0:resposta.fevereiro)
                chartDataState.datasets[0].data[2] = (resposta.marco == null?0:resposta.marco)

                chartDataState.datasets[0].data[3] = (resposta.abril == null?0:resposta.abril)
                
                chartDataState.datasets[0].data[4] = (resposta.maio == null?0:resposta.maio)
                chartDataState.datasets[0].data[5] = (resposta.junho == null?0:resposta.junho)
                chartDataState.datasets[0].data[6] = (resposta.julho == null?0:resposta.julho)
                chartDataState.datasets[0].data[7] = (resposta.agosto == null?0:resposta.agosto)
                chartDataState.datasets[0].data[8] = (resposta.setembro == null?0:resposta.setembro)
                chartDataState.datasets[0].data[9] = (resposta.outubro == null?0:resposta.outubro)
                chartDataState.datasets[0].data[10] = (resposta.novembro == null?0:resposta.novembro)
                chartDataState.datasets[0].data[11] = (resposta.dezembro == null?0:resposta.dezembro)
                
                // chartDataState.datasets[0].data 
                this.setState({chartData: chartDataState});
            },

            error: function (data) {
                console.log(data);


            }
        });
    }





    componentDidMount() {
        this.carregarArregadacao();
        this.carregarRestauranteCadastrado ();
        this.carregarConsumidorCadastrado();
        this.carregarDevedores();
    }


    carregarArregadacao (){
        let token = localStorage.getItem("token")
        const url = `${DOMINIO}/pedidos/totalcomissao`;

        $.ajax({
            url: url,
            type: 'GET',
            headers: { 'token': token },
            success: function (resposta) {

                this.setState({arrecadacao:resposta});


            }.bind(this),
            error: function (data) {

            }
        });
    }


    carregarDevedores (){
        let token = localStorage.getItem("token")
        const url = `${DOMINIO}/restaurante/qtdedebito`;

        $.ajax({
            url: url,
            type: 'GET',
            headers: { 'token': token },
            success: function (resposta) {

                this.setState({devedor:resposta});


            }.bind(this),
            error: function (data) {

            }
        });
    }

    carregarRestauranteCadastrado (){
        let token = localStorage.getItem("token")
        const url = `${DOMINIO}/restaurante/cadastrados`;

        $.ajax({
            url: url,
            type: 'GET',
            headers: { 'token': token },
            success: function (resposta) {

                this.setState({restaurante:resposta});


            }.bind(this),
            error: function (data) {

            }
        });
    }

    carregarConsumidorCadastrado (){
        let token = localStorage.getItem("token");
        const url = `${DOMINIO}/consumidor/cadastrados`;

        $.ajax({
            url: url,
            type: 'GET',
            headers: { 'token': token },
            success: function (resposta) {

                this.setState({consumidor:resposta});


            }.bind(this),
            error: function (data) {

            }
        });
    }



    

    render(){
        return(
            <div className="container">
                <h1 className="text-center mt-5 mb-5">Bem-vindo ao sistema GoDinner</h1>
                <hr/>
                <div className="row">
                    <div className="col-12 ">
                        <div className="row">
                            <div className="col-md-3 col-6 mb-2">
                                <Card className="text-center h6">
                                    <Card.Header className="bg-primary text-white text-center ">Arrecadação da Godinner</Card.Header>
                                    <Card.Body>
                                        <Card.Text>{this.state.arrecadacao.total}</Card.Text>
                                    </Card.Body>
                                </Card>
                            </div>
                            <div className="col-md-3 mb-2 col-6 h6">
                                <Card className="text-center">
                                    <Card.Header  className="bg-success text-white">Restaurantes devedores</Card.Header>
                                    <Card.Body>
                                        <Card.Text>{this.state.devedor.total}</Card.Text>
                                    </Card.Body>
                                </Card>
                            </div>
                            <div className="col-md-3 mb-2 col-6 h6">
                                <Card className="text-center">
                                    <Card.Header className="bg-info text-white">Restaurantes Cadastrados</Card.Header>
                                    <Card.Body>
                                        <Card.Text>{this.state.restaurante.total}</Card.Text>
                                    </Card.Body>
                                </Card>
                            </div>
                            <div className="col-md-3  mb-2 col-6 h6">
                                <Card className="text-center">
                                    <Card.Header className="bg-danger text-white">Consumidores cadastrados</Card.Header>
                                    <Card.Body>
                                        <Card.Text>{this.state.consumidor.total}</Card.Text>
                                    </Card.Body>
                                </Card>
                            </div>
                        </div>
                    </div>
                </div>
               <div className="row mt-3">
                    <div className="col-md-6 col-12  pb-2">
                            <Card  style={{ width: '100%', height: '380px' }}>
                                <Card.Body>
                                   <div >
                                   <Line
                                   id="grafico1"
                                    width={100}
                                    height={300}
                                    data={this.state.chartData}
                                    options={{
                                        title:{
                                        display:this.props.displayTitle,
                                        text:'Restaurantes Cadastrados (mês)',
                                        fontSize:23
                                        },
                                        maintainAspectRatio: false,
                                        legend:{
                                        display:this.props.displayLegend,
                                        position:this.props.legendPosition
                                        }
                                    }}
                                    />
                                    </div> 
                                    
                                   
                            </Card.Body>
                        </Card>
                    </div>
                    <div className="col-12 col-md-6  pb-2">
                        <Card   style={{ width: '100%', height: '380px' }}>
                            <Card.Body>
                            <Line   id="grafico2"
                                    width={100}
                                    height={300}
                                    data={this.state.chartData2}
                                    options={{
                                        title:{
                                        display:this.props.displayTitle,
                                        text:'Arrecadação da Godinner (mês)',
                                        fontSize:23
                                        },
                                        maintainAspectRatio: false,
                                        legend:{
                                        display:this.props.displayLegend,
                                        position:this.props.legendPosition
                                        }
                                    }}
                                    />
                            </Card.Body>
                        </Card>
                    </div> 
               </div>
                
               

            </div>
        )
    }
}
import React, { Component } from 'react';

import { DOMINIO, TOKEN } from '../../../link_config';

import InputMask from 'react-input-mask';
import $ from 'jquery';

import { ERRO, Notificacao, CAMPO_VAZIO } from '../../../funcoes/Alerta';
import { CorpoCemVh } from '../styled';
import './style.css';

export class Pagamento extends Component {


    constructor(props) {
        super(props);
        this.state = {
            Checkout_List_State: false,
            Products_Data: '00,00',
            Payment_Details: false,
            Payment_Success: false
        };


        this.Confirm_Pay = this.Confirm_Pay.bind(this);
        this.Checkout = this.Checkout.bind(this);
        this.AutoTab = this.AutoTab.bind(this);
    }

    componentWillMount() {

        let url = `${DOMINIO}/restaurante/debito/166`;

        $.ajax({

            url: url,
            type: 'get',
            headers: { 'token': TOKEN },
            success: function (resposta) {

                let total = resposta.total.toString();

                let resultado = total.replace(".", ",");

                this.setState({ Products_Data: resultado })

            }.bind(this),
            error: function () {

            }
        });
    }

    pagamento() {


        let id = localStorage.getItem("id");

        let url = `${DOMINIO}/pedidos/debito/${id}`;


        $.ajax({

            url: url,
            type: 'PUT',
            headers: { 'token': TOKEN },
            success: function (resposta) {

            }.bind(this),
            error: function () {

            }
        });
    }

    Confirm_Pay() {
        if (this.state.Payment_Details === false) {
            this.setState({
                Payment_Details: true
            })
        } else {
            if (this.CN_Part1.value.length === 4
                && this.CN_Part2.value.length === 4 &&
                this.CN_Part3.value.length === 4 &&
                this.CN_Part4.value.length === 4 &&
                this.CD_Name.value &&
                this.CD_CVV.value.length === 3) {
                this.pagamento();
                this.setState({
                    Payment_Success: true
                });

            } else {
                Notificacao(ERRO, CAMPO_VAZIO)
            }
        }
    }

    Checkout() {
        this.setState({
            Payment_Details: false
        })
    }

    AutoTab(event) {
        if (event.target.value.length === 4) {
            switch (event.target.name) {
                case "CN_Part1":
                    this.CN_Part2.focus();
                    break;
                case "CN_Part2":
                    this.CN_Part3.focus();
                    break;
                case "CN_Part3":
                    this.CN_Part4.focus();
                    break;
                default:
                    break;
            }
        }
    }

    render() {

        // Calc total purchase
        let Total_Purchase = this.state.Products_Data;

        var Payment_ID = Math.floor(Math.random() * 100000);

        return (
            <CorpoCemVh className="mt-5">
                <div id="pen">
                    {
                        this.state.Payment_Details ?
                            <div className="Credit-Check-Validation" ref={(ref) => this.Credit_Check_Validation = ref}>
                                <p>
                                    <svg class="svg-icon" viewBox="0 0 20 20">
                                        <path d="M18.344,16.174l-7.98-12.856c-0.172-0.288-0.586-0.288-0.758,0L1.627,16.217c0.339-0.543-0.603,0.668,0.384,0.682h15.991C18.893,16.891,18.167,15.961,18.344,16.174 M2.789,16.008l7.196-11.6l7.224,11.6H2.789z M10.455,7.552v3.561c0,0.244-0.199,0.445-0.443,0.445s-0.443-0.201-0.443-0.445V7.552c0-0.245,0.199-0.445,0.443-0.445S10.455,7.307,10.455,7.552M10.012,12.439c-0.733,0-1.33,0.6-1.33,1.336s0.597,1.336,1.33,1.336c0.734,0,1.33-0.6,1.33-1.336S10.746,12.439,10.012,12.439M10.012,14.221c-0.244,0-0.443-0.199-0.443-0.445c0-0.244,0.199-0.445,0.443-0.445s0.443,0.201,0.443,0.445C10.455,14.021,10.256,14.221,10.012,14.221" />
                                    </svg>
                                    Payment validation failed, Check your informations and Try again.
                                </p>
                            </div>
                            :
                            null
                    }
                    <div className={this.state.Payment_Details ? "Card Remove_Box" : "Card"} id={this.state.Payment_Success ? "success" : null}>
                        <div className={this.state.Payment_Details ? "flex-item Card-Checkout__List Remove_Box" : "flex-item Card-Checkout__List"}>
                        </div>
                        <div className={this.state.Payment_Details ? "flex-item Card-Inner Remove_Box" : "flex-item Card-Inner"}>
                            <div className="Card-Wave" />
                            <div className="Card-Logo">
                                <svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="659.055" height="202.068" viewBox="0 0 659.055 202.068" overflow="visible">
                                    <polygon fill="#ffffff" points="232.178 199.164 265.539 3.402 318.898 3.402 285.514 199.164 " />
                                    <path fill="#ffffff" d="M479.04 8.222C468.468 4.256 451.904 0 431.217 0c-52.724 0-89.864 26.551-90.18 64.604 -0.297 28.129 26.514 43.821 46.754 53.185 20.769 9.595 27.752 15.715 27.653 24.283 -0.132 13.122-16.586 19.117-31.923 19.117 -21.357 0-32.703-2.967-50.226-10.276l-6.876-3.112 -7.489 43.824c12.463 5.464 35.509 10.199 59.438 10.444 56.089 0 92.501-26.247 92.915-66.882 0.201-22.269-14.015-39.217-44.799-53.188 -18.651-9.056-30.073-15.099-29.952-24.269 0-8.137 9.668-16.838 30.557-16.838 17.449-0.271 30.088 3.534 39.936 7.5l4.782 2.259L479.04 8.222" />
                                    <path fill="#ffffff" d="M615.867 3.589h-41.231c-12.773 0-22.331 3.486-27.941 16.234l-79.245 179.403h56.031c0 0 9.162-24.122 11.234-29.418 6.123 0 60.554 0.084 68.336 0.084 1.596 6.853 6.492 29.334 6.492 29.334h49.512L615.867 3.589M550.07 129.767c4.413-11.279 21.259-54.723 21.259-54.723 -0.315 0.521 4.38-11.334 7.075-18.684l3.607 16.878c0 0 10.217 46.729 12.352 56.528H550.07z" />
                                    <path fill="#ffffff" d="M187.425 3.545l-52.24 133.495 -5.566-27.129c-9.725-31.274-40.025-65.156-73.899-82.119l47.767 171.203 56.455-0.065L243.946 3.545H187.425" />
                                    <path fill="#FAA61A" d="M86.722 3.424H0.681L0 7.497C66.939 23.701 111.232 62.86 129.618 109.911l-18.709-89.96C107.679 7.555 98.311 3.856 86.722 3.424" />
                                </svg>
                            </div>
                            <div className="Card-Title">
                                <h2>{this.state.Payment_Details ? "Dados do pagamento" : "Godinner"}</h2>
                            </div>
                            {
                                this.state.Payment_Details ?
                                    <div className="Credit-Number">
                                        <input ref={(ref) => this.CN_Part1 = ref} name="CN_Part1" type="text" maxLength="4" placeholder="----" className="Parts Parts_1" onChange={this.AutoTab} />
                                        <input ref={(ref) => this.CN_Part2 = ref} name="CN_Part2" type="text" maxLength="4" placeholder="----" className="Parts Parts_2" onChange={this.AutoTab} />
                                        <input ref={(ref) => this.CN_Part3 = ref} name="CN_Part3" type="text" maxLength="4" placeholder="----" className="Parts Parts_3" onChange={this.AutoTab} />
                                        <input ref={(ref) => this.CN_Part4 = ref} name="CN_Part4" type="text" maxLength="4" placeholder="----" className="Parts Parts_4" onChange={this.AutoTab} />
                                    </div>
                                    :
                                    <div className="Card-Total">
                                        <div className="Card-Total_Title">Preço Total</div>
                                        <div className="Card-Total_Number">R$ {Total_Purchase}</div>
                                    </div>
                            }
                            <div className="Card-Btn">
                                <button onClick={this.Confirm_Pay}>{this.state.Payment_Details ? "Pagar" : "Confirmar"}</button>
                            </div>
                            {
                                this.state.Payment_Details ?
                                    <div className="Card-Details-Wrapper">
                                        <div className="Card-Holder">
                                            <h4 className="Details-Title">NOME</h4>
                                            <div className="Card-Holder-input">
                                                <input ref={(ref) => this.CD_Name = ref} type="text" className="Detailes-Input" placeholder="---- ----- ---" />
                                            </div>
                                        </div>
                                        <div className="Card-Expiration">
                                            <h4 className="Details-Title">VALIDO ATÉ</h4>
                                            <div className="Card-Expiration-input">
                                                <InputMask mask="99/99" ref={(ref) => this.CD_Exp = ref} type="text" id="data-ate" className="Detailes-Input" placeholder="MM/AA" />
                                            </div>
                                        </div>
                                        <div className="Card-CVV">
                                            <h4 className="Details-Title">CVV</h4>
                                            <div className="Card-CVV-input">
                                                <input ref={(ref) => this.CD_CVV = ref} type="text" className="Detailes-Input" maxLength="3" placeholder="***" />
                                            </div>
                                        </div>
                                    </div>
                                    :
                                    null
                            }
                            <div className="Payment-Success">
                                <h2>Pagamento realizado com sucesso.</h2>
                                <p>Número da transação <b>{Payment_ID}</b></p>
                            </div>
                        </div>
                    </div>
                </div>
            </CorpoCemVh>
        );
    }

}
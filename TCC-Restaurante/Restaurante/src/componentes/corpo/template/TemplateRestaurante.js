import React, {Component} from 'react';
import { MenuTemplate } from '../../menu/MenuTemplate';
import { Carousel } from '../../carousel/Carousel';
import $ from 'jquery';
import { DOMINIO, TOKEN } from "../../../link_config"
import './../../../recursos/css/style.css'
import { Link } from 'react-router-dom';

class TemplateRestaurante extends Component{

    constructor() {
        super();
        this.state = {
            produtos: []
        }
    }

    carregarCarousel(e){

        this.setState({produtos:[]});

        let id = localStorage.getItem("id");

        const url = `${DOMINIO}/categorias/${id}`;

        $.ajax({
            url: url,
            method: 'get',
            headers: { "token": TOKEN },
            dataType: 'json',
            contentType: 'application/json',
            success: function (resposta) {

                this.setState({ itens: resposta });

            }.bind(this),
            error: function (data) {

                console.log(data)
            }
        });
    }

    componentDidMount(){
        this.carregarCarousel();
    }

    render(){
        return(
            <body className="w-75 mx-auto">
                <MenuTemplate>

                </MenuTemplate>
                <div className="container-fluid bg-image-container2" style={{minHeight: '250px'}}>
                    <h1 className="pt-2 pb-2 text-center text-light">
                        Burger Classic
                    </h1>
                    <div className="d-block">
                        <p className="h5 text-center text-light">
                            Lorem ipsum dolor sit amet, consectetur dipisicing elit, sed do eiusmod Incididunt ut labore et dolore magna aliqua.
                            Lorem ipsum dolor sit amet, consectetur dipisicing elit, sed do eiusmod Incididunt ut labore et dolore magna aliqua. 
                            Ut enim ad minim veniam quis nostrud exercitation ullamco.
                            Lorem ipsum dolor sit amet, consectetur dipisicing elit, sed do eiusmod Incididunt ut labore et dolore magna aliqua.
                            Lorem ipsum dolor sit amet, consectetur dipisicing elit, sed do eiusmod Incididunt ut labore et dolore magna aliqua. 
                            Ut enim ad minim veniam quis nostrud exercitation ullamco               
                        </p>
                    </div>
                </div>

                {/* Container Carousel */}
                <div className="container-fluid" style={{minHeight: '200px'}}>
                    <h1 className="text-center pt-2">MENU</h1>
                    <div className="container-fluid w-25 mt-3 mb-0">
                        <div className="d-flex justify-content-between">

                            <figure className="figure my-auto">
                                <img className="m-1" style={{maxHeight: '64px'}} src="https://cdn3.iconfinder.com/data/icons/fast-food-outline-2/427/burger_hamburger_meat_cheese_food_meal_fast_restaurant_junk_fastfood-64.png" alt="Icone Hambúguer" />
                            </figure>
                            <figure className="figure my-auto">
                                <img className="m-1" style={{maxHeight: '64px'}} src="https://cdn3.iconfinder.com/data/icons/fast-food-outline-2/439/pizza_italian_cheese_slice_tomato_food_meal_fast_restaurant_junk_fastfood-64.png" alt="Icone Pizza" />
                            </figure>
                            <figure className="figure my-auto">
                                <img className="m-1" style={{maxHeight: '64px'}} src="https://cdn0.iconfinder.com/data/icons/fastfood-29/64/cup-soft-drink-water-takeaway-fastfood-cola-64.png" alt="Icone Refrigerante" />
                            </figure>
                        </div>
                    </div>
                    <hr/>
                    {/* carousel */}

                    <Carousel/>
                </div>

                {/* <!-- Container Mais Vendidos --> */}
                <div className="container-fluid bg-image-container3 p-0" style={{minHeight: '400px'}}>
                    <div className="container-fluid navbarColor p-5" style={{minHeight: 'inherit'}}>
                        <div className="container w-50 mx-auto my-auto">
                            {/* <!-- Titulo --> */}
                            <h1 className="text-center text-white">Mais Vendidos</h1>

                            <div className="d-flex flex-row justify-content-between pt-3 mb-3" style={{borderBottom: '3px solid  white' , borderBottomStyle:'dotted'}}>
                                <div className="d-flex flex-column">
                                    <h3 className="text-white">Burguer 1</h3>
                                </div>
                                <div className="d-flex flex-column">
                                    <h3 className="text-white">15,00</h3>
                                </div>
                            </div>

                            <div className="d-flex flex-row justify-content-between pt-3 mb-3" style={{borderBottom: '3px solid  white' , borderBottomStyle:'dotted'}}>
                                <div className="d-flex flex-column">
                                    <h3 className="text-white">Burguer 2</h3>
                                </div>
                                <div className="d-flex flex-column">
                                    <h3 className="text-white">15,00</h3>
                                </div>
                            </div>

                            <div className="d-flex flex-row justify-content-between pt-3 mb-3" style={{borderBottom: '3px solid  white' , borderBottomStyle:'dotted'}}>
                                <div className="d-flex flex-column">
                                    <h3 className="text-white">Burguer 3</h3>
                                </div>
                                <div className="d-flex flex-column">
                                    <h3 className="text-white">15,00</h3>
                                </div>
                            </div>

                            <div className="d-flex flex-row justify-content-between pt-3 mb-3" style={{borderBottom: '3px solid  white' , borderBottomStyle:'dotted'}}>
                                <div className="d-flex flex-column">
                                    <h3 className="text-white">Burguer 4</h3>
                                </div>
                                <div className="d-flex flex-column">
                                    <h3 className="text-white">15,00</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                {/* <!-- Container sobre nós --> */}
                
                <div id="sobre" className="container-fluid" style={{minHeight: "300px"}}>
                    <h1 className="text-center p-3">Sobre Nós</h1>
                    <p className="text-center w-75 text-wrap mx-auto" style={{maxHeight: "300rem"}}>
                        Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos,
                        e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja
                        de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não
                        Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos,
                        e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja
                        de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não
                    </p>
                    <div className="d-flex flex-row w-75 mx-auto mt-5 justify-content-around">
                        <div className="d-flex flex-column">
                            Burguer Classic - Barueri
                        </div>
                        <div className="d-flex flex-column">
                            Pagamento pelo App GoDinner: Visa , Elo, Amex,
                        </div>
                    </div>
                </div>

                {/* <!-- Container Galeira --> */}
                <div id="galeria" className="container-fluid">
                    <h1 className="text-center p-3">Galeria de fotos</h1>
                    <div className="row">
                        <div className="col-md-12">
                        
                            <figure className="col-md-4 float-left">
                                <Link to="" data-size="1600x1067">
                                    <img className="img-fluid" alt="picture" src="https://mdbootstrap.com/img/Photos/Lightbox/Thumbnail/img%20(145).jpg"/>
                                </Link>
                            </figure>
                        
                            <figure className="col-md-4 float-left">
                                <Link to="" data-size="1600x1067">
                                    <img className="img-fluid" alt="picture" src="https://mdbootstrap.com/img/Photos/Lightbox/Thumbnail/img%20(150).jpg" />
                                </Link>
                            </figure>
                        
                            <figure className="col-md-4 float-left">
                                <Link to="" data-size="1600x1067">
                                    <img className="img-fluid" alt="picture" src="https://mdbootstrap.com/img/Photos/Lightbox/Thumbnail/img%20(152).jpg" />
                                </Link>
                            </figure>
                        
                            <figure className="col-md-4 float-left">
                                <Link to="" data-size="1600x1067">
                                    <img className="img-fluid" alt="picture" src="https://mdbootstrap.com/img/Photos/Lightbox/Thumbnail/img%20(42).jpg" />
                                </Link>
                            </figure>
                        
                            <figure className="col-md-4 float-left">
                                <Link to="" data-size="1600x1067">
                                    <img className="img-fluid" alt="picture" src="https://mdbootstrap.com/img/Photos/Lightbox/Thumbnail/img%20(151).jpg"  />
                                </Link>
                            </figure>
                        
                            <figure className="col-md-4 float-left">
                                <Link to="" data-size="1600x1067">
                                    <img className="img-fluid" alt="picture" src="https://mdbootstrap.com/img/Photos/Lightbox/Thumbnail/img%20(40).jpg"  />
                                </Link>
                            </figure>

                        </div>
                    </div>
                </div>
                    
            </body>
        )
    }
}
export default TemplateRestaurante
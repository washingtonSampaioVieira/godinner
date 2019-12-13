import React, { Component } from 'react';
import './../../recursos/css/style.css'


export class Carousel extends Component {
    render() {
        return (
            <div className="carousel slide mt-5" id="ProdutosRestaurante" data-ride="carousel">

                <ol className="carousel-indicators">
                    <li data-target="#ProdutosRestaurante" data-slide-to="0" className="active"></li>
                    <li data-target="#ProdutosRestaurante" data-slide-to="1"></li>
                    <li data-target="#ProdutosRestaurante" data-slide-to="2"></li>
                </ol>
                <div className="carousel-inner">
                    <div className="carousel-item active">
                        <div className="d-flex justify-content-around p-3">
                            <div className="card mb-2 float-left" style={{ width: '18rem' }}>
                                <img className="card-img-top" src="https://png.pngtree.com/element_pic/17/07/26/eb63d3b8ae4d2a1de45ba95e6d5b53ae.jpg" alt="Card image cap" />
                                <div className="card-body">
                                    <h5 className="card-title text-center">Burguer</h5>
                                    <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                            </div>
                            <div className="card mb-2 float-left" style={{ width: '18rem' }}>
                                <img className="card-img-top" src="https://png.pngtree.com/element_pic/17/07/26/eb63d3b8ae4d2a1de45ba95e6d5b53ae.jpg" alt="Card image cap" />
                                <div className="card-body">
                                    <h5 className="card-title text-center">Burguer</h5>
                                    <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                            </div>
                            <div className="card mb-2 float-left" style={{ width: '18rem' }}>
                                <img className="card-img-top" src="https://png.pngtree.com/element_pic/17/07/26/eb63d3b8ae4d2a1de45ba95e6d5b53ae.jpg" alt="Card image cap" />
                                <div className="card-body">
                                    <h5 className="card-title text-center">Burguer</h5>
                                    <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    {/* Itens Carousel */}
                    <div className="carousel-item">
                        <div className="d-flex justify-content-around p-3">

                            <div className="card mb-2 float-left" style={{ width: '18rem' }}>

                                <img className="card-img-top" src="https://png.pngtree.com/element_pic/17/07/26/eb63d3b8ae4d2a1de45ba95e6d5b53ae.jpg" alt="Card image cap" />
                                <div className="card-body">
                                    <h5 className="card-title text-center">Burguer</h5>
                                    <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                            </div>
                            <div className="card mb-2 float-left" style={{ width: '18rem' }}>
                                <img className="card-img-top" src="https://png.pngtree.com/element_pic/17/07/26/eb63d3b8ae4d2a1de45ba95e6d5b53ae.jpg" alt="Card image cap" />
                                <div className="card-body">
                                    <h5 className="card-title text-center">Burguer</h5>
                                    <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                            </div>
                            <div className="card mb-2 float-left" style={{ width: '18rem' }}>
                                <img className="card-img-top" src="https://png.pngtree.com/element_pic/17/07/26/eb63d3b8ae4d2a1de45ba95e6d5b53ae.jpg" alt="Card image cap" />
                                <div className="card-body">
                                    <h5 className="card-title text-center">Burguer</h5>
                                    <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="carousel-item">
                        <div className="d-flex justify-content-around p-3">
                            <div className="card mb-2 float-left" style={{ width: '18rem' }}>
                                <img className="card-img-top" src="https://png.pngtree.com/element_pic/17/07/26/eb63d3b8ae4d2a1de45ba95e6d5b53ae.jpg" alt="Card image cap" />
                                <div className="card-body">
                                    <h5 className="card-title text-center">Burguer</h5>
                                    <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                            </div>
                            <div className="card mb-2 float-left" style={{ width: '18rem' }}>
                                <img className="card-img-top" src="https://png.pngtree.com/element_pic/17/07/26/eb63d3b8ae4d2a1de45ba95e6d5b53ae.jpg" alt="Card image cap" />
                                <div className="card-body">
                                    <h5 className="card-title text-center">Burguer</h5>
                                    <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                            </div>
                            <div className="card mb-2 float-left" style={{ width: '18rem' }}>
                                <img className="card-img-top" src="https://png.pngtree.com/element_pic/17/07/26/eb63d3b8ae4d2a1de45ba95e6d5b53ae.jpg" alt="Card image cap" />
                                <div className="card-body">
                                    <h5 className="card-title text-center">Burguer</h5>
                                    <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                {/* SETAS */}
                <a className="carousel-control-prev" href="#ProdutosRestaurante" role="button" data-slide="prev">
                    <span className="carousel-control-prev-icon h-25 w-25 text-center" style={{ backgroundImage: 'none', outline: 'black', lineHeight: 0.9 }} aria-hidden="true"></span>
                    <span className="sr-only">Previous</span>
                </a>
                <a className="carousel-control-next" href="#ProdutosRestaurante" role="button" data-slide="next">
                    <span className="carousel-control-next-icon h-25 w-25 text-center" style={{ backgroundImage: 'none', outline: 'black', lineHeight: 0.9 }} aria-hidden="true"></span>
                    <span className="sr-only">Next</span>
                </a>
            </div>
        )
    }
} 
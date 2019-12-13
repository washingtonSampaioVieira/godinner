import React from 'react';

export const MenuRestaurante = (props) => (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">

        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div className="navbar-nav">
                <a className="nav-item nav-link" href="#">Seus Pedidos</a>
                <a className="nav-item nav-link" href="#">Cadastrar Produtos</a>
            </div>
        </div>
    </nav>
);
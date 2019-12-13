import React from 'react';
import PropTypes from 'prop-types';
import './../../recursos/css/style.css';

class ModalProduto extends React.Component {
    render() {
        if(!this.props.show) {
            return null;
        }
        
        return (
            <div className="backdropStyle">
               
                    {this.props.children}

            </div>
        );
    }
}

ModalProduto.propTypes = {
    onClose: PropTypes.func.isRequired,
    show: PropTypes.bool,
    children: PropTypes.node
};
  
export default ModalProduto;
import axios from 'axios';
import { DOMINIO } from '../../../link_config'


export const getRestaurante = () => {
    const url = `${DOMINIO}/restaurante/este`;

    let tokenRestaurante = localStorage.getItem('token');
    const request = axios.get(url, 
        { headers: { token: tokenRestaurante } }
    );

    return {
        type: 'BEM_VINDO',
        payload: request
    };
};
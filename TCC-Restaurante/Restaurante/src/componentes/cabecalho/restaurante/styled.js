import styled from 'styled-components';

//ESTILIZAÇÃO DA IMAGEM DO RESTAURANTE
export const ImgRestaurante = styled.img`
    max-width: 80px !important; 
    height: 75px !important;

`

//ESTILIZAÇÃO DIV DE OPÇÕES DO MENU
export const OpcoesMenu = styled.div`
    max-width: 820px !important; 

`
//ESTILIZAÇÃO DIV DE OPÇÕES DO MENU
export const Li = styled.li`
    max-width: ${props => props.maxWidth}; 
`
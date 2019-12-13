import { Link } from 'react-router-dom';
import styled from 'styled-components';

export const Botao = styled(Link)`
&:hover{
    background: #F26B3A !important;
    color: #ffffff !important;
},
&:link{
    color: #F26B3A !important;
border: 2px solid #F26B3A !important;
}
`;

export const LinksMenu = styled(Link)`

&:visited, &:link{
  
    
}

`;

export const BotaoLaranja = styled(Link)`
    background-color: #F26B3A !important;
    color: #ffffff !important;
`;



export const BotaoBrancoLaranja = styled.button`
&:hover{
    background: #F26B3A !important;
    color: #ffffff !important;
},
&:link{
    color: #F26B3A !important;
border: 2px solid #F26B3A !important;
}
`;




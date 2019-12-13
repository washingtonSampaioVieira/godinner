import React from 'react';
import { Botao } from './styled';
import "./style.css";

export const BotaoLink = (props) => (
  <Botao onClick={props.onClick} to={props.to} className={`btn ${props.className}`}>{props.texto}</Botao>
)
import React from 'react';
import { Botao } from './styled';
import "./style.css";
 
export const BotaoLink = (props) => (
  <Botao onClick={props.onClick} to={props.to} className={`btn ${props.className}`}>{props.texto}</Botao>
)


export class BotaoRadioSwitch extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isChecked: props.isChecked || false,
    };

    this.handleChange = this.handleChange.bind(this);
  }


  handleChange() {
    this.setState({ isChecked: !this.state.isChecked })

  }
  render() {
    return (
      <label className={`switch ${this.props.className}`} id={this.props.id} onChange={this.props.onChange(this.state.isChecked)}>
        <input id="btn-status" type="checkbox" value={this.state.isChecked} onChange={this.handleChange}/>
        <div className="slider"></div>
      </label>
    );
  }
}

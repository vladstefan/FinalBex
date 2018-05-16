import React, { Component } from 'react';
import FlatButton from 'material-ui/FlatButton';
import People from 'material-ui/svg-icons/social/people'
import TextField from 'material-ui/TextField';
import {Button} from 'react-bootstrap';
class Login extends Component {

  constructor(props) {
    super(props);
    this.state = { open: false, name:"", password:"" };
    this.submitChanges = this.submitChanges.bind(this);
  }

  submitChanges(){
    const account={name:this.state.name, password:this.state.password}
    fetch('http://localhost:8080/login', {
      method: 'post',
      credentials: 'include',


      body: JSON.stringify(account)
    }).then(response => {
      // this.processLoginResponse(response.status)
      console.log(account);
    });


  }


  handleToggle = () => this.setState({ open: !this.state.open });


  returnTextField() {

    if (this.state.open) {
      return (
          <div>
            <TextField className="username" id="user_id"
                       hintText="Username"
                       floatingLabelText="Username"
                       type="username"
                       style={{
                         marginRight: 5
                       }}
                       onChange={(e)=> this.setState({name: e.target.value})}
            />
            <TextField className="password" id="password_id"
                       hintText="Password"
                       floatingLabelText="Password"
                       type="password"
                       onChange={(e)=> this.setState({password: e.target.value})}
            />
            <Button bsStyle="danger" bsSize="small" onClick={this.submitChanges}>Submit</Button>
          </div>
      );
    }
  }

  render() {

    return (

        <div>
          <FlatButton onClick={this.handleToggle} className="bar__btn" eventKey={1} icon={< People color="#fff" />} />
          <div className="app__login">
            {this.returnTextField()}
          </div>

        </div>
    );
  }


}

export default Login;

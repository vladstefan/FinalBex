import React, { Component } from 'react';
import FlatButton from 'material-ui/FlatButton';
import People from 'material-ui/svg-icons/social/people'
import TextField from 'material-ui/TextField';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router';


class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: "",
            password: "",
            isLoggedIn: false,
            role:{}

        };
        this.submitChanges = this.submitChanges.bind(this);

    }

    submitChanges() {
        const account = { name: this.state.name, password: this.state.password }
        let statusLogin;
        fetch('http://localhost:8090/login', {
            method: 'post',
            credentials: 'include',
            body: JSON.stringify(account)
        })
        .then(response =>  {
            statusLogin = response.status;
            return response.json();
        })
        .then(role => {
          this.setState({ role })
        })
        .then(() => {
                if (statusLogin == 200) {
                    if(this.state.role.role == "user"){
                        this.setState({ isLoggedIn: true });
                        this.props.router.push('/');
                    } else if(this.state.role.role == "admin"){
                        this.props.router.push('/dashboard');
                    }

                }
            });

    }


    render() {

        return (

            <div>
                <FlatButton onClick={this.handleToggle} className="bar__btn" eventKey={1} icon={< People color="#fff" />} />
                <div className="app__login">
                    <h1> Account Login </h1>
                    <div className="login__credentials">
                        <TextField className="username" id="user_id"
                            hintText="Username"
                            floatingLabelText="Username"
                            type="username"
                            style={{
                                marginRight: 5
                            }}
                            onChange={(e) => this.setState({ name: e.target.value })}
                        />
                        <TextField className="password" id="password_id"
                            hintText="Password"
                            floatingLabelText="Password"
                            type="password"
                            onChange={(e) => this.setState({ password: e.target.value })}
                        />
                        <Button bsStyle="danger" bsSize="small" onClick={this.submitChanges}>Submit</Button>
                    </div>
                </div>

            </div>
        );
    }


}

export default Login;

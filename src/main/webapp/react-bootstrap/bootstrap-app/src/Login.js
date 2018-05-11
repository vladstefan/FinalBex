import React, { Component } from 'react';
import FlatButton from 'material-ui/FlatButton';
import People from 'material-ui/svg-icons/social/people'
import TextField from 'material-ui/TextField';
class Login extends Component {

    constructor(props) {
        super(props);
        this.state = { open: false };
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
                    />
                    <TextField className="password" id="password_id"
                        hintText="Password"
                        floatingLabelText="Password"
                        type="password"

                    />
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

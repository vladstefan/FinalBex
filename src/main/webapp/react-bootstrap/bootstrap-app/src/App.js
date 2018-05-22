import React, { Component } from 'react';
// import {Navbar, Button, FormGroup, FormControl } from 'react-bootstrap';
import './App.css';
import AppFloatingBar from './AppFloatingBar';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import PopularBooks from './PopularBooks';
import { Link } from 'react-router';
import cookie from 'react-cookie';



class App extends Component {

  constructor(props) {
    super(props);

this.accountLogged = this.accountLogged.bind();

    this.state = {
      posts: [],
      cathegories: [],
      isLogged:  false,
      isAdmin: false
    };
  }

  accountLogged(){
      // if(this.cook){
      //   return true;
      // } else {
      //   //TODO
      //   return false;
      // }
  }

  componentDidMount() {
    //the 4 categories
    fetch('http://localhost:8090/categories')
      .then(response => response.json())
      .then(cathegories => {
        this.setState({ cathegories })

      });
      //popular books
      fetch('http://localhost:8090/popular')
      .then(response => response.json())
      .then(posts => {
        this.setState({ posts })

      });

  }



  render() {
    console.log(this.state.posts);
    return (
      <div className="App">
        <div className="opacity__pic">
          <div className="up__section">
            <MuiThemeProvider>
              <AppFloatingBar items={this.state.cathegories} />
              <Link to="/dashboard">Admin_Dashboard</Link>
              <div className="picture__section">
                <div>
                  <h1 className="header">BEXLibrary</h1>
                  <h2 className="header header__description">Bring the knoledge closer!</h2>
                </div>
                <div className="popularBooks__list">
                  {<PopularBooks booklist={this.state.posts} />}
                </div>
              </div>
            </MuiThemeProvider>
          </div>
        </div>
      </div>
    );
  }
}

export default App;

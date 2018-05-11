import React, { Component } from 'react';
// import {Navbar, Button, FormGroup, FormControl } from 'react-bootstrap';
import './App.css';
import AppFloatingBar from './AppFloatingBar';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import PopularBooks from './PopularBooks';


class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      cathegories:[]
    };
  }

  componentDidMount() {
    //the 4 categories
    fetch('http://localhost:8080/categories')
      .then(response => response.json())
      .then(cathegories => {
        this.setState({ cathegories })

      });
      //popular books
      fetch('http://localhost:8080/popular')
      .then(response => response.json())
      .then(posts => {
        this.setState({ posts })

      });

  }



  render() {
    console.log(this.state.posts);
      console.log(this.state.cathegories);
      return (
      <div className="App">
        <div className="up__section">
          <MuiThemeProvider>
            <AppFloatingBar items={this.state.cathegories}/>
            <div className="picture__section">
              <div>
                <h1 className="header">BEXLibrary</h1>
                <h2 className="header header__description">Bring the knoledge closer!</h2>
              </div>
              <div className="popularBooks__list">
                {<PopularBooks booklist={this.state.posts}/>}
              </div>
            </div>
          </MuiThemeProvider>
        </div>
      </div>
    );
  }
}

export default App;

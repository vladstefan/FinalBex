import React, { Component } from 'react';
import './App.css';
import AppFloatingBar from './AppFloatingBar';
import PopularBooks from './PopularBooks';
import { Link } from 'react-router';
import cookie from 'react-cookie';



class App extends Component {

  constructor(props) {
    super(props);


    this.state = {
      posts: [],
      cathegories: [],
      isLogged:  false,
      isAdmin: false
    };
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
    return (
      <div className="App">
        <div className="opacity__pic">
          <div className="up__section">
              <AppFloatingBar items={this.state.cathegories} />
              <div className="picture__section">
                <div>
                  <h1 className="header">BEXLibrary</h1>
                  <h2 className="header header__description">Bring the knoledge closer!</h2>
                </div>
                <div className="popularBooks__list">
                  {<PopularBooks booklist={this.state.posts} />}
                </div>
              </div>
          </div>
        </div>
      </div>
    );
  }
}

export default App;

import React, { Component } from 'react';
import Book from './Book';

class Booklist extends Component{


        render() {
            const booklist = this.props.booklist.map(post => <Book title={post.title} rating={post.rating} id={post.id}/>);
            return (
                <div className="book__div">
                    {booklist}
                </div>
            );
        }
}

export default Booklist;
import React, { Component } from 'react';
import {Carousel} from 'react-bootstrap';
import Book from './Book';

const styles = {
    root: {
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'space-around',
    },
    gridList: {
        width: 500,
        height: 450,
        overflowY: 'auto',
    },
};



/**
 * A simple example of a scrollable `GridList` containing a [Subheader](/#/components/subheader).
 */
class PopularBooks extends Component {


    render() {
        const booksections = this.props.booklist.map(post => <Carousel.Item> <Book title={post.title} rating={post.rating} id={post.id}/> </Carousel.Item>);
        return (
            <div style={styles.root}>
               <Carousel className="carousel__pupularBooks">
                    {booksections}
                </Carousel>
            </div>
        );
    }
}
export default PopularBooks;
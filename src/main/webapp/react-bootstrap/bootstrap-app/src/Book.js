import React, { Component } from 'react';
import RatingStars from './RatingStars';
import RentBook from './RentButton';

class Book extends Component {


    render() {
        return (
            <div className="card">
                <div className="book__img">
                    <img src="/img/book.jpg" alt="ceva" />
                </div>
                <div>
                    <div>
                        <h2 className="book__title">{this.props.title}</h2>
                    </div>
                    <div>
                        <RatingStars rating={this.props.rating} />
                        <div className="rent__button">
                            <RentBook title={this.props.title} />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Book;

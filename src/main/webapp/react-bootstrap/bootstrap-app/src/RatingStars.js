import React, { Component } from 'react';
import ReactStars from 'react-stars';

class RatingStars extends Component {

    ratingChanged = (newRating) => {
        console.log(newRating)
    }

    render() {
        return (
            <div>
            <ReactStars 
                count={5} 
                size={24} 
                color2={'#ffd700'}
                value={this.props.rating} /> 
            </div>
    );
    }
}

export default RatingStars;
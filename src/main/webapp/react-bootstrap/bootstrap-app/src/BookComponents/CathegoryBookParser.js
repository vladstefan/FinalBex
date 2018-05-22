import React, { Component } from 'react';
import Booklist from './Booklist';

class CathegoryBookParser extends Component {


    render() {
        const booksections = this.props.booklist;

        const perChunk = 3 // items per chunk            
        let bookgroup = booksections.reduce((resultArray, item, index) => {
            const chunkIndex = Math.floor(index / perChunk)

            if (!resultArray[chunkIndex]) {
                resultArray[chunkIndex] = [] // start a new chunk
            }

            resultArray[chunkIndex].push(item)

            return resultArray
        }, []);

        const listOfBooks = bookgroup.map(post => <Booklist booklist={post} />);
        return (
            <div>
                {listOfBooks}
            </div>

        );
    }

}


export default CathegoryBookParser;
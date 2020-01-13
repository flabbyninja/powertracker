import React, { Component } from 'react';
import ListItem from './ListItem';

class List extends Component {
    constructor(props) {
        super(props);
        console.log("Loading List component...");
    }

    render() {
        console.log("About to print out items: " + this.props.items);
        return (
            <ul>
                {this.props.items.map((v, i) => (<ListItem item={this.props.items[i]} />))}
            </ul>
        )
    }
}

export default List;

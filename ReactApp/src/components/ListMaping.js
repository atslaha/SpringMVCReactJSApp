const React = require('react');

export default class ListMapping extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.listLines.line}</td>
                <td>{this.props.listLines.longest_word}</td>
                <td>{this.props.listLines.shortest_word}</td>
                <td>{this.props.listLines.line_length}</td>
                <td>{this.props.listLines.average_w_length}</td>
            </tr>
        )
    }
}
import React, { Component } from 'react';
import axios from 'axios';
import ListMapping from './ListMaping';
import UploadFile from './UploadFile';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {listLines: []};
        this.Axios = axios.create({
            baseURL: "/",
            headers: {'content-type': 'application/json', 'creds':'lines'}
        });
        this.handleChange = this.handleChange.bind(this);
        this.buttonListLines = this.buttonListLines.bind(this);
        this.buttonParse = this.buttonParse.bind(this);
    }

    componentDidMount() {
        let _this = this;
        this.Axios.get('http://localhost:8080/listLinesRequest')
            .then(function (response) {
                console.log(response);
                _this.setState({listLines: response.data});
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    handleChange([]){
        this.setState({listLines: []});
        this.buttonListLines();
    }

    buttonParse(){
        axios.get('http://localhost:8080/parse').then(response => console.log(response));
    }

    buttonListLines(){

            let _this = this;
            this.Axios.get('http://localhost:8080/listLinesRequest')
                .then(function (response) {
                    console.log(response);
                    _this.setState({listLines: response.data});
                })
                .catch(function (error) {
                    console.log(error);
                });
    }

    render() {

        var listLines = this.state.listLines.map((listLines) =>
            <ListMapping key={listLines.line_id} listLines={listLines} buttonListLines={this.buttonListLines}/>
        );

        return(

            <div>
                {/*<h1>My  React App</h1><br/>*/}

                <UploadFile/><br/>

                <button className="parse-button"  onClick={this.buttonParse}>Parse File</button><br/>
                <button className="listLinesRequest-button"  onClick={this.buttonListLines.bind(this)}>List of Lines</button><br/>

                <table>
                    <tbody>
                    <tr>
                        <td width="700px">Lines</td>
                        <td>Longest word</td>
                        <td>Shortest word</td>
                        <td>Line Length</td>
                        <td>Average Word Length</td>
                        <td width="40px"></td>
                    </tr>
                    {listLines}
                    </tbody>
                </table>
            </div>
        );
    }

}

export default App;
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
            //headers: {'content-type': 'application/json', 'creds':'lines'}
        });
    }

    // componentDidMount() {
    //     let _this = this;
    //     this.Axios.get('/listLinesRequest')
    //         .then(function (response) {
    //             console.log(response);
    //             _this.setState({listLines: response.data});
    //         })
    //         .catch(function (error) {
    //             console.log(error);
    //         });
    // }

    getMassege(){
        var str,str1 ;
        str = axios.get('/api/getTestTransaction');
        str1 = JSON.stringify(str);
        //document.write(Object.values(str) + "DATA!!!");
       // alert(str.data);
        axios.get('/api/getTestTransaction').then(response => console.log(response));

      // console.log(response)

    }

    buttonPrse(){

        axios.get('http://localhost:8080/parse').then(response => console.log(response));

    }buttonListLines(){

     //   axios.get('http://localhost:8080/listLinesRequest').then(response => console.log(response));

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
            <ListMapping key={listLines.line_id} listLines={listLines}/>
        );

        return(
            <div>

                <h1>My  React App</h1>
                <h1>I'm Happy</h1>
                <h1>I'm Happy</h1><br/>






                <UploadFile/><br/>

                <button className="parse-button"  onClick={this.buttonPrse}>Parse File</button><br/>
                <button className="listLinesRequest-button"  onClick={this.buttonListLines.bind(this)}>List of Lines</button><br/>


                <a href="responseentity">Test...</a>

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
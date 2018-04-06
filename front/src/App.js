import React from 'react';
import logo from './logo.svg';
import Paper from 'material-ui/Paper';
import Menu from 'material-ui/Menu';
import MenuItem from 'material-ui/MenuItem';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import './App.css';

const style = {
    display: 'inline-block',
    margin: '16px 32px 16px 0',
};

class App extends React.Component {

    render() {
        return (
            <MuiThemeProvider>
                <div className="App">
                    <header className="App-header">
                        <img src={logo} className="App-logo" alt="logo"/>
                        <h1 className="App-title">Welcome to Huyact</h1>
                    </header>
                    <p className="App-intro">
                        To get started, edit <code>src/App.js</code> and save to reload.
                    </p>
                    <div>
                        <Paper style={style}>
                            <Menu>
                                <MenuItem primaryText="Maps"/>
                                <MenuItem primaryText="Books"/>
                                <MenuItem primaryText="Flights"/>
                                <MenuItem primaryText="Apps"/>
                            </Menu>
                        </Paper>
                        <Paper style={style}>
                            <Menu>
                                <MenuItem primaryText="Refresh"/>
                                <MenuItem primaryText="Help &amp; feedback"/>
                                <MenuItem primaryText="Settings"/>
                                <MenuItem primaryText="Sign out"/>
                            </Menu>
                        </Paper>
                    </div>
                </div>
            </MuiThemeProvider>
        );
    }
}

export default App;

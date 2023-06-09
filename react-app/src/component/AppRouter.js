import React from "react";
import "../index.css";
import App from "./App";
import Login from "./Login";
import Join from "./Join";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Box from "@material-ui/core/Box";
import Typography from "@material-ui/core/Typography";
import ErrorBoundary from "../error/ErrorBoundary";

function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {"Copyright c "}
            fsoftwareengineer, {new Date().getFullYear()}
            {"."}
        </Typography>
    );
}

class AppRouter extends React.Component {
    render() {
        return (
            <div>
                <Router>
                    <div>
                        <Routes>
                            <Route path='/login' element={<Login />}>
                            </Route>
                            <Route path='/' element={<ErrorBoundary><App /></ErrorBoundary>}>
                            </Route>
                            <Route path='/join' element={<Join />}>
                            </Route>
                        </Routes>
                    </div>
                    <Box mt={5}>
                        <Copyright />
                    </Box>
                </Router>
            </div>
        );
    }
}

export default AppRouter;
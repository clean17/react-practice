import { Button, Container, Grid, Link, TextField, Typography } from "@material-ui/core";
import React from "react";
import { login } from "../service/ApiService";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        const email = data.get("email");
        const password = data.get("password");
        await login({ email: email, password: password });
    }

    // 레퍼런스 https://www.daleseo.com/material-ui-text-fields/
    render() {
        return (
            <Container component={"main"} maxWidth="xs" style={{ marginTop: "8%" }}>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <Typography component={"h1"} variant="h5">
                            로그인
                        </Typography>
                    </Grid>
                </Grid>
                <form noValidate onSubmit={this.handleSubmit}>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                label="Email"
                                variant="outlined"
                                required
                                fullWidth
                                id="email"
                                name="email"
                                autoComplete="email"
                                autoFocus
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                label="Password"
                                variant="outlined"
                                required
                                fullWidth
                                id="password"
                                name="password"
                                type={"password"}
                                autoComplete="current-password"
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                            >로그인
                            </Button>
                        </Grid>
                    </Grid>
                </form>
                <Grid container style={{ marginTop: "4%" }} justifyContent="center">
                    <Grid item >
                        <Link href='join' variant="body1">
                            계정이 없으신가요 ? 여기서 가입하세요.
                        </Link>
                    </Grid>
                </Grid>
            </Container>
        );
    }
}

export default Login;

import { Box } from "@material-ui/core";
import React from "react";
import useStyles from "../hooks/use-styles";
import RegisterPage from "./RegisterPage";
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';
import Paper from '@mui/material/Paper';
import Link from '@mui/material/Link';


import LockOutlinedIcon from '@mui/icons-material/LockOutlined';

import logo from './../assets/images/logo.png';

const style = {
    root: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        margin: "64px 128px"
    },
    imageContainer: {
        display: 'flex',
        alignItems: 'center',
        background: "linear-gradient(45deg, rgb(17 69 147) 0%, rgb(44 87 158) 35%, rgb(73 109 171) 100%);"
    },
    image: {
        width: "100%"
    }
}

const Register = (props) => {

    const classes = useStyles(style);

    return(<>
        <Grid container component="main" sx={{ height: '100vh' }}>
            <Grid item xs={false} sm={5} md={6} className={classes.imageContainer}>
                <img src={logo} alt="Logo" className={classes.image}/>
            </Grid>  
                
            <Grid item xs={12} sm={7} md={6} component={Paper} elevation={6} square>
                <Box className={classes.root}>
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Register
                    </Typography>

                    <RegisterPage></RegisterPage>
                    <Grid item>
                            <Link href="/" variant="body2">
                            {"Already have an account? Log In"}
                            </Link>
                    </Grid>

                    
                </Box>
            </Grid>
        </Grid>
    </>)
}

export default Register;
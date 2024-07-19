import React, { useState } from 'react';
import './Login.css';
import Header from '../HeaderComp/Header';

function Login(){

    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");

    function handleUsernameChange(e){
        setUsername(e.target.value);
    }

    function handlePasswordChange(e){
        setPassword(e.target.value);
    }


    return (
        <>
          <Header />
          <div className="login-container">
            <div className="users-data">
              <p>Username:</p>
              <input onChange={handleUsernameChange} type="text" />
              <p>Password:</p>
              <input onChange={handlePasswordChange} type="password" />
            </div>
            <div className="button-link-container">
                <div className='register-part'>
                    <p className="register">Don't have an account? <a className="register-link" href="/register">Register</a></p>
                </div>
              <button className="login-button">Log in</button>
            </div>
          </div>
        </>
      );
    }

export default Login;